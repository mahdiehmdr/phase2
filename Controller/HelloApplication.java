package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Constants;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StarterPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Hogwarts war");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }
    @Override
    public void stop(){
        Constants.writeInformationInFile();
    }
    public static void main(String[] args) {
        launch();
    }
}
