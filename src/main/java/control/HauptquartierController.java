package control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import res.Konstanten;
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
        return karteFuerMission;
    }

    @FXML
    public void initialize()
    {
    }

    @FXML
    private void flappyBirdGewaehlt ()
    {
        detailText.setText(Strings.FLAPPY_BIRD);

        DropShadow glow = new DropShadow();
        glow.setColor(Color.GREEN);
        glow.setRadius(Konstanten.INT_TWENTY);
        glow.setSpread(Konstanten.ZERO_POINT_SIX);

        missionStarten.setEffect(glow);

        missionStarten.setOnMouseClicked(e -> SceneManager.changeScene(Strings.FXML_MISSION_FLAPPYBIRD));
    }

    @FXML
    private void sammelnGewaehlt ()
    {
        karteFuerMission = true;
        detailText.setText(Strings.SAMMELN);

        DropShadow glow = new DropShadow();
        glow.setColor(Color.GREEN);
        glow.setRadius(Konstanten.INT_TWENTY);
        glow.setSpread(Konstanten.ZERO_POINT_SIX);

        missionStarten.setEffect(glow);

        missionStarten.setOnMouseClicked(e -> SceneManager.changeScene(Strings.FXML_KARTENEW));
    }

    @FXML
    private void memoryGewaehlt ()
    {
        detailText.setText(Strings.MEMORY);

        DropShadow glow = new DropShadow();
        glow.setColor(Color.GREEN);
        glow.setRadius(Konstanten.INT_TWENTY);
        glow.setSpread(Konstanten.ZERO_POINT_SIX);

        missionStarten.setEffect(glow);

        missionStarten.setOnMouseClicked(e -> SceneManager.changeScene(Strings.FXML_MISSION_MEMORY));
    }

    @FXML
    private void endgegnerGewaehlt ()
    {
        detailText.setText(Strings.ENDGEGNER);

        DropShadow glow = new DropShadow();
        glow.setColor(Color.GREEN);
        glow.setRadius(Konstanten.INT_TWENTY);
        glow.setSpread(Konstanten.ZERO_POINT_SIX);

        missionStarten.setEffect(glow);

        missionStarten.setOnMouseClicked(e -> {
            KampfController.kampfTyp = KampfController.KampfTyp.ENDGEGNER_KAMPF;
            SceneManager.changeScene(Strings.FXML_KAMPF);});
    }

    @FXML
    public void openKarte()
    {
        SceneManager.changeScene(Strings.FXML_KARTENEW);
    }

}
