package it.betacom.model;

public class Book {
	private Integer id;
	private String title;
	private Author author;
	private Genre genre;
	private Publisher publisher;
	private Integer pages;
	private Integer publicationYear;
	
	public Book() {
	}

	@Override
	public String toString() {
		return "{id:" + id + ", title:" + title + ", author:" + author.getId() + ", genre:" + genre.getId() + ", publisher:"
				+ publisher.getId() + ", pages:" + pages + ", publicationYear:" + publicationYear + "}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}
}
