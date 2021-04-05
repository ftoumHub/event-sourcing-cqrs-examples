package bankservice.projection.client;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryClientsRepository implements ClientRepository {

    private Map<UUID, ClientProjection> clientProjections = new ConcurrentHashMap<>();

    @Override
    public void save(ClientProjection clientProjection) {
        // Contrainte d'unicité à implémenter sur l'email
        clientProjections.merge(
                clientProjection.getClientId(), // key
                clientProjection, // value
                (oldValue, value) -> value); // remappingFunction
    }

    @Override
    public void updateEmail(UUID clientId, int version, String email) {

    }

    @Override
    public Optional<ClientProjection> getClient(UUID clientId) {
        ClientProjection clientProjection = clientProjections.get(clientId);

        if (null != clientProjection){
            return Optional.of(clientProjection);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ClientProjection> getClientWithEmail(String email) {
        return clientProjections.values().stream()
                .filter(c -> email.equals(c.getEmail().getValue()))
                .findAny();
    }
}
