package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

	@FXML
	JFXButton btTotal;

	int category = 0;
	int categoryCount = 0;
	int categoryChanged = 0;
	int categoryType = 0;
	
	boolean bClicked = false;
	int bValue = 0;

	String originalStyle = "-fx-background-color: #fffffc; -fx-background-radius: 10;";
	String clickedStyle = "-fx-background-color: #ffb5a7; -fx-background-radius: 10;";

	List<String> description = new ArrayList<String>();
	List<Integer> idContent = new ArrayList<Integer>();
	int idContentBought = -1;
	
	double totalToPay = 0;
	
	boolean b1Clicked = false;
	boolean b2Clicked = false;
	boolean b3Clicked = false;
	boolean b4Clicked = false;
	boolean b5Clicked = false;
	boolean b6Clicked = false;

	@FXML
	protected void initialize() {
		
		btTotal.setDisableVisualFocus(true);

		categoryChanged = 1;
		categoryType = 0;
		showCategory(categoryChanged, 0, categoryType);
		b01.setStyle(clickedStyle);
		btMovie.setStyle(clickedStyle);
		btTotal.setDisable(true);

		img01.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				new AlertController(AlertType.INFORMATION, "Description", description.get(0));
			}
		});
		img02.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				new AlertController(AlertType.INFORMATION, "Description", description.get(1));
			}
		});
		img03.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				new AlertController(AlertType.INFORMATION, "Description", description.get(2));
			}
		});
		img04.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				new AlertController(AlertType.INFORMATION, "Description", description.get(3));
			}
		});
		img05.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				new AlertController(AlertType.INFORMATION, "Description", description.get(4));
			}
		});
		img06.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				new AlertController(AlertType.INFORMATION, "Description", description.get(5));
			}
		});

	}

	private void resetStyle() {
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

	private void showCategory(int idCategory, int showNext, int type) {

		description.clear();
		idContent.clear();

		category = 0;

		for (int i = showNext; i < Content.list.size(); i++) {

			Content content = Content.list.get(i);

			if (content.getIdCategory() == idCategory && content.getType() == type) {
				category++;
				if (category == 1) {
					description.add(content.getDescription());
					idContent.add(i);
					this.setElements(img01, content.getImage(), label01, content.getName(), bt01, content.getPrice());
				} else if (category == 2) {
					description.add(content.getDescription());
					idContent.add(i);
					this.setElements(img02, content.getImage(), label02, content.getName(), bt02, content.getPrice());
				} else if (category == 3) {
					description.add(content.getDescription());
					idContent.add(i);
					this.setElements(img03, content.getImage(), label03, content.getName(), bt03, content.getPrice());
				} else if (category == 4) {
					description.add(content.getDescription());
					idContent.add(i);
					this.setElements(img04, content.getImage(), label04, content.getName(), bt04, content.getPrice());
				} else if (category == 5) {
					description.add(content.getDescription());
					idContent.add(i);
					this.setElements(img05, content.getImage(), label05, content.getName(), bt05, content.getPrice());
				} else if (category == 6) {
					description.add(content.getDescription());
					idContent.add(i);
					this.setElements(img06, content.getImage(), label06, content.getName(), bt06, content.getPrice());
				}

			}
		}

		if (category == 0) {
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
			} else if (category == 2) {
				anchorPane3.setVisible(false);
				anchorPane4.setVisible(false);
				anchorPane5.setVisible(false);
				anchorPane6.setVisible(false);
			} else if (category == 3) {
				anchorPane4.setVisible(false);
				anchorPane5.setVisible(false);
				anchorPane6.setVisible(false);
			} else if (category == 4) {
				anchorPane5.setVisible(false);
				anchorPane6.setVisible(false);
			} else if (category == 5) {
				anchorPane6.setVisible(false);
			}
		} else if (category > 6) {
			btmore.setVisible(true);
			if (showNext > 0) {
				btless.setVisible(true);
			} else {
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
			bt.setText(String.valueOf(bText) + "€");
		}
	}

	@FXML
	private void handleMoreAction(ActionEvent event) {
		categoryCount++;
		showCategory(categoryChanged, categoryCount, 0);
		btless.setVisible(true);
	}

	@FXML
	private void handleLessAction(ActionEvent event) {
		categoryCount--;
		showCategory(categoryChanged, categoryCount, categoryType);
	}

	@FXML
	private void handleSerieAction(ActionEvent event) {
		resetStyle();

		categoryChanged = 1;
		categoryType = 1;

		showCategory(categoryChanged, 0, categoryType);
		b01.setStyle(clickedStyle);
		btMovie.setStyle(originalStyle);
		btSerie.setStyle(clickedStyle);
	}

	@FXML
	private void handleMovieAction(ActionEvent event) {
		resetStyle();

		categoryChanged = 1;
		categoryType = 0;

		showCategory(categoryChanged, 0, categoryType);
		b01.setStyle(clickedStyle);
		btMovie.setStyle(clickedStyle);
		btSerie.setStyle(originalStyle);
	}

	@FXML
	private void handleChangeAction(ActionEvent event) {
		
		if(idContentBought != -1)
		{
			new AlertController(AlertType.INFORMATION, "Payment", "Unselect or finish the transaction before change screen");
			return;
		}
		
		categoryCount = 0;
		Node node = (Node) event.getSource();
		String data = (String) node.getUserData();
		int value = Integer.parseInt(data);

		resetStyle();

		showCategory(value, 0, categoryType);

		categoryChanged = value;

		switch (value) {
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

	@FXML
	private void handleCartAction(ActionEvent event) {
		
		btTotal.setDisable(false);
		
		Node node = (Node) event.getSource();
		String data = (String) node.getUserData();
		int value = Integer.parseInt(data);
		
		if(!bClicked && bValue != value)
		{
		
		String style = "-fx-background-color: #52b788; -fx-background-radius: 50;";
		
		bValue = value;

		switch (value) {
		case 1:
			idContentBought = Content.list.get(idContent.get(0)).getIdContent();
			totalToPay+=Content.list.get(idContent.get(0)).getPrice();
			bt01.setStyle(style);
			break;
		case 2:
			idContentBought = Content.list.get(idContent.get(1)).getIdContent();
			totalToPay+=Content.list.get(idContent.get(1)).getPrice();
			bt02.setStyle(style);
			break;
		case 3:
			idContentBought= Content.list.get(idContent.get(2)).getIdContent();
			totalToPay+=Content.list.get(idContent.get(2)).getPrice();
			bt03.setStyle(style);
			break;
		case 4:
			idContentBought = Content.list.get(idContent.get(3)).getIdContent();
			totalToPay+=Content.list.get(idContent.get(3)).getPrice();
			bt04.setStyle(style);
			break;
		case 5:
			idContentBought = Content.list.get(idContent.get(4)).getIdContent();
			totalToPay+=Content.list.get(idContent.get(4)).getPrice();
			bt05.setStyle(style);
			break;
		case 6:
			idContentBought = Content.list.get(idContent.get(5)).getIdContent();
			totalToPay+=Content.list.get(idContent.get(5)).getPrice();
			bt06.setStyle(style);
			break;
		}
		
		DecimalFormat dec = new DecimalFormat("#.##");
		
		btTotal.setText(dec.format(totalToPay)+"€");
		
		bClicked = true;
		}
		else
		{
			if(bValue != value)
			{
			new AlertController(AlertType.INFORMATION, "Max limite", "You can only rent one movie or serie per transaction!");
			}
			else
			{
				btTotal.setDisable(true);
				btTotal.setText("0.00€");
				
				String style = "-fx-background-color: #fffffc; -fx-background-radius: 50;";
			
				switch (value) {
				case 1:
					bt01.setStyle(style);
					break;
				case 2:
					bt02.setStyle(style);
					break;
				case 3:
					bt03.setStyle(style);
					break;
				case 4:
					bt04.setStyle(style);
					break;
				case 5:
					bt05.setStyle(style);
					break;
				case 6:
					bt06.setStyle(style);
					break;
				}
				
				idContentBought = -1;
				totalToPay = 0;
				bValue = 0;
				bClicked = false;
			}
		}

	}
	
	@FXML
	private void handlePayAction(ActionEvent event) {
		if(idContentBought != -1)
		{
			new AlertController(AlertType.CONFIRMATION, "Would you like to pay now?", "Please select an option below and remember, the price is for only 7 days. If you delay, you will be charged for the extra days!");
		}
	}
}
