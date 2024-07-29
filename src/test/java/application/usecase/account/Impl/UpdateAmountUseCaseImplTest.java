package application.usecase.account.Impl;

import application.usecase.account.UpdateAccountUseCase;
import mock.Mocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateAmountUseCaseImplTest {
    @Mock
    UpdateAccountUseCase updateAccountUseCase;

    @InjectMocks
    UpdateAmountUseCaseImpl updateAmountUseCaseImpl;

    @Test
    void should_execute_successfully() {
        var request = Mocks.authorizeTransactionRequestMock();
        var account = Mocks.accountMock();

        assertDoesNotThrow(() -> updateAmountUseCaseImpl.execute(request, account));
        verify(updateAccountUseCase).execute(account);
    }

    @Test
    void should_execute_with_food_mcc_successfully() {
        var request = Mocks.authorizeTransactionRequestFoodMock();
        var account = Mocks.accountMock();

        assertDoesNotThrow(() -> updateAmountUseCaseImpl.execute(request, account));
        verify(updateAccountUseCase).execute(account);
    }

    @Test
    void should_execute_with_food_mcc_with_half_amount_successfully() {
        var request = Mocks.authorizeTransactionRequestFoodMock();
        var account = Mocks.accountMockWithoutFoodAndWithoutMeal();

        assertDoesNotThrow(() -> updateAmountUseCaseImpl.execute(request, account));
        verify(updateAccountUseCase).execute(account);
    }

    @Test
    void should_execute_with_meal_mcc_successfully() {
        var request = Mocks.authorizeTransactionRequestMealMock();
        var account = Mocks.accountMock();

        assertDoesNotThrow(() -> updateAmountUseCaseImpl.execute(request, account));
        verify(updateAccountUseCase).execute(account);
    }

    @Test
    void should_execute_with_meal_mcc_with_half_amount_successfully() {
        var request = Mocks.authorizeTransactionRequestMealMock();
        var account = Mocks.accountMockWithoutFoodAndWithoutMeal();

        assertDoesNotThrow(() -> updateAmountUseCaseImpl.execute(request, account));
        verify(updateAccountUseCase).execute(account);
    }

    @Test
    void should_execute_without_amount_and_use_cash_successfully() {
        var request = Mocks.authorizeTransactionRequestMock();
        var account = Mocks.accountMockWithoutFoodAndWithoutMeal();

        assertDoesNotThrow(() -> updateAmountUseCaseImpl.execute(request, account));
        verify(updateAccountUseCase).execute(account);
    }
}