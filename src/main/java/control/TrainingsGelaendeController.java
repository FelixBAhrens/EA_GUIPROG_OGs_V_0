package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.GameFile;
import res.Konstanten;
import res.Strings;

import java.security.KeyPair;

public class TrainingsGelaendeController extends PaneController {

    private int gesammeltesHolz;
    private int gesammelteNahrung;
    private int gesammeltesGold;
    private int eigenschaftsUpgradePreis;

    @FXML
    private Text gesammelteObjekte;

    @FXML
    private Label bewegungsWeite;

    @FXML
    private ProgressBar bewegungsWeiteBar;

    @FXML
    private Label fernkaempfeZahl;

    @FXML
    private ProgressBar fernkaempfeZahlBar;

    @FXML
    private Label fernkampfWert;

    @FXML
    private ProgressBar fernkampfWertBar;

    @FXML
    private Label gesundheit;

    @FXML
    private ProgressBar gesundheitBar;

    @FXML
    private Label magieResistenz;

    @FXML
    private ProgressBar magieResistenzBar;

    @FXML
    private Label manapunkte;

    @FXML
    private ProgressBar manapunkteBar;

    @FXML
    private Label nahkampfWert;

    @FXML
    private ProgressBar nahkampfWertBar;

    @FXML
    private Label schild;

    @FXML
    private ProgressBar schildBar;

    @FXML
    private Label zahlAusweichen;

    @FXML
    private ProgressBar zahlAusweichenBar;

    @FXML
    public AnchorPane baustelle;

    private int price = Konstanten.INT_FIVE;

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




    @FXML
    public void initialize()
    {
        GameFile instanz = GameFile.getInstanz();
        instanz.setHolzRessource(Konstanten.INT_ONE_THOUSAND);
        instanz.setGoldRessource(Konstanten.INT_ONE_THOUSAND);
        instanz.setSteinRessource(Konstanten.INT_ONE_THOUSAND);
        instanz.setGesundheitRessource(Konstanten.INT_ONE_THOUSAND);
        bestimmmeVerbesserungsPreise();
        speichereSpielstand();
        try
        {
            gesammeltesHolz = GameFile.getInstanz().getHolzRessource();
            gesammelteNahrung = GameFile.getInstanz().getGesundheitRessource();
            gesammeltesGold = GameFile.getInstanz().getGoldRessource();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        gesammelteObjekte.setText(Strings.HOLZ_SPACE + gesammeltesHolz + Strings.GESUNDHEIT_SPACE_KOMMA + gesammelteNahrung + Strings.GOLD_SPACE_KOMMA + gesammeltesGold);

        try
        {
            gesundheitPoints = instanz.getLeader().getGesundheit();
            schildPoints = instanz.getLeader().getSchild();
            manapunktePoints = instanz.getLeader().getManapunkte();
            nahkampfWertPoints = instanz.getLeader().getNahkampfWert();
            fernkampfWertPoints = instanz.getLeader().getFernkampfWert();
            fernkaempfeZahlPoints = instanz.getLeader().getFernkaempfeVerbleibenZahl();
            zahlAusweichenPoints = instanz.getLeader().getZahlAusweichen();
            magieResistenzPoints = instanz.getLeader().getMagieResistenz();
            bewegungsWeitePoints = instanz.getLeader().getBewegungsWeite();

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
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Eigenschaft, die den Preis fuer ein EigenschaftsUpgrade abhaengig von der eingestellten Schwierigkeit setzt.
     * @author Felix Ahrens
     */
    public void bestimmmeVerbesserungsPreise(){
        switch (GameFile.getInstanz().getSchwierigkeit()){
            case EINFACH -> eigenschaftsUpgradePreis = Konstanten.INT_THREE;
            case NORMAL -> eigenschaftsUpgradePreis = Konstanten.INT_FIVE;
            case SCHWER -> eigenschaftsUpgradePreis = Konstanten.INT_SEVEN;
        }
    }

    @FXML
    public void zeigeVerbessern()
    {
        aktuellerRessourcenstand.setText("Gold: " + GameFile.getInstanz().getGoldRessource() + ", Holz: " + GameFile.getInstanz().getHolzRessource());
        baustelle.setVisible(true);
    }

    @FXML
    public void verbessereGebaeude()
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
     * Methode, die eine Eigenschaft generell verbessert, abhaengig vom Buttontext, der sie aufruft.
     *
     * @precondition die texte auf den Buttons muessen mit den String-Namen im Interface Strings uebereinstimmen.
     * @postcondition Die zugehoerige Eigenschaft des jeweiligen Buttons wurde verbessert
     * @author Felix Ahrens
     */

    @FXML
    private void kaufDurchfuehren (ActionEvent event)
    {
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();
        int TrainingsgelaendeLevel = GameFile.getInstanz().getTrainingsgelaendeLevel();

        switch (buttonId)
        {
            case "schildVerbessern":
                if (fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_FOURTY, Konstanten.INT_ZERO, Konstanten.INT_ZERO) && schildPoints < Konstanten.INT_TEN)
                {
                    adjustProgress(++schildPoints, schild, schildBar);
                }
                break;

            case "manapunkteVerbessern":
                if (fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_FOURTY, Konstanten.INT_ZERO, Konstanten.INT_ZERO) && manapunktePoints < Konstanten.INT_TEN)
                {
                    adjustProgress(manapunktePoints++, manapunkte, manapunkteBar);
                }
                break;

            case "nahkampfWertVerbessern":
                if (fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_FOURTY, Konstanten.INT_ZERO, Konstanten.INT_ZERO) && fernkampfWertPoints < Konstanten.INT_TEN)
                {
                    adjustProgress(nahkampfWertPoints++, nahkampfWert, nahkampfWertBar);
                }
                break;

            case "fernkampfWertVerbessern":
                if (fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_FOURTY, Konstanten.INT_ZERO, Konstanten.INT_ZERO) && fernkampfWertPoints < Konstanten.INT_TEN)
                {
                    adjustProgress(fernkampfWertPoints++, fernkampfWert, fernkampfWertBar);
                }
                break;

            case "fernkaempfeZahlVerbessern":
                if (fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_FOURTY, Konstanten.INT_ZERO, Konstanten.INT_ZERO) && fernkaempfeZahlPoints < Konstanten.INT_TEN)
                {
                    adjustProgress(fernkaempfeZahlPoints++, fernkaempfeZahl, fernkaempfeZahlBar);
                }
                break;

            case "zahlAusweichenVerbessern":
                if (fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_FOURTY, Konstanten.INT_ZERO, Konstanten.INT_ZERO) && zahlAusweichenPoints < Konstanten.INT_TEN)
                {
                    adjustProgress(zahlAusweichenPoints++, zahlAusweichen, zahlAusweichenBar);
                }
                break;

            case "magieResistenzVerbessern":
                if (fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_FOURTY, Konstanten.INT_ZERO, Konstanten.INT_ZERO) && magieResistenzPoints < Konstanten.INT_TEN)
                {
                    adjustProgress(magieResistenzPoints++, magieResistenz, magieResistenzBar);
                }
                break;

            case "bewegungsWeiteVerbessern":
                if (fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_FOURTY, Konstanten.INT_ZERO, Konstanten.INT_ZERO) && bewegungsWeitePoints < Konstanten.INT_TEN)
                {
                    adjustProgress(bewegungsWeitePoints++, bewegungsWeite, bewegungsWeiteBar);
                }
                break;
        }

        gesammelteObjekte.setText(Strings.HOLZ_SPACE + gesammeltesHolz + Strings.GESUNDHEIT_SPACE_KOMMA + gesammelteNahrung + Strings.GOLD_SPACE_KOMMA + gesammeltesGold);
        speichereSpielstand();
    }

    private void adjustProgress (int points, Label label, ProgressBar bar)
    {
        System.out.println(points + " " + label.getText() + " " + bar.getProgress());
        label.setText(String.valueOf(points));
        bar.setProgress((double) points / (double) Konstanten.INT_TEN);
    }
}
