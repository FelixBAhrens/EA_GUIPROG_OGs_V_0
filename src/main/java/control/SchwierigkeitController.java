package control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchwierigkeitController {
    @FXML
    private Button einfachButton;
    private Button mittelButton;
    private Button schwerButton;
    private Button zurueckButton;
    private static Schwierigkeit aktuelleSchwierigkeit;

    public enum Schwierigkeit {
        Einfach, Mittel, Schwer;
    }
    @FXML
    public void initialize() {
    }
    public static void setzeSchwierigkeit(Schwierigkeit schwierigkeit) {
        aktuelleSchwierigkeit = schwierigkeit;
    }
    public static void gebeSchwierigkeitAus() {
        if (aktuelleSchwierigkeit != null) {
            System.out.println("Aktuell ausgewaehlte Schwierigkeit: " + aktuelleSchwierigkeit);
        } else {
            System.out.println("Kein Spielstand vorhanden!");
        }
    }
        @FXML
        private void handleEinfach(MouseEvent eventt) throws IOException {
            SchwierigkeitController.setzeSchwierigkeit(Schwierigkeit.Einfach);
            Schwierigkeit schwierigkeit = Schwierigkeit.Einfach;
            System.out.println("Schwierigkeit: Einfach gewählt!");
        }

        @FXML
        private void handleMittel(MouseEvent event) throws IOException {
            SchwierigkeitController.setzeSchwierigkeit(Schwierigkeit.Mittel);
            System.out.println("Schwierigkeit: Mittel gewählt!");
        }

        @FXML
        private void handleSchwer(MouseEvent event) throws IOException {
            SchwierigkeitController.setzeSchwierigkeit(Schwierigkeit.Schwer);
            System.out.println("Schwierigkeit: Schwer gewählt!");
        }
        @FXML
        private void handleSchwierigkeitAusgabe (MouseEvent event) throws IOException {
        SchwierigkeitController.gebeSchwierigkeitAus();
        }
        @FXML
        public void zurueckHandler(MouseEvent event) throws IOException{
        SceneManager.goBack();
        }
    }
