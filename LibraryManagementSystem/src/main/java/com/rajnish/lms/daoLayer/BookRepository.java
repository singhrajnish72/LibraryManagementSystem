package com.rajnish.lms.daoLayer;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.rajnish.lms.entity.BookStore;

public interface BookRepository extends MongoRepository<BookStore, Integer> {

	
	@Query("{bookName : ?0 , bookStatus:?1}")
	List<BookStore> findByName(String bookName , String bookStatus);

	@Query("{'bookUser.userName' : ?0}")
	List<BookStore> findByUserName(String userName);

}
