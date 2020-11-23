package bankservice.service.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class OpenAccountCommand {

    private final UUID clientId;

}
