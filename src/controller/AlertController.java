package controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

//alert controller (responsible for all the alert's messages)
public class AlertController {

	//set Alert as null
	private Alert alert = null;

	//alert constructor
	public AlertController(AlertType type, String header, String body) {
		//it needs to run later or will return an error
		Platform.runLater(() -> {
			//add buttons if its confirmation's type
			if (type == type.CONFIRMATION) {
				alert = new Alert(AlertType.NONE, "", ButtonType.YES, ButtonType.NO);
			} else {
				alert = new Alert(AlertType.NONE, "", ButtonType.OK);
			}
			//create the alert
			alert.setAlertType(type);
			alert.setHeaderText(header);
			alert.initStyle(StageStyle.UNDECORATED);
			alert.setContentText(body);
			alert.showAndWait();
			this.setAlert(alert);
			//exit the system if its error is equal to database connection
			if (alert.getAlertType() == type.ERROR) {
				if (alert.getResult() == ButtonType.OK) {
					if (header.equalsIgnoreCase("Database Connection")) {
						System.exit(1);
					}
				}
			} 
			//alert type confirmation will go to payment screen
			else if (alert.getAlertType() == type.CONFIRMATION) {
				if (alert.getResult() == ButtonType.YES) {
					//load payment screen
					Platform.runLater(() -> {
						Parent root = null;
						try {
							root = FXMLLoader.load(getClass().getResource("/view/payment.fxml"));
						} catch (IOException e) {
							e.printStackTrace();
						}

						Scene scene1 = new Scene(root);

						Stage stage = new Stage();
						//transparent background
						stage.initStyle(StageStyle.TRANSPARENT);
						scene1.setFill(Color.TRANSPARENT);
						stage.setResizable(false);
						stage.setTitle("Card");
						stage.setResizable(false);
						stage.setScene(scene1);
						//stage as a modal (user can't change screen)
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.show();
						Public.stageModal = stage;
					});
				}
			}
		});
	}

	//return alert
	public Alert getAlert() {
		return alert;
	}

	//set alert
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
}
