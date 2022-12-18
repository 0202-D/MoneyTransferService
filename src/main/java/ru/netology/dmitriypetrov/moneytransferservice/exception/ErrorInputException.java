package ru.netology.dmitriypetrov.moneytransferservice.exception;



public class ErrorInputException extends RuntimeException{
    public ErrorInputException(String message) {
        super(message);
    }
}
