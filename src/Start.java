import controller.Public;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Start class extends Application
public class Start extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		//loading the first screen
		Parent root = FXMLLoader.load(getClass().getResource("/view/loading.fxml"));
		Scene scene = new Scene(root);
		//set title
		stage.setTitle("AppWatch");
		stage.setResizable(false);
		stage.setScene(scene);
		//show and save mainStage
		stage.show();
		Public.stageMain = stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	//destroy any thread in the memory
	@Override
	public void stop() throws Exception {
		super.stop();
		System.exit(0);
	}
}
