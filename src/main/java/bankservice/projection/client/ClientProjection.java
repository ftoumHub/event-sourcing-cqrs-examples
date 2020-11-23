package bankservice.projection.client;

import bankservice.domain.model.client.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class ClientProjection {

    private final UUID clientId;
    private final String name;
    private final Email email;
    private final int version;

}
