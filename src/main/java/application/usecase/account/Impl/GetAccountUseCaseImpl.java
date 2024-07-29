package application.usecase.account.Impl;

import application.usecase.account.GetAccountUseCase;
import data.repository.AccountRepository;
import jakarta.inject.Singleton;

import lombok.SneakyThrows;
import model.entity.account.Account;
import model.exception.DefaultException;
import model.exception.NotFoundException;

@Singleton
public class GetAccountUseCaseImpl implements GetAccountUseCase {
    private final AccountRepository accountRepository;

    public GetAccountUseCaseImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @SneakyThrows
    @Override
    public Account execute(String id) {
        try {
            return this.accountRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("User not found"));
        } catch (NotFoundException notFoundException) {
            throw notFoundException;
        } catch (Exception exception) {
            throw new DefaultException(exception.getMessage());
        }
    }
}
