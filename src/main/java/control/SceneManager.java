package control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import res.Konstanten;

import java.io.IOException;
import java.util.Stack;

public class SceneManager {
    private static Stage primaryStage;
    private static final Stack<Scene> sceneStack = new Stack<>();

    public SceneManager(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    public static void changeScene (String fxmlFile)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            sceneStack.push(scene);
            primaryStage.setScene(scene);
            primaryStage.show();

            root.requestFocus();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void goBack ()
    {
        if (!sceneStack.isEmpty())
        {
            sceneStack.pop();
            Scene previousScene = sceneStack.pop();
            sceneStack.push(previousScene);
            primaryStage.setScene(previousScene);
            primaryStage.show();
        }
    }
}