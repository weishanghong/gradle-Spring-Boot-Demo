package com.weiresearch.exception;

public class ServiceLoginExpiredException extends ServiceException {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "login expired";

	public ServiceLoginExpiredException() {
		super(MESSAGE);
	}

	public ServiceLoginExpiredException(Throwable cause) {
		super(MESSAGE, cause);
	}
}
