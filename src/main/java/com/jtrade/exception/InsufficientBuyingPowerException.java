package com.jtrade.exception;

public class InsufficientBuyingPowerException extends RuntimeException{
    public InsufficientBuyingPowerException (String message) {
        super(message);
    }
}