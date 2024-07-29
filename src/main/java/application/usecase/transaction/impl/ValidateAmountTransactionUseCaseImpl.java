package application.usecase.transaction.impl;

import application.usecase.transaction.ValidateAmountTransactionUseCase;
import jakarta.inject.Singleton;
import lombok.SneakyThrows;
import model.AuthorizeTransactionRequest;
import model.entity.account.Account;
import model.exception.InsufficientFundsException;
import model.util.TransactionUtil;

@Singleton
public class ValidateAmountTransactionUseCaseImpl implements ValidateAmountTransactionUseCase {
    @Override
    @SneakyThrows
    public void execute(AuthorizeTransactionRequest request, Account account) {
        if (! TransactionUtil.checkAmount(request.getTotalAmount(), TransactionUtil.checkAmountType(request), account)) {
            throw new InsufficientFundsException("Insufficient funds");
        }
    }
}
