package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import data.ContentData;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Watch;

//watchnowcontroller
public class WatchNowController {
	
	//anchor pane
	@FXML
	AnchorPane aP;

	//card field
	@FXML
	TextField cardField;

	//checkcard button
	@FXML
	JFXButton checkCard;

	//combobox list of content
	@FXML
	JFXComboBox<String> comboBox;

	//button watchnow
	@FXML
	JFXButton watchNow;

	//save the content
	private ObservableList<String> contentPaid = FXCollections.observableArrayList();

	Pattern pattern = null;
	Matcher matcher = null;

	//limite of textfield
	int maxCardNumber = 8;

	//get all content according to card's number
	ContentData contentData = new ContentData();

	@FXML
	protected void initialize() {

		//check card field only numbers
		cardField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					cardField.setText(newValue.replaceAll("[^\\d]", ""));
				}
				if (cardField.getLength() >= maxCardNumber) {
					checkCard.setVisible(true);
				} else {
					checkCard.setVisible(false);
				}
			}
		});
		//max of keys typed
		cardField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent k) {
				if (cardField.getLength() >= maxCardNumber) {
					k.consume();
				}
			}
		});
	}

	@FXML
	private void handleCloseAction(ActionEvent event) {
		//close the stage
		Public.stageWatch.close();
	}

	@FXML
	private void handleCheckAction(ActionEvent event) {
		//get all content according to the card number
		ContentData contentData = new ContentData();

		//clear old list
		Watch.listWatch.clear();

		//query sql
		boolean ret = contentData.selectAllWatch(Integer.parseInt(cardField.getText()));

		//if finds content
		if (ret == true) {
			//fill the combobox
			comboBox.setVisible(true);

			contentPaid.clear();

			for (int i = 0; i < Watch.listWatch.size(); i++) {
				Watch watch = Watch.listWatch.get(i);

				int date = (int) ChronoUnit.DAYS.between(watch.getDate().toLocalDate(),
						java.sql.Date.valueOf(LocalDate.now()).toLocalDate());

				String complete = date > 7 ? " - expired" : " - " + (7 - date) + " day(s) left";

				contentPaid.add(watch.getName() + complete);
			}

			comboBox.valueProperty().set(null);

			//set items
			comboBox.setItems(contentPaid);

			/*comboBox.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
				@Override
				public ListCell<String> call(ListView<String> p) {
					return new ListCell<String>() {

						@Override
						protected void updateItem(String item, boolean empty) {
							super.updateItem(item, empty);
							setText(item);
						}
					};
				}

			});*/
		} else {
			//if it does not find the card or does not have any enough credit
			comboBox.setVisible(false);
			new AlertController(AlertType.ERROR, "Card", "No movies or series found for this card!");
		}
	}
	
	@FXML
	protected void handleComboBoxAction(ActionEvent event)
	{
		watchNow.setVisible(true); 
		
		//check if content if expired
		if (comboBox.getValue() != null) {

			for (int i = 0; i < Watch.listWatch.size(); i++) {
				Watch watch = Watch.listWatch.get(i);
				
				if (comboBox.getValue().equalsIgnoreCase(watch.getName() + " - expired")) {
					System.out.println(watch.getName() + " - expired");
					watchNow.setVisible(false);
				}
			}

		}	
	}
	
	@FXML
	protected void handleWatchAction(ActionEvent event)
	{
		//load trailer screen
		Platform.runLater(() -> {
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("/view/trailer.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			

			Scene scene1 = new Scene(root);

			Stage stage = new Stage();
			scene1.setFill(Color.TRANSPARENT);
			stage.setResizable(false);
			stage.setTitle("Watching");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene1);
			stage.show();
			Public.stageWatch.close();
			Public.stageIsWatching = stage;
		});
	}
}
