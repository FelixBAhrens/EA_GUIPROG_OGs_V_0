package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.GameFile;
import res.Konstanten;
import res.Strings;

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
    public void initialize()
    {
        bestimmmeVerbesserungsPreise();
        speichereSpielstand();
        try
        {
            gesammeltesHolz = GameFile.getInstance().getHolzRessource();
            gesammelteNahrung = GameFile.getInstance().getGesundheitRessource();
            gesammeltesGold = GameFile.getInstance().getGoldRessource();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        gesammelteObjekte.setText(Strings.HOLZ_SPACE + gesammeltesHolz + Strings.GESUNDHEIT_SPACE_KOMMA + gesammelteNahrung + Strings.GOLD_SPACE_KOMMA + gesammeltesGold);

        try
        {
            gesundheitPoints = GameFile.getInstance().getLeader().getGesundheit();
            schildPoints = GameFile.getInstance().getLeader().getSchild();
            manapunktePoints = GameFile.getInstance().getLeader().getManapunkte();
            nahkampfWertPoints = GameFile.getInstance().getLeader().getNahkampfWert();
            fernkampfWertPoints = GameFile.getInstance().getLeader().getFernkampfWert();
            fernkaempfeZahlPoints = GameFile.getInstance().getLeader().getFernkaempfeVerbleibenZahl();
            zahlAusweichenPoints = GameFile.getInstance().getLeader().getZahlAusweichen();
            magieResistenzPoints = GameFile.getInstance().getLeader().getMagieResistenz();
            bewegungsWeitePoints = GameFile.getInstance().getLeader().getBewegungsWeite();

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
        switch (GameFile.getInstance().getSchwierigkeit()){
            case Strings.STRING_EINFACH -> eigenschaftsUpgradePreis = Konstanten.INT_THREE;
            case Strings.STRING_NORMAL -> eigenschaftsUpgradePreis = Konstanten.INT_FIVE;
            case Strings.STRING_SCHWER -> eigenschaftsUpgradePreis = Konstanten.INT_SEVEN;
        }
    }

    @FXML
    public void zeigeVerbessern(){
        baustelle.setVisible(true);
    }

    @FXML
    public void verbessereGebaeude(){
        /*
        if (fuehreTransaktionDurchWennMoeglich(10,0,1,0,0)){
            //Hier trainingsgelaende verbessern
            baustelle.setVisible(false);
        } else {
            baustellentext.setText(Kannste nicht kaufen)
        }
         */


    }

    /**
     * Methode, die eine Eigenschaft generell verbessert, abhaengig vom Buttontext, der sie aufruft.
     *
     * @precondition die texte auf den Buttons muessen mit den String-Namen im Interface Strings uebereinstimmen.
     * @postcondition Die zugehoerige Eigenschaft des jeweiligen Buttons wurde verbessert
     * @author Felix Ahrens
     */
    @FXML
    public void verbessereEigenschaft(){

        if (fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_ZERO, Konstanten.INT_ZERO)){
            //Hier Verbesserung

        } else {

        }

    }


    @FXML
    private void schildVerbessern ()
    {
        try
        {
            if (!(schildPoints == Konstanten.INT_TEN) && gesammeltesGold >= Konstanten.INT_FIVE)
            {
                gesammeltesGold -= Konstanten.INT_FIVE;
                GameFile.getInstance().setHolzRessource(gesammeltesHolz);
                gesammelteObjekte.setText(Strings.HOLZ_SPACE + gesammeltesHolz + Strings.GESUNDHEIT_SPACE_KOMMA + gesammelteNahrung + Strings.GOLD_SPACE_KOMMA + gesammeltesGold);
                schildPoints++;
                GameFile.getInstance().getLeader().setGesundheit(schildPoints);
                schild.setText(String.valueOf(schildPoints));
                schildBar.setProgress((double) schildPoints / Konstanten.INT_TEN);
                speichereSpielstand();
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void manapunkteVerbessern ()
    {
        try
        {
            if (!(manapunktePoints == Konstanten.INT_TEN) && gesammeltesGold >= Konstanten.INT_FIVE)
            {
                gesammeltesGold -= Konstanten.INT_FIVE;
                GameFile.getInstance().setGoldRessource(gesammeltesGold);
                gesammelteObjekte.setText(Strings.HOLZ_SPACE + gesammeltesHolz + Strings.GESUNDHEIT_SPACE_KOMMA + gesammelteNahrung + Strings.GOLD_SPACE_KOMMA + gesammeltesGold);
                manapunktePoints++;
                GameFile.getInstance().getLeader().setManapunkte(manapunktePoints);
                manapunkte.setText(String.valueOf(manapunktePoints));
                manapunkteBar.setProgress((double) manapunktePoints / Konstanten.INT_TEN);
                speichereSpielstand();
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void nahkampfWertVerbessern ()
    {
        try
        {
            if (!(nahkampfWertPoints == Konstanten.INT_TEN) && gesammeltesGold >= Konstanten.INT_FIVE)
            {
                gesammeltesGold -= Konstanten.INT_FIVE;
                GameFile.getInstance().setGoldRessource(gesammeltesGold);
                gesammelteObjekte.setText(Strings.HOLZ_SPACE + gesammeltesHolz + Strings.GESUNDHEIT_SPACE_KOMMA + gesammelteNahrung + Strings.GOLD_SPACE_KOMMA + gesammeltesGold);
                nahkampfWertPoints++;
                GameFile.getInstance().getLeader().setNahkampfWert(nahkampfWertPoints);
                nahkampfWert.setText(String.valueOf(nahkampfWertPoints));
                nahkampfWertBar.setProgress((double) nahkampfWertPoints / Konstanten.INT_TEN);
                speichereSpielstand();
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void fernkampfWertVerbessern ()
    {
        try
        {
            if (!(fernkampfWertPoints == Konstanten.INT_TEN) && gesammeltesGold >= Konstanten.INT_FIVE)
            {
                gesammeltesGold -= Konstanten.INT_FIVE;
                GameFile.getInstance().setGoldRessource(gesammeltesGold);
                gesammelteObjekte.setText(Strings.HOLZ_SPACE + gesammeltesHolz + Strings.GESUNDHEIT_SPACE_KOMMA + gesammelteNahrung + Strings.GOLD_SPACE_KOMMA + gesammeltesGold);
                fernkampfWertPoints++;
                GameFile.getInstance().getLeader().setFernkampfWert(fernkampfWertPoints);
                fernkampfWert.setText(String.valueOf(fernkampfWertPoints));
                fernkampfWertBar.setProgress((double) fernkampfWertPoints / Konstanten.INT_TEN);
                speichereSpielstand();
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void fernkaempfeZahlVerbessern ()
    {
        try
        {
            if (!(fernkaempfeZahlPoints == Konstanten.INT_TEN) && gesammeltesGold >= Konstanten.INT_FIVE)
            {
                gesammeltesGold -= Konstanten.INT_FIVE;
                GameFile.getInstance().setGoldRessource(gesammeltesGold);
                gesammelteObjekte.setText(Strings.HOLZ_SPACE + gesammeltesHolz + Strings.GESUNDHEIT_SPACE_KOMMA + gesammelteNahrung + Strings.GOLD_SPACE_KOMMA + gesammeltesGold);
                fernkaempfeZahlPoints++;
                GameFile.getInstance().getLeader().setFernkaempfeVerbleibenZahl(fernkaempfeZahlPoints);
                fernkaempfeZahl.setText(String.valueOf(fernkaempfeZahlPoints));
                fernkaempfeZahlBar.setProgress((double) fernkaempfeZahlPoints / Konstanten.INT_TEN);
                speichereSpielstand();
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void zahlAusweichenVerbessern ()
    {
        try
        {
            if (!(zahlAusweichenPoints == Konstanten.INT_TEN) && gesammeltesGold >= Konstanten.INT_FIVE)
            {
                gesammeltesGold -= Konstanten.INT_FIVE;
                GameFile.getInstance().setGoldRessource(gesammeltesGold);
                gesammelteObjekte.setText(Strings.HOLZ_SPACE + gesammeltesHolz + Strings.GESUNDHEIT_SPACE_KOMMA + gesammelteNahrung + Strings.GOLD_SPACE_KOMMA + gesammeltesGold);
                zahlAusweichenPoints++;
                GameFile.getInstance().getLeader().setZahlAusweichen(zahlAusweichenPoints);
                zahlAusweichen.setText(String.valueOf(zahlAusweichenPoints));
                zahlAusweichenBar.setProgress((double) zahlAusweichenPoints / Konstanten.INT_TEN);
                speichereSpielstand();
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void magieResistenzVerbessern ()
    {
        try
        {
            if (!(magieResistenzPoints == Konstanten.INT_TEN) && gesammeltesGold >= Konstanten.INT_FIVE)
            {
                gesammeltesGold -= Konstanten.INT_FIVE;
                GameFile.getInstance().setGoldRessource(gesammeltesGold);
                gesammelteObjekte.setText(Strings.HOLZ_SPACE + gesammeltesHolz + Strings.GESUNDHEIT_SPACE_KOMMA + gesammelteNahrung + Strings.GOLD_SPACE_KOMMA + gesammeltesGold);
                magieResistenzPoints++;
                GameFile.getInstance().getLeader().setMagieResistenz(magieResistenzPoints);
                magieResistenz.setText(String.valueOf(magieResistenzPoints));
                magieResistenzBar.setProgress((double) magieResistenzPoints / Konstanten.INT_TEN);
                speichereSpielstand();
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void bewegungsWeiteVerbessern ()
    {
        try
        {
            if (!(bewegungsWeitePoints == Konstanten.INT_TEN) && gesammeltesGold >= Konstanten.INT_FIVE)
            {
                gesammeltesGold -= Konstanten.INT_FIVE;
                GameFile.getInstance().setGoldRessource(gesammeltesGold);
                gesammelteObjekte.setText(Strings.HOLZ_SPACE + gesammeltesHolz + Strings.GESUNDHEIT_SPACE_KOMMA + gesammelteNahrung + Strings.GOLD_SPACE_KOMMA + gesammeltesGold);
                bewegungsWeitePoints++;
                GameFile.getInstance().getLeader().setBewegungsWeite(bewegungsWeitePoints);
                bewegungsWeite.setText(String.valueOf(bewegungsWeitePoints));
                bewegungsWeiteBar.setProgress((double) bewegungsWeitePoints / Konstanten.INT_TEN);
                speichereSpielstand();
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

}
