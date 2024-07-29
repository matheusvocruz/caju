package application.usecase.account.Impl;

import data.repository.AccountRepository;
import mock.Mocks;
import model.exception.DefaultException;
import model.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GetAccountUseCaseImplTest {
    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    GetAccountUseCaseImpl getAccountUseCaseImpl;

    @Test
    void should_execute_successfully() {
        when(accountRepository.findById(anyString())).thenReturn(Optional.of(Mocks.accountMock()));

        var result = getAccountUseCaseImpl.execute("id");

        assertAll(
                () -> assertNotNull(result),
                () -> assertNotNull(result.getId())
        );
    }

    @Test
    void execute_throw_not_found_exception() {
        when(accountRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> getAccountUseCaseImpl.execute("id"));
    }

    @Test
    void get_all_throw_default_exception() {
        when(accountRepository.findById(anyString())).thenThrow(NullPointerException.class);

        assertThrows(DefaultException.class, () -> getAccountUseCaseImpl.execute("id"));
    }
}