package controller;

import java.io.IOException;

import data.ContentData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

//loadcontroller
public class LoadingController {

	//anchorpane id
	@FXML
	AnchorPane aP;

	//check database connection 
	public class CheckDatabase extends Thread {

		public void run() {

			//alert as null
			AlertController alert = null;

			//break thread if content is true
			boolean getContent = false;

			while (true) {

				try {

					//try to get the content
					if (getContent == false) {
						//break if does not get the content
						if (new ContentData().selectAllContent() == false) {
							alert = new AlertController(AlertType.ERROR, "Database Connection",
									"Unable to get the content!");
							break; 
						}

						//set true to load new screen
						getContent = true;
					} else {
						//run main screen
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
						
						//break;
						break;
					}

					//3 seconds
					Thread.sleep(3000L);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@FXML
	protected void initialize() {
		//init thread
		new CheckDatabase().start();
	}
}
