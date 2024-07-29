package application.usecase.account;

import model.entity.account.Account;

public interface UpdateAccountUseCase {
    void execute(Account account);
}
