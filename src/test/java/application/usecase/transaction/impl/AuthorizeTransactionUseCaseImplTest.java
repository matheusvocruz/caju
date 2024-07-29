package application.usecase.transaction.impl;

import application.usecase.account.GetAccountUseCase;
import application.usecase.transaction.ProcessTransactionUseCase;
import application.usecase.transaction.ValidateAmountTransactionUseCase;
import mock.Mocks;
import model.enums.TransactionStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorizeTransactionUseCaseImplTest {
    @Mock
    GetAccountUseCase getAccountUseCase;

    @Mock
    ValidateAmountTransactionUseCase validateAmountTransactionUseCase;

    @Mock
    ProcessTransactionUseCase processTransactionUseCase;

    @InjectMocks
    AuthorizeTransactionUseCaseImpl authorizeTransactionUseCaseImpl;

    @Test
    void should_execute_successfully() {
        var request = Mocks.authorizeTransactionRequestMock();
        var account = Mocks.accountMock();

        when(getAccountUseCase.execute(anyString())).thenReturn(account);

        var result = authorizeTransactionUseCaseImpl.execute(request);
        verify(validateAmountTransactionUseCase).execute(request, account);
        verify(processTransactionUseCase).execute(request, account);

        assertAll(
                () -> assertNotNull(result),
                () -> assertNotNull(result.getCode()),
                () -> assertEquals(TransactionStatusEnum.SUCCESS.getResponseStatus(), result.getCode())
        );
    }

    @Test
    void should_execute_failed_user_not_found_successfully() {
        var request = Mocks.authorizeTransactionRequestMock();

        when(getAccountUseCase.execute(anyString())).thenThrow(RuntimeException.class);

        var result = authorizeTransactionUseCaseImpl.execute(request);

        assertAll(
                () -> assertNotNull(result),
                () -> assertNotNull(result.getCode()),
                () -> assertEquals(TransactionStatusEnum.FAILED.getResponseStatus(), result.getCode())
        );
    }

    @Test
    void should_execute_insufficient_funds_successfully() {
        var request = Mocks.authorizeTransactionRequestWithoutFundsMock();
        var account = Mocks.accountMock();

        when(getAccountUseCase.execute(anyString())).thenReturn(account);

        doThrow(new RuntimeException()).when(validateAmountTransactionUseCase).execute(request, account);

        var result = authorizeTransactionUseCaseImpl.execute(request);

        assertAll(
                () -> assertNotNull(result),
                () -> assertNotNull(result.getCode()),
                () -> assertEquals(TransactionStatusEnum.INSUFFICIENT_FUNDS.getResponseStatus(), result.getCode())
        );
    }
}