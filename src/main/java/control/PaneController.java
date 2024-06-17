package control;

public class PaneController extends ControllerController
{
    private control.StadtController stadtController;
    private control.KartenController kartenController;

    public void setStadtController(StadtController stadtController) {
        this.stadtController = stadtController;
    }

    public void setKartenController (KartenController kartenController)
    {
        this.kartenController = kartenController;
    }
}
