package br.com.softblue.bluefood.application.exception;

@SuppressWarnings("serial")
public class ApplicationServiceException extends RuntimeException {

    public ApplicationServiceException(String message) {
        super(message);
    }

    public ApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationServiceException(Throwable cause) {
        super(cause);
    }

}
