package bankservice.projection.client;

import bankservice.domain.model.client.ClientEnrolledEvent;
import com.google.common.eventbus.Subscribe;

import static com.google.common.base.Preconditions.checkNotNull;

public class ClientListener {

    private ClientRepository clientRepository;

    public ClientListener(ClientRepository clientRepository) {
        this.clientRepository = checkNotNull(clientRepository);
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void handle(ClientEnrolledEvent event) {
        ClientProjection tx = new ClientProjection(
                event.getAggregateId(), event.getName(), event.getEmail(), event.getVersion());
        clientRepository.save(tx);
    }
}
