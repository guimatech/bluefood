package br.com.softblue.bluefood.application.exception;

@SuppressWarnings("serial")
public class ValidationException extends Exception {

	public ValidationException(String message) {
		super(message);
	}
}
