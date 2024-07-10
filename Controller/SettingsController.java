package Controller;

import Model.Damage_Heal;
import Model.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.Constants;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;

public class SettingsController implements Initializable {
    @FXML
    Slider volume;
    @FXML
    public void back(ActionEvent event) {
        try {
            switchScene(event, "MainPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void prologue(ActionEvent event){
        Constants.BackGround.pause();
        Constants.Hedwig.pause();
        Constants.Arrival.pause();
        Constants.Wondrous.pause();
        Constants.mute=false;
        Constants.restartBackGroundMedia();
    }
    @FXML
    public void arrival(ActionEvent event){
        Constants.BackGround.pause();
        Constants.Hedwig.pause();
        Constants.Arrival.pause();
        Constants.Wondrous.pause();
        Constants.mute=false;
        Constants.restartArrivalMedia();
    }
    @FXML
    public void hedwig(ActionEvent event){
        Constants.BackGround.pause();
        Constants.Hedwig.pause();
        Constants.Arrival.pause();
        Constants.Wondrous.pause();
        Constants.mute=false;
        Constants.restartHedwigMedia();
    }
    @FXML
    public void wondrous(ActionEvent event){
        Constants.BackGround.pause();
        Constants.Hedwig.pause();
        Constants.Arrival.pause();
        Constants.Wondrous.pause();
        Constants.mute=false;
        Constants.restartWondrousMedia();
    }
    @FXML
    public void mute(ActionEvent event){
        Constants.BackGround.pause();
        Constants.Hedwig.pause();
        Constants.Arrival.pause();
        Constants.Wondrous.pause();
        Constants.mute=true;
    }

    private void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        Parent newPage = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene newScene = new Scene(newPage);
        newScene.getStylesheets().add(getClass().getResource("CSS/Hello.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(newScene);
        stage.setResizable(true);
        stage.show();

        newScene.setOnKeyPressed(this::handleKeyPressed);
    }

    private void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case ESCAPE:
                Platform.exit();
                break;
            // Handle other keys if necessary
            default:
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //volume.setValue(1);

        volume.valueProperty().addListener((observable, oldValue, newValue) -> {
            handleVolumeChange(newValue.doubleValue());
        });
    }

    private void handleVolumeChange(double volume) {
        Constants.BackGround.setVolume(volume);
        Constants.Hedwig.setVolume(volume);
        Constants.Arrival.setVolume(volume);
        Constants.Wondrous.setVolume(volume);
        Constants.volume=volume;
    }
}
