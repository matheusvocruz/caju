package entrypoint.controller;

import application.usecase.transaction.AuthorizeTransactionUseCase;
import mock.Mocks;
import model.enums.TransactionStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Mock
    AuthorizeTransactionUseCase authorizeTransactionUseCase;

    @InjectMocks
    TransactionController transactionController;

    @Test
    void authorize_transaction_successfully() {
        when(authorizeTransactionUseCase.execute(any())).thenReturn(Mocks.authorizeTransactionResponseSuccessMock());

        var response = transactionController.transactionAuthorize(Mocks.authorizeTransactionRequestMock());

        assertAll(
                () -> assertNotNull(response),
                () -> assertNotNull(response.getCode()),
                () -> assertEquals(TransactionStatusEnum.SUCCESS.getResponseStatus(), response.getCode())
        );
    }

    @Test
    void authorize_transaction_failed() {
        when(authorizeTransactionUseCase.execute(any())).thenReturn(Mocks.authorizeTransactionResponseFailedMock());

        var response = transactionController.transactionAuthorize(Mocks.authorizeTransactionRequestMock());

        assertAll(
                () -> assertNotNull(response),
                () -> assertNotNull(response.getCode()),
                () -> assertEquals(TransactionStatusEnum.FAILED.getResponseStatus(), response.getCode())
        );
    }

    @Test
    void authorize_transaction_failed_insufficient_funds() {
        when(authorizeTransactionUseCase.execute(any())).thenReturn(Mocks.authorizeTransactionResponseInsufficientFundsMock());

        var response = transactionController.transactionAuthorize(Mocks.authorizeTransactionRequestMock());

        assertAll(
                () -> assertNotNull(response),
                () -> assertNotNull(response.getCode()),
                () -> assertEquals(TransactionStatusEnum.INSUFFICIENT_FUNDS.getResponseStatus(), response.getCode())
        );
    }
}