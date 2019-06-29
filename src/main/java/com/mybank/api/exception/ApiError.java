package com.mybank.api.exception;

public class ApiError {
	private int statusCode;
	private String message;

	public ApiError() {}

	public ApiError(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return message;
	}
}
