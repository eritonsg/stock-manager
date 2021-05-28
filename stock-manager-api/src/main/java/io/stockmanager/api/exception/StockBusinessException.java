package io.stockmanager.api.exception;

public class StockBusinessException extends BusinessException {

    public StockBusinessException(String message) {
        super(message);
    }

    public StockBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
