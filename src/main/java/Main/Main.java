package Main;

import control.SzenenManager;
import javafx.application.Application;
import javafx.stage.Stage;
import res.Strings;

public class Main extends Application {
    /**
     * Start-Methode, die die Application startet, indem der SzenenManager initialisiert und mit diesem dann das StartMenue aufgerufen wird.
     * @param primaryStage
     * @author David Kien, Felix Ahrens
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        SzenenManager szenenManager = new SzenenManager(primaryStage);
        szenenManager.wechseleSzene(Strings.FXML_STARTMENUE);
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