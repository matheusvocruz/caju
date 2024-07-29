package model.entity.account;

import io.micronaut.serde.annotation.Serdeable;

import java.math.BigDecimal;

@Serdeable
public class Amounts {
    private BigDecimal food;
    private BigDecimal meal;
    private BigDecimal cash;

    public BigDecimal getFood() { return this.food; }
    public BigDecimal getMeal() { return this.meal; }
    public BigDecimal getCash() { return this.cash; }

    public void setFood(BigDecimal food) { this.food = food; }
    public void setMeal(BigDecimal meal) { this.meal = meal; }
    public void setCash(BigDecimal cash) { this.cash = cash; }
}
