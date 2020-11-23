package bankservice.domain.model.client;

import bankservice.domain.model.Event;
import lombok.Getter;
import org.joda.time.DateTime;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
public class ClientUpdatedEvent extends Event {

    private final String name;
    private final Email email;

    public ClientUpdatedEvent(UUID aggregateId, DateTime timestamp, int version,
                              String name, Email email) {
        super(aggregateId, timestamp, version);
        this.name = checkNotNull(name);
        this.email = checkNotNull(email);
    }

}
