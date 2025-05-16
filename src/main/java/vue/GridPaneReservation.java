package vue;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import modele.DateCalendrier;

public class GridPaneReservation extends GridPane {
    public GridPaneReservation() {
        GridPane menuReservation = new GridPane();

        this.setGridLinesVisible(true);
        this.setPadding(new Insets(40));
        this.setHgap(20);
        this.setVgap(20);

        Label dateReservation = new Label(new DateCalendrier().toString());

        Label labelCours = new Label("Cours");
        TextField textCours = new TextField();
        labelCours.setLabelFor(textCours);


        Label labelNiveau = new Label("Niveau");
        ToggleGroup toggleNiveau = new ToggleGroup();
        RadioButton buttonDebutant = new RadioButton("débutant");
        buttonDebutant.setToggleGroup(toggleNiveau);
        RadioButton buttonMoyen = new RadioButton("moyen");
        buttonMoyen.setToggleGroup(toggleNiveau);
        RadioButton buttonAvance = new RadioButton("avancé");
        buttonAvance.setToggleGroup(toggleNiveau);
        RadioButton buttonExpert = new RadioButton("expert");
        buttonExpert.setToggleGroup(toggleNiveau);

        Label labelHoraire = new Label("Horaire");
        ComboBox<Integer> heureDebut = new ComboBox<>();
        ComboBox<Integer> minDebut = new ComboBox<>();
        ComboBox<Integer> heureFin = new ComboBox<>();
        ComboBox<Integer> minFin = new ComboBox<>();
        for (int i = 0; i < 60; i += 15) {
            minDebut.getItems().add(i);
            minFin.getItems().add(i);
        }
        for (int i = 7; i <= 22; i++) {
            heureDebut.getItems().add(i);
            heureFin.getItems().add(i);
        }

        Button boutonAnnuler = new Button("Annuler");
        Button boutonEnregistrer = new Button("Enregistrer");


        this.add(dateReservation, 1, 0, 5, 1);

        this.add(labelCours, 0, 1);
        this.add(textCours, 1, 1, 5, 1);

        this.add(labelNiveau, 0, 2);
        this.add(buttonDebutant, 1, 2);
        this.add(buttonMoyen, 2, 2);
        this.add(buttonAvance,1, 3);
        this.add(buttonExpert, 2, 3);

        this.add(labelHoraire, 0, 4);
        this.add(new Label("de"), 1, 4);
        this.add(heureDebut, 2, 4);
        this.add(new Label("h"), 3, 4);
        this.add(minDebut, 4, 4);
        this.add(new Label("à"), 1, 5);
        this.add(heureFin, 2, 5);
        this.add(new Label("h"), 3, 5);
        this.add(minFin, 4, 5);

        this.add(boutonAnnuler, 3, 6);
        this.add(boutonEnregistrer, 4, 6);
    }
}
