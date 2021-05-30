package com.exb.springboot.exception;

public class InvalidFormatException extends Exception{

    private final String exceptionMessage;

    public InvalidFormatException(String message){
        this.exceptionMessage = message;
    }

}
