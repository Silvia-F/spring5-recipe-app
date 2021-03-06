package guru.springframework.commands;

import guru.springframework.domain.UnitOfMeasure;

import java.math.BigDecimal;

public class IngredientCommand {

    private Long id;

    private String description;

    private BigDecimal amount;

    private UnitOfMeasureCommand uom;

    public IngredientCommand() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UnitOfMeasureCommand getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasureCommand uom) {
        this.uom = uom;
    }
}
