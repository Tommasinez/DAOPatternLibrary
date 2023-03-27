package it.betacom.dao;

import java.util.ArrayList;

import it.betacom.model.Publisher;

public interface PublisherDao {
	void createPublisher(Publisher publisher);
	
	ArrayList<Publisher> retrievePublisherList();
	
	Publisher retrievePublisherById(Integer id);
	
	void updatePublisher(Integer id, Publisher publisher);
	
	void deletePublisher(Integer id);
}
