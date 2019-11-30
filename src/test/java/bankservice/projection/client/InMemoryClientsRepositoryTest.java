package bankservice.projection.client;

import bankservice.domain.model.client.Email;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class InMemoryClientsRepositoryTest {

    public ClientRepository clientRepository = new InMemoryClientsRepository();

    // Une liste d'ids de client à utiliser dans les tests
    public List<UUID> clientIds = List.of(UUID.randomUUID(), UUID.randomUUID());

    @Test
    void getClientByUuid() {
        Email email = new Email("georges.ginon@maif.fr");

        clientRepository.save(new ClientProjection(clientIds.get(0), "Georges", email,1));

        assertThat(clientRepository.getClient(clientIds.get(0)).get().getName(), equalTo("Georges"));
    }

    @Test
    void getClientByEmail() {
        String emailValue = "georges.ginon@maif.fr";
        Email email1 = new Email(emailValue);

        Email email2 = new Email("john.doe@gmail.com");

        clientRepository.save(new ClientProjection(clientIds.get(0), "Georges", email1,1));
        clientRepository.save(new ClientProjection(clientIds.get(1), "John", email2,1));

        assertThat("L'email du client doit être identifiable",
                clientRepository.getClientWithEmail(emailValue).get().getEmail().getValue(),
                equalTo(emailValue));
    }
}
