package model.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum MccAmountEnum {
    MEAL(new ArrayList<String>(List.of("5411", "5412")), new ArrayList<String>(List.of("RESTAURANTE", "MERCADO", "MEAL")), AmountEnum.MEAL),
    FOOD(new ArrayList<String>(List.of("5811", "5812")), new ArrayList<String>(List.of("COMIDA", "PADARIA", "FOOD")), AmountEnum.FOOD);

    private final ArrayList<String> mcc;
    private final ArrayList<String> merchants;
    private final AmountEnum amountEnum;

    MccAmountEnum(ArrayList<String> mcc, ArrayList<String> merchants, AmountEnum amountEnum) {
        this.mcc = mcc;
        this.merchants = merchants;
        this.amountEnum = amountEnum;
    }
}
