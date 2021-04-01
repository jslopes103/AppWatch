package controller;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

//traillercontroller
public class TrailerController {
	
	//anchor pane
	@FXML
	AnchorPane aP;
	
	@FXML
	protected void initialize() {
		//load a small video downloaded on youtube, just to see how it would be the system
        Media media = new Media(new File("src/video/trailer.mp4").toURI().toString());  
        MediaPlayer mediaPlayer = new MediaPlayer(media);  
        MediaView mediaView = new MediaView(mediaPlayer);  
        mediaPlayer.setAutoPlay(true);  
        //add media to the pane
        aP.getChildren().add(mediaView);  
	}
}
