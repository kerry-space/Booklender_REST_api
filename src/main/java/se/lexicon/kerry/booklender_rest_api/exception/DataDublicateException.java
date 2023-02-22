package se.lexicon.kerry.booklender_rest_api.exception;

public class DataDublicateException extends RuntimeException{
    public DataDublicateException(String message) {
        super(message);
    }
}
