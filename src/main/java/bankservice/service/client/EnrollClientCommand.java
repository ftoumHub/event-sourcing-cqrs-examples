package bankservice.service.client;

import bankservice.domain.model.client.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EnrollClientCommand {

    private final String name;
    private final Email email;

}
