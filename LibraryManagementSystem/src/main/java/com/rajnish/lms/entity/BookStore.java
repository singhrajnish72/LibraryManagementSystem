package com.rajnish.lms.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BookStore")
public class BookStore {

	@Id
	private int bookId;
	private String bookName;
	private List<Author> author;
	private double bookPrice;
	private char bookStatus;
	private BookUser bookUser;

	public BookUser getBookUser() {
		return bookUser;
	}


	public void setBookUser(BookUser bookUser) {
		this.bookUser = bookUser;
	}


	public BookStore() {
		
	}


	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public List<Author> getAuthor() {
		return author;
	}

	public void setAuthor(List<Author> author) {
		this.author = author;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public char getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(char bookStatus) {
		this.bookStatus = bookStatus;
	}


	@Override
	public String toString() {
		return "BookStore [bookId=" + bookId + ", bookName=" + bookName + ", author=" + author + ", bookPrice="
				+ bookPrice + ", bookStatus=" + bookStatus + ", bookUser=" + bookUser + "]";
	}
	
	
}
