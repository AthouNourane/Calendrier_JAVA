package vue;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modele.DateCalendrier;


public class VboxRoot extends VBox {
    public VboxRoot() {
        super(15);
        DateCalendrier today = new DateCalendrier();
        DateCalendrier demain = today.dateDuLendemain();
        Label labelDate = new Label(today.toString());
        this.getChildren().add(labelDate);
        Label labelDateDemain = new Label(demain.toString());
        this.getChildren().add(labelDateDemain);
    }

}
