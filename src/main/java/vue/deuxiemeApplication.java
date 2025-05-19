package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.File;
import java.util.Objects;

public class deuxiemeApplication extends Application {
    public void start(Stage stage)  {
        HBox root = new HBoxRoot();
        Scene scene = new Scene(root, 1250, 500);

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/agenda_logo.png")));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setTitle ("Projet Réservation");
        stage.show();
        stage.setResizable(false);



        File fileCss = new File("css" + File.separator + "premierStyle.css");
        scene.getStylesheets().add(fileCss.toURI().toString());
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
