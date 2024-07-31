package control;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import res.Konstanten;
import res.Strings;

public class Kampf2 {

    private int xPosition;
    private int yPosition;

    private void attackiere() {

    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                if (yPosition > Konstanten.INT_ZERO) yPosition = +Konstanten.INT_ONE;
                else
                    break;
            case A:
                if (xPosition > Konstanten.INT_ZERO) xPosition = -Konstanten.INT_ONE;
                break;
            case S:
                if (yPosition < Konstanten.INT_ELEVEN) yPosition = +Konstanten.INT_ONE;
                break;
            case D:
                if (xPosition < Konstanten.INT_ELEVEN) xPosition = +Konstanten.INT_ONE;
                break;
      /*  case Q:
            attackiere(spieler, gegner);
            break;
        case P:
            wendeArtefaktAn(spieler, gegner);
            break;
    }
    updateCharacterPosition();
    gegnerZug();*/
        }
    }
}

