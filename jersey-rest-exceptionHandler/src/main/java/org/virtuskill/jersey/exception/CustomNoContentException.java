package org.virtuskill.jersey.exception;

public class CustomNoContentException extends RuntimeException {

	private static final long serialVersionUID = -2416048297717811431L;

	public CustomNoContentException(String message) {
		super(message);
	}
}
