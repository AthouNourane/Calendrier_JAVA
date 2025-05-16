package vue;

import javafx.scene.layout.HBox;

public class HBoxRoot extends HBox {

    public HBoxRoot(){
        super(10);
        VBoxCalendrier calendrier = new VBoxCalendrier();
        GridPaneReservation reservation = new GridPaneReservation();

        this.getChildren().add(calendrier);
        this.getChildren().add(reservation);
    }
}
