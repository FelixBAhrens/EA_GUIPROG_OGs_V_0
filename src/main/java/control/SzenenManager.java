package control;

// COMPLETED (OPEN TODO)

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import res.Strings;
import utility.MyIO;

import java.util.Stack;

/**
 * Die Klasse SzenenManager verwaltet die Szenen in der Application und beinhaltet
 * die noetigen Methoden dafuer. Alle Szenen werden im Hauptstage, welcher auch der
 * "main" uebergeben wurde, gesetzt.
 *
 * @author Felix Ahrens, David Kien.
 */
public class SzenenManager
{
    private static Stage hauptStage;
    private static final Stack<Scene> szenenStack = new Stack<>();

    /**
     * Methode, die die aktuell gesetzte Szene zurueckgibt.
     *
     * @return Die aktuell geladene Szene.
     *
     * @pre Der szenenStack darf nicht leer sein und muss existieren.
     *
     * @post Die aktuelle Szene wurde zurueckgegeben.
     *
     * @author Felix Ahrens.
     *
     * // TODO: No usage
     */
    public Scene gebeAktuelleSzene ()
    {
        Scene aktuelleSzene = szenenStack.pop();
        szenenStack.push(aktuelleSzene);
        return aktuelleSzene;
    }

    /**
     * Konstruktor der Klasse Szenenmanager.
     *
     * @param hauptStage
     *
     * @author Felix Ahrens.
     */
    public SzenenManager (Stage hauptStage)
    {
        this.hauptStage = hauptStage;
    }

    /**
     * Methode, die die uebergebene FXML-Datei laedt und als Szene im Hauptstage
     * (der einzige Stage, der im Programm verwendet wird) setzt.
     *
     * @param fxmlDatei Der Dateiname der fxml-Datei, die die zu setzende Szene spezifiziert.
     *
     * @pre Die Klassenvariablen fuer den Szenenstack und den Hauptstage muessen
     * existieren und nicht "null" sein.
     *
     * @post Die Szene wurde auf die Szene gesetzt, die durch den uebergebenen
     * Fxml-Dateinamen im Pfad repraesentiert wird. Die neue Szene wurde auf dem
     * "szenenStack" abgelegt.
     *
     * @author David Kien, Felix Ahrens.
     */
    public static Class wechseleSzene (String fxmlDatei)
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
            return null;
            //return loader.getController();
        } catch (Exception e)
        {
            MyIO.print(Strings.FEHLERMELDUNG_SZENENWECHSEL);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Methode, die die vorherige Szene vom Szenenstack holt und setzt.
     *
     * @pre Der "szenenStack" muss existieren und den Datentyp "Scene" beherbergen.
     *
     * @post Wenn der szenenStack nicht leer ist, wurde eine Szene zurueckgegangen.
     *
     * @author Felix Ahrens.
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