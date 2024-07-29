package application.usecase.account;

import model.AuthorizeTransactionRequest;
import model.entity.account.Account;

public interface UpdateAmountUseCase {
    void execute(AuthorizeTransactionRequest request, Account account);
}
