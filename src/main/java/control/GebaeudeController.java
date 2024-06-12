package control;

import javafx.fxml.FXML;

public class GebaeudeController {

    private control.StadtController stadtController;

    public void setStadtController(StadtController stadtController) {
        this.stadtController = stadtController;
    }

    @FXML
    private void closeGebaeude() {
        if (stadtController != null) {
            stadtController.closeGebaeude();
        }
    }
}
