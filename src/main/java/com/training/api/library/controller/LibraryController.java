package com.training.api.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
