package com.training.api.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.training.api.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	Optional<Book> findByTitle(String title);
	
	List<Book> findByAuthor(String author);
	
	// Using named parameter in the query
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
    List<Book> findBooksByTitleContaining(@Param("title") String title);
    
    Optional<Book> findByIsbn(String isbn);

}
