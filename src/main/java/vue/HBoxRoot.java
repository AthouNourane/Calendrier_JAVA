package vue;

import controleur.Controleur;
import javafx.scene.layout.HBox;
import modele.PlanningCollections;

public class HBoxRoot extends HBox {
    private static PlanningCollections planning;
    private static Controleur controleur;
    private static VBoxCalendrier calendrierPane;
    private static GridPaneReservation reservationPane;

    public HBoxRoot(){
        super(10);

        planning = new PlanningCollections();
        calendrierPane = new VBoxCalendrier();
        controleur = new Controleur();
        reservationPane = new GridPaneReservation();

        this.getChildren().add(calendrierPane);
        this.getChildren().add(reservationPane);
    }

    public static PlanningCollections getPlanning() {
        return planning;
    }

    public static VBoxCalendrier getCalendrierPane(){
        return calendrierPane;
    }

    public static Controleur getControleur(){
        return controleur;
    }

    public static GridPaneReservation getReservationPane(){
        return reservationPane;
    }
}
