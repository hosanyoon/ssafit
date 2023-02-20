package com.ssafy.ssafit.exception;

public class UserIdDuplicationExcepion extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserIdDuplicationExcepion(String msg) {
		super(msg);
	}
}
