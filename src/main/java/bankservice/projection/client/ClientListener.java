package bankservice.projection.client;

import bankservice.domain.model.client.ClientEnrolledEvent;
import com.google.common.eventbus.Subscribe;
import lombok.RequiredArgsConstructor;

import static com.google.common.base.Preconditions.checkNotNull;

@RequiredArgsConstructor
public class ClientListener {

    private final ClientRepository clientRepository;

    @Subscribe
    public void handle(ClientEnrolledEvent event) {
        ClientProjection tx = new ClientProjection(
                event.getAggregateId(),
                event.getName(),
                event.getEmail(),
                event.getVersion());
        clientRepository.save(tx);
    }
}
