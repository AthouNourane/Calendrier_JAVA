package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class deuxiemeApplication extends Application {
    public void start(Stage stage)  {
        VBox root = new VBoxRootMoisV2();
        Scene scene = new Scene(root, 300, 300);

        stage.setScene(scene);
        stage.setTitle ("Petit calendrier");
        stage.show();

        File fileCss = new File("css" + File.separator + "premierStyle.css");
        scene.getStylesheets().add(fileCss.toURI().toString());
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
