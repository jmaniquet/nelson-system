package org.nelson.system.core.api.exception;

public class NelsonException extends RuntimeException {
	private static final long serialVersionUID = 626548198749486891L;

	public NelsonException() {
	}

	public NelsonException(String message) {
		super(message);
	}

	public NelsonException(Throwable cause) {
		super(cause);
	}

	public NelsonException(String message, Throwable cause) {
		super(message, cause);
	}

	public NelsonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
