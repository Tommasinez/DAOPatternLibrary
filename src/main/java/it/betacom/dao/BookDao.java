package it.betacom.dao;

import java.util.ArrayList;

import it.betacom.model.Book;

public interface BookDao {
	void createBook(Book book);
	
	ArrayList<Book> retrieveBookList();
	
	Book retrieveBookById(Integer id);
	
	void updateBook(Integer id, Book book);
	
	void deleteBook(Integer id);
}
