package application.usecase.account.Impl;

import data.repository.AccountRepository;
import mock.Mocks;
import model.exception.PersistenceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateAccountUseCaseImplTest {
    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    UpdateAccountUseCaseImpl updateAccountUseCaseImpl;

    @Test
    void should_execute_successfully() {
        var request = Mocks.accountMock();

        assertDoesNotThrow(() -> updateAccountUseCaseImpl.execute(request));
        verify(accountRepository).update(request);
    }

    @Test
    void execute_throw_persistence_exception() {
        when(accountRepository.update(any())).thenThrow(NullPointerException.class);

        assertThrows(PersistenceException.class, () -> updateAccountUseCaseImpl.execute(Mocks.accountMock()));
    }
}