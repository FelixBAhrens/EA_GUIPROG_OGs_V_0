package control;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import res.Konstanten;
import res.Strings;

public class MissionMemoryController {
    private boolean ersterSteinUmgedreht = false;
    private ImageView ersterStein;

    @FXML
    private ImageView stein0a;
    @FXML
    private ImageView stein0b;
    @FXML
    private ImageView stein1a;
    @FXML
    private ImageView stein1b;
    @FXML
    private ImageView stein2a;
    @FXML
    private ImageView stein2b;
    @FXML
    private ImageView stein3a;
    @FXML
    private ImageView stein3b;
    @FXML
    private ImageView stein4a;
    @FXML
    private ImageView stein4b;
    @FXML
    private ImageView stein5a;
    @FXML
    private ImageView stein5b;
    @FXML
    private ImageView stein6a;
    @FXML
    private ImageView stein6b;
    @FXML
    private ImageView stein7a;
    @FXML
    private ImageView stein7b;



    @FXML
    public void initialize() {

    }

    @FXML
    public void handleStein (MouseEvent event) {
        ImageView stein = (ImageView) event.getSource();
        if (!ersterSteinUmgedreht) {
            ersterStein = stein;
            ersterSteinUmgedreht = true;
            System.out.println(ersterSteinUmgedreht);
        } else {
            System.out.println(ersterStein.getId().toString().charAt(Konstanten.INT_FIVE));
            System.out.println(stein.getId().toString().charAt(Konstanten.INT_FIVE));
            if (ersterStein.getId().toString().charAt(Konstanten.INT_FIVE) == (stein.getId().toString().charAt(Konstanten.INT_FIVE))) {
                System.out.println(Strings.TOLL);

                ersterStein.setVisible(false);
                stein.setVisible(false);
            }
            ersterSteinUmgedreht = false;
            System.out.println(ersterSteinUmgedreht);
        }
    }
}
