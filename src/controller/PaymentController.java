package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class PaymentController {

	@FXML
	AnchorPane aP;

	@FXML
	TextField cardField;

	@FXML
	JFXButton payButton;

	@FXML
	CheckBox checkBox;

	@FXML
	TextField emailField;

	Pattern pattern = Pattern.compile("^(.+)@(.+)$");
	Matcher matcher = null;

	@FXML
	protected void initialize() {

		cardField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					cardField.setText(newValue.replaceAll("[^\\d]", ""));
				}
				if (cardField.getLength() >= 16) {
					payButton.setVisible(true);
				} else {
					payButton.setVisible(false);
				}
			}
		});
		cardField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent k) {

				if (cardField.getLength() >= 16) {
					k.consume();
				}

			}
		});
		emailField.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				pattern = Pattern.compile("^(.+)@(.+)$");
				matcher = pattern.matcher(emailField.getText());

				if ((emailField.getLength() <= 5) || !matcher.matches()) {
					if (checkBox.isSelected()) {
						payButton.setVisible(false);
					} else {
						payButton.setVisible(true);
					}
				} else {
					if (matcher.matches()) {
						if (checkBox.isSelected()) {
							payButton.setVisible(true);
						} else {
							payButton.setVisible(false);
						}
					}
				}

			}
		});
		emailField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent k) {
				if (emailField.getLength() >= 50) {
					k.consume();
				}
			}
		});

	}

	@FXML
	private void handleCheckAction(ActionEvent event) {
		if (checkBox.isSelected()) {
			emailField.setVisible(true);
			payButton.setVisible(false);
		} else {
			if (cardField.getLength() >= 16) {
				payButton.setVisible(true);
			}
			emailField.setVisible(false);
		}
	}

	@FXML
	private void handleCloseAction(ActionEvent event) {
		Public.stage.close();
	}
}
