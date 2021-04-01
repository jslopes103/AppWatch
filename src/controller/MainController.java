package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Content;

//maincontroller
public class MainController {

	//anchor pane id
	@FXML
	AnchorPane aP;

	//id of bordersPane
	@FXML
	BorderPane borderPane1;

	@FXML
	BorderPane borderPane2;

	@FXML
	BorderPane borderPane3;

	@FXML
	BorderPane borderPane4;

	@FXML
	BorderPane borderPane5;

	@FXML
	BorderPane borderPane6;

	//buttons ids
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

	//images ids
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

	//labels ids
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

	//buttons ids
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

	//button more and less
	@FXML
	JFXButton btmore;

	@FXML
	JFXButton btless;

	//type
	@FXML
	JFXButton btMovie;

	@FXML
	JFXButton btSerie;

	//total
	@FXML
	JFXButton btTotal;

	//watch now
	@FXML
	JFXButton btWatchNow;

	//if change screen or if there is more content
	//variables do control all the screen's changes
	int category = 0;
	int categoryCount = 0;
	int categoryChanged = 0;
	int categoryType = 0;

	boolean bClicked = false;
	int bValue = 0;

	//set style on buttons clicked
	String originalStyle = "-fx-background-color: #fffffc; -fx-background-radius: 10;";
	String clickedStyle = "-fx-background-color: #ffb5a7; -fx-background-radius: 10;";

	//list of description when clicks on the image
	List<String> description = new ArrayList<String>();
	List<Integer> idContent = new ArrayList<Integer>();
	
	//id of the content bought
	int idContentBought = -1;

	//total to pay
	double totalToPay = 0;

	//check buttons clicked
	boolean b1Clicked = false;
	boolean b2Clicked = false;
	boolean b3Clicked = false;
	boolean b4Clicked = false;
	boolean b5Clicked = false;
	boolean b6Clicked = false;

	//init a new thread (one instance)
	public static boolean startOnce = false;

	//update if the payment is true
	public class CheckPayment extends Thread {

		public void run() {
			while (true) {
				try {
					Thread.sleep(1L);

					if (Public.bought == true) {
						Platform.runLater(() -> {
							initialize();
						});

						Public.bought = false;
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@FXML
	protected void initialize() {

		//check if the thread is already running
		if (startOnce == false) {
			startOnce = true;
			new CheckPayment().start();
		}

		//reset clicks and values
		bClicked = false;
		bValue = 0;
		totalToPay = 0;

		//add originals styles
		bt01.setStyle(originalStyle);
		bt02.setStyle(originalStyle);
		bt03.setStyle(originalStyle);
		bt04.setStyle(originalStyle);
		bt05.setStyle(originalStyle);
		bt06.setStyle(originalStyle);

		//set text 0.00
		btTotal.setText("0.00€");

		//disable visual focus (box)
		btTotal.setDisableVisualFocus(true);
		btWatchNow.setDisableVisualFocus(true);

		//reset the content and start with category 1
		Content.idContentBought = -1;
		idContentBought = -1;
		categoryChanged = 1;
		categoryType = 0;
		showCategory(categoryChanged, 0, categoryType);
		b01.setStyle(clickedStyle);
		btMovie.setStyle(clickedStyle);
		btTotal.setDisable(true);

		//add events if click on the image
		//alert with description
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

	//reset style
	private void resetStyle() {
		//reset style and buttons
		b01.setStyle(originalStyle);
		b02.setStyle(originalStyle);
		b03.setStyle(originalStyle);
		b04.setStyle(originalStyle);
		b05.setStyle(originalStyle);
		b06.setStyle(originalStyle);
		b07.setStyle(originalStyle);

		borderPane1.setVisible(true);
		borderPane2.setVisible(true);
		borderPane3.setVisible(true);
		borderPane4.setVisible(true);
		borderPane5.setVisible(true);
		borderPane6.setVisible(true);
	}

	//showcategory
	private void showCategory(int idCategory, int showNext, int type) {

		//clear the list
		description.clear();
		idContent.clear();

		category = 0;

		//fill up with new category
		List<Integer> listI = new ArrayList<Integer>();

		for (int i = 0; i < Content.list.size(); i++) {

			Content content = Content.list.get(i);

			if (content.getIdCategory() == idCategory && content.getType() == type) {
				listI.add(i);
			}
		}

		//check if there is more content
		int x = 0;
		
		for (int i = showNext; i < listI.size(); i++) {

			x++;
			
			Content content = Content.list.get(listI.get(i));

			category++;
			
			//display elements acording to total
			if (x == 1) {
				description.add(content.getDescription());
				idContent.add(i);
				this.setElements(img01, content.getImage(), label01, content.getName(), bt01, content.getPrice());
			} else if (x == 2) {
				description.add(content.getDescription());
				idContent.add(i);
				this.setElements(img02, content.getImage(), label02, content.getName(), bt02, content.getPrice());
			} else if (x == 3) {
				description.add(content.getDescription());
				idContent.add(i);
				this.setElements(img03, content.getImage(), label03, content.getName(), bt03, content.getPrice());
			} else if (x == 4) {
				description.add(content.getDescription());
				idContent.add(i);
				this.setElements(img04, content.getImage(), label04, content.getName(), bt04, content.getPrice());
			} else if (x == 5) {
				description.add(content.getDescription());
				idContent.add(i);
				this.setElements(img05, content.getImage(), label05, content.getName(), bt05, content.getPrice());
			} else if (x == 6) {
				description.add(content.getDescription());
				idContent.add(i);
				this.setElements(img06, content.getImage(), label06, content.getName(), bt06, content.getPrice());
			}

		}

		//check content total
		if (category == 0) {
			borderPane1.setVisible(false);
			borderPane2.setVisible(false);
			borderPane3.setVisible(false);
			borderPane4.setVisible(false);
			borderPane5.setVisible(false);
			borderPane6.setVisible(false);
			return;
		}

		//disable some border panes if there is no content or less than the whole grid
		if (category < 7) {

			btmore.setVisible(false);
			btless.setVisible(false);

			if (category == 1) {
				borderPane2.setVisible(false);
				borderPane3.setVisible(false);
				borderPane4.setVisible(false);
				borderPane5.setVisible(false);
				borderPane6.setVisible(false);
			} else if (category == 2) {
				borderPane3.setVisible(false);
				borderPane4.setVisible(false);
				borderPane5.setVisible(false);
				borderPane6.setVisible(false);
			} else if (category == 3) {
				borderPane4.setVisible(false);
				borderPane5.setVisible(false);
				borderPane6.setVisible(false);
			} else if (category == 4) {
				borderPane5.setVisible(false);
				borderPane6.setVisible(false);
			} else if (category == 5) {
				borderPane6.setVisible(false);
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

	//set elements
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

	//click on more action
	@FXML
	private void handleMoreAction(ActionEvent event) {
		//counter
		categoryCount++;
		showCategory(categoryChanged, categoryCount, 0);
		btless.setVisible(true);
	}

	//click on less action
	@FXML
	private void handleLessAction(ActionEvent event) {
		//counter
		categoryCount--;
		showCategory(categoryChanged, categoryCount, categoryType);
	}

	//change the screen to serie
	@FXML
	private void handleSerieAction(ActionEvent event) {

		//check if there is any content clicked
		if (idContentBought != -1) {
			new AlertController(AlertType.INFORMATION, "Payment",
					"Unselect or finish the transaction before change screen");
			return;
		}

		//reset style
		resetStyle();

		//change category
		categoryChanged = 1;
		categoryType = 1;

		showCategory(categoryChanged, 0, categoryType);
		b01.setStyle(clickedStyle);
		btMovie.setStyle(originalStyle);
		btSerie.setStyle(clickedStyle);
	}

	//change the screen to movie
	@FXML
	private void handleMovieAction(ActionEvent event) {

		//check if there is any content clicked
		if (idContentBought != -1) {
			new AlertController(AlertType.INFORMATION, "Payment",
					"Unselect or finish the transaction before change screen");
			return;
		}

		//reset style
		resetStyle();

		categoryChanged = 1;
		categoryType = 0;

		//change category
		showCategory(categoryChanged, 0, categoryType);
		b01.setStyle(clickedStyle);
		btMovie.setStyle(clickedStyle);
		btSerie.setStyle(originalStyle);
	}

	//change the screen to new category
	@FXML
	private void handleChangeAction(ActionEvent event) throws IOException {

		//check if there is any content clicked
		if (idContentBought != -1) {
			new AlertController(AlertType.INFORMATION, "Payment",
					"Unselect or finish the transaction before change screen");
			return;
		}

		//add elements to new screen
		categoryCount = 0;
		Node node = (Node) event.getSource();
		String data = (String) node.getUserData();
		int value = Integer.parseInt(data);
		
		//load main loading
		AnchorPane newAnchor = FXMLLoader.load(getClass().getResource("/view/main_loading.fxml"));
		AnchorPane saveAnchor = new AnchorPane();
		
		//it needs to save the original anchorpane
		saveAnchor.getChildren().clear();
		saveAnchor.getChildren().setAll(aP.getChildren());
		
		//reset
		aP.getChildren().clear();
		aP.getChildren().setAll(newAnchor);
		
		//reset style
		resetStyle();

		//pause for 2 seconds and show new screen with new elements
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				showCategory(value, 0, categoryType);
				aP.getChildren().clear();
				aP.getChildren().setAll(saveAnchor);
			}
		});
		//play
		pause.play();

		//change styles
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

	//cart
	@FXML
	private void handleCartAction(ActionEvent event) {

		//disable button if click.
		btTotal.setDisable(false);

		//get with button was clicked
		Node node = (Node) event.getSource();
		String data = (String) node.getUserData();
		int value = Integer.parseInt(data);

		//check clicks and values
		if (!bClicked && bValue != value) {

			String style = "-fx-background-color: #52b788; -fx-background-radius: 50;";

			bValue = value;
			
			//add total and save the content clicked
			//save the content name to send email.
			switch (value) {
			case 1:
				Content.nameContentBought = Content.list.get(idContent.get(0)).getName();
				idContentBought = Content.list.get(idContent.get(0)).getIdContent();
				totalToPay += Content.list.get(idContent.get(0)).getPrice();
				bt01.setStyle(style);
				break;
			case 2:
				Content.nameContentBought = Content.list.get(idContent.get(1)).getName();
				idContentBought = Content.list.get(idContent.get(1)).getIdContent();
				totalToPay += Content.list.get(idContent.get(1)).getPrice();
				bt02.setStyle(style);
				break;
			case 3:
				Content.nameContentBought = Content.list.get(idContent.get(2)).getName();
				idContentBought = Content.list.get(idContent.get(2)).getIdContent();
				totalToPay += Content.list.get(idContent.get(2)).getPrice();
				bt03.setStyle(style);
				break;
			case 4:
				Content.nameContentBought = Content.list.get(idContent.get(3)).getName();
				idContentBought = Content.list.get(idContent.get(3)).getIdContent();
				totalToPay += Content.list.get(idContent.get(3)).getPrice();
				bt04.setStyle(style);
				break;
			case 5:
				Content.nameContentBought = Content.list.get(idContent.get(4)).getName();
				idContentBought = Content.list.get(idContent.get(4)).getIdContent();
				totalToPay += Content.list.get(idContent.get(4)).getPrice();
				bt05.setStyle(style);
				break;
			case 6:
				Content.nameContentBought = Content.list.get(idContent.get(5)).getName();
				idContentBought = Content.list.get(idContent.get(5)).getIdContent();
				totalToPay += Content.list.get(idContent.get(5)).getPrice();
				bt06.setStyle(style);
				break;
			}
			
			//add values in decimal formart class converter

			DecimalFormat dec = new DecimalFormat("#.##");

			btTotal.setText(dec.format(totalToPay) + "€");

			//click as true
			bClicked = true;
		} else {
			//set limite
			if (bValue != value) {
				new AlertController(AlertType.INFORMATION, "Max limite",
						"You can only rent one movie or serie per transaction!");
			} else {
				//reset button
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

	//payment action will alert if the user wants to pay (will open a screen if yes)
	//load screen is in AlertController
	@FXML
	private void handlePayAction(ActionEvent event) {
		if (idContentBought != -1) {
			Content.idContentBought = idContentBought;
			new AlertController(AlertType.CONFIRMATION, "Would you like to pay now?",
					"Please select an option below and remember, the price is for only 7 days. If you delay, you will be charged for the extra days!");
		}
	}

	//open watchnow screen
	@FXML
	private void handleWatchAction(ActionEvent event) {
		//check if there is any content clicked
		if (idContentBought != -1) {

			new AlertController(AlertType.INFORMATION, "Payment", "Unselect or finish the transaction before watch.");
			return;
		}

		//load new screen
		Platform.runLater(() -> {
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("/view/watchnow.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Scene scene1 = new Scene(root);

			Stage stage = new Stage();
			stage.initStyle(StageStyle.TRANSPARENT);
			scene1.setFill(Color.TRANSPARENT);
			stage.setResizable(false);
			stage.setTitle("WatchNow");
			stage.setResizable(false);
			stage.setScene(scene1);
			//set as modal to not click in any other screen
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
			Public.stageWatch = stage;
		});
	}
}
