package application.usecase.transaction.impl;

import application.usecase.transaction.CreateTransactionUseCase;
import data.repository.TransactionRepository;
import jakarta.inject.Singleton;
import lombok.SneakyThrows;
import model.AuthorizeTransactionRequest;
import model.entity.transaction.Transaction;
import model.exception.PersistenceException;

@Singleton
public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {
    private final TransactionRepository transactionRepository;

    public CreateTransactionUseCaseImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    @SneakyThrows
    public void execute(AuthorizeTransactionRequest authorizeTransactionRequest) {
        try {
            var transaction = buildTransaction(authorizeTransactionRequest);
            transactionRepository.save(transaction);
        } catch (Exception exception) {
            throw new PersistenceException(exception.getMessage());
        }
    }

    private Transaction buildTransaction(AuthorizeTransactionRequest authorizeTransactionRequest) {
        var transaction = new Transaction();
        transaction.setAccountId(authorizeTransactionRequest.getAccount());
        transaction.setAmount(authorizeTransactionRequest.getTotalAmount());
        transaction.setMcc(authorizeTransactionRequest.getMcc());
        transaction.setMerchant(authorizeTransactionRequest.getMerchant());

        return transaction;
    }
}
