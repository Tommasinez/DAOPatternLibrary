package it.betacom.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.betacom.dao.BookDao;
import it.betacom.factory.ConnectionFactory;
import it.betacom.model.Book;

public class BookDaoImpl implements BookDao {

	@Override
	public void createBook(Book book) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "INSERT INTO book (title, author, genre, publisher, pages, publication_year) VALUES (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, book.getTitle());
			statement.setInt(2, book.getAuthor().getId());
			statement.setInt(3, book.getGenre().getId());
			statement.setInt(4, book.getPublisher().getId());
			statement.setInt(5, book.getPages());
			statement.setInt(6, book.getPublicationYear());
			statement.executeUpdate();
			System.out.println("Book created!");
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public ArrayList<Book> retrieveBookList() {
		ArrayList<Book> bookList = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "SELECT * FROM book";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				Book book = new Book();
				book.setId(result.getInt("id"));
				book.setTitle(result.getString("title"));
				book.getAuthor().setId(result.getInt("author"));
				book.getGenre().setId(result.getInt("genre"));
				book.getPublisher().setId(result.getInt("publisher"));
				book.setPages(result.getInt("pages"));
				book.setPublicationYear(result.getInt("publication_year"));
				bookList.add(book);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return bookList;
	}

	@Override
	public Book retrieveBookById(Integer id) {
		Book book = new Book();
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "SELECT * FROM book WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				book.setId(result.getInt("id"));
				book.setTitle(result.getString("title"));
				book.getAuthor().setId(result.getInt("author"));
				book.getGenre().setId(result.getInt("genre"));
				book.getPublisher().setId(result.getInt("publisher"));
				book.setPages(result.getInt("pages"));
				book.setPublicationYear(result.getInt("publication_year"));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return book;
	}

	@Override
	public void updateBook(Integer id, Book book) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "UPDATE book SET title=?, author=?, genre=?, publisher=?, pages=?, publication_year=? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, book.getTitle());
			statement.setInt(2, book.getAuthor().getId());
			statement.setInt(3, book.getGenre().getId());
			statement.setInt(4, book.getPublisher().getId());
			statement.setInt(5, book.getPages());
			statement.setInt(6, book.getPublicationYear());
			statement.setInt(7, id);
			statement.executeUpdate();
			System.out.println("Book updated!");
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	void resetAutoIncrement() {
		try (Connection connection = ConnectionFactory.getConnection()) {
			Statement statement = connection.createStatement();
			String set = "SET @num := 0;";
			String update = "UPDATE book SET id = @num := (@num+1)";
			String alter = "ALTER TABLE book AUTO_INCREMENT =1;";
			statement.addBatch(set);
			statement.addBatch(update);
			statement.addBatch(alter);
			statement.executeBatch();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void deleteBook(Integer id) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "DELETE FROM book WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			System.out.println("Book deleted");
			resetAutoIncrement();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}
