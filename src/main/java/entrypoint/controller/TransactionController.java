package entrypoint.controller;

import application.usecase.transaction.AuthorizeTransactionUseCase;
import controller.TransactionApi;
import io.micronaut.http.annotation.Controller;
import model.AuthorizeTransactionRequest;
import model.AuthorizeTransactionResponse;

@Controller
public class TransactionController implements TransactionApi {
    private final AuthorizeTransactionUseCase authorizeTransactionUseCase;

    public TransactionController(AuthorizeTransactionUseCase authorizeTransactionUseCase) {
        this.authorizeTransactionUseCase = authorizeTransactionUseCase;
    }

    @Override
    public AuthorizeTransactionResponse transactionAuthorize(AuthorizeTransactionRequest authorizeTransactionRequest) {
        return authorizeTransactionUseCase.execute(authorizeTransactionRequest);
    }
}
