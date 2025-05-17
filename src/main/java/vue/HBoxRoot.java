package vue;

import controleur.Controleur;
import javafx.scene.layout.HBox;
import modele.PlanningCollections;

public class HBoxRoot extends HBox {
    private static PlanningCollections planning;
    private static Controleur controleur;
    private static VBoxCalendrier calendrierPane;
    private static GridPaneFormulaireReservation reservationPane;
    private static VBoxAffichagePlanning planningPane;

    public HBoxRoot(){
        super(30);

        planning = new PlanningCollections();
        controleur = new Controleur();
        calendrierPane = new VBoxCalendrier(controleur);
        reservationPane = new GridPaneFormulaireReservation(controleur);
        planningPane = new VBoxAffichagePlanning();


        this.getChildren().add(calendrierPane);
        this.getChildren().add(reservationPane);
        this.getChildren().add(planningPane);
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

    public static GridPaneFormulaireReservation getReservationPane(){
        return reservationPane;
    }

    public static VBoxAffichagePlanning getPlanningPane(){
        return planningPane;
    }
}
