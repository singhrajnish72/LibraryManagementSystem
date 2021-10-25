package com.rajnish.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rajnish.lms.dto.UserDto;
import com.rajnish.lms.dto.confirmMessage;
import com.rajnish.lms.entity.BookStore;
import com.rajnish.lms.execption.ResourceNotFoundExecption;
import com.rajnish.lms.serviceLayer.BookService;

@RestController
public class Controller {
	
	@Autowired
	BookService bookService;
	
	@PostMapping("/saveBook")
	public confirmMessage saveBook(@RequestBody BookStore book)
	{	
		return bookService.saveBook(book);
	}
	
	@PostMapping("/saveAllBooks")
	public confirmMessage saveAllBooks(@RequestBody List<BookStore> books)
	{	
		return bookService.saveAllBooks(books);
	}

	@GetMapping("/findAllBooks")
	public confirmMessage findAllBooks() throws ResourceNotFoundExecption
	{	
		return bookService.findAllBooks();
	}
	
	@GetMapping("/findBookById/{id}")
	public confirmMessage findBookById(@PathVariable int id) throws ResourceNotFoundExecption
	{	
		return bookService.findBookById(id);
	}
	
	@PutMapping("/registerBook") 
	public confirmMessage registerBook(@RequestBody UserDto userDto) throws ResourceNotFoundExecption
	{
		return bookService.registerBookUser(userDto);
	}
	
	@PutMapping("/returnBook")
	public confirmMessage returnBook(@RequestBody UserDto userDto)
	{
		return bookService.returnBook(userDto);
	}
	
	@DeleteMapping("/removeBook/{bookId}")
	public confirmMessage removeBookById(@PathVariable int bookId) throws ResourceNotFoundExecption
	{
		return bookService.removeBookById(bookId);
	}
	
	@GetMapping("/getBorrowedBooks")
	public List<BookStore> findBorrowedBooks() throws ResourceNotFoundExecption
	{
		return bookService.findBorrowedBooks();
	}
	
	

	
}
