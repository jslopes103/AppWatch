package controller;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Content;

public class MainController {

	@FXML
	AnchorPane aP;

	@FXML
	AnchorPane anchorPane1;

	@FXML
	AnchorPane anchorPane2;

	@FXML
	AnchorPane anchorPane3;

	@FXML
	AnchorPane anchorPane4;

	@FXML
	AnchorPane anchorPane5;

	@FXML
	AnchorPane anchorPane6;

	@FXML
	JFXButton b01;

	@FXML
	JFXButton b02;

	@FXML
	JFXButton b03;

	@FXML
	JFXButton b04;

	@FXML
	JFXButton b05;

	@FXML
	JFXButton b06;

	@FXML
	JFXButton b07;

	@FXML
	ImageView img01;

	@FXML
	ImageView img02;

	@FXML
	ImageView img03;

	@FXML
	ImageView img04;

	@FXML
	ImageView img05;

	@FXML
	ImageView img06;
	
	@FXML
	Label label01;
	
	@FXML
	Label label02;
	
	@FXML
	Label label03;
	
	@FXML
	Label label04;
	
	@FXML
	Label label05;
	
	@FXML
	Label label06;
	
	@FXML
	JFXButton bt01;
	
	@FXML
	JFXButton bt02;
	
	@FXML
	JFXButton bt03;
	
	@FXML
	JFXButton bt04;
	
	@FXML
	JFXButton bt05;
	
	@FXML
	JFXButton bt06;
	
	@FXML
	JFXButton btmore;
	
	@FXML
	JFXButton btless;
	
	@FXML
	JFXButton btMovie;
	
	@FXML
	JFXButton btSerie;

	int category = 0;
	int categoryCount = 0;
	int categoryChanged = 0;
	
	String originalStyle = "-fx-background-color: #fffffc; -fx-background-radius: 10;";
	String clickedStyle = "-fx-background-color: #ffb5a7; -fx-background-radius: 10;";

	@FXML
	protected void initialize() {

		categoryChanged = 1;
		showCategory(1, 0);
		b01.setStyle(clickedStyle);
		btMovie.setStyle(clickedStyle);

	}
	
	private void resetStyle()
	{
		b01.setStyle(originalStyle);
		b02.setStyle(originalStyle);
		b03.setStyle(originalStyle);
		b04.setStyle(originalStyle);
		b05.setStyle(originalStyle);
		b06.setStyle(originalStyle);
		b07.setStyle(originalStyle);
		
		anchorPane1.setVisible(true);
		anchorPane2.setVisible(true);
		anchorPane3.setVisible(true);
		anchorPane4.setVisible(true);
		anchorPane5.setVisible(true);
		anchorPane6.setVisible(true);
	}

	private void showCategory(int idCategory, int showNext) {
		
		category = 0;
		
		for (int i = showNext; i < Content.list.size(); i++) {
			
			Content content = Content.list.get(i);

			if (content.getIdCategory() == idCategory) {
				category++;
				if (category == 1) {
					this.setElements(img01, content.getImage(), label01, content.getName(), bt01, content.getPrice());
				} else if (category == 2) {
					this.setElements(img02, content.getImage(), label02, content.getName(), bt02, content.getPrice());
				} else if (category == 3) {
					this.setElements(img03, content.getImage(), label03, content.getName(), bt03, content.getPrice());
				} else if (category == 4) {
					this.setElements(img04, content.getImage(), label04, content.getName(), bt04, content.getPrice());
				} else if (category == 5) {
					this.setElements(img05, content.getImage(), label05, content.getName(), bt05, content.getPrice());
				} else if (category == 6) {
					this.setElements(img06, content.getImage(), label06, content.getName(), bt06, content.getPrice());
				}

			}
		}
		
		if(category == 0)
		{
			anchorPane1.setVisible(false);
			anchorPane2.setVisible(false);
			anchorPane3.setVisible(false);
			anchorPane4.setVisible(false);
			anchorPane5.setVisible(false);
			anchorPane6.setVisible(false);
			return;
		}

		if (category < 7) {

			btmore.setVisible(false);
			btless.setVisible(false);
			
			if (category == 1) {
				anchorPane2.setVisible(false);
				anchorPane3.setVisible(false);
				anchorPane4.setVisible(false);
				anchorPane5.setVisible(false);
				anchorPane6.setVisible(false);
			}
			else if (category == 2) {
				anchorPane3.setVisible(false);
				anchorPane4.setVisible(false);
				anchorPane5.setVisible(false);
				anchorPane6.setVisible(false);
			}
			else if (category == 3) {
				anchorPane4.setVisible(false);
				anchorPane5.setVisible(false);
				anchorPane6.setVisible(false);
			}
			else if (category == 4) {
				anchorPane5.setVisible(false);
				anchorPane6.setVisible(false);
			}
			else if (category == 5) {
				anchorPane6.setVisible(false);
			}
		}
		else if(category > 6)
		{
			btmore.setVisible(true);
			if(showNext > 0)
			{
			   btless.setVisible(true);
			}
			else
			{
				btless.setVisible(false);
			}
		}
	}

	
	private void setElements(ImageView view, String url, Label label, String text, JFXButton bt, double bText) {
		if (url == null) {
			view.setImage(null);
			label.setText(null);
			bt.setText(null);
		} else {
			view.setImage(new Image(url));
			label.setText(text);
			bt.setText(String.valueOf(bText)+"€");
		}
	}
	
	@FXML
	private void handleMoreAction(ActionEvent event)
	{
		categoryCount++;
		showCategory(categoryChanged, categoryCount);
		btless.setVisible(true);
	}
	
	@FXML
	private void handleLessAction(ActionEvent event)
	{
		categoryCount--;
		showCategory(categoryChanged, categoryCount);
	}
	
	@FXML
	private void handleChangeAction(ActionEvent event)
	{
		categoryCount = 0;
		Node node = (Node) event.getSource() ;
	    String data = (String) node.getUserData();
	    int value = Integer.parseInt(data);
	    
	    resetStyle();
	    
		showCategory(value, 0);
		
		categoryChanged = value;
		
		switch(value)
		{
		case 1:
			b01.setStyle(clickedStyle);
			break;
		case 2:
			b02.setStyle(clickedStyle);
			break;
		case 3:
			b03.setStyle(clickedStyle);
			break;
		case 4:
			b04.setStyle(clickedStyle);
			break;
		case 5:
			b05.setStyle(clickedStyle);
			break;
		case 6:
			b06.setStyle(clickedStyle);
			break;
		case 7:
			b07.setStyle(clickedStyle);
			break;
		}
	}
}
