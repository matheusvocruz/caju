package application.usecase.transaction.impl;

import application.usecase.account.UpdateAmountUseCase;
import application.usecase.transaction.CreateTransactionUseCase;
import application.usecase.transaction.ProcessTransactionUseCase;
import jakarta.inject.Singleton;
import model.AuthorizeTransactionRequest;
import model.entity.account.Account;

@Singleton
public class ProcessTransactionUseCaseImpl implements ProcessTransactionUseCase {
    private final CreateTransactionUseCase createTransactionUseCase;
    private final UpdateAmountUseCase updateAmountUseCase;

    public ProcessTransactionUseCaseImpl(CreateTransactionUseCase createTransactionUseCase, UpdateAmountUseCase updateAmountUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
        this.updateAmountUseCase = updateAmountUseCase;
    }

    @Override
    public void execute(AuthorizeTransactionRequest request, Account account) {
        createTransactionUseCase.execute(request);
        updateAmountUseCase.execute(request, account);
    }
}
