package it.betacom.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.betacom.dao.AuthorDao;
import it.betacom.factory.ConnectionFactory;
import it.betacom.model.Author;

public class AuthorDaoImpl implements AuthorDao {
	@Override
	public void createAuthor(Author author) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "INSERT INTO author (name, birth, death, gender, country) VALUES (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, author.getName());
			statement.setInt(2, author.getBirth());
			statement.setInt(3, author.getDeath());
			statement.setString(4, author.getGender());
			statement.setString(5, author.getCountry());
			statement.executeUpdate();
			System.out.println("Author created!");
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public ArrayList<Author> retrieveAuthorList() {
		ArrayList<Author> authorList = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "SELECT * FROM author";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				Author author = new Author();
				author.setId(result.getInt("id"));
				author.setName(result.getString("name"));
				author.setBirth(result.getInt("birth"));
				author.setDeath(result.getInt("death"));
				author.setGender(result.getString("gender"));
				author.setCountry(result.getString("country"));
				authorList.add(author);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return authorList;
	}

	@Override
	public Author retrieveAuthorById(Integer id) {
		Author author = new Author();
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "SELECT * FROM author WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				author.setId(result.getInt("id"));
				author.setName(result.getString("name"));
				author.setBirth(result.getInt("birth"));
				author.setDeath(result.getInt("death"));
				author.setGender(result.getString("gender"));
				author.setCountry(result.getString("country"));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return author;
	}

	@Override
	public void updateAuthor(Integer id, Author author) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "UPDATE author SET name=?, birth=?, death=?, gender=?, country=? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, author.getName());
			statement.setInt(2, author.getBirth());
			statement.setInt(3, author.getDeath());
			statement.setString(4, author.getGender());
			statement.setString(5, author.getCountry());
			statement.setInt(6, id);
			statement.executeUpdate();
			System.out.println("Author updated!");
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	void resetAutoIncrement() {
		try (Connection connection = ConnectionFactory.getConnection()) {
			Statement statement = connection.createStatement();
			String set = "SET @num := 0;";
			String update = "UPDATE author SET id = @num := (@num+1)";
			String alter = "ALTER TABLE author AUTO_INCREMENT =1;";
			statement.addBatch(set);
			statement.addBatch(update);
			statement.addBatch(alter);
			statement.executeBatch();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	
	@Override
	public void deleteAuthor(Integer id) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "DELETE FROM author WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			System.out.println("Author deleted");
			resetAutoIncrement();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}
