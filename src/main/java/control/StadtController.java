package control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        hauptStage.setTitle("Stadt");
        Button schmiede = new Button("Schmiede");
        schmiede.setOnAction(e -> hauptStage.setScene(schmiedeSz));
        Button schenke = new Button("Schenke");
        schenke.setOnAction(e -> hauptStage.setScene(schenkeSz));
        Button magieverstaerker = new Button("Magieverstärker");
        magieverstaerker.setOnAction(e -> hauptStage.setScene(magieverstaerkerSz));
        Button basisCamp = new Button("Basiscamp");
        basisCamp.setOnAction(e -> hauptStage.setScene(basisCampSz));
        Button fraktionenCamps = new Button("FraktionenCamps");
        fraktionenCamps.setOnAction(e->hauptStage.setScene(fraktionenCampsSz));
        Button trainingsGelaende = new Button("Trainingsgelände");
        trainingsGelaende.setOnAction(e->hauptStage.setScene(trainingsGelaendeSz));
        Button markt = new Button("Markt");
        markt.setOnAction(e->hauptStage.setScene(marktSz));
        Button hauptquartier = new Button("Hauptquartier");
        hauptquartier.setOnAction(e->hauptStage.setScene(hauptquartierSz));

        HBox haeuser = new HBox(10, schmiede, schenke, magieverstaerker, basisCamp, fraktionenCamps, trainingsGelaende, markt, hauptquartier);

        stadtSz = new Scene(haeuser, 400, 200);
        hauptStage.setScene(stadtSz);

        hauptStage.setScene(stadtSz);
        hauptStage.show();
    }

    public static void zeigeSchmiede(){
        
    }

    public static void zeigeSchenke(){

    }
    public static void zeigemagieverstaerker(){

    }
    public static void zeigeBasiscamp(){

    }
    public static void zeigeFraktionenCamps(){

    }
    public static void zeigeTrainingsgelaende(){

    }
    public static void zeigeMarkt(){

    }
    public static void zeigeHauptquartier(){

    }

}
