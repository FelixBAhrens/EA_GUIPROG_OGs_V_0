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
    public Button missionStarten;
    @FXML
    public Button flappyBirdWaehlen;
    @FXML
    public Button sammelnWaehlen;
    @FXML
    public Button memoryWaehlen;
    @FXML
    public Button endgegnerWaehlen;

    @FXML
    public AnchorPane flappyBirdPane;
    @FXML
    public AnchorPane sammelPane;
    @FXML
    public AnchorPane memoryPane;
    @FXML
    public AnchorPane endgegnerPane;

    private boolean karteFuerMission = false;

    public HauptquartierController ()
    {
        instance = this;
    }

    public static HauptquartierController getInstance ()
    {
        return instance;
    }


    public boolean istKarteFuerMission ()
    {
        return karteFuerMission;
    }

    @FXML
    public void initialize ()
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

        missionStarten.setOnMouseClicked(e -> SzenenManager.wechseleSzene(Strings.FXML_MISSION_FLAPPYBIRD));
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

        missionStarten.setOnMouseClicked(e -> SzenenManager.wechseleSzene(Strings.FXML_KARTENEW));
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

        missionStarten.setOnMouseClicked(e -> SzenenManager.wechseleSzene(Strings.FXML_MISSION_MEMORY));
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
            SzenenManager.wechseleSzene(Strings.FXML_KAMPF);
        });
    }

    @FXML
    public void openKarte ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_KARTENEW);
    }

}
