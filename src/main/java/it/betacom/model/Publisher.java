package it.betacom.model;

public class Publisher {
	private Integer id;
	private String name;
	private String city;

	public Publisher() {
	}

	public Publisher(String name) {
		this.name = name;
	}

	public Publisher(String name, String city) {
		this.name = name;
		this.city = city;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", name:" + name + ", city:" + city + "}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
