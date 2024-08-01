package control;

// TODO: Mission soll sich nicht währen einer Mission in der Karte öffnen lassen und
// Es soll ein separates Fenster angezeigt werden wie viele objekte man gesammelt hat
// TODO: Stein als ressource hinzufügen
// holz statt wood usw.

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
 * Die Klasse KartenController bildet die Controllerklasse fuer die "karteNew-view.fxml".
 * In ihr befinden sich die Methoden zum Darstellen der Karte, zum Verwenden von Nutzereingaben
 * und zur generellen Kartenlogik.
 *
 * @author David Kien, Felix Ahrens
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
    private Rectangle wood;
    @FXML
    private Rectangle gold;
    @FXML
    private Rectangle health;
    @FXML
    private Rectangle missionStarter1;
    @FXML
    private Rectangle missionStarter2;
    @FXML
    private Rectangle shape1;

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
    public Label detailTextLabel;

    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();
    private BooleanProperty ePressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed).or(ePressed);
    private BooleanBinding movingHorizontally = wPressed.or(sPressed);
    private BooleanBinding movingVertically = aPressed.or(dPressed);
    private boolean onMissionStarter = false;

    private List<Rectangle> barriers = new ArrayList<>();

    private int woodCount = GameFile.getInstanz().getHolzRessource();
    private int healthCount = GameFile.getInstanz().getGesundheitRessource();
    private int goldCount = GameFile.getInstanz().getGoldRessource();
    private int movementVariable = Konstanten.INT_TWO;
    private int timeRemaining = Konstanten.INT_NINETY;

    private boolean missionStatus;

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
                moveY = handleMovement(shape1.getLayoutX(), shape1.getLayoutY() - movementVariable, moveY, -movementVariable);
            }
            if (aPressed.get())
            {
                moveX = handleMovement(shape1.getLayoutX() - movementVariable, shape1.getLayoutY(), moveX, -movementVariable);
            }
            if (sPressed.get())
            {
                moveY = handleMovement(shape1.getLayoutX(), shape1.getLayoutY() + movementVariable, moveY, movementVariable);
            }
            if (dPressed.get())
            {
                moveX = handleMovement(shape1.getLayoutX() + movementVariable, shape1.getLayoutY(), moveX, movementVariable);
            }

            if (movingHorizontally.get() && movingVertically.get())
            {
                moveX /= Math.sqrt(Konstanten.INT_TWO);
                moveY /= Math.sqrt(Konstanten.INT_TWO);
            }

            // Aktualisierung der Position der Spielfigur asierend auf der berechneten Bewegung.
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
     * hinzugefuegt, um den 'timer' iininobiuh
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
            } else
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
     * @pre Das Enum muss auf einen der Werte der Cases gesetzt sein. Die verwendeten GUI-Elemente, Methoden und
     * Konstanten muessen existieren und erreichbar sein.
     * @post Der "detailTextLabel"-Dialog wurde auf einen im Kontext sinnigen Text gesetzt.
     * @Author Felix Ahrens
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
     * Methode zum Starten der "sammelnMission". Diese manipuliert die GUI-Elemente so, dass sie in der Mission sinnig
     * verwendet werden koennen. Utility, wie der "missionTimer" wird sichtbar geschaltet.
     *
     * @pre Die GUI-Elemente muessen existieren und von der Methode manipulierbar sein. Die verwendeten Methoden und
     * Konstanten muessen erreichbar sein.
     * @post Die GUI fuer die "sammelnMission" wurde sinnvoll gesetzt.
     * @Author David Kien, Felix Ahrens
     */
    public void starteSammelnMission ()
    {
        startenDialog.setVisible(false);
        healthCount = Konstanten.INT_ZERO;
        missionTimer.setVisible(true);
        startCountdown();
        gesammelteObjekte.setText(Strings.GESUNDHEIT_SPACE + healthCount);
        wood.setVisible(false);
        gold.setVisible(false);
    }

    /**
     * Methode zum Starten der Standardkarte, wie sie aus der Stadt zum einfachen Sammeln von Ressourcen etc aufgerufen
     * wird. Die Methode initialisiert die GUI zum Anzeigen der gesammelten Objekte.
     *
     * @pre Das GUI-Element, die Methoden und die Konstanten muessen erreichbar sein. Die Singleton-Instanz der GameFile
     * muss gesetzt sein.
     * @post Das GUI-Element "gesammelteObjekte" zeigt die in der Karte gesammelten Ressourcen an.
     * @Author Felix Ahrens
     */
    public void starteStandardKarte ()
    {
        gesammelteObjekte.setText(Strings.HOLZ_SPACE + GameFile.getInstanz().getHolzRessource()
                + Strings.GESUNDHEIT_SPACE_KOMMA + GameFile.getInstanz().getGesundheitRessource()
                + Strings.GOLD_SPACE_KOMMA + GameFile.getInstanz().getGoldRessource());
    }

    /**
     * Methode, die das Fortfahren handled. Diese wird vom Button "detailFortfahrenButton" aufgerufen und ruft die
     * Methoden zum Starten des spezifischen Kartentyps auf.
     *
     * @pre Der "startenDialog" muss existieren. Das Enum "kartenTyp" muss gesetzt sein und einem der beiden Cases
     * entsprechen. Die verwendeten Methoden muessen existieren.
     * @post Die Kartenmethode wurde gestartet.
     * @Author Felix Ahrens
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
     * der herunterzaehlt.
     *
     * @pre Der 'missionTimer' muss initialisiert und sichtbar sein. Die verwendete
     * Konstante muss initialisiert sein.
     *
     * @post Der Countdown beginnt zu laufen und wird auf dem 'missionTimer'
     * angezeigt. Der Timer stoppt automatisch, wenn die Zeit abgelaufen ist.
     *
     * @author David Kien.
     */
    private void startCountdown ()
    {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(Konstanten.INT_ONE), event ->
        {
            timeRemaining--;
            missionTimer.setText(formatTime(timeRemaining));
            if (timeRemaining <= Konstanten.INT_ZERO)
            {
                ((Timeline) event.getSource()).stop();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
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
     * Richtet die Bewegungseinstellung ein, indem Event-Handler fuer Tastenereignisse
     * hinzugefuegt werden.
     *
     * @pre Die Szene 'scene' und die Tastenereignis-Methoden muessen verfuegbar sein.
     *
     * @post Bei Tastendruck- und -freigabeereignissen wurden die entsprechenden
     * Methoden aufgerufen, um die Bewegungsvariablen zu setzen.
     *
     * @author David Kien.
     */
    private void setupMovement ()
    {
        scene.setOnKeyPressed(e -> setMovementKeys(e.getCode(), true));
        scene.setOnKeyReleased(e -> setMovementKeys(e.getCode(), false));
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
        boolean intersects1 = shape1.getBoundsInParent().intersects(missionStarter1.getBoundsInParent());
        boolean intersects2 = shape1.getBoundsInParent().intersects(missionStarter2.getBoundsInParent());

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
            if (node instanceof Rectangle rectangle && !node.equals(shape1) && !node.equals(gold) && !node.equals(wood) && !node.equals(health) && !node.equals(missionStarter1) && !node.equals(missionStarter2))
            {
                barriers.add(rectangle);
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
     * @pre Die Bewegungseinstellung und die Hindernissliste muessen initialisiert sein.
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
        if (!checkCollisionWithBarriers(x, y, shape1))
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
        shape1.setLayoutX(shape1.getLayoutX() + moveX);
        shape1.setLayoutY(shape1.getLayoutY() + moveY);
    }

    /**
     * Ueberprueft, ob die Spielfigur Ressourcen eingesammelt hat und aktualisiert
     * den Status entsprechend.
     *
     * @pre Die Ressourcenrechtecke (wood, health, gold) und die Spielfigur (shape1)
     * muessen initialisiert sein.
     *
     * @post Falls eine Ressource eingesammelt wurde, wurde die entsprechende Ressource
     * innerhalb der Karte neu positioniert und der jeweilige Zaehler erhoeht.
     *
     * @author David Kien.
     */
    private void checkForResourceCollection ()
    {
        GameFile instanz = GameFile.getInstanz();

        if (checkResourceCollection(shape1.getBoundsInParent().intersects(wood.getBoundsInParent()), wood, () -> woodCount++, () -> {
            try
            {
                instanz.setHolzRessource(instanz.getHolzRessource() + Konstanten.INT_ONE);
            } catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }))
        {
            return;
        }

        if (checkResourceCollection(shape1.getBoundsInParent().intersects(health.getBoundsInParent()), health, () -> healthCount++, () ->
        {
            try
            {
                instanz.setGesundheitRessource(instanz.getGesundheitRessource() + Konstanten.INT_ONE);
            } catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }))
        {
            return;
        }

        checkResourceCollection(shape1.getBoundsInParent().intersects(gold.getBoundsInParent()), gold, () -> goldCount++, () ->
        {
            try
            {
                instanz.setGoldRessource(instanz.getGoldRessource() + Konstanten.INT_ONE);
            } catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        });

    }

    /**
     * Ueberprueft, ob die Spielfigur eine Ressource einsammelt und fuehrt die entsprechenden
     * Aktionen aus.
     *
     * @pre Die verwendeten Variablem muessen gueltige Werte haben.
     *
     * @post Falls die Spielfigur eine Ressource einsammelt, wurde der Zaehler erhoeht,
     * die Ressource innerhalb der Karte neu positioniert und der Status aktualisiert.
     *
     * @param intersects Gibt an, ob die Spielfigur mit einer Ressource kollidiert.
     *
     * @param resource Das Rechteck, dass die Ressource repraesentiert.
     *
     * @param incrementCount Ein Runnable, der den Ressourcen-Zaehler erhoeht.
     *
     * @param incrementResource Ein Runnable, der die Ressource im Spiel erhoeht.
     *
     * @return Ein boolean, der anzeigt, ob die Ressource eingesammelt wurde.
     *
     * @author David Kien.
     */
    private boolean checkResourceCollection (boolean intersects, Rectangle resource, Runnable incrementCount, Runnable incrementResource)
    {
        if (intersects && ePressed.get())
        {
            incrementResource(incrementCount, incrementResource);
            placeRandomlyWithinMap(resource);
            ePressed.set(false);
            return true;
        }
        return false;
    }

    /**
     * Erhoeht den Zaehler fuer eine gesammelte Ressource und aktualisiert die Anzeige.
     *
     * @pre Die Parameter muessen gueltige Werte haben.
     *
     * @post Der Zaehler fuer die angegebene Ressource wurde erhoeht und die Anzeige
     * aktualisiert, je nachdem, ob die Mission aktiv ist.
     *
     * TODO: Oberen Kommentar checken und die Kommentare ergänzen
     *
     * @param incrementCount
     * @param incrementResource
     */
    private void incrementResource (Runnable incrementCount, Runnable incrementResource)
    {
        try
        {
            incrementCount.run();
            incrementResource.run();
            if (missionStatus == true)
            {
                gesammelteObjekte.setText(String.format(Strings.GESUNDHEIT_PERCENT_D, healthCount));
            } else
            {
                gesammelteObjekte.setText(String.format(Strings.RESOURCES_PERCENTS_DS, woodCount, healthCount, goldCount));
            }
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    //@Author David Kien
    private void checkForCollections ()
    {
        placeRandomlyWithinMap(wood);
        placeRandomlyWithinMap(health);
        placeRandomlyWithinMap(gold);
    }

    //@Author David Kien
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
            intersects = barriers.stream().anyMatch(barrier -> barrier.getBoundsInParent().intersects(finalRandomX, finalRandomY, object.getWidth(), object.getHeight()));
        }
        while (intersects);

        object.setLayoutX(randomX);
        object.setLayoutY(randomY);
    }

    //@Author David Kien
    private boolean checkCollisionWithBarriers (double x, double y, Rectangle movingRectangle)
    {
        return barriers.stream().anyMatch(barrier -> barrier.getBoundsInParent().intersects(x, y, movingRectangle.getWidth(), movingRectangle.getHeight()));
    }

    /**
     * Ueberschriebene Methode zum Verwenden der Zurueck-Funktionalitaet. Diese ruft zusaetzlich die Methode
     * "stopSaving" auf, um das Speichern zu beenden.
     *
     * @pre Die verwendeten Methoden muessen erreichbar sein
     * @post Es wurde eine Szene zurueckgegangen und das Speichern gestoppt.
     * @Author David Kien, Felix Ahrens
     */
    @Override
    public void handleZurueck ()
    {
        stopSaving();
        SzenenManager.szeneZurueck();
    }
}
