package com.training.api.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.api.library.entity.Book;
import com.training.api.library.repository.BookRepository;

@Service
public class BookService {

	private BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Book getBookById(int id) {

		if (id <= 0) {
			throw new IllegalArgumentException("id cannot be less than or equal Zero");
		}

		return bookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Book with ID: %d not found", id)));
	}

	public Book getBookByTitle(String title) {

		checkIfNull(title, "Title cannot be null or empty");

		return bookRepository.findByTitle(title)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Book with Title: %s not found", title)));

	}

	public List<Book> getBookByAuthor(String author) {
		checkIfNull(author, "Author cannot be null or empty");

		List<Book> books = bookRepository.findByAuthor(author);

		if (books.isEmpty()) {
			throw new IllegalArgumentException(String.format("Book with Author: %s not found", author)); // Place holder
		}

		return books;
	}
	
	public List<Book> getBooksByTitleContaining(String title){
		checkIfNull(title, "Title cannot be null or empty");
		
		List<Book> books = bookRepository.findBooksByTitleContaining(title);
		if (books.isEmpty()) {
			throw new IllegalArgumentException("No matches for the word:" + title); // Place holder
		}
		
		return books;
	}

	public Book updateBook(Book book) {
		checkIfNull(book, "Cannot update null book");

		return bookRepository.save(book);
	}

	public Book saveNewBook(Book book) {
		checkIfNull(book, "Cannot save null book");
		// no need to set id to 0

		return bookRepository.save(book);
	}

	public void deleteBook(Book book) {

		checkIfNull(book, "Cannot delete null book");

		bookRepository.delete(book);

	}

	// convenience methods
	public <T> void checkIfNull(T t, String message) {
	    if (t == null) {
	        throw new IllegalArgumentException(message);
	    }

	    if (t instanceof String && ((String) t).isEmpty()) {
	        throw new IllegalArgumentException(message);
	    }
	}

}
