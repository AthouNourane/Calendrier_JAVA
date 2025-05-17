package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import modele.*;
import vue.*;

public class Controleur implements EventHandler {

    @Override
    public void handle(Event event) {
        PlanningCollections planning = HBoxRoot.getPlanning();
        GridPaneFormulaireReservation reservationPane = HBoxRoot.getReservationPane();

        // la source de event est un ToggleButton du calendrier
        if (event.getSource() instanceof ToggleButton) {
            ToggleButton clickedButton = (ToggleButton) event.getSource();
            DateCalendrier selDate = (DateCalendrier) clickedButton.getUserData();
            System.out.println(selDate.toString());
            reservationPane.updateDateSel(selDate);
        }
        // la source de event est le bouton "Enregistrer" du formulaire de réservation
        if (event.getSource() instanceof Button) {
            DateCalendrier dateReservation = reservationPane.getSelDate();
            String coursReservation = reservationPane.getTextCours();
            String niveauReservation = reservationPane.getNiveau();
            Horaire heureDebut = new Horaire(reservationPane.getHeureDebut(), reservationPane.getMinDebut());
            Horaire heureFin = new Horaire(reservationPane.getHeureFin(), reservationPane.getMinFin());
            try {
                PlageHoraire plageReservation = new PlageHoraire(heureDebut, heureFin);
                Reservation reservation = new Reservation(dateReservation, plageReservation, coursReservation, niveauReservation);
                planning.ajout(reservation);
            } catch (ExceptionHoraire e) {
                System.out.println("Erreur sur les horaires !");
            } catch (ExceptionReservation e) {
                System.out.println("Erreur sur la reservation !");
            } catch (ExceptionPlanning e) {
                System.out.println("Erreur lors de l'ajout dans le planning !");
            }
            System.out.println(planning);
        }
    }
}
