package vue;

import controleur.Controleur;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import modele.DateCalendrier;

public class GridPaneFormulaireReservation extends GridPane {
    private Label dateReservation;
    private DateCalendrier selDate;
    private TextField textCours;
    private ComboBox<Integer> heureDebut;
    private ComboBox<Integer> heureFin;
    private ComboBox<Integer> minDebut;
    private ComboBox<Integer> minFin;
    private ToggleGroup toggleNiveau;
    public GridPaneFormulaireReservation(Controleur controleur) {

        // this.setGridLinesVisible(true);
        this.setHgap(5);
        this.setVgap(5);

        selDate = new DateCalendrier();
        dateReservation = new Label(selDate.toString());
        dateReservation.getStyleClass().add("title");
        Label labelCours = new Label("_Cours");
        labelCours.getStyleClass().add("title");
        labelCours.setMnemonicParsing(true);
        textCours = new TextField();
        labelCours.setLabelFor(textCours);
        textCours.setPromptText("Entre le nom d'une réservation...");
        Platform.runLater(textCours::requestFocus); // Platform.runLater(() -> textCours.requestFocus());

        Label labelNiveau = new Label("Niveau");
        labelNiveau.getStyleClass().add("title");
        toggleNiveau = new ToggleGroup();
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
        labelHoraire.getStyleClass().add("title");
        heureDebut = new ComboBox<>();
        minDebut = new ComboBox<>();
        heureFin = new ComboBox<>();
        minFin = new ComboBox<>();
        heureDebut.setValue(7);
        minDebut.setValue(0);
        heureFin.setValue(9);
        minFin.setValue(0);
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
        boutonEnregistrer.addEventHandler(ActionEvent.ACTION, controleur);


        this.add(dateReservation, 1, 0, 5, 1);

        this.add(labelCours, 0, 1);
        this.add(textCours, 1, 1, 5, 1);

        this.add(labelNiveau, 0, 2);
        this.add(buttonDebutant, 2, 2);
        this.add(buttonMoyen, 4, 2);
        this.add(buttonAvance,2, 3);
        this.add(buttonExpert, 4, 3);

        this.add(labelHoraire, 0, 4);
        this.add(new Label("de"), 1, 4);
        this.add(heureDebut, 2, 4);
        GridPane.setHalignment(heureDebut, HPos.CENTER);
        this.add(new Label("h"), 3, 4);
        this.add(minDebut, 4, 4);
        this.add(new Label("à"), 1, 5);
        this.add(heureFin, 2, 5);
        GridPane.setHalignment(heureFin, HPos.CENTER);
        this.add(new Label("h"), 3, 5);
        this.add(minFin, 4, 5);
        this.add(boutonAnnuler, 2, 6);
        GridPane.setHalignment(boutonAnnuler, HPos.RIGHT);
        this.add(boutonEnregistrer, 4, 6);
    }

    public void updateDateSel(DateCalendrier parDate){
        selDate = parDate;
        dateReservation.setText(parDate.toString());
    }

    public DateCalendrier getSelDate(){
        return selDate;
    }

    public String getTextCours(){
        return textCours.getText();
    }

    public int getHeureDebut(){
        return heureDebut.getValue();
    }

    public int getHeureFin(){
        return heureFin.getValue();
    }

    public int getMinDebut(){
        return minDebut.getValue();
    }

    public int getMinFin(){
        return minFin.getValue();
    }

    public String getNiveau(){
        RadioButton selNiveau = (RadioButton) toggleNiveau.getSelectedToggle();
        return selNiveau.getText().substring(1);
    }
}
