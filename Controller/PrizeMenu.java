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

public class PrizeMenu implements Initializable {
    @FXML
    Label exp, name, coin, levelup;
    @FXML
    ImageView expIm, coinIm, levelupIm, prof;
    @FXML
    public void back(ActionEvent event) {
        Constants.writeInformationInFile();
        try {
            switchScene(event, "MainPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        for(int i=0; i<21; i++){
            if(Constants.loggedInUser.getHp()<Constants.secondUser.getHp()){
                if(Constants.game.isBetMood()) {
                    Constants.secondUser.addCoin(Constants.game.getBetCoin());
                }
                Constants.secondUser.addCoin(5*Math.abs(Constants.game.getGuestDamage()-Constants.game.getHostDamage()));
                if(Constants.game.isBetMood())
                    coin.setText(String.valueOf(5*Math.abs(Constants.game.getGuestDamage()-Constants.game.getHostDamage()))+" + "+String.valueOf(Constants.game.getBetCoin()));
                coin.setText(String.valueOf(5*Math.abs(Constants.game.getGuestDamage()-Constants.game.getHostDamage())));
                Constants.secondUser.addExp(20*(Math.abs(Constants.game.getGuestDamage()-Constants.game.getHostDamage())));
                exp.setText(String.valueOf(20*(Math.abs(Constants.game.getGuestDamage()-Constants.game.getHostDamage()))));
                Constants.secondUser.updateLevel();
                levelup.setText(String.valueOf(Constants.secondUser.getLevel()));
                Constants.secondUser.reduceHP(-200);
                Constants.loggedInUser.resetHP();
                String forWinner = Constants.loggedInUser.getUsername() + " " + Constants.game.getDateAndTime() + " won";
                Game win = new Game(Constants.loggedInUser.getUsername(), Constants.game.getDate(), Constants.game.getTime(), "win");
                Constants.secondUser.addGamesToGames(win);
                String forLoser = Constants.secondUser.getUsername() + " " + Constants.game.getDateAndTime() + " lose";
                Game lose = new Game(Constants.secondUser.getUsername() ,Constants.game.getDate(), Constants.game.getTime(), "lose");
                Constants.loggedInUser.addGamesToGames(lose);
                Constants.loggedInUser.addGame(forLoser);
                Constants.secondUser.addGame(forWinner);
                prof.setImage(getCharIm(Constants.secondUser.getCharacter()));
                name.setText(Constants.secondUser.getUsername());
                return;
            }
            else if(Constants.secondUser.getHp()<=Constants.loggedInUser.getHp()){
                if(Constants.game.isBetMood()) {
                    Constants.loggedInUser.addCoin(Constants.game.getBetCoin());
                }
                Constants.loggedInUser.addCoin(5*Math.abs(Constants.game.getGuestDamage()-Constants.game.getHostDamage()));
                if(Constants.game.isBetMood())
                    coin.setText(String.valueOf(5*Math.abs(Constants.game.getGuestDamage()-Constants.game.getHostDamage()))+" + "+String.valueOf(Constants.game.getBetCoin()));
                coin.setText(String.valueOf(5*Math.abs(Constants.game.getGuestDamage()-Constants.game.getHostDamage())));
                Constants.loggedInUser.addExp(20*(Math.abs(Constants.game.getGuestDamage()-Constants.game.getHostDamage())));
                exp.setText(String.valueOf(20*(Math.abs(Constants.game.getGuestDamage()-Constants.game.getHostDamage()))));
                Constants.loggedInUser.updateLevel();
                levelup.setText(String.valueOf(Constants.secondUser.getLevel()));
                Constants.loggedInUser.reduceHP(-200);
                Constants.secondUser.resetHP();
                String forLoser = Constants.loggedInUser.getUsername() + " " + Constants.game.getDateAndTime() + " lose";
                String forWinner = Constants.secondUser.getUsername() + " " + Constants.game.getDateAndTime() + " won";
                Game win = new Game(Constants.secondUser.getUsername(), Constants.game.getDate(), Constants.game.getTime(), "win");
                Constants.loggedInUser.addGamesToGames(win);
                Game lose = new Game(Constants.loggedInUser.getUsername() ,Constants.game.getDate(), Constants.game.getTime(), "lose");
                Constants.secondUser.addGamesToGames(lose);
                Constants.loggedInUser.addGame(forWinner);
                Constants.secondUser.addGame(forLoser);
                prof.setImage(getCharIm(Constants.loggedInUser.getCharacter()));
                name.setText(Constants.loggedInUser.getUsername());
                return;
            }
        }
    }
    public Image getCharIm(String character) {
        switch (character) {
            case "Harry Potter":
                return Constants.harryProf;
            case "Draco Malfoy":
                return Constants.dracoProf;
            case "Hermione Granger":
                return Constants.hermioneProf;
            case "Ronald Weasley":
                return Constants.ronProf;
        }
        return null;
    }
}
