package control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class SceneManager {
    private static Stage primaryStage;
    private static final Stack<Scene> sceneStack = new Stack<>();

    public SceneManager(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    public static Scene getVorherigeSzene () {
        if (sceneStack.size() > 1) {
            return sceneStack.get(sceneStack.size() - 2);
        }
        return null;
    }

    public static void changeScene (String fxmlFile) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlFile));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        sceneStack.push(scene);


        primaryStage.setScene(scene);
        primaryStage.show();

        root.requestFocus();
    }

    public static void goBack ()
    {
        if (!sceneStack.isEmpty())
        {
            sceneStack.pop();
            Scene previousScene = sceneStack.pop();
            sceneStack.push(previousScene);
            primaryStage.setScene(previousScene);
        }
    }

    /*
    public <T> T changeSceneAndGetController(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        return loader.getController();
    }
     */
}
