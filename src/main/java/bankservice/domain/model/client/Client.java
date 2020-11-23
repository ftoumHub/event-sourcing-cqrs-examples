package bankservice.domain.model.client;

import bankservice.domain.model.Aggregate;
import bankservice.domain.model.Event;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;

@Getter
public class Client extends Aggregate {

    private String name;
    private Email email;

    public Client(UUID id, String name, Email email) {
        super(id);
        checkArgument(isNotBlank(name));
        checkNotNull(email);

        ClientEnrolledEvent clientEnrolledEvent = new ClientEnrolledEvent(id, now(UTC), getNextVersion(), name, email);
        applyNewEvent(clientEnrolledEvent);
    }

    public Client(UUID id, List<Event> eventStream) {
        super(id, eventStream);
    }

    public void update(String name, Email email) {
        ClientUpdatedEvent clientUpdatedEvent = new ClientUpdatedEvent(
                getId(), now(UTC), getNextVersion(), name, email);
        applyNewEvent(clientUpdatedEvent);
    }

    public void apply(ClientEnrolledEvent event) {
        this.name = event.getName();
        this.email = event.getEmail();
    }

    private void apply(ClientUpdatedEvent event) {
        this.name = event.getName();
        this.email = event.getEmail();
    }

}
