package vue;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import modele.CalendrierDuMois;
import modele.ConstantesCalendrier;
import modele.DateCalendrier;

import java.util.List;

public class VBoxCalendrier extends VBox implements ConstantesCalendrier {
    private Label moisLabel;
    public VBoxCalendrier(Controleur controleur) {
        Button BoutonPrec = new Button("<");
        Button ButtonSuiv = new Button(">");
        Button BoutonDernier = new Button(">>");
        Button BoutonPremier = new Button("<<");

        DateCalendrier today = new DateCalendrier();

        // stackPaneMois : pour empiler 1 conteneur par mois (ici un TilePane)
        StackPane stackPaneMois = new StackPane();

        // Les boutons seront insérés dans buttonGroup ainsi l'utilisateur en sélectionne 1 seul à la fois
        ToggleGroup buttonGroup = new ToggleGroup();
        for (int numMois = 1; numMois <= 12; numMois++) {
            CalendrierDuMois monthCalendar = new CalendrierDuMois(numMois, today.getAnnee());

            // 1 conteneur tilePane par mois
            TilePane tilePane = new TilePane(Orientation.HORIZONTAL);
            tilePane.setPrefColumns(7);
            tilePane.setHgap(5); // Espace horizontal entre les éléments
            tilePane.setVgap(5); // Espace vertical entre les éléments

            // 1 ligne pour lu, ma, ... et 4, 5, 6 lignes pour les boutons Date
            tilePane.setPrefRows(monthCalendar.getDates().size() / 7 + 1);

            // setId -> à utiliser dans la feuille de style
            tilePane.setId("opaque");

            // boucle pour créer la 1ere ligne lu, ma, ...
            for (String jourAb : JOURS_SEMAINE_ABR) {
                Label labelJour = new Label(jourAb);
                tilePane.getChildren().add(labelJour);
            }

            for (DateCalendrier date : monthCalendar.getDates()) {
                // création d'1 bouton par jour
                ToggleButton boutonDate = new ToggleButton(Integer.toString(date.getJour()));

                // insère le boutonDate dans le groupe
                boutonDate.setToggleGroup(buttonGroup);
                tilePane.getChildren().add(boutonDate);

                // associe une date au toggleBouton, utilisé par la suite

                boutonDate.setUserData(date);
                boutonDate.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        boutonDate.setId("selection");
                        if (date.isToday()) {
                            boutonDate.setId("today");
                        }
                    }
                });
                // les attributs id sont utilisés dans la feuille de style
                if (date.getMois()!= monthCalendar.getMois()) {
                    boutonDate.setId("dateHorsMois");
                    boutonDate.setDisable(true);
                }
                if (date.isToday()) {
                    boutonDate.setId("today");
                }
                boutonDate.addEventFilter(ActionEvent.ACTION, controleur);
            }

            tilePane.setAccessibleText(MOIS[numMois - 1]);
            stackPaneMois.getChildren().add(tilePane);
        }
        List<Node> liste = stackPaneMois.getChildren();
        final int dernierIndice = liste.size()-1;
        Node premierMois = liste.getFirst();
        Node dernierMois = liste.get(dernierIndice);

        while (!liste.get(dernierIndice).getAccessibleText().equals(MOIS[today.getMois() - 1])){
            liste.get(dernierIndice).toBack();
        }

        this.getChildren().add(stackPaneMois);

        HBox alignement = new HBox();
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        moisLabel = new Label(MOIS[today.getMois()-1]);
        moisLabel.getStyleClass().add("title");
        HBox.setMargin(BoutonPremier, new Insets(5, 2.5, 0, 0));
        HBox.setMargin(BoutonDernier, new Insets(5, 0, 0, 2.5));
        HBox.setMargin(BoutonPrec, new Insets(5, 2.5, 0, 2.5));
        HBox.setMargin(ButtonSuiv, new Insets(5, 2.5, 0, 2.5));
        // Alignement des boutons
        alignement.getChildren().addAll(BoutonPremier, BoutonPrec,
                ButtonSuiv, BoutonDernier, spacer, moisLabel);
        moisLabel.setId("moisLabel");

        this.getChildren().add(alignement);

        ButtonSuiv.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                HBoxRoot.getCalendrierPane().updateMoisLabel(liste.getFirst().getAccessibleText());
                liste.getFirst().toFront();
            }
        });

        BoutonPrec.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                liste.getLast().toBack();
                HBoxRoot.getCalendrierPane().updateMoisLabel(liste.getLast().getAccessibleText());
            }
        });

        BoutonDernier.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                while (liste.getLast() != dernierMois) {
                    liste.getLast().toBack();
                    HBoxRoot.getCalendrierPane().updateMoisLabel(liste.getLast().getAccessibleText());
                }

            }
        });

        BoutonPremier.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                while (liste.getLast() != premierMois) {
                    HBoxRoot.getCalendrierPane().updateMoisLabel(liste.getFirst().getAccessibleText());
                    liste.getFirst().toFront();
                }

            }
        });
    }

    public void updateMoisLabel(String parMois){
        moisLabel.setText(parMois);
    }
}