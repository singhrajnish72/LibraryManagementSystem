package com.rajnish.lms.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class confirmMessage {

	private List<Integer> bookId;
	private String message;
	
	public confirmMessage() {
		// TODO Auto-generated constructor stub
	}
	

	public confirmMessage(List<Integer> bookId, String message) {
		this.bookId = bookId;
		this.message = message;
	}
	

	public List<Integer> getBookId() {
		return bookId;
	}


	public void setBookId(List<Integer> bookId) {
		this.bookId = bookId;
	}

	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
