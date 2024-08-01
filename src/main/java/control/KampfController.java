package control;

// COMPLETED (OPEN TODO 3x)

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Artefakt;
import model.Charakter;
import model.GameFile;
import model.Kaempfer;
import res.Konstanten;
import res.Strings;

import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;

/**
 * Kampfcontroller, der saemtliche Methoden zum Kaempfen beinhaltet.
 * Dazu gehoeren GUI-Manipulationsmethoden, Methoden zum Verwalten und ausfuehren
 * von Kampfhandlungen, Initialisierungsmethoden und weitere. Der Kampfcontroller ist die
 * GUI-Controllerklasse der "kampf-view.fxml".
 *
 * @author David Kien, Felix Ahrens.
 */
public class KampfController extends ControllerController implements Initializable
{
    /**
     * Enum fuer den Kampftyp. Wird verwendet, um der Controllerklasse den Kampftyp
     * mitzuteilen, bevor die kampf-FXML-Datei aufgerufen wird.
     *
     * @author Felix Ahrens.
     */
    public enum KampfTyp
    {
        ENDGEGNER_KAMPF(Strings.ENDGEGNER),
        ANDERER_KAMPF(Strings.ANDERER),
        ARENA_KAMPF(Strings.ARENA);
        private final String beschreibung;

        KampfTyp (String beschreibung)
        {
            this.beschreibung = beschreibung;
        }
    }

    public Queue<Kaempfer> timeLine = new LinkedList<>();

    public static KampfTyp kampfTyp;

    private Kaempfer spieler;

    private Kaempfer gegner;

    private static String nachKampfSzenenName = Strings.FXML_STADT;

    @FXML
    public Text siegerText;

    @FXML
    public Label gegnerStats;

    @FXML
    public ImageView hintergrund;

    @FXML
    private Rectangle spielerRec;
    @FXML
    private Rectangle gegnerRec;

    @FXML
    public AnchorPane kampfStartDialog;
    @FXML
    public AnchorPane kampfEndeDialog;
    @FXML
    public AnchorPane kaempferPane;
    @FXML
    public AnchorPane gegnerPane;

    @FXML
    private GridPane gridPane;

    @FXML
    public Pane schwert_Pane;
    @FXML
    public Pane statue_Pane;
    @FXML
    public Pane ring_Pane;

    @FXML
    public HBox timeLineHBox;
    @FXML
    public HBox artefakteDisplay;

    @FXML
    private VBox kaempferDatenVBox;

    @FXML
    public Button zugBeendenButton;
    @FXML
    public Button MagieButton;
    @FXML
    public Button AttackierenButton;
    @FXML
    public Button EinheitenButton;

    @FXML
    public ProgressBar gegnerGesundheitsBar;

    /**
     * Methode, um den Kampf zu initialisieren.
     *
     * @pre Die Methoden, die Variablen und das Enum muessen vorhanden und zugreifbar sein.
     *
     * @post Der "kampfStartDialog" wurde invisible gesetzt und, abhaengig vom Kampftyp,
     * eine Methode zum Starten/Initialisieren eines bestimmten Kampfes aufgerufen.
     *
     * @author David Kien, Felix Ahrens.
     */
    @FXML
    public void initialisiereKampf ()
    {
        kampfStartDialog.setVisible(false);
        createMap();
        switch (kampfTyp)
        {
            case ENDGEGNER_KAMPF -> starteEndgegnerKampf();
            case ARENA_KAMPF -> starteArenaKampf();
        }
    }

    /**
     * Methode, um den Endgegnerkampf zu initialisieren. Diese erstellt und setzt
     * die Kaempfer und ruft Methoden auf, um Charaktere und Gegner zu initialisieren,
     * diese dem kaempferArray hinzuzufuegen, die Timeline zu berechnen, die
     * Charakterposition, die angezeigte Timeline und die "KampfAnchorPanes" zu
     * aktualisieren. Ebenso wird die Methode "naechsterZug" aufgerufen.
     *
     * @pre Die Methoden muessen vorhanden und erreichbar sein.
     *
     * @post Der EndgegnerKampf wurde mit allen notwendigen Elementen initialisiert.
     *
     * @author David Kien, Felix Ahrens.
     */
    public void starteEndgegnerKampf ()
    {
        this.spieler = Kaempfer.macheNeuenKaempferAusCharakter(GameFile.getInstanz().getLeader());
        this.gegner = Kaempfer.erstelleEndgegner();

        initialisiereCharacter();
        initialisiereGegner();
        Kaempfer[] kaempferArray = {spieler, gegner};
        berechneTimeLine(kaempferArray);
        updateCharacterPosition();
        updateKampfAnchorPanes();
        updateTimeLine();
        naechsterZug();
    }

    /**
     * Methode, die am Ende eines Zuges aufgerufen wird, und abhaengig von dem
     * naechsten Element der Queue den naechsten Zug aufruft.
     * Ist die Queue leer, wird der Kampf mit einem Unentschieden beendet,
     * wenn kein Kaempfer gestorben ist.
     */
    public void naechsterZug ()
    {
        updateTimeLine();
        if (!timeLine.isEmpty())
        {
            switch (timeLine.remove().getName())
            {
                case Strings.LEADER -> eigenZug();
                case Strings.ENDGEGNER -> gegnerZug();
            }
        } else
        {
            checkeLebtNoch();
            beendeKampf();
        }
    }

    /**
     * Methode, um den Stand-Alone-Kampf ueber Netzwerk zu starten.
     * Bei einem Arenakampf ist die Szenerie anders. Da die Spiellogik und das
     * Netzwerk zu diesem Zeitpunkt aber noch nicht durchdacht ist,
     * kann die GUI hierfuer nicht naeher entwickelt werden.
     *
     * @pre Hinter dem "ARENA_BILDPFAD" muss sich der Dateipfad zu einem Bild
     * befinden, das eine Arena zeigt.
     *
     * @post Das Hintergrundbild des Kampfes wurde auf das Bild einer Arena gesetzt.
     *
     * @author Felix Ahrens.
     */
    public void starteArenaKampf ()
    {
        Image bild = new Image(getClass().getResource(Strings.ARENA_BILDPFAD).toExternalForm());
        hintergrund.setImage(bild);
        //Schnittstelle zum Netzwerk. Hier muessen die gegnerischen Werte dann geladen werden.
    }

    /**
     * Methode zum Abschliessen des Kampfes. Der Sieger wurde von der
     * checkeLebtNoch-Methode ermittelt.
     *
     * @param sieger Die Instanz der Klasse Kaempfef, die im Kampf als Sieger
     * festgestellt wurde, und der Methode als "Sieger" uebergeben wurde.
     *
     * @pre Die Strings muessen Interface enthalten sein, der KampfEndeDialog muss
     * als AnchorPane in der Klasse vorhanden sein.
     *
     * @post Der "kampfEndeDialog" wurde mit dem Sieger als Text ausgegeben.
     *
     * @author Felix Ahrens.
     */
    public void beendeKampf (Kaempfer sieger)
    {
        siegerText.setText(sieger.getName() + Strings.HAT_GEWONNEN);
        kampfEndeDialog.setVisible(true);
    }

    /**
     * Ueberschriebene Methode, die den Kampf mit einem Unentschieden beendet,
     * wenn die Timeline ausgelaufen ist.
     *
     * @pre Die Strings muessen Interface enthalten sein, der KampfEndeDialog
     * muss als AnchorPane in der Klasse vorhanden sein.
     *
     * @post Der "kampfEndeDialog" wurde mit "Unentschieden" ausgegeben.
     * Die darauf folgende Szene wurde als Stadt "angemeldet".
     *
     * @author Felix Ahrens.
     */
    public void beendeKampf ()
    {
        siegerText.setText(Strings.UNENTSCHIEDEN);
        nachKampfSzenenName = Strings.FXML_STADT;
        kampfEndeDialog.setVisible(true);
    }

    /**
     * Initialisiert den Controller nach dem Laden der FXML-Datei.
     * <p>
     * Diese Methode wird aufgerufen, um den initialen Zustand der
     * Benutzeroberflaeche zu setzen. Sie macht das Dialogfenster "kampfStartDialog"
     * sichtbar und fügt einen Listener hinzu, der auf Aenderungen der Szene des
     * "gridPane" reagiert. Wenn eine neue Szene gesetzt wird, wird ein Event-Handler
     * registriert, der Tastendruecke in der Szene behandelt.
     *
     * @param location  Die URL, die zum Aufloesen relativer Pfade für die Hauptcontainer
     * der Benutzeroberflaeche verwendet wird.
     *
     * @param resources Die Ressourcen, die zur Lokalisierung der Hauptcontainer
     * der Benutzeroberflaeche verwendet werden.
     *
     * @pre Die Methoden und Variablen muessen vorhanden sein.
     *
     * @post Der "kampfStartDialog" ist visible.
     *
     * @author David Kien, Felix Ahrens.
     */
    @FXML
    public void initialize (URL location, ResourceBundle resources)
    {
        // Macht den kampfStartDialog sichtbar.
        kampfStartDialog.setVisible(true);
        gridPane.sceneProperty().addListener(((observableValue, oldScene, newScene) ->
        {
            // Fuegt einen Listener hinzu, der auf Szenenaenderungen im gridPane reagiert.
            if (newScene != null)
            {
                // Setzt einen Event-Handler für Tastendrücke in der neuen Szene
                newScene.setOnKeyPressed(this::handleKeyPress);
            }
        }));
    }

    /**
     * Methode, die den von der nutzenden Person spielbaren Zug steuert.
     * In dieser Methode werden Nutzereingaben freigeschaltet, die nur gemacht
     * werden können, wenn der Mensch am Zug ist. Das Gegenteil dazu ist die Methode
     * "gegnerZug()", die den Gegner (Bot) steuert.
     *
     * @pre "zugBeenden" muss ein Button sein, der sich in der FXML-Datei "kampf-view.fxml"
     * befindet. Der Button muss auch im Contoller sein.
     *
     * @post Der Knopf wurde freigeschaltet und diesem ein Eventlistener hinzugefuegt,
     * der den Zug beendet und die Methode "naechsterZug" aufruft.
     *
     * @author Felix Ahrens.
     */
    @FXML
    private void eigenZug ()
    {
        aktiviereButtonsWennMoeglich();
        zugBeendenButton.setOnAction(event -> {
            deaktiviereButtons();
            naechsterZug();
        });
    }

    /**
     * Methode, die Methoden zum Bestimmen der Ausfuehrbarkeit von Kampfhandlungen aufruft,
     * um, falls ja, die zugehoerigen Buttons zu aktivieren.
     *
     * @pre Die Methoden und Buttons muessen Existieren und erreichbar sein.
     * Die jeweils aktivierten Buttons duerfen nur die Handlung ausfuehren, die
     * von der jeweiligen Methode, die hier aufgerufen wurde, als machbar bestaetigt
     * wurde.
     *
     * @post Die Buttons, deren zugehoerige Handlung von den Methoden als
     * ausfuehrbar eingestuft wurde, wurden aktiviert.
     *
     * @author Felix Ahrens.
     */
    public void aktiviereButtonsWennMoeglich ()
    {
        if (magieWirkenMoeglich(spieler))
        {
            MagieButton.setDisable(false);
        }
        if (attackierenMoeglich(spieler, gegner))
        {
            AttackierenButton.setDisable(false);
        }
        if (einheitenEinsetzenMoeglich())
        {
            EinheitenButton.setDisable(false);
        }
        zugBeendenButton.setDisable(false);
    }

    public void deaktiviereButtons ()
    {
        zugBeendenButton.setDisable(true);
        MagieButton.setDisable(true);
        EinheitenButton.setDisable(true);
        AttackierenButton.setDisable(true);
    }

    /**
     * Methode zum Verlassen der Kampfszene. Diese wird durch einen Button im
     * "kampfBeendenDialog" aufgerufen und setzt die naechste Szene auf die Szene,
     * deren Name in "nachKampfSzenenName" gespeichert ist.
     *
     * @pre In "nachKampfSzenenName" muss ein gueltiger Dateiname fuer eine
     * existierende FXML-Datei gespeichert sein. Die Methode "wechseleSzene" in
     * der Klasse SzenenManager muss vorhanden sein und die ihr uebergebene FXML-Datei
     * lesen und als neue Szene setzen.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void verlasseKampfSzene ()
    {
        SzenenManager.wechseleSzene(nachKampfSzenenName);
    }

    /**
     * Methode zum Ausfuehren des Gegnerzuges. Diese steuert den Gegner (Bot)
     * indem sie verschiedene Methoden aufruft.
     *
     * @pre Die Methoden muessen existieren und Design-By-Contract erfuellen.
     *
     * @post Der gegnerische Zug wurde ausgefuehrt. Dabei wurde die Timeline
     * aktualisiert, eine Sekunde gewartet, der Gegner bewegt und Attakiert.
     *
     * @author Felix Ahrens.
     */
    private void gegnerZug ()
    {
        updateTimeLine();
        bewegeGegnerDynamisch();
        attackiere(gegner, spieler);
        naechsterZug();
    }

    /**
     * Erstellt eine Karte aus Rechtecken und fuegt diese in ein GridPane ein.
     * Genau genommen wird ein 12x12 großes Raster aus Rechtecken (Kacheln) mit
     * einer festen Groesse und Farbe generiert. Die Kacheln werden in das
     * GridPane-Layout eingefuegt, wobei jede Kachel eine Zelle im Raster repraesentiert.
     *
     * @pre Die verwendete Konstanten muessen definiert sein und das GridPane-Objekt
     * muss initialisiert sein und zur Verfuegung stehen.
     *
     * @post Ein 12x12-Raster aus Rechtecken wurde dem GridPane hizugefuegt. Jedes
     * Rechteck hat die Dimensionen 45x45 Einheiten, eine hellgraue Fuellfarbe
     * und einen schwarzen Rand.
     *
     * @author David Kien.
     */
    private void createMap ()
    {
        // Schleife ueber die Zeilen des Rasters.
        for (int row = Konstanten.INT_ZERO; row < Konstanten.INT_TWELVE; row++)
        {
            // Schleife ueber die Spalten des Rasters.
            for (int col = Konstanten.INT_ZERO; col < Konstanten.INT_TWELVE; col++)
            {
                // Erstellen eines Rechtecks mit einer Groesse von 45x45 Einheiten.
                Rectangle tile = new Rectangle(Konstanten.INT_FOURTY_FIVE, Konstanten.INT_FOURTY_FIVE);
                // Setzen der Fuellfarbe des Rechtecks auf Hellgrau.
                tile.setFill(Color.LIGHTGRAY);
                // Setzen der Umrandung des Rechecks auf Schwarz.
                tile.setStroke(Color.BLACK);
                // Hinzufuegen des Rechtecks zum GridPane an der Position (Spalte, Zeile).
                gridPane.add(tile, col, row);
            }
        }
    }

    /**
     * TODO
     * Methode, die die Timeline berechnen soll. Tut sie aber nicht, weil ich
     * fuer die GUI verantwortlich bin. Aber ich brauche die Methode,
     * um daraus eben eine Timeline anzeigen zu koennen.
     * Deshalb diese voreingestellte Reihenfolge.
     *
     * @param alleKaempferArray Die Kaempfer des Kampfes.
     *
     * @pre Im uebergebenen Kaempferarray muessen die Teilnehmer des Kampfes enthalten sein.
     *
     * @post die Queue "timeLine" enthaelt eine Reihenfolge der Kaempfer.
     *
     * @author Felix Ahrens.
     * @TODO hier noch Zufall hinzufuegen
     */
    public void berechneTimeLine (Kaempfer[] alleKaempferArray)
    {
        timeLine.add(alleKaempferArray[Konstanten.INT_ZERO]);
        timeLine.add(alleKaempferArray[Konstanten.INT_ONE]);
        timeLine.add(alleKaempferArray[Konstanten.INT_ZERO]);
        timeLine.add(alleKaempferArray[Konstanten.INT_ZERO]);
        timeLine.add(alleKaempferArray[Konstanten.INT_ONE]);
        timeLine.add(alleKaempferArray[Konstanten.INT_ZERO]);
        timeLine.add(alleKaempferArray[Konstanten.INT_ONE]);
        timeLine.add(alleKaempferArray[Konstanten.INT_ONE]);
        timeLine.add(alleKaempferArray[Konstanten.INT_ONE]);
        timeLine.add(alleKaempferArray[Konstanten.INT_ZERO]);
    }

    /**
     * Initialisiert den Charakter des Spiels und fuegt ihn in das GridPane ein.
     * Erstellt also ein Rechteck, dass den Spieler darstellt, mit einer festen
     * Groesse und Farbe. Rechteck wird dann an die Position des Spielers in
     * das GridPane eingefuegt.
     *
     * @pre Die verwendeten Konstanten muessen definiert sein. Das Objekt 'spieler'
     * muss initialisiert sein und gueltige Positionen fuer 'getxPosition()' und
     * 'getyPosition()' liefern. Ausserdem muss das GridPane-Objekt 'gridPane' initialisiert
     * sein.
     *
     * @post Ein Rechteck, dass den Spieler darstellt, wurde im GridPane an der Position
     * des Spielers hinzugefuegt. Das Rechteck hat die Dimension 45x45 Einheiten und
     * eine blaue Fuellfarbe.
     *
     * @author David Kien.
     */
    private void initialisiereCharacter ()
    {
        spielerRec = new Rectangle(Konstanten.INT_FOURTY_FIVE, Konstanten.INT_FOURTY_FIVE);
        spielerRec.setFill(Color.BLUE);
        gridPane.add(spielerRec, spieler.getxPosition(), spieler.getyPosition());
    }

    // TODO: Oben und unten redundant.

    /**
     * Initialisiert den Gegner des Spiels und fuegt ihn in das GridPane ein.
     * Erstellt also ein Rechteck, dass den Spieler darstellt, mit einer festen
     * Groesse und Farbe. Rechteck wird dann an die Position des Spielers in
     * das GridPane eingefuegt.
     *
     * @pre Die verwendeten Konstanten muessen definiert sein. Das Objekt 'gegner'
     * muss initialisiert sein und gueltige Positionen fuer 'getxPosition()' und
     * 'getyPosition()' liefern. Ausserdem muss das GridPane-Objekt 'gridPane' initialisiert
     * sein.
     *
     * @post Ein Rechteck, dass den Gegner darstellt, wurde im GridPane an der Position
     * des Gegners hinzugefuegt. Das Rechteck hat die Dimension 45x45 Einheiten und
     * eine rote Fuellfarbe.
     *
     * @author Felix Ahrens.
     */
    private void initialisiereGegner ()
    {
        gegnerRec = new Rectangle(Konstanten.INT_FOURTY_FIVE, Konstanten.INT_FOURTY_FIVE);
        gegnerRec.setFill(Color.RED);
        gridPane.add(gegnerRec, gegner.getxPosition(), gegner.getyPosition());
    }

    /**
     * Diese Methode dient als Handler fuer ein Key-Event. Sie wird aufgerufen, wenn
     * eine bestimmte Taste gedrueckt wird, und fuehrt die entsprechende Aktion
     * basierend auf die gedrueckte Taste aus.
     *
     * @param keyEvent Das Ereignis, das durch eine Tastenaktion ausgeloest
     * wurde und zum Methodenaufruf gefuehrt hat.
     *
     * @pre Das Objekt 'spieler' muss initialisiert und seine Position muss definiert
     * sein. Ausserdem muessen auch die verwendeten Konstanten definiert sein.
     * Die Methoden 'attackiere' und 'updateCharacterPosition' muessen implementiert sein.
     *
     * @post Die Position des Spielers wurde entsprechend der gedrueckten Taste
     * aktualisiert. Wurde die Taste 'Q' gedrueckt, so wurde die Methode 'attackiere'
     * aufgerufen. Die Methode 'updateCharacterPoition' wurde aufgerufen um die Postion
     * des Spielers zu aktualisieren.
     *
     * @author David Kien.
     */
    private void handleKeyPress (KeyEvent keyEvent)
    {
        switch (keyEvent.getCode())
        {
            case W:
                if (spieler.getyPosition() > Konstanten.INT_ZERO)
                {
                    spieler.setyPosition(spieler.getyPosition() - Konstanten.INT_ONE);
                }
                break;
            case A:
                if (spieler.getxPosition() > Konstanten.INT_ZERO)
                {
                    spieler.setxPosition(spieler.getxPosition() - Konstanten.INT_ONE);
                }
                break;
            case S:
                if (spieler.getyPosition() < Konstanten.INT_TWELVE - Konstanten.INT_ONE)
                {
                    spieler.setyPosition(spieler.getyPosition() + Konstanten.INT_ONE);
                }
                break;
            case D:
                if (spieler.getxPosition() < Konstanten.INT_TWELVE - Konstanten.INT_ONE)
                {
                    spieler.setxPosition(spieler.getxPosition() + Konstanten.INT_ONE);
                }
                break;
            case Q:
                attackiere(spieler, gegner);
                break;
        }
        updateCharacterPosition();
    }

    /**
     * Methode, die von der spielenden Person aufgerufen wurde und die Methode
     * "attackiere" mit den spielerseitigen Parametern aufruft.
     *
     * @pre Die Methode "attackiere" muss existieren und die Kaempfer "spieler"
     * und "gegner" muessen in der Klasse gespeichert sein.
     * Der Kaempfer muss die vom Spieler steuerbare Instanz der Klasse Kaempfer sein.
     *
     * @post Die Methode "attackiere" wurde mit dem Spieler (Leader) als
     * Angreifer aufgerufen.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleAttackieren ()
    {
        attackiere(spieler, gegner);
        AttackierenButton.setDisable(true);
    }

    /**
     * Aktualisiert die Positionen der Spielcharaktere im GridPane anhand der jeweiligen
     * aktuellen Koordinaten. Dies wird erreicht, indem die Spalten- und Zeilenindizes
     * der Rechtecke im GridPane entsprechend aktualisiert werden.
     *
     * @pre Die Objekte 'spieler' und 'gegner' muessen initialisiert und deren
     * Positionen gueltige Werte haben. Die Rechtecke 'spielerRec' und 'gegnerRec'
     * muessen mit den entsprechenden Charakteren verbunden sein. Ausserdem muss
     * das GridPane-Layout das entsprechende Layout-Managemanet fuer diese Rechtecke
     * uebernommen haben.
     *
     * @post Das Rechteck 'spielerRec' wird im GridPane an die durch 'spieler.getxPosition()'
     * und 'spieler.getyPosition()' definierten Positionen verschoben. Das Rechteck
     * 'gegnerRec' wird im GridPane an die durch 'gegner.getxPosition' und
     * 'gegner.getyPosition' definierten Positionen verschoben.
     *
     * @author David Kien.
     */
    private void updateCharacterPosition ()
    {
        GridPane.setColumnIndex(spielerRec, spieler.getxPosition());
        GridPane.setRowIndex(spielerRec, spieler.getyPosition());
        GridPane.setColumnIndex(gegnerRec, gegner.getxPosition());
        GridPane.setRowIndex(gegnerRec, gegner.getyPosition());
    }

    /**
     * Methode, die die Timeline mit Panes, entsprechend dem Inhalt der Queue "timeLine" fuellt.
     *
     * @pre Die verwendeten Methoden muessen existieren und erreichbar sein.
     *
     * @post Die HBox "timeLineHBox" wurde entsprechend des Inhalts der Queue aktualisiert.
     *
     * @author Felix Ahrens.
     */
    public void updateTimeLine ()
    {
        timeLineHBox.getChildren().clear();
        for (Kaempfer kaempfer : timeLine)
        {
            timeLineHBox.getChildren().addAll(kaempfer.toPane());
        }
    }

    /**
     * Methode zum Wirken von Magie. Dabei wird geprueft, ob das Wirken von Magie
     * ueberhaupt moeglich ist. Falls ja, werden die Manapunkte des Angreifers auf
     * 0 gesetzt und der Schaden berechnet. Der berechnete Schaden wird danach
     * auf den gegner angewendet. Zuletzt werden Methoden zum ueberpruefen des
     * Lebens der Beteiligten und zum Aktualisieren der AnchorPanes aufgerufen.
     *
     * @pre Die genutzten Parameter und Methoden muessen von der Methode aus
     * erreichbar sein und ihre Arbeit erfuellen.
     *
     * @post Magie wurde gewirkt.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void wirkeMagie ()
    {
        if (magieWirkenMoeglich(spieler))
        {
            spieler.setManapunkte(Konstanten.INT_ZERO);
            int schaden = spieler.getFernkampfWert() * Konstanten.INT_THREE
                    - gegner.getMagieResistenz()
                    - gegner.getSchild();
            gegner.setGesundheit(gegner.getGesundheit() - schaden);
            checkeLebtNoch();
            updateKampfAnchorPanes();
        }
    }

    /**
     * Methode die das Einsetzen eines Artefakts verwaltet. Da die Methode von
     * allen Artefakt-Panes aufgerufen wird, muss diese erst das passende Artefakt
     * herausfiltern. Das geschieht ueber eine Switch-Anweisung. Damit wird die
     * Methode wendeArtefaktAn aufgerufen, dieser wird das jeweilige ausgewaehlte
     * Artefakt uebergeben sowie die Kaempfer-Instanzen "spieler" und "gegner".
     *
     * @param event Das Event, dem der Methodenaufruf entspricht.
     *
     * @pre Die Methode muss von einer Pane aufgerufen werden. Deren ID muss aus
     * einem Bezeichner, einem Unterstricht und danach dem Namen des Artefakt
     * {Schwert, Statue, Ring} bestehen. Die verwendeten Methoden, Klassen, Instanzen
     * und Interfaces muessen erreichbar sein.
     *
     * @post Das Artefakt wurde gegen den Gegner eingesetzt.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleArtefaktEinsetzen (MouseEvent event)
    {
        GameFile instanz = GameFile.getInstanz();
        wendeArtefaktAn(switch (((Pane) (event.getSource())).getId().split(Strings.UNTERSTRICH)[Konstanten.INT_ONE])
        {
            case Strings.SCHWERT -> instanz.getSchwert();
            case Strings.STATUE -> instanz.getStatue();
            case Strings.RING -> instanz.getRing();
            default -> null;
        }, spieler, gegner);
    }

    /**
     * Boolesche Methode, die bestimmt, ob das Wirken von Magie moeglich ist.
     * Das wird anhand der Zahl der Manapunkte festgemacht.
     *
     * @param kaempfer Der Angreifer, anhand dessen Manapunktezahl festgemacht wird,
     * ob das Wirken moeglich ist.
     *
     * @return True, wenn der kaempfer mehr als fuenf Manapunkte hat, false, wenn nicht.
     *
     * @pre Der Manapunktewert der Instanz der Klasse Kaempfer darf nicht null sein.
     *
     * @post Es wurde festgestellt, ob das Wirken von Magie erlaubt ist.
     *
     * @author Felix Ahrens.
     */
    public boolean magieWirkenMoeglich (Kaempfer kaempfer)
    {
        return (kaempfer.getManapunkte() > Konstanten.INT_FIVE);
    }

    /**
     * Methode, die bestimmt, ob Attackieren moeglich ist. Wichtig dafuer ist die
     * Entfernung und die Anzahl der verbleibenden Fernkaempfe.
     *
     * @param angreifer Der Angreifer, dessen Faehigkeit, den Verteidiger zu
     * attackieren, bestimmt werden soll.
     *
     * @param verteidiger Der Verteidiger.
     *
     * @return True, wenn die Entfernung zwischen angreifer und verteidiger geringer
     * als drei ist oder geringer als sechs ist und die Zahl verbleibender Fernkaempfe
     * des Angreifers groesser als null(Zahlenwert) ist.
     *
     * @pre Die Methoden muessen existieren und erreichbar sein. "berechneEntfernung"
     * muss eine Double-Zahl zurueckliefern.
     *
     * @post Es wurde bestimmt, ob das Attackieren vom angreifer aus moeglich ist.
     *
     * @author Felix Ahrens.
     */
    public boolean attackierenMoeglich (Kaempfer angreifer, Kaempfer verteidiger)
    {
        double entfernung = berechneEntfernung(angreifer, verteidiger);
        return (entfernung <= Konstanten.INT_THREE || (entfernung <= Konstanten.INT_SIX && angreifer.getFernkaempfeVerbleibenZahl() > Konstanten.INT_ZERO));
    }

    /**
     * Methode, die ueberpruefen soll, ob Einheiten eingesetzt werden koennen
     *
     * @return False, da Einheiten noch nicht im Spiel implementiert sind.
     *
     * @pre
     *
     * @post
     *
     * @author Felix Ahrens.
     */
    public boolean einheitenEinsetzenMoeglich ()
    {
        return false;
    }

    /**
     * Methode zum Attackieren. Ist das Attackieren moeglich, wird die Methode
     * "verwalteSchaden" aufgerufen, die den Schaden berechnet und austeilt.
     *
     * @param angreifer Der Angreifer, der den Verteidiger attackiert.
     *
     * @param verteidiger Der Verteidiger.
     *
     * @pre Die genutzten Methoden muessen erreichbar sein.
     *
     * @post Die Attacke wurde durchgefuehrt, wenn es moeglich war.
     *
     * @author Felix Ahrens.
     */
    public void attackiere (Kaempfer angreifer, Kaempfer verteidiger)
    {
        if (attackierenMoeglich(angreifer, verteidiger))
        {
            verwalteSchaden(angreifer, verteidiger);
        }

    }

    // ToDo: untere Methode

    /**
     * Methode zum Anwenden eines Artefakts. @David, @Enes muesst ihr noch machen
     *
     * @param artefakt Das anzuwendende Artefakt.
     *
     * @param angreifer Der Anwender des Artefakts.
     *
     * @param verteidiger Der Verteidiger, auf den das Artefakt angewendet wird.
     *
     * @pre /.
     *
     * @post /.
     *
     * @author Felix Ahrens.
     */
    public void wendeArtefaktAn (Artefakt artefakt, Kaempfer angreifer, Kaempfer verteidiger)
    {
        System.out.println(Strings.ARTEFAKT);
    }

    /**
     * Methode zum Verwalten vom Schaden. Diese berechnet den Schaden, den der
     * Angreifer dem Verteidiger hinzufuegt auf Basis der Attribute fuer Position,
     * Nahkampfwert, Fernkampfwert und Zahl der Fernkaempfe und wendet diesen
     * entsprechend auf den Verteidiger an.
     *
     * @param angreifer   Der Angreifer, von dem der Schaden ausgeht.
     *
     * @param verteidiger Der Verteidiger, auf den der Schaden angewendet wird.
     *
     * @pre Die verwendeten Parameter, Methoden, Konstanten und Instanzvariablen
     * (bzw. Getter und Setter davon) muessen vorhanden und erreichbar sein.
     *
     * @post Der berechnete Schaden wurde auf den Verteidiger angewendet und es
     * wurde ueberprueft, ob dieser nach der Attacke noch lebt.
     *
     * @author Felix Ahrens.
     */
    public void verwalteSchaden (Kaempfer angreifer, Kaempfer verteidiger)
    {
        double entfernung = berechneEntfernung(angreifer, verteidiger);
        int schaden = Konstanten.INT_ZERO;
        if (entfernung <= Konstanten.INT_THREE)
        {
            schaden = angreifer.getNahkampfWert();
        }
        else if (entfernung < Konstanten.INT_SIX & angreifer.getFernkaempfeVerbleibenZahl() > Konstanten.INT_ZERO)
        {
            schaden = angreifer.getFernkampfWert();
        }
        verteidiger.setGesundheit(verteidiger.getGesundheit() - schaden);
        updateKampfAnchorPanes();
        checkeLebtNoch();
    }

    /**
     * Methode zum Berechnen der Entfernung. Dabei wird die Entfernung mit dem Satz
     * des Pythagoras berechnet und als Double zurueckgegeben.
     *
     * @param kaempferEins Der erste uebergebene Kaempfer.
     *
     * @param kaempferZwei Der zweite uebergebene Kaempfer.
     *
     * @return Die direkte Entfernung zwischen den gespeicherten Positionen der
     * beiden uebergebenen Kaempfer.
     *
     * @pre Die Methoden und Konstanten muessen existieren und erreichbar sein.
     *
     * @post Die berechnete Entfernung zwischen den in den Instanzen der Klasse
     * Kaempfer gespeicherten wird zurueckgegeben.
     *
     * @author Felix Ahrens.
     */
    public double berechneEntfernung (Kaempfer kaempferEins, Kaempfer kaempferZwei)
    {
        int xentf = Math.abs(kaempferEins.getxPosition() - kaempferZwei.getxPosition());
        int yentf = Math.abs(kaempferEins.getyPosition() - kaempferZwei.getyPosition());
        return Math.sqrt((Math.pow(xentf, Konstanten.INT_TWO)) + (Math.pow(yentf, Konstanten.INT_TWO)));

    }

    /**
     * Methode, die ueberprueft, ob einer der im Kampf Beteiligten einen Gesundheitswert
     * kleiner eins hat. Ist dies der Fall, wird der Kampf ueber den Methodenaufruf
     * von "beendeKampf" beendet. Der Methode wird der festgestellte Sieger uebergeben.
     *
     * @pre Die genutzten Methoden und Variablen muessen erreichbar sein.
     * Die Strings und Konstanten muessen im jeweiligen Interface vorhanden sein.
     *
     * @post Der Kampf wurde, falls einer der Kaempfer keine Gesundheit mehr hat, beendet.
     * Der Dateiname der entsprechenden Nachkampfszene wurde in die Variable
     * "nachKampfSzenenName" gesetzt.
     *
     * @author Felix Ahrens.
     */
    public void checkeLebtNoch ()
    {
        if (spieler.getGesundheit() < Konstanten.INT_ONE)
        {
            nachKampfSzenenName = Strings.FXML_PLAYER_REBORN;
            beendeKampf(gegner);
        }
        else if (gegner.getGesundheit() < Konstanten.INT_ONE)
        {
            nachKampfSzenenName = Strings.FXML_STADT;
            beendeKampf(spieler);
        }

    }

    /**
     * Methode, mit der der Gegner den kuerzesten Weg zum Spieler nimmt.
     * Da die Spiellogik, insbesondere zu Kampfhandlungen gemacht wurde, wurde
     * diese Methode entwurfen, um wenigstens ganz banale Kampfhandlungen zu ermoegichen.
     *
     * @pre Die Methoden, insbesondere in der Klasse Kaempfer, muessen existieren
     * und erreichbar sein.
     *
     * @post Der Gegner wurde auf dem kuerzesten Weg in Richtung Kaempfer bewegt.
     *
     * @author Felix Ahrens.
     */
    public void bewegeGegnerDynamisch ()
    {
        int gegnerX = gegner.getxPosition();
        int gegnerY = gegner.getyPosition();
        int spielerX = spieler.getxPosition();
        int spielerY = spieler.getyPosition();
        int xDiff = spielerX - gegnerX;
        int yDiff = spielerY - gegnerY;

        if (Math.abs(xDiff) > Math.abs(yDiff))
        {
            if (xDiff > Konstanten.INT_ZERO && gegnerX + Konstanten.INT_ONE < Konstanten.INT_TWELVE)
            {
                gegner.setxPosition(gegnerX + Konstanten.INT_ONE);
            }
            else if (xDiff < Konstanten.INT_ZERO && gegnerX - Konstanten.INT_ONE >= Konstanten.INT_ZERO)
            {
                gegner.setxPosition(gegnerX - Konstanten.INT_ONE);
            }
        }
        else
        {
            if (yDiff > Konstanten.INT_ZERO && gegnerY + Konstanten.INT_ONE < Konstanten.INT_TWELVE)
            {
                gegner.setyPosition(gegnerY + Konstanten.INT_ONE);
            }
            else if (yDiff < Konstanten.INT_ZERO && gegnerY - Konstanten.INT_ONE >= Konstanten.INT_ZERO)
            {
                gegner.setyPosition(gegnerY - Konstanten.INT_ONE);
            }
        }
        updateCharacterPosition();
    }

    /**
     * Methode, die die AnchorPanes, die diverse Informationen zum Kampf und zu
     * Werten enthalten, aktualisieren lassen.
     *
     * @pre Die GUI-Elemente muessen in der FXML-Datei und der Klasse existieren.
     * Alle anderen Methoden, Variablen und Instanzen muessen auch existieren und
     * erreichbar sein.
     *
     * @post Die AnchorPanes von Spieler und Gegner wurden auf den neuesten Stand gebracht.
     *
     * @author Felix Ahrens.
     */
    public void updateKampfAnchorPanes ()
    {
        Charakter leader = GameFile.getInstanz().getLeader();
        for (Node node : kaempferDatenVBox.getChildren())
        {
            System.out.println(((ProgressBar) ((HBox) node).getChildren().get(Konstanten.INT_ONE)).getProgress());
            ((ProgressBar) ((HBox) node).getChildren().get(Konstanten.INT_ONE)).setProgress((double) (switch (((Text) ((HBox) node).getChildren().get(Konstanten.INT_ZERO)).getText())
            {
                case Strings.GESUNDHEIT -> leader.getGesundheit();
                case Strings.SCHILD -> leader.getSchild();
                case Strings.MANAPUNKTE -> leader.getManapunkte();
                case Strings.NAHKAMPFWERT -> leader.getNahkampfWert();
                case Strings.FERNKAMPFWERT -> leader.getFernkampfWert();
                case Strings.FERNKAEMPFE_VERBLEIBEND -> leader.getFernkaempfeVerbleibenZahl();
                case Strings.AUSWEICHEN_ZAHL -> leader.getZahlAusweichen();
                case Strings.MAGIERESISTENZ -> leader.getMagieResistenz();
                case Strings.BEWEGUNGSWEITE -> leader.getBewegungsWeite();
                case Strings.INITIATIVE -> leader.getInitiative();
                default -> Konstanten.INT_ZERO;
            }) / Konstanten.INT_TEN);
            System.out.println(((ProgressBar) ((HBox) node).getChildren().get(Konstanten.INT_ONE)).getProgress());
        }

        updateArtefakteDisplay();
    }

    /**
     * Methode, die das ArtefaktDisplay aktualisiert und die sich im Besitz
     * befindenden Artefakte in die HBox "artefaktDisplay" als Panes hinzufuegt.
     *
     * @pre Die genutzten Methoden muessen existieren. Die Singleton-Instanz der Klasse
     * GameFile muss gesetzt sein.
     *
     * @post Das ArtefaktDisplay wurde aktualisiert und die Artefakte, die als "imBesitz"
     * gespeicherten Artefakte sind als Panes in der HBox "artefakteDisplay" fuer
     * die spielende Person sichtbar.
     *
     * @author Felix Ahrens.
     */
    public void updateArtefakteDisplay ()
    {
        GameFile instanz = GameFile.getInstanz();
        schwert_Pane.setVisible(instanz.getSchwert().istImBesitz());
        statue_Pane.setVisible(instanz.getStatue().istImBesitz());
        ring_Pane.setVisible(instanz.getRing().istImBesitz());
    }
}