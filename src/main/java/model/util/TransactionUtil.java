package model.util;

import model.AuthorizeTransactionRequest;
import model.entity.account.Account;
import model.enums.AmountEnum;
import model.enums.MccAmountEnum;

import java.math.BigDecimal;

public class TransactionUtil {
    public static AmountEnum checkAmountType(AuthorizeTransactionRequest request) {
        if (MccAmountEnum.MEAL.getMcc().contains(request.getMcc())
                || MccAmountEnum.MEAL.getMerchants().contains(request.getMerchant()))
            return MccAmountEnum.MEAL.getAmountEnum();

        if (MccAmountEnum.FOOD.getMcc().contains(request.getMcc())
                || MccAmountEnum.FOOD.getMerchants().contains(request.getMerchant()))
            return MccAmountEnum.FOOD.getAmountEnum();

        return AmountEnum.CASH;
    }

    public static Boolean checkAmount(BigDecimal amount, AmountEnum amountEnum, Account account) {
        BigDecimal totalAmount = switch (amountEnum) {
            case MEAL -> account.getAmounts().getMeal().add(account.getAmounts().getCash());
            case FOOD -> account.getAmounts().getFood().add(account.getAmounts().getCash());
            default -> account.getAmounts().getCash();
        };

        return totalAmount.compareTo(amount) >= 0;
    }
}
