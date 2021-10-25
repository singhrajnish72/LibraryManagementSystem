package com.rajnish.lms.serviceLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajnish.lms.daoLayer.BookRepository;
import com.rajnish.lms.dto.UserDto;
import com.rajnish.lms.dto.confirmMessage;
import com.rajnish.lms.entity.BookStore;
import com.rajnish.lms.entity.BookUser;
import com.rajnish.lms.execption.ResourceNotFoundExecption;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	confirmMessage confirmMessage;
    
	@Autowired
	BookUser bookUser;
	
	String bookStatus = "A";
    
	List<Integer> bookIdList = new ArrayList<>();
	List<String> bookNameList = new ArrayList<>();
	List<BookStore> bookList = new ArrayList<>();
	List<BookUser> bookUsersList = new ArrayList<>();
	
	HashMap<Integer, BookUser> bookMap = new HashMap<>();
	
	public confirmMessage saveBook(BookStore book) {
		
		bookRepository.save(book);
		confirmMessage.setBookId(List.of(book.getBookId()));
		confirmMessage.setMessage("Book added successfully!");
		return confirmMessage;
	}

	public confirmMessage saveAllBooks(List<BookStore> books) {
		bookRepository.saveAll(books);
		
	     
		for(BookStore book : books)
		{
			bookIdList.add(book.getBookId());
		}
		
		confirmMessage.setBookId(bookIdList);
		confirmMessage.setMessage("List of Books added successfully!");
		return confirmMessage;
	}

	public confirmMessage findAllBooks() throws ResourceNotFoundExecption {
		
		bookList = bookRepository.findAll();
		System.out.println("books:"+bookList);
		System.out.println("booksSize"+bookList.size());
		
		if(bookList.size() != 0)
		{
			bookIdList = bookList.stream().map(book -> book.getBookId()).collect(Collectors.toList());
			confirmMessage.setBookId(bookIdList);
		    confirmMessage.setMessage("Successfully Fetched!");		
		}
		else
		{
			confirmMessage.setBookId(null);
		    confirmMessage.setMessage("No record found");
		    
			throw new ResourceNotFoundExecption("No books are available in BookStore");
		}
		
		return confirmMessage;
		
	}

	public confirmMessage findBookById(int id) throws ResourceNotFoundExecption{
		
		BookStore book = bookRepository.findById(id).orElseThrow(() -> new  ResourceNotFoundExecption("Book not found by Id:"+id));
		bookIdList.add(book.getBookId());
		confirmMessage.setBookId(bookIdList);
	    confirmMessage.setMessage("Successfully Fetched Book By id!");
		return confirmMessage;
	}

	public confirmMessage registerBookUser(UserDto userDto) throws ResourceNotFoundExecption {
		

		try {
		bookList = bookRepository.findByName(userDto.getBookName() , bookStatus);
		}
		catch(Exception e)
		{
			System.out.println("Execption Occurs");
		}
		if(bookList.size() != 0)
		{
			
			List<BookStore> availableBooks = bookList.stream()
				       .filter(book -> book.getBookName().equalsIgnoreCase(userDto.getBookName()))
				       .collect(Collectors.toList());
			
			if(availableBooks.size() != 0)
			{
				BookStore availBook = availableBooks.get(0);	
				System.out.println("availBook:"+availBook);
				confirmMessage = updateBookInfo(availBook ,userDto);		
			}
			else
			{

				confirmMessage.setBookId(List.of(0));
			    confirmMessage.setMessage("Book not Available");
			}			
					
		}
		else
		{
			//confirmMessage.setBookId(List.of(0));
		    //confirmMessage.setMessage("No record found");    
			throw new ResourceNotFoundExecption("No books are available in BookStore");
		}
		
		return confirmMessage;		
		
	}
	
	public confirmMessage updateBookInfo(BookStore book ,UserDto userDto) {
		
		book.setBookStatus('B');
		bookUser.setUserId(userDto.getUserId());
		bookUser.setUserName(userDto.getUserName());
		bookUser.setDept(userDto.getDept());
		bookUser.setAddress(userDto.getAddress());
		bookUser.setContactNo(userDto.getContactNo());
		bookUser.setEmail(userDto.getEmail());
		book.setBookUser(bookUser);
		bookRepository.save(book);
		confirmMessage.setBookId(List.of(book.getBookId()));
	    confirmMessage.setMessage("Successfully Updated!");
		
	    return confirmMessage;
	}

	public confirmMessage returnBook(UserDto userDto) {
		
		bookList = bookRepository.findByUserName(userDto.getUserName());
		System.out.println("bookList.size():"+bookList.size());
		System.out.println("bookList:"+bookList);
		if(bookList.size() != 0)
		{
			
			List<BookStore> borrowedBooks = bookList.stream()
			       .filter(book -> book.getBookName().equalsIgnoreCase(userDto.getBookName()) &&
			    		   book.getBookStatus() == 'B')
			       .collect(Collectors.toList());
			if(borrowedBooks.size() > 0)
			{
				     BookStore book = borrowedBooks.get(0);
            		 book.setBookStatus('A');	 
            		 bookRepository.save(book);
         			 confirmMessage.setBookId(List.of(book.getBookId()));
         		     confirmMessage.setMessage("Successfully Updated BookStatus!");
            
             }
			
		}
		else
		{
			confirmMessage.setBookId(List.of(0));
		    confirmMessage.setMessage("No Book is Taken by User!!!");    
		}
		
		return confirmMessage;	
		
	}

	public confirmMessage removeBookById(int bookId) throws ResourceNotFoundExecption {
		confirmMessage confmMessage1 = findBookById(bookId);
		if(confmMessage1 != null)
		{
		bookRepository.deleteById(bookId);
		}
		confmMessage1.setMessage("Book Deleted");
		return confmMessage1;
	}

	public List<BookStore> findBorrowedBooks() throws ResourceNotFoundExecption {
		bookList = bookRepository.findAll();
		List<BookStore> borrowedBookList;
		
		if(bookList.size() > 0)
		{
		borrowedBookList = bookList.stream()
				                           .filter(book -> book.getBookStatus() == 'A')
				                           .collect(Collectors.toList());
		}
		else
		{
	         throw new ResourceNotFoundExecption("No books are available in BookStore");
		}
		return borrowedBookList;
	}


}
