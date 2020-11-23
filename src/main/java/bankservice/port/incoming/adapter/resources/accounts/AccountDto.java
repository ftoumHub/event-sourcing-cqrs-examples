package bankservice.port.incoming.adapter.resources.accounts;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static java.math.BigDecimal.ROUND_HALF_UP;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class AccountDto {

    @JsonProperty(access = READ_ONLY) private UUID id;
    @JsonProperty(access = READ_ONLY) private BigDecimal balance;
    private UUID clientId;


    @SuppressWarnings("unused")
    public void setId(UUID id) {
        this.id = id;
    }

    @SuppressWarnings("unused")
    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2, ROUND_HALF_UP);
    }

    @SuppressWarnings("unused")
    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }
}
