package bankservice.domain.model.client;

import static com.google.common.base.Preconditions.checkNotNull;

import bankservice.domain.model.Event;
import java.util.UUID;

import lombok.Getter;
import org.joda.time.DateTime;

@Getter
public class ClientEnrolledEvent extends Event {

    private final String name;
    private final Email email;

    public ClientEnrolledEvent(UUID aggregateId, DateTime timestamp, int version,
                               String name, Email email) {
        super(aggregateId, timestamp, version);
        this.name = checkNotNull(name);
        this.email = checkNotNull(email);
    }

}
