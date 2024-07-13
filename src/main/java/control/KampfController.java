package control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.GameFile;
import model.Kaempfer;
import res.Konstanten;
import res.Strings;

import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;

// ControllerController
public class KampfController extends ControllerController implements Initializable
{
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
    public ImageView hintergrund;
    @FXML
    private GridPane gridPane;
    @FXML
    private Rectangle spielerRec;
    @FXML
    private Rectangle gegnerRec;
    @FXML
    public AnchorPane kampfStartDialog;
    @FXML
    public AnchorPane kampfEndeDialog;
    @FXML
    public Text siegerText;
    @FXML
    public HBox timeLineHBox;

    @FXML
    public AnchorPane kaempferPane;
    @FXML
    public Label kaempferStats;
    @FXML
    public ProgressBar kaempferGesundheitsBar;
    @FXML
    public Button zugBeendenButton;
    @FXML
    public Button MagieButton;
    @FXML
    public Button AttackierenButton;
    @FXML
    public Button EinheitenButton;

    @FXML
    public AnchorPane gegnerPane;
    @FXML
    public Label gegnerStats;
    @FXML
    public ProgressBar gegnerGesundheitsBar;

    /**
     * Methode, um den Kampf zu initialisieren.
     * @pre Die Methoden, die Variablen und das Enum muessen vorhanden und zugreifbar sein.
     * @post Der "kampfStartDialog" wurde invisible gesetzt und, abhaengig vom Kampftyp, eine Methode zum Starten/Initialisieren
     *  eines bestimmten Kampfes aufgerufen
     * @author David Kien, Felix Ahrens
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
     * Methode, um den Endgegnerkampf zu initialisieren. Diese erstellt und setzt die Kaempfer und ruft Methoden auf,
     *  um Charaktere und Gegner zu initialisieren, diese dem kaempferArray hinzuzufuegen, die Timeline zu berechnen,
     *  die Charakterposition, die angezeigte Timeline und die "KampfAnchorPanes" zu aktualisieren. Ebenso wird die Methode
     *  "naechsterZug" aufgerufen.
     * @pre Die Methoden muessen vorhanden und erreichbar sein.
     * @post Der EndgegnerKampf wurde mit allen notwendigen Elementen initialisiert.
     * @Author David Kien, Felix Ahrens
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
     * Methode, die am Ende eines Zuges aufgerufen wird, und abhaengig von dem naechsten Element der Queue
     *  den naechsten Zug aufruft. Ist die Queue leer, wird der Kampf mit einem Unentschieden beendet, wenn
     *  kein Kaempfer gestorben ist.
     */
    public void naechsterZug () {
        updateTimeLine();
        if (!timeLine.isEmpty()){
            switch (timeLine.remove().getName()){
                case Strings.LEADER -> eigenZug();
                case Strings.ENDGEGNER -> gegnerZug();
            }
        }
        else {
            checkeLebtNoch();
            beendeKampf();
        }

    }

    /**
     * Methode, um den Stand-Alone-Kampf ueber Netzwerk zu starten. Bei einem Arenakampf ist die Szenerie anders.
     *  Da die Spiellogik und das Netzwerk zu diesem Zeitpunkt aber noch nicht durchdacht ist, kann die GUI hierfuer nicht naeher entwickelt werden.
     * @pre Hinter dem "ARENA_BILDPFAD" muss sich der Dateipfad zu einem Bild befinden, das eine Arena zeigt.
     * @post Das Hintergrundbild des Kampfes wurde auf das Bild einer Arena gesetzt.
     * @Author Felix Ahrens
     */
    public void starteArenaKampf ()
    {
        Image bild = new Image(getClass().getResource(Strings.ARENA_BILDPFAD).toExternalForm());
        hintergrund.setImage(bild);
        //Schnittstelle zum Netzwerk. Hier müssen die gegnerischen Werte dann geladen werden
    }

    /**
     * Methode zum Abschliessen des Kampfes. Der Sieger wurde von der checkeLebtNoch-Methode ermittelt.
     * @pre Die Strings muessen Interface enthalten sein, der KampfEndeDialog muss als AnchorPane in der Klasse vorhanden sein.
     * @post Der "kampfEndeDialog" wurde mit dem Sieger als Text ausgegeben.
     * @param sieger Die Instanz der Klasse Kaempfef, die im Kampf als Sieger festgestellt wurde, und der Methode als "Sieger" uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void beendeKampf (Kaempfer sieger)
    {
        siegerText.setText(sieger.getName() + Strings.HAT_GEWONNEN);
        kampfEndeDialog.setVisible(true);
    }

    /**
     * Ueberschriebene Methode, die den Kampf mit einem Unentschieden beendet, wenn die Timeline ausgelaufen ist.
     * @pre Die Strings muessen Interface enthalten sein, der KampfEndeDialog muss als AnchorPane in der Klasse vorhanden sein.
     * @post Der "kampfEndeDialog" wurde mit "Unentschieden" ausgegeben. Die darauf folgende Szene wurde als Stadt "angemeldet".
     * @Author Felix Ahrens
     */
    public void beendeKampf () {
        siegerText.setText(Strings.UNENTSCHIEDEN);
        nachKampfSzenenName = Strings.FXML_STADT;
        kampfEndeDialog.setVisible(true);
    }

    /**
     * Initialize-Methode, deren Verwendung fuer FXML-Controllerklassen verpflichtend ist.
     * @pre Die Methoden und Variablen muessen vorhanden sein.
     * @post Der "kampfStartDialog" ist visible,
     * @param location
     * @param resources
     * @author David Kien, Felix Ahrens
     * @TODO @DAVID Bitte auskommentieren das hier ist dein bereich
     */
    @FXML
    public void initialize (URL location, ResourceBundle resources)
    {
        kampfStartDialog.setVisible(true);
        gridPane.sceneProperty().addListener(((observableValue, oldScene, newScene) ->
        {
            if (newScene != null)
            {
                newScene.setOnKeyPressed(this::handleKeyPress);
            }
        }));
    }

    /**
     * Methode, die den von der nutzenden Person spielbaren Zug steuert. In dieser Methode werden Nutzereingaben freigeschaltet,
     *  die nur gemacht werden können, wenn der Mensch am Zug ist. Das Gegenteil dazu ist die Methode "gegnerZug()", die den Gegner (Bot) steuert.
     * @pre "zugBeenden" muss ein Button sein, der sich in der FXML-Datei "kampf-view.fxml" befindet. Der Button muss auch im Contoller sein.
     * @post Der Knopf wurde freigeschaltet und diesem ein Eventlistener hinzugefuegt, der den Zug beendet und die Methode "naechsterZug" aufruft.
     * @Author Felix Ahrens
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
     * Methode, die Methoden zum Bestimmen der Ausfuehrbarkeit von Kampfhandlungen aufruft, um, falls ja, die zugehoerigen Buttons zu aktivieren.
     * @pre Die Methoden und Buttons muessen Existieren und erreichbar sein. Die jeweils aktivierten Buttons duerfen nur die
     *  Handlung ausfuehren, die von der jeweiligen Methode, die hier aufgerufen wurde, als machbar bestaetigt wurde.
     * @post Die Buttons, deren zugehoerige Handlung von den Methoden als ausfuehrbar eingestuft wurde, wurden aktiviert.
     * @Author Felix Ahrens
     */
    public void aktiviereButtonsWennMoeglich (){
        if (magieWirkenMoeglich(spieler)){
            MagieButton.setDisable(false);
        }
        if (attackierenMoeglich(spieler, gegner)) {
            AttackierenButton.setDisable(false);
        }
        if (einheitenEinsetzenMoeglich()){
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
     * Methode zum Verlassen der Kampfszene. Diese wird durch einen Button im "kampfBeendenDialog" aufgerufen und setzt
     *  die naechste Szene auf die Szene, deren Name in "nachKampfSzenenName" gespeichert ist.
     * @pre In "nachKampfSzenenName" muss ein gueltiger Dateiname fuer eine existierende FXML-Datei gespeichert sein.
     *  Die Methode "wechseleSzene" in der Klasse SzenenManager muss vorhanden sein und die ihr uebergebene FXML-Datei lesen und als neue Szene setzen.
     * @Author Felix Ahrens
     */
    @FXML
    public void verlasseKampfSzene ()
    {
        SzenenManager.wechseleSzene(nachKampfSzenenName);
    }

    /**
     * Methode zum Ausfuehren des Gegnerzuges. Diese steuert den Gegner (Bot) indem sie verschiedene Methoden aufruft.
     * @pre Die Methoden muessen existieren und Design-By-Contract erfuellen.
     * @post Der gegnerische Zug wurde ausgefuehrt. Dabei wurde die Timeline aktualisiert, eine Sekunde gewartet, der Gegner bewegt und Attakiert.
     * @Author Felix Ahrens
     */
    private void gegnerZug ()
    {
        updateTimeLine();
        halteAn(Konstanten.INT_ONE_THOUSAND);
        bewegeGegnerDynamisch();
        attackiere(gegner, spieler);
        naechsterZug();
    }

    /**
     * CreateMap-Methode
     *
     * @author David Kien
     */
    private void createMap ()
    {
        for (int row = Konstanten.INT_ZERO; row < Konstanten.INT_TWELVE; row++)
        {
            for (int col = Konstanten.INT_ZERO; col < Konstanten.INT_TWELVE; col++)
            {
                Rectangle tile = new Rectangle(Konstanten.INT_FOURTY_FIVE, Konstanten.INT_FOURTY_FIVE);
                tile.setFill(Color.LIGHTGRAY);
                tile.setStroke(Color.BLACK);
                gridPane.add(tile, col, row);
            }
        }
    }

    /**
     * Methode, die die Timeline berechnen soll. Tut sie aber nicht, weil ich fuer die GUI verantwortlich bin.
     *  Aber ich brauche die Methode, um daraus eben eine Timeline anzeigen zu koennen. Deshalb diese voreingestellte Reihenfolge.
     * @pre Im uebergebenen Kaempferarray muessen die Teilnehmer des Kampfes enthalten sein
     * @post die Queue "timeLine" enthaelt eine Reihenfolge der Kaempfer.
     * @param alleKaempferArray Die Kaempfer des Kampfes
     * @Author Felix Ahrens
     * @TODO hier noch Zufall hinzufuegen
     */
    public void berechneTimeLine (Kaempfer[] alleKaempferArray) {
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
     * initialize Charakter - Methode
     *
     * @author David Kien
     */
    private void initialisiereCharacter ()
    {
        spielerRec = new Rectangle(Konstanten.INT_FOURTY_FIVE, Konstanten.INT_FOURTY_FIVE);
        spielerRec.setFill(Color.BLUE);
        gridPane.add(spielerRec, spieler.getxPosition(), spieler.getyPosition());
    }

    /**
     * Methode, die eine Rectangle erstellt und sie der "gridPane" an der fuer den Gegner spezifizierten Position hinzufuegt.
     * @pre 
     *
     * @author Felix Ahrens
     */
    private void initialisiereGegner ()
    {
        gegnerRec = new Rectangle(Konstanten.INT_FOURTY_FIVE, Konstanten.INT_FOURTY_FIVE);
        gegnerRec.setFill(Color.RED);
        gridPane.add(gegnerRec, gegner.getxPosition(), gegner.getyPosition());
    }

    /**
     * Handler fuer ein KeyEvent
     *
     * @param keyEvent Das Ereignis, das durch eine Tastenaktion ausgeloest wurde und zum Methodenaufruf gefuehrt hat.
     * @author David Kien
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
            case P:
                wendeArtefaktAn(spieler, gegner);
                break;
        }
        updateCharacterPosition();
    }

    /**
     * Methode, die von der spielenden Person aufgerufen wurde und die Methode "attackiere" mit den spielerseitigen
     *  Parametern aufruft.
     * @pre Die Methode "attackiere" muss existieren und die Kaempfer "spieler" und "gegner" muessen in der Klasse gespeichert sein.
     *  Der Kaempfer muss die vom Spieler steuerbare Instanz der Klasse Kaempfer sein.
     * @post Die Methode "attackiere" wurde mit dem Spieler (Leader) als Angreifer aufgerufen
     * @Author Felix Ahrens
     */
    @FXML
    public void handleAttackieren(){
        attackiere(spieler, gegner);
    }

    /**
     * Methode zum Aktualisieren der Position des Charakters
     *
     * @author David Kien
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
     * @pre Die verwendeten Methoden muessen existieren und erreichbar sein.
     * @post Die HBox "timeLineHBox" wurde entsprechend des Inhalts der Queue aktualisiert
     * @Author Felix Ahrens
     */
    public void updateTimeLine ()
    {
        timeLineHBox.getChildren().clear();
        for (Kaempfer kaempfer : timeLine){
            timeLineHBox.getChildren().addAll(kaempfer.toPane());
        }
    }

    /**
     * Methode zum Wirken von Magie. Dabei wird geprueft, ob das Wirken von Magie ueberhaupt moeglich ist.
     *  Falls ja, werden die Manapunkte des Angreifers auf 0 gesetzt und der Schaden berechnet. Der berechnete Schaden wird
     *  danach auf den gegner angewendet. Zuletzt werden Methoden zum ueberpruefen des Lebens der Beteiligten und zum
     *  Aktualisieren der AnchorPanes aufgerufen.
     * @pre Die genutzten Parameter und Methoden muessen von der Methode aus erreichbar sein und ihre Arbeit erfuellen.
     * @post Magie wurde gewirkt.
     * @Author Felix Ahrens
     */
    @FXML
    public void wirkeMagie ()
    {
        if (magieWirkenMoeglich(spieler)){
            spieler.setManapunkte(Konstanten.INT_ZERO);
            int schaden = spieler.getFernkampfWert()*Konstanten.INT_THREE
                    - gegner.getMagieResistenz()
                    - gegner.getSchild();
            gegner.setGesundheit(gegner.getGesundheit() - schaden);
            checkeLebtNoch();
            updateKampfAnchorPanes();
        }
    }

    /**
     * Boolesche Methode, die bestimmt, ob das Wirken von Magie moeglich ist. Das wird anhand der Zahl der Manapunkte festgemacht.
     * @pre Der Manapunktewert der Instanz der Klasse Kaempfer darf nicht null sein.
     * @post Es wurde festgestellt, ob das Wirken von Magie erlaubt ist.
     * @param kaempfer Der Angreifer, anhand dessen Manapunktezahl festgemacht wird, ob das Wirken moeglich ist
     * @return True, wenn der kaempfer mehr als 5 Manapunkte hat, false, wenn nicht.
     * @Author Felix Ahrens
     */
    public boolean magieWirkenMoeglich (Kaempfer kaempfer)
    {
        return (kaempfer.getManapunkte() > Konstanten.INT_FIVE);
    }

    /**
     * Methode, die bestimmt, ob Attackieren moeglich ist. Wichtig dafuer ist die Entfernung und die Anzahl der verbleibenden Fernkaempfe.
     * @pre Die Methoden muessen existieren und erreichbar sein. "berechneEntfernung" muss eine Double-Zahl zurueckliefern.
     * @post Es wurde bestimmt, ob das Attackieren vom angreifer aus moeglich ist.
     * @param angreifer Der Angreifer, dessen Faehigkeit, den Verteidiger zu attackieren, bestimmt werden soll
     * @param verteidiger Der Verteidiger.
     * @return True, wenn die Entfernung zwischen angreifer und verteidiger geringer als drei ist oder geringer als sechs
     *  ist und die Zahl verbleibender Fernkaempfe des Angreifers groesser als null(Zahlenwert) ist.
     * @Author Felix Ahrens
     */
    public boolean attackierenMoeglich (Kaempfer angreifer, Kaempfer verteidiger)
    {
        double entfernung = berechneEntfernung(angreifer, verteidiger);
        return (entfernung <= Konstanten.INT_THREE || (entfernung <= Konstanten.INT_SIX && angreifer.getFernkaempfeVerbleibenZahl() > Konstanten.INT_ZERO));
    }

    /**
     * Methode, die ueberpruefen soll, ob Einheiten eingesetzt werden koennen
     * @pre
     * @post
     * @return False, da Einheiten noch nicht im Spiel implementiert sind.
     * @Author Felix Ahrens
     */
    public boolean einheitenEinsetzenMoeglich(){
        return false;
    }

    /**
     * Methode zum Attackieren. Ist das Attackieren moeglich, wird die Methode "verwalteSchaden" aufgerufen, die
     *  den Schaden berechnet und austeilt.
     * @pre Die genutzten Methoden muessen erreichbar sein.
     * @post Die Attacke wurde durchgefuehrt, wenn es moeglich war
     * @param angreifer Der Angreifer, der den Verteidiger attackiert
     * @param verteidiger Der Verteidiger.
     * @Author Felix Ahrens
     */
    public void attackiere (Kaempfer angreifer, Kaempfer verteidiger)
    {
        if (attackierenMoeglich(angreifer, verteidiger))
        {
            verwalteSchaden(angreifer, verteidiger);
        }

    }

    public void wendeArtefaktAn (Kaempfer angreifer, Kaempfer verteidiger)
    {
        System.out.println(Strings.ARTEFAKT);
    }

    public void verwalteSchaden (Kaempfer angreifer, Kaempfer verteidiger)
    {
        double entfernung = berechneEntfernung(angreifer, verteidiger);
        int schaden = Konstanten.INT_ZERO;
        if (entfernung <= Konstanten.INT_THREE)
        {
            schaden = angreifer.getNahkampfWert();
        } else if (entfernung < Konstanten.INT_SIX & angreifer.getFernkaempfeVerbleibenZahl() > Konstanten.INT_ZERO)
        {
            schaden = angreifer.getFernkampfWert();
        }
        verteidiger.setGesundheit(verteidiger.getGesundheit() - schaden);
        updateKampfAnchorPanes();
        checkeLebtNoch();
    }

    public double berechneEntfernung (Kaempfer kaempferEins, Kaempfer kaempferZwei)
    {
        int xentf = Math.abs(kaempferEins.getxPosition() - kaempferZwei.getxPosition());
        int yentf = Math.abs(kaempferEins.getyPosition() - kaempferZwei.getyPosition());
        return Math.sqrt((Math.pow(xentf, Konstanten.INT_TWO)) + (Math.pow(yentf, Konstanten.INT_TWO)));

    }

    /**
     * Methode, die ueberprueft, ob einer der im Kampf Beteiligten einen Gesundheitswert kleiner eins hat. Ist dies der
     * Fall, wird
     *
     * @param
     * @author Felix Ahrens
     */
    public void checkeLebtNoch ()
    {
        if (spieler.getGesundheit() < Konstanten.INT_ONE)
        {
            nachKampfSzenenName = Strings.FXML_PLAYER_REBORN;
            beendeKampf(gegner);
        } else if (gegner.getGesundheit() < Konstanten.INT_ONE)
        {
            nachKampfSzenenName = Strings.FXML_STADT;
            beendeKampf(spieler);
        }

    }

    /**
     * Methode, mit der der Gegner den kuerzesten Weg zum Spieler nimmt.
     *
     * @author Felix Ahrens
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
            } else if (xDiff < Konstanten.INT_ZERO && gegnerX - Konstanten.INT_ONE >= Konstanten.INT_ZERO)
            {
                gegner.setxPosition(gegnerX - Konstanten.INT_ONE);
            }
        } else
        {
            if (yDiff > Konstanten.INT_ZERO && gegnerY + Konstanten.INT_ONE < Konstanten.INT_TWELVE)
            {
                gegner.setyPosition(gegnerY + Konstanten.INT_ONE);
            } else if (yDiff < Konstanten.INT_ZERO && gegnerY - Konstanten.INT_ONE >= Konstanten.INT_ZERO)
            {
                gegner.setyPosition(gegnerY - Konstanten.INT_ONE);
            }
        }
    }

    public void updateKampfAnchorPanes ()
    {
        kaempferGesundheitsBar.setProgress((double) spieler.getGesundheit() / (double) Konstanten.INT_ONE_HUNDRED);
        gegnerGesundheitsBar.setProgress((double) gegner.getGesundheit() / (double) Konstanten.INT_ONE_HUNDRED);
        System.out.println(gegner.getGesundheit());
    }

    /**
     * Methode zum warten.
     *
     * @param ms
     * @author Felix Ahrens
     */
    public void halteAn (long ms)
    {
        try
        {
            Thread.sleep(ms);
        } catch (InterruptedException e)
        {
            // Unterbrechung behandeln
            e.printStackTrace();
        }
    }
}
