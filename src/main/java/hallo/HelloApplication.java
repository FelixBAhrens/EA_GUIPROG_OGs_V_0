package hallo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application
{

        /**
         * Startet das Programm und öffnet die Scene Startmenü
         * @param stage
         * @throws IOException
         */
        @Override
        public void start(Stage stage) throws IOException
        {
            Parent root = FXMLLoader.load(getClass().getResource("/resources/org.example/menue-view.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("HALlo");
            stage.show();
        }
    }

