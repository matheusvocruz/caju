package application.usecase.account.Impl;

import application.usecase.account.UpdateAccountUseCase;
import data.repository.AccountRepository;
import jakarta.inject.Singleton;
import lombok.SneakyThrows;
import model.entity.account.Account;
import model.exception.PersistenceException;

@Singleton
public class UpdateAccountUseCaseImpl implements UpdateAccountUseCase {
    private final AccountRepository accountRepository;

    public UpdateAccountUseCaseImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @SneakyThrows
    public void execute(Account account) {
        try {
            accountRepository.update(account);
        } catch (Exception exception) {
            throw new PersistenceException(exception.getMessage());
        }
    }
}
