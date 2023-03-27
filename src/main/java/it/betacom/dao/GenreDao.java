package it.betacom.dao;

import java.util.ArrayList;

import it.betacom.model.Genre;

public interface GenreDao {
	void createGenre(Genre genre);
	
	ArrayList<Genre> retrieveGenreList();
	
	Genre retrieveGenreById(Integer id);
	
	void updateGenre(Integer id, Genre genre);
	
	void deleteGenre(Integer id);
}
