package com.training.api.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.api.library.entity.Book;
import com.training.api.library.service.BookService;

@RestController
@RequestMapping("/api")
public class LibraryController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return bookService.getAllBooks();
	}
	
	@GetMapping("/books/{bookId}")
	public Book getBookById(@PathVariable int bookId) {
		return bookService.getBookById(bookId);
	}
	
	@PostMapping("/books")
	public Book createBook(@RequestBody Book book) {
	    Book newBook = bookService.saveNewBook(book);
	    return newBook;
	}
	
	@PutMapping("/books")
	public Book updateBook(@RequestBody Book book) {
	    return bookService.updateBook(book);
	}
	
	@DeleteMapping("/books/{bookId}")
	public String removeBook(@PathVariable int bookId) {
		Book book = bookService.getBookById(bookId);
		bookService.deleteBook(book);
		
		return "Book with title " + book.getTitle() + " has been deleted";
	}
	
}
