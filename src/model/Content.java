package model;

import java.util.ArrayList;
import java.util.List;

public class Content {
	
	public static List<Content> list = new ArrayList<Content>();
	
	int idContent;
	String name;
	String image;
	int idCategory;
	int type;
	double price;
	
	public int getIdContent() {
		return idContent;
	}
	public void setIdContent(int idContent) {
		this.idContent = idContent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
