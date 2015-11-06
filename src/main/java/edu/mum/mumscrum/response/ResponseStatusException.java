package edu.mum.mumscrum.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Record Found!!")
public class ResponseStatusException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String exceptionMessage = null;
	
	public ResponseStatusException(){}
	
	public ResponseStatusException(String error){
		this.exceptionMessage = error;
	}

	public String getFullMessage() {
		return exceptionMessage == null ? super.getMessage(): exceptionMessage;
	}
}
