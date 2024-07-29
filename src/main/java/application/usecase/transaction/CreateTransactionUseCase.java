package application.usecase.transaction;

import model.AuthorizeTransactionRequest;

public interface CreateTransactionUseCase {
    void execute(AuthorizeTransactionRequest authorizeTransactionRequest);
}
