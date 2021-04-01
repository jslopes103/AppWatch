package model;

import java.util.ArrayList;
import java.util.List;

//content model
public class Content {
	
	//list of all contents
	public static List<Content> list = new ArrayList<Content>();
	
	public static int idContentBought = -1;
	public static String nameContentBought = "";
	
	int idContent;
	String name;
	String description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
