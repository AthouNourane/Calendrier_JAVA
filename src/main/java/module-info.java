module com.example.deuxiemeprojet {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.example.deuxiemeprojet to javafx.fxml;
    exports com.example.deuxiemeprojet;
    exports controleur;
    exports vue;
    exports modele;
}