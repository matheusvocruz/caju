package application.usecase.account.Impl;

import application.usecase.account.UpdateAccountUseCase;
import application.usecase.account.UpdateAmountUseCase;
import jakarta.inject.Singleton;
import model.AuthorizeTransactionRequest;
import model.entity.account.Account;
import model.enums.AmountEnum;
import model.util.TransactionUtil;

import java.math.BigDecimal;

@Singleton
public class UpdateAmountUseCaseImpl implements UpdateAmountUseCase {
    private final UpdateAccountUseCase updateAccountUseCase;

    public UpdateAmountUseCaseImpl(UpdateAccountUseCase updateAccountUseCase) {
        this.updateAccountUseCase = updateAccountUseCase;
    }

    @Override
    public void execute(AuthorizeTransactionRequest request, Account account) {
        updateAmount(request.getTotalAmount(), account, TransactionUtil.checkAmountType(request));
        updateAccountUseCase.execute(account);
    }

    private void updateAmount(BigDecimal amount, Account account, AmountEnum amountType) {
        BigDecimal totalAmount = switch (amountType) {
            case MEAL -> account.getAmounts().getMeal();
            case FOOD -> account.getAmounts().getFood();
            default -> account.getAmounts().getCash();
        };

        if (totalAmount.compareTo(amount) >= 0) {
            updateAmountFromType(amount, account, amountType);
        } else {
            updateAmountFromTypeAndCash(amount, account, amountType);
        }
    }

    private void updateAmountFromType(BigDecimal amount, Account account, AmountEnum amountType) {
        switch (amountType) {
            case MEAL -> account.getAmounts().setMeal(account.getAmounts().getMeal().subtract(amount));
            case FOOD -> account.getAmounts().setFood(account.getAmounts().getFood().subtract(amount));
            default -> account.getAmounts().setFood(account.getAmounts().getCash().subtract(amount));
        }
    }

    private void updateAmountFromTypeAndCash(BigDecimal amount, Account account, AmountEnum amountType) {
        switch (amountType) {
            case MEAL -> {
                var subtracted = amount.subtract(account.getAmounts().getMeal());
                account.getAmounts().setMeal(BigDecimal.ZERO);
                account.getAmounts().setCash(account.getAmounts().getCash().subtract(subtracted));
            }
            case FOOD -> {
                var subtracted = amount.subtract(account.getAmounts().getFood());
                account.getAmounts().setFood(BigDecimal.ZERO);
                account.getAmounts().setCash(account.getAmounts().getCash().subtract(subtracted));
            }
        }
    }
}
