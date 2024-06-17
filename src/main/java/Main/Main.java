package Main;

import javafx.application.Application;
import javafx.stage.Stage;
import control.SceneManager;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.changeScene("startMenue-view.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
