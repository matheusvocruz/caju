package application.usecase.account;

import model.entity.account.Account;

public interface GetAccountUseCase {
    Account execute(String id);
}
