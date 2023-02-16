package se.lexicon.kerry.booklender_rest_api.exception;

public class DataDuplicateException extends RuntimeException {

    public DataDuplicateException(String message) {
        super(message);
    }
}
