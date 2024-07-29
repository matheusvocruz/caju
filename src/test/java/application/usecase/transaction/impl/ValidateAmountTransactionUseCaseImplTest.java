package application.usecase.transaction.impl;

import mock.Mocks;
import model.exception.InsufficientFundsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidateAmountTransactionUseCaseImplTest {
    @InjectMocks
    ValidateAmountTransactionUseCaseImpl validateAmountTransactionUseCaseImpl;

    @Test
    void should_execute_successfully() {
        assertDoesNotThrow(() -> validateAmountTransactionUseCaseImpl.execute(Mocks.authorizeTransactionRequestMock(), Mocks.accountMock()));
    }

    @Test
    void should_execute_with_half_food_amount_successfully() {
        assertDoesNotThrow(() ->
                validateAmountTransactionUseCaseImpl.execute(Mocks.authorizeTransactionRequestFoodMock(), Mocks.accountMockWithoutFoodAndWithoutMeal()));
    }

    @Test
    void should_execute_with_half_meal_amount_successfully() {
        assertDoesNotThrow(() ->
                validateAmountTransactionUseCaseImpl.execute(Mocks.authorizeTransactionRequestMealMock(), Mocks.accountMockWithoutFoodAndWithoutMeal()));
    }

    @Test
    void should_throw_insufficient_funds_exception() {
        assertThrows(
                InsufficientFundsException.class,
                () -> validateAmountTransactionUseCaseImpl.execute(Mocks.authorizeTransactionRequestWithoutFundsMock(), Mocks.accountMock())
        );
    }
}