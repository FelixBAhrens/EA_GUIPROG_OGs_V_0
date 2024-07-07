package control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import res.Konstanten;
import res.Strings;


public class HauptquartierController extends PaneController
{
    @FXML
    public Label flappyBirdDetail;
    @FXML
    public Label sammelnDetail;
    @FXML
    public Label memoryDetail;
    @FXML
    public Label endgegnerDetail;

    public enum Mission {
        FLAPPY_BIRD(Strings.FLAPPY_BIRD),
        ENDGEGNER(Strings.ENDGEGNER),
        MEMORY(Strings.MEMORY),
        SAMMELN(Strings.SAMMELN);

        Mission (String missionsName)
                {
        }
    }
    private Mission missionsName;

    private static HauptquartierController instance;
    @FXML
    private Label detailLabel;
    @FXML
    public Button missionStarten;
    @FXML
    public AnchorPane flappyBirdPane;
    @FXML
    public AnchorPane sammelPane;
    @FXML
    public AnchorPane memoryPane;
    @FXML
    public AnchorPane endgegnerPane;
    @FXML
    public AnchorPane missionDetailPane;


    @FXML
    public void initialize ()
    {
        aktualisiereangezeigteBelohnungen();
    }

    /**
     * Methode, um das enum fuer die ausgewaehlte Mission passend zur uebergebenen ID zu setzen.
     * @param ID
     * @Author Felix Ahrens
     */
    public void setzeEnumNachID (String ID){
        switch (ID){
            case Strings.ID_BUTTON_FLAPPY_BIRD -> missionsName = Mission.FLAPPY_BIRD;
            case Strings.ID_BUTTON_ENDGEGNER -> missionsName = Mission.ENDGEGNER;
            case Strings.ID_BUTTON_MEMORY -> missionsName = Mission.MEMORY;
            case Strings.ID_BUTTON_SAMMELN -> missionsName = Mission.SAMMELN;
        }
    }

    /**
     * Methode zum Aktivieren der Mission
     * @param event
     * @Author Felix Ahrens
     */
    @FXML
    public void handleMissionWaehlen (MouseEvent event) {
        setzeEnumNachID(((Button)event.getSource()).getId());

        aktualisiereMissionDetailPane();
        missionDetailPane.setVisible(true);
    }

    /**
     * Methode zum aktualisieren der DetailPane.
     * @Autor Felix Ahrens
     */
    public void aktualisiereMissionDetailPane (){
        detailLabel.setText(switch (missionsName){
            case FLAPPY_BIRD -> Strings.TEXT_FLAPPY_BIRD;
            case ENDGEGNER -> Strings.TEXT_ENDGEGNER;
            case MEMORY -> Strings.TEXT_MEMORY;
            case SAMMELN -> Strings.TEXT_SAMMELN;
        });
    }

    /**
     *
     */
    public void aktualisiereangezeigteBelohnungen (){
        flappyBirdDetail.setText(erstelleBelohnungsText(Konstanten.BELOHNUNGEN_FLAPPY_BIRD));
        sammelnDetail.setText(erstelleBelohnungsText(Konstanten.BELOHNUNGEN_SAMMELN));
        memoryDetail.setText(erstelleBelohnungsText(Konstanten.BELOHNUNGEN_MEMORY));
        endgegnerDetail.setText(erstelleBelohnungsText(Konstanten.BELOHNUNGEN_ENDGEGNER));
    }

    public String erstelleBelohnungsText (int[] belohnungsArray){
        return (Strings.BELOHNUNGEN + Strings.DOPPELPUNKT + Strings.NEWLINE
            + Strings.HOLZ + Strings.DOPPELPUNKT + Strings.SPACE + belohnungsArray[Konstanten.INT_ZERO] + Strings.NEWLINE
            + Strings.STEIN + Strings.DOPPELPUNKT + Strings.SPACE + belohnungsArray[Konstanten.INT_ONE] + Strings.NEWLINE
            + Strings.GOLD + Strings.DOPPELPUNKT + Strings.SPACE + belohnungsArray[Konstanten.INT_TWO] + Strings.NEWLINE
            + Strings.GESUNDHEIT + Strings.DOPPELPUNKT + Strings.SPACE + belohnungsArray[Konstanten.INT_THREE] + Strings.NEWLINE
            + Strings.BANONAS + Strings.DOPPELPUNKT + Strings.SPACE + belohnungsArray[Konstanten.INT_FOUR] + Strings.NEWLINE);
    }

    /**
     * Methode, die eine Mission startet.
     * @Author Felix Ahrens
     */
    @FXML
    public void starteMission ()
    {
        SzenenManager.wechseleSzene(switch (missionsName){
            case FLAPPY_BIRD -> Strings.FXML_MISSION_FLAPPYBIRD;
            case ENDGEGNER -> Strings.FXML_MISSION_ENDGEGNER;
            case MEMORY -> Strings.FXML_MISSION_MEMORY;
            case SAMMELN -> Strings.FXML_MISSION_SAMMELN;
        });
    }


    @FXML
    public void openKarte ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_KARTENEW);
    }

}
