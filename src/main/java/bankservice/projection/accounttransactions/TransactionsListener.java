package bankservice.projection.accounttransactions;

import bankservice.domain.model.account.AccountDepositedEvent;
import bankservice.domain.model.account.AccountWithdrawnEvent;
import com.google.common.eventbus.Subscribe;
import lombok.RequiredArgsConstructor;

import static bankservice.projection.accounttransactions.TransactionProjection.TransactionType.DEPOSIT;
import static bankservice.projection.accounttransactions.TransactionProjection.TransactionType.WITHDRAWAL;

@RequiredArgsConstructor
public class TransactionsListener {

    private final TransactionsRepository transactionsRepository;

    @Subscribe
    public void handle(AccountDepositedEvent event) {
        TransactionProjection tx = new TransactionProjection(
                event.getAggregateId(), DEPOSIT, event.getAmount(), event.getTimestamp(), event.getVersion());
        transactionsRepository.save(tx);
    }

    @Subscribe
    public void handle(AccountWithdrawnEvent event) {
        TransactionProjection tx = new TransactionProjection(
                event.getAggregateId(), WITHDRAWAL, event.getAmount(), event.getTimestamp(), event.getVersion());
        transactionsRepository.save(tx);
    }
}
