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

public class AlertController {

	private Alert alert = null;
	

	public AlertController(AlertType type, String header, String body) {
		Platform.runLater(() -> {
			if(type == type.CONFIRMATION)
			{
				alert = new Alert(AlertType.NONE, "", ButtonType.YES, ButtonType.NO);
			}
			else
			{
				alert = new Alert(AlertType.NONE, "", ButtonType.OK);
			}
			alert.setAlertType(type);
			alert.setHeaderText(header);
			alert.initStyle(StageStyle.UNDECORATED);
			alert.setContentText(body);
			alert.showAndWait();
			this.setAlert(alert);
			if (alert.getAlertType() == type.ERROR) {
				if (alert.getResult() == ButtonType.OK) {
					System.exit(1);
				}
			}
			else if (alert.getAlertType() == type.CONFIRMATION) {
				if (alert.getResult() == ButtonType.YES) {
					Platform.runLater(() -> {
						Parent root = null;
						try {
							root = FXMLLoader.load(getClass().getResource("/view/payment.fxml"));
						} catch (IOException e) {
							e.printStackTrace();
						}

						Scene scene1 = new Scene(root);

						Stage stage = new Stage();
						stage.initStyle(StageStyle.TRANSPARENT);
						scene1.setFill(Color.TRANSPARENT);
						stage.setResizable(false);
						stage.setTitle("Card");
						stage.setResizable(false);
						stage.setScene(scene1);
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.show();
						Public.stage = stage;
					});
				}
			}
		});
	}

	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}
}
