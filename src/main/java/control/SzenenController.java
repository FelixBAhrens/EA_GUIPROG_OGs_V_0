package control;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import model.GameFile;
import res.Strings;

import java.awt.*;
import java.util.Stack;

public class SzenenController {
    static Stack<Scene> szenenStack = new Stack<>();
    static Stack<String> szenenNamenStack = new Stack<>();

    public static void setzeSzene(String szenenName, Scene scene, Stage stage) {
        szenenStack.push(scene);
        szenenNamenStack.push(szenenName);
        stage.setScene(scene);
        stage.setTitle(szenenName);
    }

    public static void zurueckSzene(Stage stage) {
        szenenStack.pop();
        stage.setScene(szenenStack.pop());
        stage.setTitle(szenenNamenStack.pop());
    }

    public static Button gebeZurueckButton(Stage stage){
        Button zurueckButton = new Button(Strings.ZURUECK);
        zurueckButton.setOnAction(e -> {zurueckSzene(stage);});
        return zurueckButton;
    }
}
