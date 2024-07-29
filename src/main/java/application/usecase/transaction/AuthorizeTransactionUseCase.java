package application.usecase.transaction;

import model.AuthorizeTransactionRequest;
import model.AuthorizeTransactionResponse;

public interface AuthorizeTransactionUseCase {
    AuthorizeTransactionResponse execute(AuthorizeTransactionRequest request);
}
