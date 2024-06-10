package control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import org.w3c.dom.Text;
import res.Konstanten;
import res.Strings;

public class StadtController {
    static Scene stadtSz;
    static Scene schmiedeSz;
    static Scene schenkeSz;
    static Scene magieverstaerkerSz;
    static Scene basisCampSz;
    static Scene fraktionenCampsSz;
    static Scene trainingsGelaendeSz;
    static Scene marktSz;
    static Scene hauptquartierSz;

    public static void zeigeStadt(Stage hauptStage){
        hauptStage.setTitle(Strings.STADT);
        Button schmiede = new Button(Strings.SCHMIEDE);
        schmiede.setOnAction(e -> hauptStage.setScene(schmiedeSz));
        Button schenke = new Button(Strings.SCHENKE);
        schenke.setOnAction(e -> hauptStage.setScene(schenkeSz));
        Button magieverstaerker = new Button(Strings.MAGIEVERSTAERKER);
        magieverstaerker.setOnAction(e -> zeigemagieverstaerker(hauptStage));
        Button basisCamp = new Button(Strings.BASISCAMP);
        basisCamp.setOnAction(e -> hauptStage.setScene(basisCampSz));
        Button fraktionenCamps = new Button(Strings.FRAKTIONENCAMPS);
        fraktionenCamps.setOnAction(e->hauptStage.setScene(fraktionenCampsSz));
        Button trainingsGelaende = new Button(Strings.TRAININGSGELAENDE);
        trainingsGelaende.setOnAction(e->hauptStage.setScene(trainingsGelaendeSz));
        Button markt = new Button(Strings.MARKT);
        markt.setOnAction(e->hauptStage.setScene(marktSz));
        Button hauptquartier = new Button(Strings.HAUPTQUARTIER);
        hauptquartier.setOnAction(e->hauptStage.setScene(hauptquartierSz));

        HBox haeuser = new HBox(Konstanten.INT_TEN, schmiede, schenke, magieverstaerker, basisCamp, fraktionenCamps, trainingsGelaende, markt, hauptquartier);

        stadtSz = new Scene(haeuser, Konstanten.INT_FOUR_HUNDRED, Konstanten.INT_SIX_HUNDRED);
        hauptStage.setScene(stadtSz);

        hauptStage.setScene(stadtSz);
        hauptStage.show();
    }

    public static void zeigeSchmiede(){
        
    }

    public static void zeigeSchenke(){

    }
    public static void zeigemagieverstaerker(Stage hauptStage){

        SzenenController.setzeSzene(Strings.MAGIEVERSTAERKER, magieverstaerkerSz, hauptStage);
    }
    public static void zeigeBasiscamp(){

    }
    public static void zeigeFraktionenCamps(){

    }
    public static void zeigeTrainingsgelaende(Stage hauptStage){
        Button trainiere = new Button(Strings.TRAINIERE);
        SzenenController.setzeSzene(Strings.TRAININGSGELAENDE, trainingsGelaendeSz, hauptStage);
    }
    public static void zeigeMarkt(){

    }
    public static void zeigeHauptquartier(){

    }

}
