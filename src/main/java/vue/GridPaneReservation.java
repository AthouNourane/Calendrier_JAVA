package vue;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import modele.DateCalendrier;

public class GridPaneReservation extends GridPane {
    public GridPaneReservation() {

        // this.setGridLinesVisible(true);
        this.setHgap(5);
        this.setVgap(5);

        Label dateReservation = new Label(new DateCalendrier().toString());

        Label labelCours = new Label("_Cours");
        labelCours.setMnemonicParsing(true);
        TextField textCours = new TextField();
        labelCours.setLabelFor(textCours);
        textCours.setPromptText("Entre le nom d'une réservation...");
        Platform.runLater(textCours::requestFocus); // Platform.runLater(() -> textCours.requestFocus());

        Label labelNiveau = new Label("Niveau");
        ToggleGroup toggleNiveau = new ToggleGroup();
        RadioButton buttonDebutant = new RadioButton("_débutant");
        buttonDebutant.setMnemonicParsing(true);
        buttonDebutant.setSelected(true);
        buttonDebutant.setToggleGroup(toggleNiveau);
        RadioButton buttonMoyen = new RadioButton("_moyen");
        buttonMoyen.setMnemonicParsing(true);
        buttonMoyen.setToggleGroup(toggleNiveau);
        RadioButton buttonAvance = new RadioButton("_avancé");
        buttonAvance.setMnemonicParsing(true);
        buttonAvance.setToggleGroup(toggleNiveau);
        RadioButton buttonExpert = new RadioButton("_expert");
        buttonExpert.setMnemonicParsing(true);
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

        Button boutonAnnuler = new Button("_Annuler");
        boutonAnnuler.setMnemonicParsing(true);
        Button boutonEnregistrer = new Button("_Enregistrer");
        boutonEnregistrer.setMnemonicParsing(true);


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
