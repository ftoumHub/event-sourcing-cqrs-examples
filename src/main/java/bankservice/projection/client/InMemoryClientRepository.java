package bankservice.projection.client;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryClientRepository implements ClientRepository {

    private Map<UUID, ClientProjection> clientProjections = new ConcurrentHashMap<>();

    @Override
    public void save(ClientProjection clientProjection) {
        clientProjections.merge(
                clientProjection.getClientId(), // key
                clientProjection, // value
                (oldValue, value) -> value); // remappingFunction
    }

    @Override
    public void updateEmail(UUID clientId, int version, String email) {

    }

    @Override
    public ClientProjection getClient(UUID clientId) {
        return null;
    }

    @Override
    public ClientProjection getClientWithEmail(String email) {
        return null;
    }
}
