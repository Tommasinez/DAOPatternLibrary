package it.betacom.model;

public class Author {
	private Integer id;
	private String name;
	private Integer birth;
	private Integer death;
	private String gender;
	private String country;

	public Author() {
	}

	public Author(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", name:" + name + ", birth:" + birth + ", death:" + death + ", gender:" + gender
				+ ", country:" + country + "}";
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

	public Integer getBirth() {
		return birth;
	}

	public void setBirth(Integer birth) {
		this.birth = birth;
	}

	public Integer getDeath() {
		return death;
	}

	public void setDeath(Integer death) {
		this.death = death;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
