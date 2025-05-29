package vue;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modele.CalendrierDuMois;
import modele.ConstantesCalendrier;
import modele.DateCalendrier;

import java.util.List;

public class VBoxRootMois extends VBox implements ConstantesCalendrier {

    public VBoxRootMois() {
        Button BoutonPrec = new Button("<");
        Button ButtonSuiv = new Button(">");
        Button BoutonDernier = new Button(">>");
        Button BoutonPremier = new Button("<<");

        //System.out.println (monthCalendar);

        DateCalendrier today = new DateCalendrier();
        StackPane stackPaneMois = new StackPane();
        for (int i = 1; i <= 12; i++){
            CalendrierDuMois monthCalendar = new CalendrierDuMois(i, 2025);
            VBox boiteDates = new VBox ();
            ScrollPane scrollPaneDates = new ScrollPane();
            scrollPaneDates.setContent(boiteDates);
            VBox.setMargin( scrollPaneDates, new Insets(4) );
                for (DateCalendrier date : monthCalendar.getDates()) {
                    Label labelDate = new Label(date.toString());
                    // les attributs id sont utilisés dans la feuille de style
                    if (date.getMois()!= monthCalendar.getMois()) {
                        labelDate.setId("dateHorsMois");
                    }
                    if (date.isToday()) {
                        labelDate.setId("today");
                    }
                    VBox.setMargin( labelDate, new Insets(8));
                    boiteDates.getChildren().add(labelDate);
                }


            scrollPaneDates.setAccessibleText(MOIS[i - 1]);
            stackPaneMois.getChildren().add(scrollPaneDates);
        }
        List<Node> liste = stackPaneMois.getChildren();
        final int dernierIndice = liste.size()-1;
        Node premierMois = liste.getFirst();
        Node dernierMois = liste.get(dernierIndice);

        while (!liste.get(dernierIndice).getAccessibleText().equals(MOIS[today.getMois() - 1])){
            liste.get(dernierIndice).toBack();
        }
        HBox alignement = new HBox();
        this.getChildren().add(stackPaneMois);

        // Alignement des boutons
        alignement.getChildren().add(BoutonPremier);
        alignement.getChildren().add(BoutonPrec);
        alignement.getChildren().add(ButtonSuiv);
        alignement.getChildren().add(BoutonDernier);

        this.getChildren().add(alignement);


        ButtonSuiv.setOnAction(_ -> {
            System.out.println("bouton suivant");
            liste.getFirst().toFront();
        });

        BoutonPrec.setOnAction(_ -> {
            System.out.println("bouton précédent");
            liste.getLast().toBack();
        });

        BoutonDernier.setOnAction(_ -> {
            while (liste.getLast() != dernierMois) {
                liste.getLast().toBack();
            }
        });


        BoutonPremier.setOnAction(_ -> {
            while (liste.getLast() != premierMois) {
                liste.getFirst().toFront();
            }
        });

    }
}