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
    static Scene magieverstaerkerSz;
    static Scene basisCampSz;
    static Scene fraktionenCampsSz;
    static Scene trainingsGelaendeSz;
    static Scene marktSz;
    static Scene hauptquartierSz;

    @Override
    public void start(Stage stadtFenster) throws Exception {

    }
    public static void zeigeStadt(Stage hauptStage){
        Button schmiede = new Button("Schmiede");
        schmiede.setOnAction(e -> hauptStage.setScene(schmiedeSz));
        Button schenke = new Button("Schenke");
        schenke.setOnAction(e -> hauptStage.setScene(schenkeSz));
        Button magieverstaerker = new Button("Magieverstärker");
        magieverstaerker.setOnAction(e -> hauptStage.setScene(magieverstaerkerSz));
        HBox haeuser = new HBox(schmiede, schenke, magieverstaerker);
        stadtSz = new Scene(haeuser, 400, 200);
        hauptStage.setScene(stadtSz);

        hauptStage.setScene(stadtSz);
        hauptStage.show();
    }

    public static void setupSchmiede(){

    }
}
