package Main;

import control.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;
import res.Strings;

public class Main extends Application {
    /**
     * Start-Methode, die die Application startet, indem der SceneManager initialisiert und mit diesem dann das StartMenue aufgerufen wird.
     * @param primaryStage
     * @author David Kien, Felix Ahrens
     */
    @Override
    public void start(Stage primaryStage) {
        SceneManager.initialisiereSceneManager(primaryStage);
        SceneManager.changeScene(Strings.FXML_STARTMENUE);
    }

    /**
     * Main-methode des Programms
     * @param args
     * @author David Kien
     */
    public static void main(String[] args) {
        launch(args);
    }
}