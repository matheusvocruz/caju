package model.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultException extends Exception {
    public DefaultException(String message) {
        super(message);
    }
}