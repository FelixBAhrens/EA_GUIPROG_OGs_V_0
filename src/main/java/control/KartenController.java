package control;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.GameFile;
import res.Konstanten;
import res.Strings;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Die Klasse KartenController bildet die Controllerklasse fuer die "karte-view.fxml".
 * In ihr befinden sich die Methoden zum Darstellen der Karte, zum Verwenden von Nutzereingaben
 * und zur generellen Kartenlogik.
 *
 * @author David Kien, Felix Ahrens.
 */
public class KartenController extends ControllerController implements Initializable
{
    /**
     * Enum fuer den Kartentyp. Wird verwendet, dem Controller die Art der Karte mitzuteilen,
     * bevor die FXML-Datei geladen wird.
     *
     * @author Felix Ahrens.
     */
    public enum KartenTyp
    {
        STANDARD_KARTE(Strings.KARTE_STANDARD),
        SAMMELN_MISSION(Strings.MISSION_SAMMELN);
        private final String beschreibung;

        KartenTyp (String beschreibung)
        {
            this.beschreibung = beschreibung;
        }
    }

    public static KartenTyp kartenTyp;

    @FXML
    private AnchorPane map;
    @FXML
    private AnchorPane scene;
    @FXML
    public AnchorPane startenDialog;
    @FXML
    private AnchorPane missionInfo;

    @FXML
    private Rectangle holz;
    @FXML
    private Rectangle gold;
    @FXML
    private Rectangle gesundheit;
    @FXML
    private Rectangle missionStarter1;
    @FXML
    private Rectangle missionStarter2;
    @FXML
    private Rectangle spieler;

    @FXML
    private Pane menuePane;
    @FXML
    private Pane missionStartenPane;
    @FXML
    private Pane hintergrundPane;

    @FXML
    private TextField gesammelteObjekte;
    @FXML
    private TextField missionTimer;

    @FXML
    private Label missionInfoText;
    @FXML
    public Label detailTextLabel;

    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();
    private BooleanProperty ePressed = new SimpleBooleanProperty();
    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed).or(ePressed);
    private BooleanBinding movingHorizontally = wPressed.or(sPressed);
    private BooleanBinding movingVertically = aPressed.or(dPressed);

    private Timeline countdownTimeline;

    private List<Rectangle> hindernisse = new ArrayList<>();

    private boolean onMissionStarter = false;
    private boolean missionStatus;
    private boolean istSpielPausiert;

    private int holzZaehler = GameFile.getInstanz().getHolzRessource();
    private int gesundheitZaehler = GameFile.getInstanz().getGesundheitRessource();
    private int goldZaehler = GameFile.getInstanz().getGoldRessource();
    private int movementVariable = Konstanten.INT_TWO;
    private int missionszeit = Konstanten.INT_NINETY;

    //--------------------------------------------------------------------------

    /**
     * Initialisiert eine neue Instanz von AnimationTimer, die kontinuierlich
     * aufgerufen wird, um Animationen zu aktualisieren und Eingaben zu verarbeiten.
     *
     * @author David Kien.
     */
    private final AnimationTimer timer = new AnimationTimer()
    {
        /**
         * Wird kontinuierlich aufgerufen waehrend der AnimationTimer laeuft.
         * Verarbeitet dabei aktuelle Benutzereingaben und aktualisiert die
         * Positionen der Spielfigur entsprechend.
         *
         * @pre Die verwendeten Variablen muessen korrekt initialisiert sein und den
         * Zustand der jeweiligen Tasten darstellen. Die verwendeten Konstanten muessen
         * definiert sein. Die Methoden 'handleMovement', 'updateShapePosition',
         * checkForResourceCollection' und 'checkForMissionStarterCollision' muessen
         * implementiert und funktionsfaehig sein.
         *
         * @post Die Position der Spielfigur 'shape1' wurde entsprechend der Benutzereingaben
         * aktualisiert. Falls eine Diagonale Bewegung festgestellt wurde, wurde die
         * Bewegungsgeschwindigkeit reduziert. Eine Ueberpruefung auf Ressourcensammlung
         * und Kollision wurde durchgefuehrt.
         *
         * @param timestamp Der Zeitstempel des aktuellen Frames in Nanosekunden.
         *
         * @author David Kien.
         */
        @Override
        public void handle (long timestamp)
        {
            double moveX = Konstanten.INT_ZERO;
            double moveY = Konstanten.INT_ZERO;

            if (wPressed.get())
            {
                // 'handleMovment' gibt die Bewegungung zurueck, wenn keine Kollision mit Barrieren erkannt wurde.
                moveY = handleMovement(spieler.getLayoutX(), spieler.getLayoutY() - movementVariable, moveY, -movementVariable);
            }
            if (aPressed.get())
            {
                // 'handleMovment' gibt die Bewegungung zurueck, wenn keine Kollision mit Barrieren erkannt wurde.
                moveX = handleMovement(spieler.getLayoutX() - movementVariable, spieler.getLayoutY(), moveX, -movementVariable);
            }
            if (sPressed.get())
            {
                // 'handleMovment' gibt die Bewegungung zurueck, wenn keine Kollision mit Barrieren erkannt wurde.
                moveY = handleMovement(spieler.getLayoutX(), spieler.getLayoutY() + movementVariable, moveY, movementVariable);
            }
            if (dPressed.get())
            {
                // 'handleMovment' gibt die Bewegungung zurueck, wenn keine Kollision mit Barrieren erkannt wurde.
                moveX = handleMovement(spieler.getLayoutX() + movementVariable, spieler.getLayoutY(), moveX, movementVariable);
            }

            // Wird bei diagonalen Bewegungen ausgefuehrt.
            if (movingHorizontally.get() && movingVertically.get())
            {
                /** Reduziert die Bewegungswerte bei diagonalen Bewegungen, um
                 * eine geleichmaessige Bewegungsgeschwindigkeit beizubehalten.
                 */
                moveX /= Math.sqrt(Konstanten.INT_TWO);
                moveY /= Math.sqrt(Konstanten.INT_TWO);
            }

            // Aktualisierung der Position der Spielfigur basierend auf der berechneten Bewegung.
            updateShapePosition(moveX, moveY);

            // Ueberpruefen, ob die Figur Ressourcen einsammelt.
            checkForResourceCollection();

            // Ueberpruefen, ob die Spielfigur auf ein Missionsobjekt trifft.
            checkForMissionStarterCollision();
        }
    };

    /**
     * Ueberschriebene Initialize-Methode. Ist verpflichtend fuer Controllerklassen von FXML-Dateien.
     *
     * @pre Das verwendete GUI-Element und die Methode muessen erreichbar sein.
     *
     * @post Der "startenDialog" wurde initialisiert und visible gesetzt.
     *
     * @param url Der Ort, von dem die FXML-Datei geladen wurde.
     *
     * @param resourceBundle Ein ResourceBundle, das zur Internationalisierung
     * verwendet werden kann.
     *
     * @author David Kien, Felix Ahrens.
     */
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle)
    {
        initialisiereDialog();
        startenDialog.setVisible(true);
    }

    /**
     * Initialisiert die notwendigen Komponenten und startet die Missionslogik.
     *
     * @pre Es wird erwartet, dass alle notwendigen Ressourcen und Objekte fuer die
     * Missionslogik vorbereitet und korrekt initialisiert sind. Das Objekt 'map'
     * sollte initialisiert sein und der Methode 'requestFocus()' bereit sein,
     * den Fokus zu erhalten. Ausserdem sollte der timer korrekt konfiguriert sein,
     * um bei Bedarf gestartet und gestoppt zu werden.
     *
     * @post Die Bewegungslogik wurde eingerichtet und ein Listener fuer Tastatureingaben
     * hinzugefuegt, um den 'timer' basierend auf die Tastatureingaben zu starten
     * und zu stoppen. Die Hindernisse wurden spezifiziert und der Fokus wurde auf
     * die Karte 'map' gesetzt, um sicherzustellen, dass sie Tastatur eingaben empfaengt.
     * Die Ressourcen wurden auf der Karte platziert und ein Mechanismus zum periodischen
     * Speichern des Missionsstatuses wurde gestartet.
     *
     * @author David Kien.
     */
    public void starteMissionsUtil ()
    {
        setupMovement();

        keyPressed.addListener((observableValue, oldValue, newValue) ->
        {
            if (newValue)
            {
                timer.start();
            }
            else
            {
                timer.stop();
            }
        });

        addBarriers();
        map.requestFocus();
        checkForCollections();

        startSaving();
    }

    /**
     * Methode zum I des StartDialogs. Der angezeigte Text ist abhaengig vom gesetzten Enum.
     *
     * @pre Das Enum muss auf einen der Werte der Cases gesetzt sein.
     * Die verwendeten GUI-Elemente, Methoden und Konstanten muessen existieren und erreichbar sein.
     *
     * @post Der "detailTextLabel"-Dialog wurde auf einen im Kontext sinnigen Text gesetzt.
     *
     * @author Felix Ahrens.
     */
    public void initialisiereDialog ()
    {
        detailTextLabel.setText(switch (kartenTyp)
        {
            case SAMMELN_MISSION -> Strings.TEXT_SAMMELN + Strings.NEWLINE + Strings.SAMMELN_MISSION_BESCHREIBUNG;
            case STANDARD_KARTE -> Strings.KARTE_BESCHREIBUNG;
        });
    }

    /**
     * Methode zum Starten der "sammelnMission". Diese manipuliert die GUI-Elemente so,
     * dass sie in der Mission sinnig verwendet werden koennen.
     * Utility, wie der "missionTimer" wird sichtbar geschaltet.
     *
     * @pre Die Methode erwartet, dass alle relevanten GUI-Elemente korrekt initialisiert sind.
     * Insbesondere müssen die GUI-Komponenten `startenDialog`, `missionTimer`, `gesammelteObjekte`,
     * `holz`, und `gold` vorhanden und bereit sein, modifiziert zu werden.
     * Ausserdem muss der `gesundheitZaehler` auf 0 gesetzt werden, bevor die Mission startet.
     * Die `startCountdown`-Methode muss funktional sein, um den Missionstimer zu starten.
     *
     *
     * @post Die Methode setzt den `gesundheitZaehler` auf Null, blendet den Startdialog
     * aus und macht den Missionstimer sichtbar. Der Zaehler für gesammelte Gesundheit
     * wird auf der Anzeige aktualisiert und die Countdown-Logik wird gestartet.
     * Waehrend der Mission werden die Ressourcen für Holz und Gold ausgeblendet.
     * Die Mission befindet sich nun im aktiven Zustand (`missionStatus` wird auf true gesetzt).
     *
     * @author David Kien, Felix Ahrens.
     */
    public void starteSammelnMission ()
    {
        // Der Dialog zur Erklaerung der Spielsteuerung wird nicht angezeigt.
        startenDialog.setVisible(false);

        // Die Missionstarter-Objekte werden versteckt.
        missionStarter1.setVisible(false);
        missionStarter2.setVisible(false);
        missionStartenPane.setVisible(false);

        // Die Anzahl an gesammelter Gesundheit wird fuer die Missionsrunde auf 0 gesetzt.
        gesundheitZaehler = Konstanten.INT_ZERO;

        // Der Missionstimer wird sichtbar gemacht und gestartet.
        missionTimer.setVisible(true);
        missionStatus = true;
        startCountdown();

        // Die Anzahl an gesammelter Gesundheit wird angezeigt.
        gesammelteObjekte.setText(Strings.GESUNDHEIT_SPACE + gesundheitZaehler);

        // Holz und Gold werden waehrend der Mission nicht auf der Karte platziert.
        holz.setVisible(false);
        gold.setVisible(false);
    }

    /**
     * Methode zum Starten der Standardkarte, wie sie aus der Stadt zum einfachen
     * Sammeln von Ressourcen etc aufgerufen wird.
     * Die Methode initialisiert die GUI zum Anzeigen der gesammelten Objekte.
     *
     * @pre Das GUI-Element, die Methoden und die Konstanten muessen erreichbar sein.
     * Die Singleton-Instanz der GameFile muss gesetzt sein.
     *
     * @post Das GUI-Element "gesammelteObjekte" zeigt die in der Karte gesammelten
     * Ressourcen an.
     *
     * @author Felix Ahrens.
     */
    public void starteStandardKarte ()
    {
        gesammelteObjekte.setText(Strings.HOLZ_SPACE + GameFile.getInstanz().getHolzRessource()
                + Strings.GESUNDHEIT_SPACE_KOMMA + GameFile.getInstanz().getGesundheitRessource()
                + Strings.GOLD_SPACE_KOMMA + GameFile.getInstanz().getGoldRessource());
    }

    /**
     * Methode, die das Fortfahren handled. Diese wird vom Button "detailFortfahrenButton"
     * aufgerufen und ruft die Methoden zum Starten des spezifischen Kartentyps auf.
     *
     * @pre Der "startenDialog" muss existieren. Das Enum "kartenTyp" muss gesetzt sein
     * und einem der beiden Cases entsprechen. Die verwendeten Methoden muessen existieren.
     *
     * @post Die Kartenmethode wurde gestartet.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleFortfahren ()
    {
        startenDialog.setVisible(false);
        switch (kartenTyp)
        {
            case SAMMELN_MISSION -> starteSammelnMission();
            case STANDARD_KARTE -> starteStandardKarte();
        }
        starteMissionsUtil();
    }

    /**
     * Die Methode startet den Timer fuer die Mission und zeigt einen Countdown an,
     * der herunterzaehlt. Sobald der Timer abgelaufen ist, wird der Timer gestoppt
     * und der Missionsstatus auf inaktiv gesetzt. Danach wird eine Methode aufgerufen,
     * um die gesammelte Gesundheit anzuzeigen.
     *
     * @pre Die Variablen 'missionszeit' und 'missionTimer' muessen initialisiert
     * sein. Die Methode 'formatTime(int)' muss verfuegbar sein, um die verbleibende
     * Zeit als String darzustellen.
     *
     * @post Der Countdown begann, die verbleibende Zeot anzuzeigen und der Timer wurde
     * bei Ablauf der Missionszeit gestoppt. Das GUI-Element zur Anzeige der Missionszeit
     * wurde aktualisiert, und der Zustand der Mission entsprechend angepasst. Bei Ablauf
     * des Timers wurde die Methode 'showCollectedHealth()' aufgerufen.
     *
     * @author David Kien.
     */
    private void startCountdown()
    {
        countdownTimeline = new Timeline(new KeyFrame(Duration.seconds(Konstanten.INT_ONE), event ->
        {
            missionszeit--;
            missionTimer.setText(formatTime(missionszeit));

            if (missionszeit <= Konstanten.INT_ZERO)
            {
                countdownTimeline.stop();
                missionStatus = false;
                showCollectedHealth();
            }
        }));

        countdownTimeline.setCycleCount(Timeline.INDEFINITE);
        countdownTimeline.play();
    }

    /**
     * Diese Methode zeigt die gesammelte Gesundheit nach dem Abschluss einer Mission an.
     * Sie setzt den Zustand des Spiels auf pausiert, deaktiviert die Bewegungssteuerung und
     * zeigt eine entsprechende Nachricht auf dem Bildschirm an, basierend auf der Menge der
     * gesammelten Gesundheit.
     *
     * @pre Die Variablen `gesundheitZaehler`, `missionInfoText`, `missionInfo` und
     * `istSpielPausiert` muessen initialisiert sein. Die Methode wird nach Ablauf der
     * Missionszeit aufgerufen.
     *
     * @post Das Spiel wurde pausiert, die Bewegungssteuerung deaktiviert, und eine Nachricht
     * angezeigt, die angibt, ob die Mission erfolgreich war oder nicht, basierend auf der gesammelten
     * Gesundheit. Das GUI-Element `missionInfo` wurde sichtbar gemacht und zeigt
     * die entsprechende Nachricht an. Der Nutzer hat die Moeglichkeit die Mission
     * erneut zu starten oder zum Hauotmenue zurueck zu kehren.
     *
     * @author David Kien.
     */
    private void showCollectedHealth ()
    {
        istSpielPausiert = true;

        wPressed.set(false);
        aPressed.set(false);
        sPressed.set(false);
        dPressed.set(false);

        missionInfoText.setText(String.valueOf(gesundheitZaehler));
        missionInfo.setVisible(true);

        if (gesundheitZaehler < Konstanten.INT_TWENTY)
        {
            missionInfoText.setText(Strings.GESUNDHEIT_SAMMELN_MISSION_FEHLGESCHLAGEN + gesundheitZaehler);
        }
        else
        {
            missionInfoText.setText(Strings.GESUNDHEIT_SAMMELN_MISSION_ERFOLGREICH + gesundheitZaehler);
        }
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Benutzer die Option "Erneut versuchen"
     * waehlt. Sie setzt den Status der Mission zurueck und startet sie neu.
     *
     * @pre Das Spiel muss sich in einem pausierten Zustand befinden, und alle relevaten
     * GUI-Elemente und Variablen muessen initialisiert sein.
     *
     * @post Das Spiel wurde auf den Anfangszustand der Mission zurueckgesetzt, der Timer
     * wurde neu gestartet und die Anzeige der gesammelten Gesundheit aktualisiert.
     * Alle relevanten GUI-Elemente wurden fuer die neue Mission vorbereitet.
     */
    @FXML
    private void erneutVersuchen ()
    {
        istSpielPausiert = false;

        missionszeit = Konstanten.INT_NINETY;
        missionStatus = true;

        gesundheitZaehler = Konstanten.INT_ZERO;

        gesammelteObjekte.setText(Strings.GESUNDHEIT_SPACE + gesundheitZaehler);

        missionInfo.setVisible(false);
        missionStartenPane.getChildren().clear();

        missionTimer.setVisible(true);
        startCountdown();

        starteSammelnMission();
    }

    /**
     * Formatiert die in Sekunden angegebene Zeit in ein Minuten-Sekunden-Format.
     *
     * @pre Die verwendete Konstante und das Formatierungsmuster 'Strings.FORMAT_TIME'
     * muessen definiert sein.
     *
     * @post Die gegebene Zeit wurde in einem String mit Minuten-Sekunden-Format umgewandelt
     * und zurueckgegeben.
     *
     * @param seconds Die Zeit in Sekunden, die Formatiert werden soll.
     *
     * @return Ein String im Format "MM:SS".
     *
     * @author David Kien.
     */
    private String formatTime (int seconds)
    {
        int minutes = seconds / Konstanten.INT_SIXTY;
        int secs = seconds % Konstanten.INT_SIXTY;
        return String.format(Strings.FORMAT_TIME, minutes, secs);
    }

    /**
     * Diese Methode richtet die Bewegungseinstellungen ein, indem Event-Handler fuer Tastenereignisse
     * hinzugefuegt werden. Diese Handler setzen oder deaktivieren die Bewegungsstatus-Variablen
     * basierend auf den gedrueckten Tasten und dem Spielstatus.
     *
     * @pre Die Szene `scene` muss initialisiert sein und die Tastenereignis-Methoden
     * muessen verfuegbar sein. Die Variable `istSpielPausiert` muss korrekt den Pausenzustand des
     * Spiels widerspiegeln.
     *
     * @post Wenn das Spiel nicht pausiert ist (`istSpielPausiert` ist `false`), wurden
     * die entsprechenden BooleanProperties für die Tastenbewegungen (`wPressed`, `aPressed`, `sPressed`,
     * `dPressed`) basierend auf dem Tastendruck gesetzt oder zurueckgesetzt. Bei gedrueckter Taste
     * wurde die Bewegung aktiviert, und bei losgelassener Taste wird die Bewegung deaktiviert.
     *
     * @author David Kien.
     */
    private void setupMovement ()
    {
        scene.setOnKeyPressed(e ->
        {
            if (!istSpielPausiert)
            {
                setMovementKeys(e.getCode(), true);
            }
        });
        scene.setOnKeyReleased(e ->
        {
            if (!istSpielPausiert)
            {
                setMovementKeys(e.getCode(), false);
            }
        });
    }

    /**
     * Setzt die Bewegungsstatus-Variablen basierend auf die gedrueckten Tasten.
     *
     * @pre Die KeyCode-Werte und Bewegungs-BooleanProperties muessen definiert sein.
     *
     * @post Die entsprechenden BooleanProperties werden basieren auf dem Tastendruck
     * gesetzt oder zurueckgesetzt.
     *
     * @param code Die Taste die gedrueckt oder losgelassen wurde.
     *
     * @param pressed Gibt an, ob die Taste gedrueckt (true) oder losgelassen (false) wurde.
     *
     * @author David Kien.
     */
    private void setMovementKeys (KeyCode code, boolean pressed)
    {
        switch (code)
        {
            case W -> wPressed.set(pressed);
            case A -> aPressed.set(pressed);
            case S -> sPressed.set(pressed);
            case D -> dPressed.set(pressed);
            case E -> ePressed.set(pressed);
        }
    }

    /**
     * Ueberprueft, ob die Spielfigur mit einem Missionstarter-Objekt kollidiert.
     *
     * @pre Die Rectangle-Objekte muessen initialisiert sein.
     *
     * @post Falls eine Kollision festgestellt wurde, wurde das im Zeitraum der
     * Kollision das Missionstarten-Pane angezeigt.
     *
     * @author David Kien.
     */
    private void checkForMissionStarterCollision ()
    {
        boolean intersects1 = spieler.getBoundsInParent().intersects(missionStarter1.getBoundsInParent());
        boolean intersects2 = spieler.getBoundsInParent().intersects(missionStarter2.getBoundsInParent());

        if (intersects1 || intersects2)
        {
            if (!onMissionStarter)
            {
                onMissionStarter = true;
                loadFXMLIntoPane(missionStartenPane, Strings.FXML_MISSION_STARTEN);
            }
        }
        else
        {
            if (onMissionStarter)
            {
                onMissionStarter = false;
                missionStartenPane.getChildren().clear();
            }
        }
    }

    /**
     * Fuegt alle Hindernisrechtecke (Barrieren) der Karte zu einer Liste hinzu, ausser
     * speziellen Objekten wie Ressourcen- oder Missionstarter-Rechtecke.
     *
     * @pre Die 'map' und die zu pruefenden Rectangle-Objekte muessen initialisiert sein.
     *
     * @post Alle nicht spezifizierten Rectangle-Objekte in der Karte wurden als
     * Barrieren in die Liste 'barriers' aufgenommen.
     *
     * @author David Kien.
     */
    private void addBarriers ()
    {
        for (Node node : map.getChildren())
        {
            if (node instanceof Rectangle rectangle && !node.equals(spieler) && !node.equals(gold) && !node.equals(holz) && !node.equals(gesundheit) && !node.equals(missionStarter1) && !node.equals(missionStarter2))
            {
                hindernisse.add(rectangle);
            }
        }
    }

    /**
     * Laedt eine FXML-Datei in ein angegebenes Pane.
     *
     * @pre Die FXML-Datei muss existieren und das Pane-Objekt muss initialisiert sein.
     *
     * @post Das angegeben Pane wird mit dem Inhalt der geladenen FXML-Datei aktualisiert.
     *
     * @param pane Das Pane, in das der FXML-Inhalt geladen werden soll.
     *
     * @param fxmlFile Der Pfad zur FXML-Datei, die geladen werden soll.
     *
     * @author David Kien.
     */
    private void loadFXMLIntoPane (Pane pane, String fxmlFile)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane newPane = loader.load();
            pane.getChildren().setAll(newPane);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Handhabt die Bewegung der Spielfigur und verhindert Kollisionen mit Hindernissen.
     *
     * @pre Die Bewegungseinstellung und die Hindernisliste muessen initialisiert sein.
     *
     * @post Wenn keine Kollision festgestell wurde, wurde die Bewegungsaenderung zurueckgegeben.
     *
     * @param x Die geplante x-Koordinate der Bewegung.
     *
     * @param y Die geplante y-Koordinate der Bewegung.
     *
     * @param move Der aktuelle Bewegungsbetrag.
     *
     * @param movementVariable Der Wert, um den die Bewegung geaendert werden soll.
     *
     * @return Die aktualisierte Bewegung, wenn keine Kollision erkannt wurde.
     *
     * @author David Kien.
     */
    private double handleMovement (double x, double y, double move, double movementVariable)
    {
        if (!checkCollisionWithBarriers(x, y, spieler))
        {
            move += movementVariable;
        }
        return move;
    }

    /**
     * Aktualisiert die Position der Spielfigur basierend auf den berechneten Bewegungswerten.
     *
     * @pre Die Spielfigur muss initialisiert sein und die Bewegungswerte muessen
     * gueltige Werte besitzen.
     *
     * @post Die Position der Spielfigur wurde entsprechend den uebergebeneden
     * Bewegungswerten aktualisiert.
     *
     * @param moveX Die horizontale Bewegungsaenderung.
     *
     * @param moveY Die vertikale Bewegungsaenderung.
     *
     * @author David Kien.
     */
    private void updateShapePosition (double moveX, double moveY)
    {
        spieler.setLayoutX(spieler.getLayoutX() + moveX);
        spieler.setLayoutY(spieler.getLayoutY() + moveY);
    }

    /**
     * Diese Methode ueberprueft, ob der Spieler mit Ressourcenobjekten kollidiert und
     * fuehrt entsprechende Aktionen zur Sammlung durch.
     *
     * @pre Die Instanz `GameFile` muss existieren, und die Rechtecke für
     * die Ressourcen (holz, gesundheit, gold) und der Spieler (`spieler`) muessen
     * initialisiert sein. Die Methode `checkResourceCollection` muss definiert sein
     * und korrekt funktionieren.
     *
     * @post Falls eine Ressource eingesammelt wurde, wurde der entsprechende
     * Zaehler (holzZaehler, gesundheitZaehler, goldZaehler) erhoeht, die zugehoerige
     * Methode des `GameFile`-Objekts aufgerufen, um die Ressource im Spiel zu
     * erhoehen, und die Methode beendet sich.
     *
     * @author David Kien.
     */
    private void checkForResourceCollection()
    {
        GameFile instanz = GameFile.getInstanz();

        if (checkResourceCollection(spieler.getBoundsInParent().intersects(holz.getBoundsInParent()), holz,
                    () -> holzZaehler++,
                    () -> instanz.setHolzRessource(instanz.getHolzRessource() + Konstanten.INT_ONE)) ||
            checkResourceCollection(spieler.getBoundsInParent().intersects(gesundheit.getBoundsInParent()), gesundheit,
                    () -> gesundheitZaehler++,
                    () -> instanz.setGesundheitRessource(instanz.getGesundheitRessource() + Konstanten.INT_ONE)) ||
            checkResourceCollection(spieler.getBoundsInParent().intersects(gold.getBoundsInParent()), gold,
                    () -> goldZaehler++,
                    () -> instanz.setGoldRessource(instanz.getGoldRessource() + Konstanten.INT_ONE)))
        {
            return;
        }
    }


    /**
     * Ueberprueft, ob der Spieler eine bestimmte Ressource einsammelt und fuehrt entsprechende
     * Aktionen aus, falls dies der Fall ist. Die Methode wird aufgerufen, wenn der Spieler
     * mit der Ressource kollidiert (`intersects` ist true) und die Aktionstaste (`ePressed`)
     * gedrueckt ist.
     *
     * @pre Die Parameter muessen gueltige Werte haben. Insbesondere muessen
     * `intersects` den Kollisionsstatus korrekt darstellen, `resource` die Ressource
     * repraesentieren, und `incrementCount` und `incrementResource` definierte
     * Aktionen fuer das Erhoehen des Zaehlers und das Aktualisieren der Ressource im Spiel
     * enthalten.
     *
     * @post Falls die Bedingung erfuellt war, dass der Spieler mit der Ressource
     * kollidiert und die Aktionstaste drueckte, wurde der Ressourcen-Zaehler erhoeht, die
     * Ressource innerhalb der Karte neu positioniert, und `ePressed` zurueckgesetzt.
     * Die Methode gab `true` zurueck, um anzuzeigen, dass die Ressource eingesammelt wurde.
     * Andernfalls wurde `false` zurueckgegeben.
     *
     * @param intersects Gibt an, ob die Spielfigur mit einer Ressource kollidiert.
     *
     * @param resource Das Rechteck, das die Ressource repraesentiert.
     *
     * @param incrementCount Ein Runnable, das den Ressourcen-Zaehler erhoeht.
     *
     * @param incrementResource Ein Runnable, das die Ressource im Spiel erhoeht.
     *
     * @return Ein boolean, der anzeigt, ob die Ressource eingesammelt wurde.
     *
     * @author David Kien.
     */
    private boolean checkResourceCollection (boolean intersects, Rectangle resource, Runnable incrementCount, Runnable incrementResource)
    {
        if (intersects && ePressed.get())
        {
            incrementCount.run();
            try
            {
                incrementResource.run();
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
            incrementResource();
            placeRandomlyWithinMap(resource);
            ePressed.set(false);
            return true;
        }
        return false;
    }

    /**
     * Aktualisiert die Anzeige der gesammelten Ressourcen im Spiel. Die Methode ueberprueft,
     * ob sich der Spieler in einem Missionsstatus befindet (`missionStatus`). Wenn eine Mission
     * aktiv ist, wird nur die Anzahl der gesammelten Gesundheitsressourcen angezeigt. Andernfalls
     * werden alle gesammelten Ressourcen (Holz, Gesundheit, Gold) angezeigt.
     *
     * @pre Die Ressourcen-Zaehler (`holzZaehler`, `gesundheitZaehler`, `goldZaehler`)
     * muessen korrekte Werte enthalten, und das Textfeld `gesammelteObjekte` muss initialisiert
     * und bereit sein, aktualisiert zu werden.
     *
     * @post Der Text in `gesammelteObjekte` wurde entsprechend dem aktuellen
     * Spielstatus und den gesammelten Ressourcen aktualisiert. Bei einem Fehler in der
     * Aktualisierung wurde eine `RuntimeException` geworfen.
     *
     * @author David Kien.
     */
    private void incrementResource ()
    {
        try
        {
            if (missionStatus)
            {
                gesammelteObjekte.setText(String.format(Strings.GESUNDHEIT_PERCENT_D, gesundheitZaehler));
            } else
            {
                gesammelteObjekte.setText(String.format(Strings.RESOURCES_PERCENTS_DS, holzZaehler, gesundheitZaehler, goldZaehler));
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


    private void checkForCollections ()
    {
        placeRandomlyWithinMap(holz);
        placeRandomlyWithinMap(gesundheit);
        placeRandomlyWithinMap(gold);
    }

    /**
     * Platziert ein uebergebenes Rechteck (`object`) zufaellig innerhalb der Karte (`map`),
     * ohne Kollisionen mit bestehenden Hindernissen zu verursachen. Die Positionierung wird
     * so lange wiederholt, bis eine Platzierung ohne Ueberschneidungen mit Hindernissen
     * (`hindernisse`) gefunden wird.
     *
     * @pre Das Rechteck `object` und die Liste der Hindernisse (`hindernisse`)
     * muessen initialisiert sein. Die Karte (`map`) muss eine gueltige Groessee haben.
     *
     * @post Das Rechteck `object` wurde an einer zufaelligen Position innerhalb der
     * Karte platziert, wobei sichergestellt wurde, dass es sich nicht mit anderen Hindernissen
     * ueberschneidet.
     *
     * @param object Das Rechteck, das zufaellig innerhalb der Karte platziert werden soll.
     *
     * @author David Kien.
     */
    private void placeRandomlyWithinMap (Rectangle object)
    {
        Random random = new Random();
        double paneWidth = map.getPrefWidth();
        double paneHeight = map.getPrefHeight();

        double randomX;
        double randomY;
        boolean intersects;

        do
        {
            randomX = random.nextDouble() * (paneWidth - object.getWidth());
            randomY = random.nextDouble() * (paneHeight - object.getHeight());
            double finalRandomY = randomY;
            double finalRandomX = randomX;
            intersects = hindernisse.stream().anyMatch(barrier -> barrier.getBoundsInParent().intersects(finalRandomX, finalRandomY, object.getWidth(), object.getHeight()));
        }
        while (intersects);

        object.setLayoutX(randomX);
        object.setLayoutY(randomY);
    }

    /**
     * Ueberprueft, ob ein Rechteck an einer gegebenen Position mit vorhandenen Hindernissen kollidiert.
     * Diese Methode nimmt die geplanten Koordinaten (`x`, `y`) und die Abmessungen eines sich bewegenden
     * Rechtecks (`movingRectangle`) und prueft, ob es eine Ueberschneidung mit einem der Hindernisse
     * auf der Karte gibt.
     *
     * @pre Die Liste `hindernisse` muss initialisiert und mit den Hindernisobjekten
     * gefuellt sein. `movingRectangle` muss ein gueltiges Rechteck-Objekt sein.
     *
     * @post Gibt `true` zurueck, wenn eine Kollision zwischen `movingRectangle`
     * und einem der Hindernisse festgestellt wurde, ansonsten `false`.
     *
     * @param x Die geplante x-Koordinate der oberen linken Ecke des sich bewegenden Rechtecks.
     *
     * @param y Die geplante y-Koordinate der oberen linken Ecke des sich bewegenden Rechtecks.
     *
     * @param movingRectangle Das Rechteck, dessen Kollision ueberprueft wird.
     *
     * @return `true` wenn eine Kollision erkannt wurde, ansonsten `false`.
     *
     * @author David Kien.
     */
    private boolean checkCollisionWithBarriers (double x, double y, Rectangle movingRectangle)
    {
        return hindernisse.stream().anyMatch(barrier -> barrier.getBoundsInParent().intersects(x, y, movingRectangle.getWidth(), movingRectangle.getHeight()));
    }

    /**
     * Ueberschriebene Methode zum Verwenden der Zurueck-Funktionalitaet.
     * Diese ruft zusaetzlich die Methode "stopSaving" auf, um das Speichern zu beenden.
     *
     * @pre Die verwendeten Methoden muessen erreichbar sein.
     *
     * @post Es wurde eine Szene zurueckgegangen und das Speichern gestoppt.
     *
     * @author David Kien, Felix Ahrens.
     */
    @Override
    public void handleZurueck ()
    {
        stopSaving();
        SzenenManager.szeneZurueck();
    }
}