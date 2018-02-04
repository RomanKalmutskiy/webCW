package models;

import java.io.File;

public class Product {
	private int id;
	private String description;
	private int price;
	private String category;
	private File image;
	
	public Product() {
	}

	public Product(int id, String description, int price, String category, File image) {
		this.id = id;
		this.description = description;
		this.price = price;
		this.category = category;
		this.image = image;
	}
	
	
	public Product(int id, String description, int price, File image) {
		this.id = id;
		this.description = description;
		this.price = price;
		this.image = image;
	}
	
	public Product(int id, String description, int price, String category) {
		this.id = id;
		this.description = description;
		this.price = price;
		this.category = category;
	}
	
	
	public Product(int id,String description, int price) {
		super();
		this.id=id;
		this.description = description;
		this.price = price;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + price;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (price != other.price)
			return false;
		return true;
	}
	
	
	
	
}