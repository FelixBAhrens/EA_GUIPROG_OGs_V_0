package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import res.Strings;
import utility.MyIO;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Die Klasse EinstellungenController ist die ControllerKlasse der zugehoerigen FXML-Datei "einstellungen-view.fxml". In
 * ihr koennen Einstellungen getaetigt werden. Die Klasse beinhaltet Methoden zur Steuerung und Reaktion auf
 * GUI-Eingaben
 *
 * @Author Felix Ahrens, Enes Oezcan
 */
public class EinstellungenController extends ControllerController
{
    @FXML
    public Label ipLabel;

    /**
     * Initialize-methode, die bei Controllerklassen verpflichtend ist. Diese ruft die Methode "zeigeIP" auf, damit die
     * Einstellungen wenigstens eine Verwendung haben.
     *
     * @pre Die aufgerufene Methode muss erreichbar sein.
     * @post Die IP-Adresse wird der nutzenden Person angezeigt
     * @Author Felix Ahrens, Enes Oezcan
     */
    @FXML
    public void initialize ()
    {
        zeigeIp();
    }

    /**
     * Methode "zeigeIp" setzt die lokale IP-Adresse als String mit Bezeichner als Label in die Szene "Einstellungen".
     *
     * @pre Die verwendeten Klassen, Methoden und Konstanten muessen erreichbar sein.
     * @post Die IP-Adresse wurde gelabelt und im "ipLabel" angezeigt.
     * @author Felix Ahrens
     */
    public void zeigeIp ()
    {
        try
        {
            InetAddress ip = InetAddress.getLocalHost();
            ipLabel.setText(Strings.IP + Strings.DOPPELPUNKT + Strings.SPACE + ip.getHostAddress());
            ipLabel.setVisible(true);
        } catch (UnknownHostException e)
        {
            MyIO.print(Strings.FEHLERMELDUNG_IP);
        }
    }

}


