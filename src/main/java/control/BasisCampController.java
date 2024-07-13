package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import res.Konstanten;
import res.Strings;

public class BasisCampController extends PaneController
{

    @FXML
    public VBox einheitenVbox;
    @FXML
    public Label detailLabel;

    @FXML
    public void initialize ()
    {

    }

    public void updateEinheitenDisplay (){

    }

    /**
     * Methode zum Anzeigen von Details
     * @param event Das Ereignis, das durch eine Mausaktion ausgeloest wurde und zum Methodenaufruf gefuehrt hat.
     */
    @FXML
    public void zeigeDetail (MouseEvent event){
        HBox hBox = ((HBox)event.getSource());
        //((Label)(hBox.getChildren().get(Konstanten.INT_ZERO)))
        detailLabel.setText(switch (hBox.getId()){
            case (Strings.HBOX + Konstanten.INT_ZERO) -> Strings.BC_TEXT_EINHEIT_A;
            case (Strings.HBOX + Konstanten.INT_ONE) -> Strings.BC_TEXT_EINHEIT_B;
            case (Strings.HBOX + Konstanten.INT_TWO) -> Strings.BC_TEXT_EINHEIT_C;
            case (Strings.HBOX + Konstanten.INT_THREE) -> Strings.BC_TEXT_EINHEIT_D;
            case (Strings.HBOX + Konstanten.INT_FOUR) -> Strings.BC_TEXT_EINHEIT_E;
            default -> Strings.FEHLERMELDUNG_ID;
        });
        detailLabel.setVisible(true);
    }

    @FXML
    public void entferneDetail () {
        detailLabel.setText(Strings.SPACE);
    }

    /**
     * Methode, die die Hbox, die eine Einheit repraesentiert, zurueckgibt
     * @param index
     * @return
     * @Author Felix Ahrens
     */
    public HBox findeHbox (int index) {
        return ((HBox)einheitenVbox.getChildren().get(index));
    }

}
