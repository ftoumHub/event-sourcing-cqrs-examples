package bankservice.service.client;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.singletonList;
import static java.util.UUID.randomUUID;

import bankservice.domain.model.Event;
import bankservice.domain.model.EventStore;
import bankservice.domain.model.OptimisticLockingException;
import bankservice.domain.model.client.Client;
import bankservice.projection.client.ClientProjection;
import bankservice.service.Retrier;
import com.google.common.eventbus.EventBus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class ClientService {

    private final EventStore eventStore;

    private final EventBus eventBus;
    private final Retrier conflictRetrier;

    public ClientService(EventStore eventStore, EventBus eventBus) {
        this.eventStore = checkNotNull(eventStore);
        this.eventBus = checkNotNull(eventBus);
        int maxAttempts = 3;
        this.conflictRetrier = new Retrier(singletonList(OptimisticLockingException.class), maxAttempts);
    }

    /**
     * Si on essai d'ajouter un client avec un email existant on doit se faire jeter.
     * ==> contrainte d'unicité à mettre ici ? {@link bankservice.projection.client.InMemoryClientsRepository#save(ClientProjection)}
     */
    public Client process(EnrollClientCommand command) {
        Client client = new Client(randomUUID(), command.getName(), command.getEmail());
        storeAndPublishEvents(client);
        return client;
    }

    public Optional<Client> loadClient(UUID id) {
        List<Event> eventStream = eventStore.load(id);
        if (eventStream.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new Client(id, eventStream));
    }

    public void process(UpdateClientCommand command) {
        process(command.getId(), c -> c.update(command.getName(), command.getEmail()));
    }

    private Client process(UUID clientId, Consumer<Client> consumer) throws ClientNotFoundException, OptimisticLockingException {
        return conflictRetrier.get(() -> {
            Client client = loadClient(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
            consumer.accept(client);
            storeAndPublishEvents(client);
            return client;
        });
    }

    private void storeAndPublishEvents(Client client) {
        eventStore.store(client.getId(), client.getNewEvents(), client.getBaseVersion());
        client.getNewEvents().forEach(eventBus::post);
    }
}
