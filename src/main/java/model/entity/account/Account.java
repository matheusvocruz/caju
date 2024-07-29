package model.entity.account;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@MappedEntity
public class Account {
    @Id
    @GeneratedValue
    private String id;
    private String name;
    private Amounts amounts;

    public String getId() { return this.id; }
    public String getName() { return this.name; }
    public Amounts getAmounts() { return this.amounts; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAmounts(Amounts amounts) { this.amounts = amounts; }
}
