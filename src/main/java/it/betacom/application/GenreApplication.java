package it.betacom.application;

import it.betacom.dao.implementation.GenreDaoImpl;
import it.betacom.model.Genre;

public class GenreApplication {
	public static void main(String[] args) {	
		GenreDaoImpl genreDao = new GenreDaoImpl();
		
		System.out.println(genreDao.retrieveGenreList());
				
		genreDao.createGenre(new Genre("Fumetto"));
		
		System.out.println(genreDao.retrieveGenreList());	
		
		Genre myGenre = genreDao.retrieveGenreById(4);
	
		System.out.println(myGenre);
		
		genreDao.updateGenre(myGenre.getId(), new Genre("Romanzi Update"));
			
		System.out.println(genreDao.retrieveGenreList());

		genreDao.updateGenre(myGenre.getId(), new Genre("Romanzi"));
		
		genreDao.deleteGenre(7);

		System.out.println(genreDao.retrieveGenreList());
	}
}
