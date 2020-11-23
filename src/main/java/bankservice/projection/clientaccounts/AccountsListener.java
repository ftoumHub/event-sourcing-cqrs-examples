package bankservice.projection.clientaccounts;

import bankservice.domain.model.account.AccountDepositedEvent;
import bankservice.domain.model.account.AccountOpenedEvent;
import bankservice.domain.model.account.AccountWithdrawnEvent;
import com.google.common.eventbus.Subscribe;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountsListener {

    private final AccountsRepository accountsRepository;

    @Subscribe
    public void handle(AccountOpenedEvent event) {
        AccountProjection accountProjection = new AccountProjection(
            event.getAggregateId(), event.getClientId(), event.getBalance(), event.getVersion());
        accountsRepository.save(accountProjection);
    }

    @Subscribe
    public void handle(AccountDepositedEvent event) {
        accountsRepository.updateBalance(event.getAggregateId(), event.getBalance(), event.getVersion());
    }

    @Subscribe
    public void handle(AccountWithdrawnEvent event) {
        accountsRepository.updateBalance(event.getAggregateId(), event.getBalance(), event.getVersion());
    }
}
