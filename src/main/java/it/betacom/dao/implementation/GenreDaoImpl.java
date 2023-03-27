package it.betacom.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.betacom.dao.GenreDao;
import it.betacom.factory.ConnectionFactory;
import it.betacom.model.Genre;

public class GenreDaoImpl implements GenreDao {
	@Override
	public void createGenre(Genre genre) {
		try (Connection connection = ConnectionFactory.getConnection()) {

			String query = "INSERT INTO genre (category) VALUES (?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, genre.getCategory());
			statement.executeUpdate();

			System.out.println("Genre created!");
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public ArrayList<Genre> retrieveGenreList() {
		ArrayList<Genre> genreList = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "SELECT * FROM genre";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				Genre genre = new Genre();
				genre.setId(result.getInt("id"));
				genre.setCategory(result.getString("category"));
				genreList.add(genre);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}

		return genreList;
	}

	@Override
	public Genre retrieveGenreById(Integer id) {
		Genre genre = new Genre();
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "SELECT * FROM genre WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();
			if (result.next()) {
				genre.setId(result.getInt("id"));
				genre.setCategory(result.getString("category"));
			}

		} catch (SQLException exception) {
			exception.printStackTrace();
		}

		return genre;
	}

	@Override
	public void updateGenre(Integer id, Genre genre) {
		try (Connection connection = ConnectionFactory.getConnection()) {

			String query = "UPDATE genre SET category=? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, genre.getCategory());
			statement.setInt(2, id);
			statement.executeUpdate();

			System.out.println("Genre updated!");

		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	void resetAutoIncrement() {
		try (Connection connection = ConnectionFactory.getConnection()) {
			Statement statement = connection.createStatement();
			String set = "SET @num := 0;";
			String update = "UPDATE genre SET id = @num := (@num+1)";
			String alter = "ALTER TABLE genre AUTO_INCREMENT =1;";
			statement.addBatch(set);
			statement.addBatch(update);
			statement.addBatch(alter);
			statement.executeBatch();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void deleteGenre(Integer id) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "DELETE FROM genre WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			System.out.println("Genre deleted");

			resetAutoIncrement();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}

	}
}
