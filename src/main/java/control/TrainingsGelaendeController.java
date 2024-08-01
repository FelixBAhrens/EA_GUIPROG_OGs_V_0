package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Charakter;
import model.GameFile;
import res.Konstanten;
import res.Strings;

/**
 * Die Klasse TrainingsGelaendeController bildet die Controllerklasse zur
 * "trainingsgelaende-view.fxml". Hier befinden sich saemtliche Methoden zur Behandlung
 * von Nutzereingaben und dessen Folgen sowie zur GUI-Manipulation und -ausgabe.
 *
 * @author David Kien, Felix Ahrens.
 */
public class TrainingsGelaendeController extends StadtController
{
    private int gesammeltesHolz;
    private int gesammelteNahrung;
    private int gesammeltesGold;
    private int eigenschaftsUpgradePreis;

    @FXML
    private Text gesammelteObjekte;

    @FXML
    private Label bewegungsWeite;
    @FXML
    private Label fernkaempfeZahl;
    @FXML
    private Label fernkampfWert;
    @FXML
    private Label magieResistenz;
    @FXML
    private Label manapunkte;
    @FXML
    private Label nahkampfWert;
    @FXML
    private Label schild;
    @FXML
    private Label zahlAusweichen;

    @FXML
    private ProgressBar bewegungsWeiteBar;
    @FXML
    private ProgressBar fernkaempfeZahlBar;
    @FXML
    private ProgressBar fernkampfWertBar;
    @FXML
    private ProgressBar magieResistenzBar;
    @FXML
    private ProgressBar manapunkteBar;
    @FXML
    private ProgressBar nahkampfWertBar;
    @FXML
    private ProgressBar schildBar;

    @FXML
    private ProgressBar zahlAusweichenBar;

    @FXML
    public AnchorPane baustelle;

    private int gesundheitPoints;
    private int schildPoints;
    private int manapunktePoints;
    private int nahkampfWertPoints;
    private int fernkampfWertPoints;
    private int fernkaempfeZahlPoints;
    private int zahlAusweichenPoints;
    private int magieResistenzPoints;
    private int bewegungsWeitePoints;

    @FXML
    private Text aktuellerRessourcenstand;

    @FXML
    private Button schildVerbessern;
    @FXML
    private Button manapunkteVerbessern;
    @FXML
    private Button nahkampfWertVerbessern;
    @FXML
    private Button fernkampfWertVerbessern;
    @FXML
    private Button fernkaempfeZahlVerbessern;
    @FXML
    private Button zahlAusweichenVerbessern;
    @FXML
    private Button magieResistenzVerbessern;
    @FXML
    private Button bewegungsWeiteVerbessern;

    //--------------------------------------------------------------------------

    /**
     * Initialisiert die Trainingsgelaende-Szene und laedt die relevanten Daten wie
     * Ressourcen und Charaktereigenschaften. Stellt die Benutzeroberflaeche entsprechend den
     * geladenen Daten ein und aktualisiert die Zustaende der Buttons.
     *
     * @pre Die Singleton-Instanz von GameFile muss existieren und korrekt initialisiert sein.
     * Die Methode `speichereSpielstand` muss definiert und funktional sein.
     * Die GUI-Elemente muessen korrekt initialisiert sein.
     *
     * @post Alle relevanten Ressourcen- und Charaktereigenschaften-Daten wurden geladen.
     * Die Benutzeroberflaeche wurde entsprechend den geladenen Daten angepasst.
     * Die Zustaende der Buttons wurden aktualisiert, um die Verfuegbarkeit von
     * Upgrades widerzuspiegeln.
     *
     * @throws RuntimeException Wenn beim Laden der Daten ein Fehler auftritt.
     *
     * @author David Kien.
     */
    @FXML
    public void initialize ()
    {
        GameFile instanz = GameFile.getInstanz();
        Charakter leader = instanz.getLeader();
        bestimmmeVerbesserungsPreise();
        speichereSpielstand();
        try
        {
            gesammeltesHolz = instanz.getHolzRessource();
            gesammelteNahrung = instanz.getGesundheitRessource();
            gesammeltesGold = instanz.getGoldRessource();

            gesundheitPoints = leader.getGesundheit();
            schildPoints = leader.getSchild();
            manapunktePoints = leader.getManapunkte();
            nahkampfWertPoints = leader.getNahkampfWert();
            fernkampfWertPoints = leader.getFernkampfWert();
            fernkaempfeZahlPoints = leader.getFernkaempfeVerbleibenZahl();
            zahlAusweichenPoints = leader.getZahlAusweichen();
            magieResistenzPoints = leader.getMagieResistenz();
            bewegungsWeitePoints = leader.getBewegungsWeite();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        adjustInformation();
        updateButtonStates();
    }

    /**
     * Aktualisiert die Informationen auf der Benutzeroberflaeche, einschliesslich der
     * gesammelten Objekte und der Charaktereigenschaften sowie deren Fortschrittsbalken.
     *
     * @pre Alle relevanten Ressourcen- und Eigenschaftsvariablen muessen korrekt initialisiert sein.
     *
     * @post Die Labels und Fortschrittsbalken der Benutzeroberflaeche wurden mit den
     * aktuellen Werten aktualisiert.
     *
     * @author David Kien.
     */
    private void adjustInformation ()
    {
        gesammelteObjekte.setText(Strings.HOLZ_SPACE + gesammeltesHolz + Strings.GESUNDHEIT_SPACE_KOMMA + gesammelteNahrung + Strings.GOLD_SPACE_KOMMA + gesammeltesGold);

        schild.setText(String.valueOf(schildPoints));
        schildBar.setProgress((double) schildPoints / Konstanten.INT_TEN);

        manapunkte.setText(String.valueOf(manapunktePoints));
        manapunkteBar.setProgress((double) manapunktePoints / Konstanten.INT_TEN);

        nahkampfWert.setText(String.valueOf(nahkampfWertPoints));
        nahkampfWertBar.setProgress((double) nahkampfWertPoints / Konstanten.INT_TEN);

        fernkampfWert.setText(String.valueOf(fernkampfWertPoints));
        fernkampfWertBar.setProgress((double) fernkampfWertPoints / Konstanten.INT_TEN);

        fernkaempfeZahl.setText(String.valueOf(fernkaempfeZahlPoints));
        fernkaempfeZahlBar.setProgress((double) fernkaempfeZahlPoints / Konstanten.INT_TEN);

        zahlAusweichen.setText(String.valueOf(zahlAusweichenPoints));
        zahlAusweichenBar.setProgress((double) zahlAusweichenPoints / Konstanten.INT_TEN);

        magieResistenz.setText(String.valueOf(magieResistenzPoints));
        magieResistenzBar.setProgress((double) magieResistenzPoints / Konstanten.INT_TEN);

        bewegungsWeite.setText(String.valueOf(bewegungsWeitePoints));
        bewegungsWeiteBar.setProgress((double) bewegungsWeitePoints / Konstanten.INT_TEN);
    }

    /**
     * Eigenschaft, die den Preis fuer ein EigenschaftsUpgrade abhaengig von der
     * eingestellten Schwierigkeit setzt.
     *
     * @pre Die Singleton-Instanz muss gesetzt sein und die Schwierigkeit muss
     * eine von {EINFACH, NORMAL, SCHWER} sein. Der "eigenschaftsUpgradePreis" und die
     * Konstanten muessen erreichbar sein.
     *
     * @post Der "eigenschaftsUpgradePreis" wurde abhaengig von der gesetzten Schwierigkeit
     * auf einen Integer-wert gesetzt.
     *
     * @author Felix Ahrens.
     */
    public void bestimmmeVerbesserungsPreise ()
    {
        switch (GameFile.getInstanz().getSchwierigkeit())
        {
            case EINFACH -> eigenschaftsUpgradePreis = Konstanten.INT_THREE;
            case NORMAL -> eigenschaftsUpgradePreis = Konstanten.INT_FIVE;
            case SCHWER -> eigenschaftsUpgradePreis = Konstanten.INT_SEVEN;
        }
    }

    /**
     * Methode, die die Baustelle anzeigt.
     *
     * @pre Die Konstanten, GUI-Elemente und Methdoen muessen erreichbar sein.
     *
     * @post Die Baustelle und der "aktuellerRessourcenstand" werden angezeigt, um
     * der nutzenden Person informationen zu liefern.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void zeigeVerbessern ()
    {
        aktuellerRessourcenstand.setText(Strings.GOLD + Strings.DOPPELPUNKT + GameFile.getInstanz().getGoldRessource()
                + Strings.NEWLINE + Strings.HOLZ + Strings.DOPPELPUNKT + GameFile.getInstanz().getHolzRessource());

        baustelle.setVisible(true);
    }

    /**
     * Verbessert das Gebaeude, sofern genuegend Ressourcen vorhanden sind.
     * Blendet danach die Baustelle aus.
     *
     * @pre Die Ressourcen Gold, Holz und Stein muessen verfuegbar und ausreichend vorhanden sein,
     * um die Kosten zu decken. Das aktuelle Level des Trainingsgelaendes muss beruecksichtigt werden.
     *
     * @post Die Ressourcen wurden entsprechend reduziert, das Level des Trainingsgelaendes
     * wurde erhoeht. Die Baustelle wurde ausgeblendet.
     *
     * @todo Ueberpruefung, ob diese Funktionalitaet nicht mit der Kaufmethode integriert werden kann.
     *
     * @author David Kien.
     */
    @FXML
    public void verbessereGebaeude ()
    {
        if (GameFile.getInstanz().getGoldRessource() >= Konstanten.INT_FIVE && GameFile.getInstanz().getHolzRessource() >= Konstanten.INT_FIFTY && GameFile.getInstanz().getSteinRessource() >= Konstanten.INT_TEN && (GameFile.getInstanz().getTrainingsgelaendeLevel() == Konstanten.INT_ZERO))
        {
            GameFile.getInstanz().setGoldRessource(GameFile.getInstanz().getGoldRessource() - Konstanten.INT_FIVE);
            GameFile.getInstanz().setHolzRessource(GameFile.getInstanz().getHolzRessource() - Konstanten.INT_FIFTY);
            GameFile.getInstanz().setSteinRessource(GameFile.getInstanz().getSteinRessource() - Konstanten.INT_TEN);
            GameFile.getInstanz().setTrainingsgelaendeLevel(GameFile.getInstanz().getTrainingsgelaendeLevel() + Konstanten.INT_ONE);
        }
        baustelle.setVisible(false);
    }

    /**
     * Methode, die eine Eigenschaft generell verbessert, abhaengig vom Buttontext,
     * der sie aufruft.
     *
     * @pre die texte auf den Buttons muessen mit den String-Namen im Interface
     * Strings uebereinstimmen.
     *
     * @post Die zugehoerige Eigenschaft des jeweiligen Buttons wurde verbessert.
     *
     * @author David Kien, Felix Ahrens.
     */

    @FXML
    private void kaufDurchfuehren (ActionEvent event)
    {
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();
        switch (buttonId)
        {
            case Strings.SCHILD_VERBESSERN:
                if (schildPoints < Konstanten.INT_TEN && fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_FOURTY, Konstanten.INT_ZERO, Konstanten.INT_ZERO))
                {
                    adjustProgress(++schildPoints, schild, schildBar);
                }
                break;

            case Strings.MANAPUNKTE_VERBESSERN:
                if (manapunktePoints < Konstanten.INT_TEN && fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_FOURTY, Konstanten.INT_ZERO, Konstanten.INT_ZERO))
                {
                    adjustProgress(++manapunktePoints, manapunkte, manapunkteBar);
                }
                break;

            case Strings.NAHKAMPF_WERT_VERBESSERN:
                if (nahkampfWertPoints < Konstanten.INT_TEN && fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_FOURTY, Konstanten.INT_ZERO, Konstanten.INT_ZERO))
                {
                    adjustProgress(++nahkampfWertPoints, nahkampfWert, nahkampfWertBar);
                }
                break;

            case Strings.FERNKAMPF_WERT_VERBESSERN:
                if (fernkampfWertPoints < Konstanten.INT_TEN && fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_FOURTY, Konstanten.INT_ZERO, Konstanten.INT_ZERO))
                {
                    adjustProgress(++fernkampfWertPoints, fernkampfWert, fernkampfWertBar);
                }
                break;

            case Strings.FERNKAEMPFE_ZAHL_VERBESSERN:
                if (fernkaempfeZahlPoints < Konstanten.INT_TEN && fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_FOURTY, Konstanten.INT_ZERO, Konstanten.INT_ZERO))
                {
                    adjustProgress(++fernkaempfeZahlPoints, fernkaempfeZahl, fernkaempfeZahlBar);
                }
                break;

            case Strings.ZAHL_AUSWEICHEN_VERBESSERN:
                if (zahlAusweichenPoints < Konstanten.INT_TEN && fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_FOURTY, Konstanten.INT_ZERO, Konstanten.INT_ZERO))
                {
                    adjustProgress(++zahlAusweichenPoints, zahlAusweichen, zahlAusweichenBar);
                }
                break;

            case Strings.MAGIE_RESISTENZ_VERBESSERN:
                if (magieResistenzPoints < Konstanten.INT_TEN && fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_FOURTY, Konstanten.INT_ZERO, Konstanten.INT_ZERO))
                {
                    adjustProgress(++magieResistenzPoints, magieResistenz, magieResistenzBar);
                }
                break;

            case Strings.BEWEGUNGS_WEITE_VERBESSERN:
                if (bewegungsWeitePoints < Konstanten.INT_TEN && fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_FOURTY, Konstanten.INT_ZERO, Konstanten.INT_ZERO))
                {
                    adjustProgress(++bewegungsWeitePoints, bewegungsWeite, bewegungsWeiteBar);
                }
                break;
        }

        GameFile instanz = GameFile.getInstanz();

        gesammelteObjekte.setText(Strings.HOLZ_SPACE + instanz.getHolzRessource() + Strings.GESUNDHEIT_SPACE_KOMMA + instanz.getGesundheitRessource() + Strings.GOLD_SPACE_KOMMA + instanz.getGoldRessource());
        speichereSpielstand();

        updateButtonStates();
    }

    /**
     * Aktualisiert den Status der Upgrade-Buttons, basierend auf den aktuellen
     * Ressourcen und den maximalen Punktwerten.
     *
     * @pre Die Methode geht davon aus, dass alle relevanten Eigenschaften und Ressourcen
     * initialisiert und erreichbar sind.
     *
     * @post Die Upgrade-Buttons werden deaktiviert, wenn das entsprechende Attribut
     * bereits das Maximum erreicht hat oder nicht genuegend Ressourcen vorhanden sind,
     * um das Upgrade durchzufuehren.
     *
     * @author David Kien.
     */
    private void updateButtonStates ()
    {
        GameFile instanz = GameFile.getInstanz();
        boolean genugGold = instanz.getGoldRessource() >= Konstanten.INT_FOURTY;

        schildVerbessern.setDisable(schildPoints >= Konstanten.INT_TEN || !genugGold);
        manapunkteVerbessern.setDisable(manapunktePoints >= Konstanten.INT_TEN || !genugGold);
        nahkampfWertVerbessern.setDisable(nahkampfWertPoints >= Konstanten.INT_TEN || !genugGold);
        fernkampfWertVerbessern.setDisable(fernkampfWertPoints >= Konstanten.INT_TEN || !genugGold);
        fernkaempfeZahlVerbessern.setDisable(fernkaempfeZahlPoints >= Konstanten.INT_TEN || !genugGold);
        zahlAusweichenVerbessern.setDisable(zahlAusweichenPoints >= Konstanten.INT_TEN || !genugGold);
        magieResistenzVerbessern.setDisable(magieResistenzPoints >= Konstanten.INT_TEN || !genugGold);
        bewegungsWeiteVerbessern.setDisable(bewegungsWeitePoints >= Konstanten.INT_TEN || !genugGold);
    }

    /**
     * Passt den Fortschritt und die Anzeige einer bestimmten Charaktereigenschaft an.
     *
     * @pre Die Parameter `points`, `label` und `bar` muessen korrekt initialisiert sein.
     *
     * @post Die Anzeige des Labels und der Fortschrittsbalken wurden basierend
     * auf den neuen Punkten aktualisiert. Die Methode `adjustInformation` wurde
     * aufgerufen, um alle relevanten Informationen zu aktualisieren.
     *
     * @param points Die neue Punktzahl für die Eigenschaft, die angezeigt wird.
     *
     * @param label Das Label, das die Punktzahl der Eigenschaft anzeigt.
     *
     * @param bar Der Fortschrittsbalken, der den Fortschritt der Eigenschaft darstellt.
     *
     * @author David Kien.
     */
    private void adjustProgress (int points, Label label, ProgressBar bar)
    {
        System.out.println(points + " " + label.getText() + " " + bar.getProgress());
        label.setText(String.valueOf(points));
        bar.setProgress((double) points / (double) Konstanten.INT_TEN);
        adjustInformation();
    }
}