package it.betacom.dao;

import java.util.ArrayList;

import it.betacom.model.Author;

public interface AuthorDao {

	void createAuthor(Author author);
	
	ArrayList<Author> retrieveAuthorList();
	
	Author retrieveAuthorById(Integer id);
	
	void updateAuthor(Integer id, Author author);
	
	void deleteAuthor(Integer id);
	
}
