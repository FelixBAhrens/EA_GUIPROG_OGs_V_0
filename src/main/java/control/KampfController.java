package control;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import res.Konstanten;

import java.net.URL;
import java.util.ResourceBundle;

// ControllerController
public class KampfController extends ControllerController implements Initializable
{
    private static final int GRID_SIZE = Konstanten.INT_TWELVE;
    private static final int TILE_SIZE = 45;
    private int characterX = Konstanten.INT_ZERO;
    private int characterY = Konstanten.INT_ZERO;

    @FXML
    private GridPane gridPane;
    private Rectangle character;

    @FXML
    public void initialize (URL location, ResourceBundle resources)
    {
        createMap();
        initializeCharacter();

        gridPane.sceneProperty().addListener(((observableValue, oldScene, newScene) ->
        {
            if (newScene != null)
            {
                newScene.setOnKeyPressed(this::handleKeyPress);
            }
        }));
    }

    private void createMap ()
    {
        for (int row = Konstanten.INT_ZERO; row < GRID_SIZE; row++)
        {
            for (int col = Konstanten.INT_ZERO; col < GRID_SIZE; col++)
            {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                tile.setFill(Color.LIGHTGRAY);
                tile.setStroke(Color.BLACK);
                gridPane.add(tile, col, row);
            }
        }
    }

    private void initializeCharacter ()
    {
        character = new Rectangle(TILE_SIZE, TILE_SIZE);
        character.setFill(Color.RED);
        gridPane.add(character, characterX, characterY);
    }

    private void handleKeyPress (KeyEvent keyEvent)
    {
        switch (keyEvent.getCode())
        {
            case W:
                if (characterY > Konstanten.INT_ZERO) characterY--;
                break;
            case A:
                if (characterX > Konstanten.INT_ZERO) characterX--;
                break;
            case S:
                if (characterY < GRID_SIZE - Konstanten.INT_ONE) characterY++;
                break;
            case D:
                if (characterX < GRID_SIZE - Konstanten.INT_ONE) characterX++;
                break;
        }
        updateCharacterPosition();
    }

    private void updateCharacterPosition ()
    {
        GridPane.setRowIndex(character, characterY);
        GridPane.setColumnIndex(character, characterX);
    }

    /**
     * README:
     * - Artefakte koennen im Kampf angewendet werden
     */
}
