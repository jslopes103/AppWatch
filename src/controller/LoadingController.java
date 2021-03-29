package controller;

import java.io.IOException;

import data.ContentData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class LoadingController {

	@FXML
	AnchorPane aP;

	public class CheckDatabase extends Thread {

		public void run() {

			AlertController alert = null;

			boolean getContent = false;

			while (true) {

				try {

					if (getContent == false) {
						if (new ContentData().selectAllContent() == false) {
							alert = new AlertController(AlertType.ERROR, "Database Connection",
									"Unable to get the content!");
							break; 
						}

						getContent = true;
					} else {
						Platform.runLater(() -> {
							AnchorPane anchor = null;
							try {
								anchor = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							aP.getChildren().clear();
							aP.getChildren().setAll(anchor);
						});
						
						break;
					}

					Thread.sleep(3000L);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@FXML
	protected void initialize() {
		new CheckDatabase().start();
	}
}
