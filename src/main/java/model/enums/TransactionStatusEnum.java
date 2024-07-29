package model.enums;

import lombok.Getter;

@Getter
public enum TransactionStatusEnum {
    SUCCESS("00"),
    FAILED("07"),
    INSUFFICIENT_FUNDS("51");

    private final String responseStatus;

    TransactionStatusEnum(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}
