package application.usecase.transaction.impl;

import data.repository.TransactionRepository;
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
class CreateTransactionUseCaseImplTest {
    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    CreateTransactionUseCaseImpl createTransactionUseCaseImpl;

    @Test
    void should_execute_successfully() {
        assertDoesNotThrow(() -> createTransactionUseCaseImpl.execute(Mocks.authorizeTransactionRequestMock()));
        verify(transactionRepository).save(any());
    }

    @Test
    void execute_throw_persistence_exception() {
        when(transactionRepository.save(any())).thenThrow(NullPointerException.class);

        assertThrows(PersistenceException.class, () -> createTransactionUseCaseImpl.execute(Mocks.authorizeTransactionRequestMock()));
    }
}