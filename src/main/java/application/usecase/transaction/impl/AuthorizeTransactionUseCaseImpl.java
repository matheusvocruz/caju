package application.usecase.transaction.impl;

import application.usecase.account.GetAccountUseCase;
import application.usecase.transaction.AuthorizeTransactionUseCase;
import application.usecase.transaction.ProcessTransactionUseCase;
import application.usecase.transaction.ValidateAmountTransactionUseCase;
import jakarta.inject.Singleton;
import model.AuthorizeTransactionRequest;
import model.AuthorizeTransactionResponse;
import model.entity.account.Account;
import model.enums.TransactionStatusEnum;
import model.exception.InsufficientFundsException;

@Singleton
public class AuthorizeTransactionUseCaseImpl implements AuthorizeTransactionUseCase {
    private final GetAccountUseCase getAccountUseCase;
    private final ValidateAmountTransactionUseCase validateAmountTransactionUseCase;
    private final ProcessTransactionUseCase processTransactionUseCase;

    public AuthorizeTransactionUseCaseImpl(GetAccountUseCase getAccountUseCase, ValidateAmountTransactionUseCase validateAmountTransactionUseCase, ProcessTransactionUseCase processTransactionUseCase) {
        this.getAccountUseCase = getAccountUseCase;
        this.validateAmountTransactionUseCase = validateAmountTransactionUseCase;
        this.processTransactionUseCase = processTransactionUseCase;
    }

    @Override
    public AuthorizeTransactionResponse execute(AuthorizeTransactionRequest request) {
        AuthorizeTransactionResponse response = new AuthorizeTransactionResponse();

        try {
            var account = getAccountUseCase.execute(request.getAccount());
            if (!validateAmountTransaction(request, account)){
                throw new InsufficientFundsException("Insufficient funds");
            }

            processTransactionUseCase.execute(request, account);
            response.setCode(TransactionStatusEnum.SUCCESS.getResponseStatus());
        } catch (InsufficientFundsException insufficientFundsException) {
            response.setCode(TransactionStatusEnum.INSUFFICIENT_FUNDS.getResponseStatus());
        } catch (Exception exception) {
            response.setCode(TransactionStatusEnum.FAILED.getResponseStatus());
        }

        return response;
    }

    private Boolean validateAmountTransaction(AuthorizeTransactionRequest request, Account account) {
        try {
            validateAmountTransactionUseCase.execute(request, account);
            return true;
        } catch (Exception exception) {
           return false;
        }
    }
}
