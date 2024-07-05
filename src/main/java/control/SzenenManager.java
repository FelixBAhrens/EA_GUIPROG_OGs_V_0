package control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import res.Strings;
import utility.MyIO;

import java.io.IOException;
import java.util.Stack;

public class SzenenManager
{
    private static Stage hauptStage;
    private static final Stack<Scene> szenenStack = new Stack<>();

    public SzenenManager (Stage hauptStage)
    {
        this.hauptStage = hauptStage;
    }

    /**
     * Methode, die die uebergebene FXML-Datei laedt und als Szene im Hauptstage (der einzige Stage, der im Programm
     * verwendet wird) setzt.
     *
     * @param fxmlDatei
     * @author David Kien, Felix Ahrens
     */
    public static void wechseleSzene (String fxmlDatei)
    {
        try {
            FXMLLoader loader = new FXMLLoader(SzenenManager.class.getResource(fxmlDatei));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            szenenStack.push(scene);
            hauptStage.setScene(scene);
            hauptStage.show();
            root.requestFocus();
        } catch (IOException e) {
            MyIO.print(Strings.FEHLERMELDUNG_SZENENWECHSEL);
        }
    }

    /**
     * Methode, die die vorherige Szene vom Szenenstack holt und setzt.
     *
     * @author Felix Ahrens, David Kien
     */
    public static void szeneZurueck ()
    {
        if (!szenenStack.isEmpty()) {
            szenenStack.pop();
            Scene previousScene = szenenStack.pop();
            szenenStack.push(previousScene);
            hauptStage.setScene(previousScene);
            hauptStage.show();
        }
    }
}