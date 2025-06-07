package vue;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import modele.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class VBoxAffichagePlanning extends VBox {
    private static Label semaine;
    private static TableView<Reservation> tableDesReservations;
    private final PlanningCollections planning = HBoxRoot.getPlanning();
    private DateCalendrier date;
    private Button boutonSupprimer;
    private TableRow<Reservation> selectedRow;
    private static DataBase dataBase = new DataBase();
    public VBoxAffichagePlanning(Controleur controleur){
        super(10);
        date = new DateCalendrier();
        semaine = new Label("Semaine " + date.getWeekOfYear());
        semaine.getStyleClass().add("title");

        tableDesReservations = new TableView<>();

        TableColumn<Reservation, DateCalendrier> dateColumn = new TableColumn<>("Date");
        TableColumn<Reservation, String> coursColumn = new TableColumn<>("Cours");
        TableColumn<Reservation, String> niveauColumn = new TableColumn<>("Niveau");
        TableColumn<Reservation, PlageHoraire> horaireColumn = new TableColumn<>("Heure");

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        coursColumn.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        niveauColumn.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        horaireColumn.setCellValueFactory(new PropertyValueFactory<>("horaire"));

        tableDesReservations.getColumns().add(dateColumn);
        tableDesReservations.getColumns().add(coursColumn);
        tableDesReservations.getColumns().add(niveauColumn);
        tableDesReservations.getColumns().add(horaireColumn);
        for (TableColumn<Reservation, ?> table : tableDesReservations.getColumns()) {
            table.setResizable(false);
        }
        dateColumn.setPrefWidth(175);
        dateColumn.setSortType(TableColumn.SortType.ASCENDING);
        horaireColumn.setSortType(TableColumn.SortType.ASCENDING);
        tableDesReservations.getSortOrder().add(dateColumn);
        tableDesReservations.getSortOrder().add(horaireColumn); // Ajoute la colonne à la liste de tri
        this.updateSemaine(date);
        tableDesReservations.sort(); // Applique le tri immédiatement


        boutonSupprimer = new Button("Supprimer la réservation");
        boutonSupprimer.setUserData("Suppression");
        boutonSupprimer.setDisable(true);
        tableDesReservations.setRowFactory(_ -> {
            TableRow<Reservation> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    boutonSupprimer.setDisable(false);
                    selectedRow = row;
                }
            });
            return row;
        });
        boutonSupprimer.addEventHandler(ActionEvent.ACTION, controleur);

        this.getChildren().addAll(semaine, tableDesReservations, boutonSupprimer);
    }

    public void updateSemaine(DateCalendrier parDate){
        tableDesReservations.getItems().clear();
        if (boutonSupprimer != null){
        boutonSupprimer.setDisable(true);
        }
        if (planning.getChMapReservations().containsKey(parDate.getWeekOfYear())) {
            System.out.println(parDate.getWeekOfYear());
            System.out.println(planning);
            for (Reservation reservation : planning.getChMapReservations().get(parDate.getWeekOfYear())) {
                tableDesReservations.getItems().add(reservation);
            }
            tableDesReservations.sort();
        }
        date = parDate;
        semaine.setText("Semaine " + parDate.getWeekOfYear());
    }

    public void ajoutTable(DateCalendrier parDate, String parNiveau, String parCours, PlageHoraire parPlageHoraire){

        LocalDate dateTable = LocalDate.of(parDate.getAnnee(), parDate.getMois(), parDate.getJour());

        Horaire heureDebut = parPlageHoraire.getChHoraireDebut();
        LocalTime timeDebut = LocalTime.of(heureDebut.getHeure(), heureDebut.getQuartHeure());
        LocalDateTime debut = LocalDateTime.of(dateTable, timeDebut);

        Horaire heureFin = parPlageHoraire.getChHoraireFin();
        LocalTime timeFin = LocalTime.of(heureFin.getHeure(), heureFin.getQuartHeure());
        LocalDateTime fin = LocalDateTime.of(dateTable, timeFin);

        dataBase.insererReservation(dateTable, parCours, parNiveau, debut, fin, date.getWeekOfYear());
    }

    public void supprimerTable(DateCalendrier parDate, PlageHoraire parPlageHoraire){
        LocalDate dateTable = LocalDate.of(parDate.getAnnee(), parDate.getMois(), parDate.getJour());

        Horaire heureDebut = parPlageHoraire.getChHoraireDebut();
        LocalTime timeDebut = LocalTime.of(heureDebut.getHeure(), heureDebut.getQuartHeure());
        LocalDateTime debut = LocalDateTime.of(dateTable, timeDebut);

        Horaire heureFin = parPlageHoraire.getChHoraireFin();
        LocalTime timeFin = LocalTime.of(heureFin.getHeure(), heureFin.getQuartHeure());
        LocalDateTime fin = LocalDateTime.of(dateTable, timeFin);

        dataBase.supprimerReservation(dateTable, debut, fin);
    }

    public Reservation selectedReservation(){
        return selectedRow.getItem();
    }
}
