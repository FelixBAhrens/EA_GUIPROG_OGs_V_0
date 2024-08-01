package control;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import res.Strings;

/**
 * Controllerklasse fuer die "tutorial-view.fxml"-Datei.
 *
 * @author David Kien, Enes Oezcan.
 */
public class TutorialController
{
    @FXML
    private TextArea tutorialTextArea;
    private Stage stage;
    private Scene menueScene;

    //--------------------------------------------------------------------------

    /**
     * Initialisiert den Controller mit der Buehne und der Menueszenen-Referenz.
     *
     * @pre Die Parameter 'stage' und 'menueScene'muessen gueltige und initialisierte
     * Objekte sein. Der Controller muss mit FXML verbunden und korrekt geladen sein.
     *
     * @post Die Instanzvariablen 'stage' und menueScene' sind gesetzt worden und koennen in
     * anderen Methoden des Controllers verwendet werden, um auf die Hauptbuehne und
     * die Menueszenen-Referenz zuzugreifen.
     *
     * @param stage Die Hauptbuehne (Stage) der Anwendung, die verwendet wird, um die
     * Szene anzuzeigen.
     *
     * @param menueScene Die Szene (Scene) fuer das Menue, die verwendet wird, um die
     * Ansicht zu wechseln.
     *
     * @author David Kien, Enes Oezcan.
     */
    @FXML
    public void initialize (Stage stage, Scene menueScene)
    {
        this.stage = stage;
        this.menueScene = menueScene;
    }

    /**
     * Handhabt die Aktion zum Weiterschalten in die naechste Szene der Anwendung.
     *
     * @pre Die Methode 'wechsleSzene' des 'SzenenManager' muss implementiert und
     * funktionsfaehig sein. Die Konstante 'Strings.FXML_STORY' muss definiert sein
     * und den Pfad zur FXML-Datei der naechsten Szene enthalten.
     *
     * @post Die aktuelle Szene wurde zur Szene, die durch den Pfad 'Strings.FXML_STORY'
     * beschrieben wird.
     *
     * @author David Kien.
     */
    @FXML
    public void handleWeiter ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_STORY);
    }

    /**
     * Handhabt die Aktion zum Zurueckwechseln in die vorherige Szene der Anwendung.
     *
     * @pre Die Methode 'szeneZurueck' des 'SzenenManager' muss implementiert und
     * funktionsfaehig sein. Es muss eine vorherige Szene existieren, zu der
     * zurueckgewechselt werden kann.
     *
     * @post Die Anwendung wechselte zurueck zu der vorherigen Szene.
     *
     * @author David Kien.
     */
    @FXML
    public void handleZurueck ()
    {
        SzenenManager.szeneZurueck();
    }
}