package br.com.api.exception;

import java.time.LocalDateTime;

public class RestResponseMessageException extends RuntimeException {

	private static final long serialVersionUID = 1908123261896001883L;

	private int status;
	private String message;
	private LocalDateTime date;


	public RestResponseMessageException(final RestMessageCode apiException) {
		this.status = apiException.getCodeStatus();
		this.message = apiException.getMessage();
		this.date = LocalDateTime.now();
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the date
	 */
	public LocalDateTime getDate() {
		return date;
	}

}
