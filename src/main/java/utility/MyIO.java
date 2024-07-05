package utility;

import res.Konstanten;
import res.Strings;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MyIO
{
    //Variable fuer die startZeit
    private static long startZeit;

    //Konstante fuer das Datumsformat
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss:SSS";
    //Konstante fuer ein Pfeilsymbol
    private static final String PROMPT = "-> ";

    //Variable fuer den timeStamp
    private static boolean timeStamp = false;
    //Variable fuer den verboseMode
    private static boolean verboseMode = true;

    /**
     * Print-Methode, die einen Text ausgibt.
     *
     * @param text Der Text der ausgegeben werden soll.
     * @precondition Der eingegebene Text muss ein String sein.
     * @postcondition Es wird ein Text ausgegeben. Ein Timestamp wird mit ausgegeben, wenn die Variable "timeStamp" true
     * ist.
     * @author provided by Prof. Dr.-Ing. Heiko Mosemann
     */
    public static void print (String text)
    {
        if (verboseMode)
        {
            if (timeStamp)
            {
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)) + PROMPT + text);
            } else
            {
                System.out.println(text);
            }
        }
    }

    /**
     * Ueberladene print-Methode, die neben dem Text auch die Schriftfarbe uebergeben bekommt. Ein Text wird
     * ausgegeben.
     *
     * @param text  Der Text der ausgegeben werden soll.
     * @param color Die Schriftfarbe des auszugebenden Textes.
     * @precondition Der eingegebene Text muss ein String sein, die Farbe muss Parameter der Klasse ConsoleColor sein.
     * @postcondition Es wird ein Text ausgegeben in der Farbe, die beim Methodenaufruf ausgegeben wurde.
     * @author provided by Prof. Dr.-Ing. Heiko Mosemann
     */
    public static void print (String text, ConsoleColor color)
    {
        System.out.print(color);
        print(text);
        System.out.print(ConsoleColor.RESET);
    }

    /**
     * Ueberladene print-Methode, die neben dem Text auch die Schriftfarbe und Hintergrundfarbe uebergeben bekommt.
     *
     * @param text            Der Text der ausgegeben werden soll.
     * @param color           Die Schriftfarbe des auszugebenden Textes.
     * @param backgroundColor Die Hintergrundfarbe des auszugebenden Textes.
     * @precondition Der eingegebene Text muss ein String sein, die Farben muessen Parameter der Klasse ConsoleColor
     * sein.
     * @postcondition Es wird ein text ausgegeben mit den zugehoerigen Farben.
     * @author provided by Prof. Dr.-Ing. Heiko Mosemann
     */
    public static void print (String text, ConsoleColor color, ConsoleColor backgroundColor)
    {
        System.out.print(color);
        System.out.print(backgroundColor);
        print(text);
        System.out.print(ConsoleColor.RESET);
    }

    /**
     * Setter fuer den timeStamp.
     *
     * @param timeStamp
     * @precondition "timeStamp" muss einen boolschen Wert haben.
     * @postcondition "timeStamp" wurde auf den uebergebenen boolschen Parameter gesetzt.
     * @author provided by Prof. Dr.-Ing. Heiko Mosemann
     */
    public static void setTimeStamp (boolean timeStamp)
    {
        MyIO.timeStamp = timeStamp;
    }

    /**
     * Setter fuer den verboseMode. Ist dieser auf "true" gesetzt, werden zusaetzliche Informationen ausgegeben.
     *
     * @param verboseMode der boolsche Parameter, auf den "verboseMode" gesetzt werden soll.
     * @precondition "verboseMode" muss einen boolschen Wert haben.
     * @postcondition "verboseMode" wurde auf den uebergebenen boolschen Parameter gesetzt.
     * @author provided by Prof. Dr.-Ing. Heiko Mosemann
     */
    public static void setVerboseMode (boolean verboseMode)
    {
        MyIO.verboseMode = verboseMode;
    }

}
