package bankservice.projection.client;

import java.util.UUID;

public interface ClientRepository {

    void save(ClientProjection clientProjection);

    void updateEmail(UUID clientId, int version, String email);

    ClientProjection getClient(UUID clientId);

    ClientProjection getClientWithEmail(String email);
}
