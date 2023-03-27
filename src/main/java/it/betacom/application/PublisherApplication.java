package it.betacom.application;

import it.betacom.dao.implementation.PublisherDaoImpl;
import it.betacom.model.Publisher;

public class PublisherApplication {
	public static void main(String[] args) {
		PublisherDaoImpl publisherDao = new PublisherDaoImpl();
		
		System.out.println(publisherDao.retrievePublisherList());
		
		publisherDao.createPublisher(new Publisher("Laterza"));
		publisherDao.createPublisher(new Publisher("De Agostini"));
		publisherDao.createPublisher(new Publisher("Bompiani"));
		publisherDao.createPublisher(new Publisher("Adelphi"));
		
		System.out.println(publisherDao.retrievePublisherList());

		Publisher myPublisher = publisherDao.retrievePublisherById(2);
		
		System.out.println(myPublisher);
		
		publisherDao.updatePublisher(myPublisher.getId(), new Publisher("Mondadori Update"));
		
		System.out.println(publisherDao.retrievePublisherList());
		
		publisherDao.updatePublisher(myPublisher.getId(), new Publisher("Mondadori", "Milano"));

		publisherDao.deletePublisher(3);
		publisherDao.deletePublisher(3);
		publisherDao.deletePublisher(3);
		publisherDao.deletePublisher(3);
		
		System.out.println(publisherDao.retrievePublisherList());
	}
}
