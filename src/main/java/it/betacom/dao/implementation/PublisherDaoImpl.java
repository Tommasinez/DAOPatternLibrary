package it.betacom.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.betacom.dao.PublisherDao;
import it.betacom.factory.ConnectionFactory;
import it.betacom.model.Publisher;

public class PublisherDaoImpl implements PublisherDao {
	@Override
	public void createPublisher(Publisher publisher) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "INSERT INTO publisher (name, city) VALUES (?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, publisher.getName());
			statement.setString(2, publisher.getCity());
			statement.executeUpdate();
			System.out.println("Publisher created!");
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public ArrayList<Publisher> retrievePublisherList() {
		ArrayList<Publisher> publisherList = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "SELECT * FROM publisher";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				Publisher publisher = new Publisher();
				publisher.setId(result.getInt("id"));
				publisher.setName(result.getString("name"));
				publisher.setCity(result.getString("city"));
				publisherList.add(publisher);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return publisherList;
	}

	@Override
	public Publisher retrievePublisherById(Integer id) {
		Publisher publisher = new Publisher();
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "SELECT * FROM publisher WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				publisher.setId(result.getInt("id"));
				publisher.setName(result.getString("name"));
				publisher.setCity(result.getString("city"));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return publisher;
	}

	@Override
	public void updatePublisher(Integer id, Publisher publisher) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "UPDATE publisher SET name=?, city=? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, publisher.getName());
			statement.setString(2, publisher.getCity());
			statement.setInt(3, id);
			statement.executeUpdate();
			System.out.println("Publisher updated!");
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	void resetAutoIncrement() {
		try (Connection connection = ConnectionFactory.getConnection()) {
			Statement statement = connection.createStatement();
			String set = "SET @num := 0;";
			String update = "UPDATE publisher SET id = @num := (@num+1)";
			String alter = "ALTER TABLE publisher AUTO_INCREMENT =1;";
			statement.addBatch(set);
			statement.addBatch(update);
			statement.addBatch(alter);
			statement.executeBatch();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void deletePublisher(Integer id) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "DELETE FROM publisher WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			System.out.println("Publisher deleted");
			resetAutoIncrement();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}
