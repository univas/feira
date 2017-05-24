package br.edu.univas.si.services;

import java.io.Serializable;

public class Response implements Serializable {
	
	private static final long serialVersionUID = 7034469548498703629L;

	private String message;
	
	public Response() {
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return message;
	}

}
