package bankservice.projection.clientaccounts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
@RequiredArgsConstructor
public class AccountProjection {

    private final UUID accountId;
    private final UUID clientId;
    private final BigDecimal balance;
    private final int version;

}
