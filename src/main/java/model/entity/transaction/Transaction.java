package model.entity.transaction;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

import java.math.BigDecimal;

@Serdeable
@MappedEntity
public class Transaction {
    @Id
    @GeneratedValue
    private String id;
    private String accountId;
    private BigDecimal amount;
    private String merchant;
    private String mcc;

    public String getId() { return this.id; }
    public String getAccountId() { return this.accountId; }
    public BigDecimal getAmount() { return this.amount; }
    public String getMerchant() { return this.merchant; }
    public String getMcc() { return this.mcc; }

    public void setId(String id) { this.id = id; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setMerchant(String amount) { this.merchant = merchant; }
    public void setMcc(String mcc) { this.mcc = mcc; }
}
