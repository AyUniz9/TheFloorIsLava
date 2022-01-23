package me.niz.thefloorislava.api.exception;

public class SQLPlayerDataException extends Exception {

    public SQLPlayerDataException(String message) {
        super(message);
    }

    public SQLPlayerDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
