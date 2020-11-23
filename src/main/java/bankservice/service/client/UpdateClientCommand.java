package bankservice.service.client;

import bankservice.domain.model.client.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class UpdateClientCommand {

    private final UUID id;
    private final String name;
    private final Email email;

}
