package bankservice.port.incoming.adapter.resources.clients;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class ClientDto {

    @JsonProperty(access = READ_ONLY)
    private UUID id;
    @NotBlank private String name;
    @Email private String email;

}
