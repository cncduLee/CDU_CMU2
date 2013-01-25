package edu.cmucdu.ecommerce.web.custom;

public class IndexProductEntity {

	String name;
	String description;
	long id;

	public IndexProductEntity(String name, String description, long id) {
		super();
		this.name = name;
		this.description = description;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
