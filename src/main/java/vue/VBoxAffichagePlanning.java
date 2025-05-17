package vue;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modele.DateCalendrier;

public class VBoxAffichagePlanning extends VBox {
    private Label semaine;
    public VBoxAffichagePlanning(){
        semaine = new Label("Semaine " + new DateCalendrier().getWeekOfYear());

        this.getChildren().add(semaine);
    }

    public void updateSemaine(DateCalendrier date){
        semaine.setText("Semaine " + date.getWeekOfYear());
    }
}
