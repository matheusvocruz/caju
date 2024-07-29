package application.usecase.transaction.impl;

import application.usecase.account.UpdateAmountUseCase;
import application.usecase.transaction.CreateTransactionUseCase;
import data.repository.TransactionRepository;
import mock.Mocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProcessTransactionUseCaseImplTest {
    @Mock
    CreateTransactionUseCase createTransactionUseCase;

    @Mock
    UpdateAmountUseCase updateAmountUseCase;

    @InjectMocks
    ProcessTransactionUseCaseImpl processTransactionUseCaseImpl;

    @Test
    void should_execute_successfully() {
        var request = Mocks.authorizeTransactionRequestMock();
        var account = Mocks.accountMock();

        assertDoesNotThrow(() -> processTransactionUseCaseImpl.execute(request, account));
        verify(createTransactionUseCase).execute(request);
        verify(updateAmountUseCase).execute(request, account);
    }
}