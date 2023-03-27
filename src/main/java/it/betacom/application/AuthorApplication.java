package it.betacom.application;

import it.betacom.dao.implementation.AuthorDaoImpl;
import it.betacom.model.Author;

public class AuthorApplication {
	public static void main(String[] args) {
		AuthorDaoImpl authorDao = new AuthorDaoImpl();
		
		System.out.println(authorDao.retrieveAuthorList());
		
		authorDao.createAuthor(new Author("Nevo"));
		
		System.out.println(authorDao.retrieveAuthorList());

		Author myAuthor = authorDao.retrieveAuthorById(1);
		
		System.out.println(myAuthor);
		
		authorDao.updateAuthor(myAuthor.getId(), new Author("Manzoni Update"));
		
		System.out.println(authorDao.retrieveAuthorList());

		authorDao.updateAuthor(myAuthor.getId(), new Author("Manzoni"));
		
		authorDao.deleteAuthor(10);

		System.out.println(authorDao.retrieveAuthorList());
	}
}
