package bankservice.projection.client;

import bankservice.domain.model.client.Email;

import java.util.UUID;

public class ClientProjection {

    private final UUID clientId;
    private final String name;
    private final Email email;
    private final int version;

    public ClientProjection(UUID clientId, String name, Email email, int version) {
        this.clientId = clientId;
        this.name = name;
        this.email = email;
        this.version = version;
    }

    public UUID getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public int getVersion() {
        return version;
    }
}
