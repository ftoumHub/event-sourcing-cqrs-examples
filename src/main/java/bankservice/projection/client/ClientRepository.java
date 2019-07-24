package bankservice.projection.client;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {

    void save(ClientProjection clientProjection);

    void updateEmail(UUID clientId, int version, String email);

    Optional<ClientProjection> getClient(UUID clientId);

    Optional<ClientProjection> getClientWithEmail(String email);
}
