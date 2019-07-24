package bankservice.projection.client;

import bankservice.domain.model.client.Email;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class InMemoryClientsRepositoryTest {

    public ClientRepository clientRepository = new InMemoryClientsRepository();

    @Test
    void getClientByUuid() {
        UUID clientId = UUID.randomUUID();
        String name = "Georges";
        Email email = new Email("georges.ginon@maif.fr");

        clientRepository.save(new ClientProjection(clientId, name, email,1));

        assertThat(clientRepository.getClient(clientId).get().getName(), equalTo("Georges"));
    }

    @Test
    void getClientByEmail() {
        UUID clientId1 = UUID.randomUUID();
        String name1 = "Georges";
        String emailValue = "georges.ginon@maif.fr";
        Email email1 = new Email(emailValue);

        UUID clientId2 = UUID.randomUUID();
        String name2 = "John";
        Email email2 = new Email("john.doe@gmail.com");

        clientRepository.save(new ClientProjection(clientId1, name1, email1,1));
        clientRepository.save(new ClientProjection(clientId2, name2, email2,1));

        assertThat("L'email du client doit être identifiable",
                clientRepository.getClientWithEmail(emailValue).get().getEmail().getValue(),
                equalTo(emailValue));
    }
}
