package bankservice.service.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class WithdrawAccountCommand {

    private final UUID id;
    private final BigDecimal amount;

}
