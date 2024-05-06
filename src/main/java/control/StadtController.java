package control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class StadtController extends Application {
    static Scene stadtSz;
    static Scene schmiedeSz;
    static Scene schenkeSz;
    static Scene magieverstärkerSz;
    static Scene basisCampSz;
    static Scene fraktionenCampsSz;
    static Scene trainingsGelaendeSz;
    static Scene marktSz;
    static Scene hauptquartierSz;

    @Override
    public void start(Stage stadtFenster) throws Exception {

    }

    public static void zeigeStadt(Stage hauptstage){
       // Stage stadtFenster = new Stage();
        Button schmiede = new Button("Schmiede");
        schmiede.setOnAction(e -> hauptstage.setScene(schmiedeSz));
        Button schenke = new Button("Schenke");
        schenke.setOnAction(e -> hauptstage.setScene(schenkeSz));

        HBox haeuser = new HBox(schmiede, schenke);
        stadtSz = new Scene(haeuser, 400, 200);
        hauptstage.setScene(stadtSz);

        hauptstage.setScene(stadtSz);
        hauptstage.show();
    }

    public static void setupSchmiede(){

    }
}
