package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import modele.*;
import vue.*;

import java.util.Set;

public class Controleur implements EventHandler {

    @Override
    public void handle(Event event) {
        PlanningCollections planning = HBoxRoot.getPlanning();
        GridPaneFormulaireReservation reservationPane = HBoxRoot.getReservationPane();
        VBoxAffichagePlanning planningPane = HBoxRoot.getPlanningPane();

        // la source de event est un ToggleButton du calendrier
        if (event.getSource() instanceof ToggleButton clickedButton) {
            DateCalendrier selDate = (DateCalendrier) clickedButton.getUserData();
            reservationPane.updateDateSel(selDate);
            planningPane.updateSemaine(selDate);

        }
        // la source de event est un bouton
        if (event.getSource() instanceof Button bouton) {
            DateCalendrier dateReservation = reservationPane.getSelDate();
            if (bouton.getUserData().equals("Enregistrer")) {
                String coursReservation = reservationPane.getTextCours();
                String niveauReservation = reservationPane.getNiveau();
                Horaire heureDebut = new Horaire(reservationPane.getHeureDebut(), reservationPane.getMinDebut());
                Horaire heureFin = new Horaire(reservationPane.getHeureFin(), reservationPane.getMinFin());
                try {
                    PlageHoraire plageReservation = new PlageHoraire(heureDebut, heureFin);
                    Reservation reservation = new Reservation(dateReservation, plageReservation, coursReservation, niveauReservation);
                    planning.ajout(reservation);
                    planningPane.updateSemaine(dateReservation);
                    planningPane.ajoutTable(dateReservation, niveauReservation, coursReservation, plageReservation);
                } catch (ExceptionHoraire e) {
                    System.out.println("Erreur sur les horaires !");
                } catch (ExceptionReservation e) {
                    System.out.println("Erreur sur la reservation !");
                } catch (ExceptionPlanning e) {
                    System.out.println("Erreur lors de l'ajout dans le planning !");
                }
            }
            if (bouton.getUserData().equals("Suppression")) {
                Reservation selectedReservation = planningPane.selectedReservation();
                PlageHoraire plageHoraire = selectedReservation.getHoraire();
                DateCalendrier dateSupp = (DateCalendrier) selectedReservation.getDate();
                planningPane.supprimerTable(dateSupp, plageHoraire);
                Set<Reservation> reservationSet = planning.getChMapReservations().get(dateSupp.getWeekOfYear());
                reservationSet.remove(selectedReservation);
                if (reservationSet.isEmpty()){
                    planning.getChMapReservations().remove(dateSupp.getWeekOfYear());
                }
                planningPane.updateSemaine(dateReservation);
                System.out.println("Réservation supprimée !");
            }
        }
    }
}
