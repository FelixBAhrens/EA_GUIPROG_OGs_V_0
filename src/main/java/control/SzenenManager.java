package control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import res.Strings;
import utility.MyIO;

import java.io.IOException;
import java.util.Stack;

/**
 * Die Klasse SzenenManager verwaltet die Szenen in der Application und beinhaltet die noetigen Methoden dafuer.
 * Alle Szenen werden im Hauptstage, welcher auch der "main" uebergeben wurde, gesetzt.
 * @Author Felix Ahrens, David Kien
 */
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
     *  verwendet wird) setzt.
     * @precondition Die Klassenvariablen fuer den Szenenstack und den Hauptstage muessen existieren und nicht "null"
     *  sein.
     * @postcondition Die Szene wurde auf die Szene gesetzt, die durch den uebergebenen Fxml-Dateinamen im Pfad
     * repraesentiert wird. Die neue Szene wurde auf dem "szenenStack" abgelegt.
     * @param fxmlDatei Der Dateiname der fxml-Datei, die die zu setzende Szene spezifiziert.
     * @author David Kien, Felix Ahrens
     */
    public static void wechseleSzene (String fxmlDatei)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(SzenenManager.class.getResource(fxmlDatei));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(SzenenManager.class.getResource("styles.css").toExternalForm());
            szenenStack.push(scene);
            hauptStage.setScene(scene);
            hauptStage.show();
            root.requestFocus();
        } catch (IOException e)
        {
            MyIO.print(Strings.FEHLERMELDUNG_SZENENWECHSEL);
        }
    }

    /**
     * Methode, die die vorherige Szene vom Szenenstack holt und setzt.
     * @precondition Der "szenenStack" muss existieren und den Datentyp "Scene" beherbergen.
     * @postcondition Wenn der szenenStack nicht leer ist, wurde eine Szene zurueckgegangen.
     * @author Felix Ahrens
     */
    public static void szeneZurueck ()
    {
        if (!szenenStack.isEmpty())
        {
            szenenStack.pop();
            Scene previousScene = szenenStack.pop();
            szenenStack.push(previousScene);
            hauptStage.setScene(previousScene);
            hauptStage.show();
        }
    }
}