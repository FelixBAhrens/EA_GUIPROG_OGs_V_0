package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import res.Strings;
import utility.MyIO;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Die Klasse EinstellungenController ist die ControllerKlasse der zugehoerigen FXML-Datei "einstellungen-view.fxml". In
 * ihr koennen Einstellungen getaetigt werden.
 */
public class EinstellungenController extends ControllerController
{
    @FXML
    public Label ipLabel;

    /**
     * Initialize-methode, die bei Controllerklassen verpflichtend ist. Diese ruft die Methode "zeigeIP" auf, damit die
     * Einstellungen wenigstens eine Verwendung haben.
     *
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
     * @author Felix Ahrens
     */
    public void zeigeIp ()
    {
        try
        {
            InetAddress ip = InetAddress.getLocalHost();
            ipLabel.setText(Strings.IP + Strings.DOPPELPUNKT + Strings.SPACE + ip.getHostAddress().toString());
        } catch (UnknownHostException e)
        {
            MyIO.print(Strings.FEHLERMELDUNG_IP);
        }
    }

}


