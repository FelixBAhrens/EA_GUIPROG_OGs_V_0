package control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import res.Strings;
import utility.MyIO;

import java.io.IOException;
import java.util.Stack;

public class SceneManager {
    // Singleton des SceneManagers -----------------------------
    /**
     * Die einzelne Instanz der Klasse SceneManager
     * @author Felix Ahrens
     */
    private static SceneManager instance;

    /**
     * Getter fuer die Singleton-Instanz der Klasse GameFile
     * @return
     * @throws Exception
     * @author Felix Ahrens
     */
    public static SceneManager getInstance() throws Exception {
        if (instance != null) {
            return instance;
        }
        else {
            throw new Exception(Strings.FEHLERMELDUNG_SCENEMANAGER);
        }
    }

    /**
     * Setter des Singletons der Klasse GameFile.
     * @author Felix Ahrens
     */
    public static void initialisiereSceneManager (Stage stage){
        instance = new SceneManager(stage);
    }

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

    public static void changeScene (String fxmlFile)
    {
        try{
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            //Class<CharakterController> controllerClass = loader.getController();
            sceneStack.push(scene);
            primaryStage.setScene(scene);
            primaryStage.show();

            root.requestFocus();
        } catch (IOException e){
            MyIO.print(Strings.FEHLERMELDUNG_SZENENWECHSEL);
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