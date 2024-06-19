package Main;

import control.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;
import res.Strings;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.changeScene(Strings.FXML_STARTMENUE);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
