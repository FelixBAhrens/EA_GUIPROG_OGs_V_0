package control;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import res.Strings;

public class HauptquartierController extends PaneController
{
    private static HauptquartierController instance;
    @FXML
    private Text detailText;

    @FXML
    private Button missionStarten;
    @FXML
    private Button flappyBirdWaehlen;
    @FXML
    private Button sammelnWaehlen;
    @FXML
    private Button memoryWaehlen;
    @FXML
    private Button endgegnerWaehlen;

    @FXML
    private AnchorPane flappyBirdPane;
    @FXML
    private AnchorPane sammelPane;
    @FXML
    private AnchorPane memoryPane;
    @FXML
    private AnchorPane endgegnerPane;
    private boolean karteFuerMission = false;

    public HauptquartierController ()
    {
        instance = this;
    }

    public static HauptquartierController getInstance()
    {
        return instance;
    }


    public boolean istKarteFuerMission ()
    {
        System.out.println(karteFuerMission + "HHHH");
        return karteFuerMission;
    }




    @FXML
    public void initialize()
    {

    }

    @FXML
    public void missionStartenPaneInitialisieren (MouseEvent event)
    {
        detailText.setText(event.getSource().toString());

    }

    @FXML
    public void verbergeMissionsDetails (MouseEvent event) {
        detailText.setText("");
    }

    @FXML
    private void flappyBirdGewaehlt ()
    {
        detailText.setText("Flappy Bird!");

        DropShadow glow = new DropShadow();
        glow.setColor(Color.GREEN);
        glow.setRadius(20);
        glow.setSpread(0.6);

        missionStarten.setEffect(glow);

        missionStarten.setOnMouseClicked(e -> SceneManager.changeScene(Strings.FXML_MISSION_FLAPPYBIRD));
    }

    @FXML
    private void sammelnGewaehlt ()
    {
        karteFuerMission = true;
        detailText.setText("Sammeln!");

        DropShadow glow = new DropShadow();
        glow.setColor(Color.GREEN);
        glow.setRadius(20);
        glow.setSpread(0.6);

        missionStarten.setEffect(glow);

        missionStarten.setOnMouseClicked(e -> SceneManager.changeScene(Strings.FXML_KARTENEW));
    }

    @FXML
    private void memoryGewaehlt ()
    {
        detailText.setText("Memory!");

        DropShadow glow = new DropShadow();
        glow.setColor(Color.GREEN);
        glow.setRadius(20);
        glow.setSpread(0.6);

        missionStarten.setEffect(glow);

        missionStarten.setOnMouseClicked(e -> SceneManager.changeScene(Strings.FXML_MISSION_MEMORY));
    }

    @FXML
    private void endgegnerGewaehlt ()
    {
        detailText.setText("Endgegner!");

        DropShadow glow = new DropShadow();
        glow.setColor(Color.GREEN);
        glow.setRadius(20);
        glow.setSpread(0.6);

        missionStarten.setEffect(glow);

        missionStarten.setOnMouseClicked(e -> SceneManager.changeScene(Strings.FXML_MISSION_ENDGEGNER));
    }

    @FXML
    public void openKarte() {
        SceneManager.changeScene(Strings.FXML_KARTENEW);
    }

}
