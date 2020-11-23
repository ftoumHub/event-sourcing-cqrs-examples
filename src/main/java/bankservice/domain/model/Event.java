package bankservice.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
@RequiredArgsConstructor
public abstract class Event {

    private final UUID aggregateId;
    private final DateTime timestamp;
    private final int version;

}
