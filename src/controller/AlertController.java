package controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert.AlertType;

public class AlertController {

	private Alert alert = null;

	public AlertController(AlertType type, String header, String body) {
		Platform.runLater(() -> {
			alert = new Alert(AlertType.NONE, "", ButtonType.OK);
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
		});
	}

	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}
}
