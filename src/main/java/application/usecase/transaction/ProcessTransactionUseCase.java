package application.usecase.transaction;

import model.AuthorizeTransactionRequest;
import model.entity.account.Account;

public interface ProcessTransactionUseCase {
    void execute(AuthorizeTransactionRequest request, Account account);
}
