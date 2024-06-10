package model;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import res.Konstanten;

import javax.sound.sampled.Line;
import javax.swing.*;

public class StickMan {

    public Group getStickmanGroup() {
        return stickmanGroup;
    }

    private Group stickmanGroup;
    private Rectangle koerper;

    private int xPos;
    private int yPos;
    private int movementSpeed;
    private boolean movingUp;
    private boolean movingDown;
    private boolean movingLeft;
    private boolean movingRight;

//--------------------------------------------------------
    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getMovementSpeed() {return movementSpeed;}

    public void setMovementSpeed(int movementSpeed) {this.movementSpeed = movementSpeed;}

    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }
//----------------------------------------------------------


    public StickMan (int groesse, Color farbe, int xPos, int yPos, int movementSpeed, boolean movingUp, boolean movingDown, boolean movingLeft, boolean movingRight)
    {
        stickmanGroup = new Group();

        // Erstelle den Kopf
        Circle head = new Circle(Konstanten.INT_FIFTEEN);
        head.setFill(Color.BLACK);
        head.setCenterX(Konstanten.INT_ZERO);
        head.setCenterY(-Konstanten.INT_TEN / Konstanten.INT_TWO- Konstanten.INT_FIFTEEN);

        Rectangle body = new Rectangle(Konstanten.INT_TEN, Konstanten.INT_TWENTY, Color.BLACK);


        // Füge alle Teile zur Gruppe hinzu
        stickmanGroup.getChildren().addAll(head, body);

        koerper = new Rectangle (groesse, groesse, farbe);
        this.xPos = xPos;
        this.yPos = yPos;
        this.movementSpeed = movementSpeed;
        this.movingUp = movingUp;
        this.movingDown = movingDown;
        this.movingLeft = movingLeft;
        this.movingRight = movingRight;
    }

    public void moveStickman (int WINDOW_HEIGHT, int WINDOW_WIDTH){
        int useThisSpeedItMayBeNew = movementSpeed;
        if ((movingUp|movingDown) && (movingLeft|movingRight))
        {
            useThisSpeedItMayBeNew = (int)Math.sqrt((movementSpeed^Konstanten.INT_TWO)+(movementSpeed ^Konstanten.INT_TWO));
        }
        if (movingUp && yPos > useThisSpeedItMayBeNew)
        {
            setyPos(getyPos() - useThisSpeedItMayBeNew);
        }
        if (movingDown && yPos < WINDOW_HEIGHT - koerper.getHeight())
        {
            setyPos(yPos + useThisSpeedItMayBeNew);
        }
        if (movingLeft && xPos > useThisSpeedItMayBeNew)
        {
            setxPos(xPos - useThisSpeedItMayBeNew);
        }
        if (movingRight && xPos < WINDOW_WIDTH - koerper.getWidth())
        {
            setxPos(xPos + useThisSpeedItMayBeNew);
        }
    }




}
