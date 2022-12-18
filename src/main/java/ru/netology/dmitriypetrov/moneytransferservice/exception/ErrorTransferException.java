package ru.netology.dmitriypetrov.moneytransferservice.exception;


public class ErrorTransferException extends RuntimeException{
    public ErrorTransferException(String message) {
        super(message);
    }
}
