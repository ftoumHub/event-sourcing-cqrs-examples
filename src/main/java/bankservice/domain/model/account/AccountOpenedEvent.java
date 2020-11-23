package bankservice.domain.model.account;

import static com.google.common.base.Preconditions.checkNotNull;

import bankservice.domain.model.Event;
import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import org.joda.time.DateTime;

@Getter
public class AccountOpenedEvent extends Event {

    private final UUID clientId;
    private final BigDecimal balance;

    public AccountOpenedEvent(UUID aggregateId, DateTime timestamp, int version, UUID clientId, BigDecimal balance) {
        super(aggregateId, timestamp, version);
        this.clientId = checkNotNull(clientId);
        this.balance = checkNotNull(balance);
    }

}
