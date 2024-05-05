package Main;

import control.StartMenueController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;



public class Main {

    public static void main(String[] args) throws Exception {
        StartMenueController.launch(StartMenueController.class, args);
    }
}
