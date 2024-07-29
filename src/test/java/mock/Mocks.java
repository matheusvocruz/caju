package mock;

import model.AuthorizeTransactionRequest;
import model.AuthorizeTransactionResponse;
import model.entity.account.Account;
import model.entity.account.Amounts;

import java.math.BigDecimal;

public class Mocks {
    public static AuthorizeTransactionRequest authorizeTransactionRequestMock() {
        var request = new AuthorizeTransactionRequest();
        request.setAccount("account");
        request.setMcc("account");
        request.setMerchant("account");
        request.setTotalAmount(BigDecimal.TEN);
        return request;
    }

    public static AuthorizeTransactionRequest authorizeTransactionRequestFoodMock() {
        var request = new AuthorizeTransactionRequest();
        request.setAccount("account");
        request.setMcc("5411");
        request.setMerchant("account");
        request.setTotalAmount(BigDecimal.TEN);
        return request;
    }

    public static AuthorizeTransactionRequest authorizeTransactionRequestMealMock() {
        var request = new AuthorizeTransactionRequest();
        request.setAccount("account");
        request.setMcc("5812");
        request.setMerchant("account");
        request.setTotalAmount(BigDecimal.TEN);
        return request;
    }

    private static AuthorizeTransactionResponse authorizeTransactionResponseMock(String code) {
        var response = new AuthorizeTransactionResponse();
        response.setCode(code);
        return response;
    }

    public static AuthorizeTransactionRequest authorizeTransactionRequestWithoutFundsMock() {
        var request = new AuthorizeTransactionRequest();
        request.setAccount("account");
        request.setMcc("account");
        request.setMerchant("account");
        request.setTotalAmount(BigDecimal.valueOf(100000));
        return request;
    }

    public static AuthorizeTransactionResponse authorizeTransactionResponseSuccessMock() {
        return authorizeTransactionResponseMock("00");
    }

    public static AuthorizeTransactionResponse authorizeTransactionResponseFailedMock() {
        return authorizeTransactionResponseMock("07");
    }

    public static AuthorizeTransactionResponse authorizeTransactionResponseInsufficientFundsMock() {
        return authorizeTransactionResponseMock("51");
    }

    public static Account accountMock() {
        var account = new Account();
        account.setId("id");
        account.setName("name");
        account.setAmounts(amountsMock(BigDecimal.valueOf(100), BigDecimal.valueOf(100), BigDecimal.valueOf(100)));
        return account;
    }

    public static Account accountMockWithoutFoodAndWithoutMeal() {
        var account = new Account();
        account.setId("id");
        account.setName("name");
        account.setAmounts(amountsMock(BigDecimal.valueOf(5), BigDecimal.valueOf(5), BigDecimal.valueOf(100)));
        return account;
    }

    private static Amounts amountsMock(BigDecimal food, BigDecimal meal, BigDecimal cash) {
        var amount = new Amounts();
        amount.setFood(food);
        amount.setMeal(meal);
        amount.setCash(cash);
        return amount;
    }
}
