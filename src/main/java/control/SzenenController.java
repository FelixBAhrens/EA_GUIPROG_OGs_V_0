package control;

import javafx.scene.Scene;
import javafx.stage.Stage;

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
        stage.setScene(szenenStack.pop());
        stage.setTitle(szenenNamenStack.pop());
    }
}
