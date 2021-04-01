package controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;

import data.ContentData;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Content;

//paymentcontroller
public class PaymentController {

	//anchor pane
	@FXML
	AnchorPane aP;

	//card field
	@FXML
	TextField cardField;

	//pay button
	@FXML
	JFXButton payButton;

	//check box
	@FXML
	CheckBox checkBox;

	//email label
	@FXML
	Label labelEmail;

	//text email field
	@FXML
	TextField emailField;

	//pattern
	Pattern pattern = null;
	Matcher matcher = null;

	//limit textfield
	int maxCardNumber = 8;

	ContentData contentData = new ContentData();

	@FXML
	protected void initialize() {

		//add listener on the cardfield
		cardField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				//check numbers
				if (!newValue.matches("\\d*")) {
					cardField.setText(newValue.replaceAll("[^\\d]", ""));
				}
				//set button as visible if its more than 8
				if (cardField.getLength() >= maxCardNumber) {
					payButton.setVisible(true);
				} else {
					payButton.setVisible(false);
				}
			}
		});
		//check key typed
		cardField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent k) {
				//check if there is more than 8 and consume
				if (cardField.getLength() >= maxCardNumber) {
					k.consume();
				}
				if (checkBox.isSelected()) {
					if (cardField.getLength() >= maxCardNumber) {
						payButton.setVisible(true);
					} else {
						payButton.setVisible(false);
					}
				}
			}
		});
		//check email
		emailField.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				//email pattern = email@something.com or any
				pattern = Pattern.compile("^(.+)@(.+)$");
				matcher = pattern.matcher(emailField.getText());

				//validations and size
				//control buttons of payment according to the changes
				if ((emailField.getLength() <= 5) || !matcher.matches()) {
					if (checkBox.isSelected()) {
						if (cardField.getLength() >= maxCardNumber) {
							payButton.setVisible(false);
						}
					} else {
						payButton.setVisible(true);
					}
				} else {
					if (matcher.matches()) {
						if (checkBox.isSelected()) {
							if (cardField.getLength() >= maxCardNumber) {
								payButton.setVisible(true);
							}
						} else {
							payButton.setVisible(false);
						}
					}
				}

			}
		});
		//key typed in email
		emailField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent k) {
				//check if its more than 50 and consume
				if (emailField.getLength() >= 50) {
					k.consume();
				}
			}
		});

	}

	//check box action
	@FXML
	private void handleCheckAction(ActionEvent event) {
		//set labels and button visible if its checked
		if (checkBox.isSelected()) {
			emailField.setVisible(true);
			labelEmail.setVisible(true);
			payButton.setVisible(false);

			pattern = Pattern.compile("^(.+)@(.+)$");
			matcher = pattern.matcher(emailField.getText());

			if (matcher.matches()) {
				payButton.setVisible(true);
			}
		} else {
			if (cardField.getLength() >= maxCardNumber) {
				payButton.setVisible(true);
			}
			emailField.setVisible(false);
			labelEmail.setVisible(false);
		}
	}

	@FXML
	private void handleCloseAction(ActionEvent event) {
		//x button
		Public.stageModal.close();
	}

	@FXML
	private void handlePayAction(ActionEvent event) throws IOException {

		//payment action load a process screen
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/process.fxml"));
		aP.getChildren().clear();
		aP.getChildren().setAll(pane);

		//query sql
		boolean ret = contentData.selectCardAndPay(Integer.parseInt(cardField.getText()), 2);

		//load payment screen
		AnchorPane newAnchor = FXMLLoader.load(getClass().getResource("/view/payment.fxml"));

		//pause transition for 5 seconds while wait for the query
		PauseTransition pause = new PauseTransition(Duration.seconds(5));
		pause.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				//change elements after get the result
				aP.getChildren().clear();
				aP.getChildren().setAll(newAnchor);

				if (ret == true) {
					//send email
					if (checkBox.isSelected()) {
						new EmailController().sendEmail(emailField.getText(), cardField.getText(),
								Content.nameContentBought);
					}

					//close the modal
					//control the thread Public.bought
					Public.bought = true;
					new AlertController(AlertType.INFORMATION, "Payment", "Payment was accepted! You can now watch!");
					Public.stageModal.close();
				} else {
					//in case of declining.
					new AlertController(AlertType.ERROR, "Payment",
							"Payment was declined! You may not have enough credit.");
				}
			}
		});
		//play
		pause.play();
	}
}
