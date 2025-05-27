package fr.iut.saeterraria.sae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Terraria_ extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Terraria_.class.getResource("vueTerraria.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),200,200);
        stage.setTitle("Terraria.exe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}
