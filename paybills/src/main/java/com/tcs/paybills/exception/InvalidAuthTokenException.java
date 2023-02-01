package com.tcs.paybills.exception;

public class InvalidAuthTokenException extends RuntimeException{

    private String authTokenHeaderIsInvalid = "authTokenHeaderIsInvalid";


    @Override
    public String toString() {
        return "InvalidAuthTokenException AuthTokenHeaderIsInvalid";
    }
}
