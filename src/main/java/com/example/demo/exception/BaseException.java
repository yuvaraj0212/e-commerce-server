package com.example.demo.exception;


public class BaseException extends Exception{
	
	private String errorCode;
	private String errorMessage;
	public BaseException() {
		super();
	}
	
	public BaseException(final String errorCode,final String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public BaseException(final Throwable throwable) {
		super(throwable);
		this.errorCode = "0";
		this.errorMessage = throwable.getLocalizedMessage();
	}
	
	public BaseException(final String errorCode,final Throwable throwable) {
		super(throwable);
		this.errorCode = errorCode;
		this.errorMessage = throwable.getLocalizedMessage();
	}
	
	public BaseException(final String errorCode,final String errorMessage,final Throwable throwable) {
		super(errorMessage,throwable);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}	
	
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

