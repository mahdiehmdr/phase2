package Controller;

import Model.Damage_Heal;
import Model.Game;
import Model.Spell;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import util.Constants;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu implements Initializable {
    @FXML
    Label name, coin, exp;
    @FXML
    ImageView prof,dementor,boggart,aragog,sectum,fluffy,patronus,deatheator,phonix,hippogriff,elf,basillisk,werwolf,
            vampire,unicorn,centaur,quilin,niffler,thestral,goblin,peeves,willow,merpeople,madam,hedwig,pixie;
    @FXML
    Button dementorB,boggartB,aragogB,sectumB,fluffyB,patronusB,deatheatorB,phonixB,hippB,elfB,basilliskB,werwolfB,
            vampireB,unicornB,centaurB,quilinB,nifflerB,thestralB,goblinB,peevesB,willowB,mermaidB,madamB,hedwigB,pixieB;
    @FXML
    public void back(ActionEvent event) {
        try {
            switchScene(event, "MainPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void dementorBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCardFromDeckByName("DEMENTOR")==null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("DEMENTOR").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getCardByName("DEMENTOR"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getCardByName("DEMENTOR").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
        else if(Constants.loggedInUser.getCardFromDeckByName("DEMENTOR")!=null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("DEMENTOR").getUpgradeCost()&&Constants.loggedInUser.getLevel()>Constants.getCardByName("DEMENTOR").getUpgradeLevel()){
            Constants.loggedInUser.upgradeCard("DEMENTOR");
            Constants.loggedInUser.setCoin(Constants.loggedInUser.getCoin()-Constants.getCardByName("DEMENTOR").getUpgradeCost());
            scan();
        }
    }
    @FXML
    public void boggartBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCardFromDeckByName("BOGGART")==null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("BOGGART").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getCardByName("BOGGART"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getCardByName("BOGGART").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
        else if(Constants.loggedInUser.getCardFromDeckByName("BOGGART")!=null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("BOGGART").getUpgradeCost()&&Constants.loggedInUser.getLevel()>Constants.getCardByName("BOGGART").getUpgradeLevel()){
            Constants.loggedInUser.upgradeCard("BOGGART");
            Constants.loggedInUser.setCoin(Constants.loggedInUser.getCoin()-Constants.getCardByName("BOGGART").getUpgradeCost());
            scan();
        }
    }
    @FXML
    public void aragogBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCardFromDeckByName("ARAGOG")==null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("ARAGOG").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getCardByName("ARAGOG"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getCardByName("ARAGOG").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
        else if(Constants.loggedInUser.getCardFromDeckByName("ARAGOG")!=null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("ARAGOG").getUpgradeCost()&&Constants.loggedInUser.getLevel()>Constants.getCardByName("ARAGOG").getUpgradeLevel()){
            Constants.loggedInUser.upgradeCard("ARAGOG");
            Constants.loggedInUser.setCoin(Constants.loggedInUser.getCoin()-Constants.getCardByName("ARAGOG").getUpgradeCost());
            scan();
        }
    }
    @FXML
    public void sectumBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCoin()>Constants.getSpellByName("SECTUMSEMPRA").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getSpellByName("SECTUMSEMPRA"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getSpellByName("SECTUMSEMPRA").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
    }
    @FXML
    public void fluffyBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCoin()>Constants.getSpellByName("FLUFFY").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getSpellByName("FLUFFY"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getSpellByName("FLUFFY").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
    }
    @FXML
    public void patronusBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCoin()>Constants.getSpellByName("PATRONUS").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getSpellByName("PATRONUS"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getSpellByName("PATRONUS").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
    }
    @FXML
    public void deatheatorBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCardFromDeckByName("DEATHEATOR")==null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("DEATHEATOR").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getCardByName("DEATHEATOR"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getCardByName("DEATHEATOR").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
        else if(Constants.loggedInUser.getCardFromDeckByName("DEATHEATOR")!=null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("DEATHEATOR").getUpgradeCost()&&Constants.loggedInUser.getLevel()>Constants.getCardByName("DEATHEATOR").getUpgradeLevel()){
            Constants.loggedInUser.upgradeCard("DEATHEATOR");
            Constants.loggedInUser.setCoin(Constants.loggedInUser.getCoin()-Constants.getCardByName("DEATHEATOR").getUpgradeCost());
            scan();
        }
    }
    @FXML
    public void phonixBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCardFromDeckByName("PHONIX")==null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("PHONIX").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getCardByName("PHONIX"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getCardByName("PHONIX").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
        else if(Constants.loggedInUser.getCardFromDeckByName("PHONIX")!=null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("PHONIX").getUpgradeCost()&&Constants.loggedInUser.getLevel()>Constants.getCardByName("PHONIX").getUpgradeLevel()){
            Constants.loggedInUser.upgradeCard("PHONIX");
            Constants.loggedInUser.setCoin(Constants.loggedInUser.getCoin()-Constants.getCardByName("PHONIX").getUpgradeCost());
            scan();
        }
    }
    @FXML
    public void hippBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCardFromDeckByName("HIPPOGRIFF")==null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("HIPPOGRIFF").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getCardByName("HIPPOGRIFF"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getCardByName("HIPPOGRIFF").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
        else if(Constants.loggedInUser.getCardFromDeckByName("HIPPOGRIFF")!=null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("HIPPOGRIFF").getUpgradeCost()&&Constants.loggedInUser.getLevel()>Constants.getCardByName("HIPPOGRIFF").getUpgradeLevel()){
            Constants.loggedInUser.upgradeCard("HIPPOGRIFF");
            Constants.loggedInUser.setCoin(Constants.loggedInUser.getCoin()-Constants.getCardByName("HIPPOGRIFF").getUpgradeCost());
            scan();
        }
    }
    @FXML
    public void elfBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCardFromDeckByName("HOUSEELF")==null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("HOUSEELF").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getCardByName("HOUSEELF"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getCardByName("HOUSEELF").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
        else if(Constants.loggedInUser.getCardFromDeckByName("HOUSEELF")!=null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("HOUSEELF").getUpgradeCost()&&Constants.loggedInUser.getLevel()>Constants.getCardByName("HOUSEELF").getUpgradeLevel()){
            Constants.loggedInUser.upgradeCard("HOUSEELF");
            Constants.loggedInUser.setCoin(Constants.loggedInUser.getCoin()-Constants.getCardByName("HOUSEELF").getUpgradeCost());
            scan();
        }
    }
    @FXML
    public void basilliskBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCardFromDeckByName("BASILLISK")==null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("BASILLISK").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getCardByName("BASILLISK"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getCardByName("BASILLISK").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
        else if(Constants.loggedInUser.getCardFromDeckByName("BASILLISK")!=null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("BASILLISK").getUpgradeCost()&&Constants.loggedInUser.getLevel()>Constants.getCardByName("BASILLISK").getUpgradeLevel()){
            Constants.loggedInUser.upgradeCard("BASILLISK");
            Constants.loggedInUser.setCoin(Constants.loggedInUser.getCoin()-Constants.getCardByName("BASILLISK").getUpgradeCost());
            scan();
        }
    }
    @FXML
    public void werwolfBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCardFromDeckByName("WERWOLF")==null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("WERWOLF").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getCardByName("WERWOLF"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getCardByName("WERWOLF").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
        else if(Constants.loggedInUser.getCardFromDeckByName("WERWOLF")!=null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("WERWOLF").getUpgradeCost()&&Constants.loggedInUser.getLevel()>Constants.getCardByName("WERWOLF").getUpgradeLevel()){
            Constants.loggedInUser.upgradeCard("WERWOLF");
            Constants.loggedInUser.setCoin(Constants.loggedInUser.getCoin()-Constants.getCardByName("WERWOLF").getUpgradeCost());
            scan();
        }
    }
    @FXML
    public void vampireBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCardFromDeckByName("VAMPIRE")==null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("VAMPIRE").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getCardByName("VAMPIRE"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getCardByName("VAMPIRE").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
        else if(Constants.loggedInUser.getCardFromDeckByName("VAMPIRE")!=null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("VAMPIRE").getUpgradeCost()&&Constants.loggedInUser.getLevel()>Constants.getCardByName("VAMPIRE").getUpgradeLevel()){
            Constants.loggedInUser.upgradeCard("VAMPIRE");
            Constants.loggedInUser.setCoin(Constants.loggedInUser.getCoin()-Constants.getCardByName("VAMPIRE").getUpgradeCost());
            scan();
        }
    }
    @FXML
    public void unicornBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCardFromDeckByName("UNICORN")==null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("UNICORN").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getCardByName("UNICORN"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getCardByName("UNICORN").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
        else if(Constants.loggedInUser.getCardFromDeckByName("UNICORN")!=null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("UNICORN").getUpgradeCost()&&Constants.loggedInUser.getLevel()>Constants.getCardByName("UNICORN").getUpgradeLevel()){
            Constants.loggedInUser.upgradeCard("UNICORN");
            Constants.loggedInUser.setCoin(Constants.loggedInUser.getCoin()-Constants.getCardByName("UNICORN").getUpgradeCost());
            scan();
        }
    }
    @FXML
    public void centaurBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCardFromDeckByName("CENTAUR")==null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("CENTAUR").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getCardByName("CENTAUR"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getCardByName("CENTAUR").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
        else if(Constants.loggedInUser.getCardFromDeckByName("CENTAUR")!=null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("CENTAUR").getUpgradeCost()&&Constants.loggedInUser.getLevel()>Constants.getCardByName("CENTAUR").getUpgradeLevel()){
            Constants.loggedInUser.upgradeCard("CENTAUR");
            Constants.loggedInUser.setCoin(Constants.loggedInUser.getCoin()-Constants.getCardByName("CENTAUR").getUpgradeCost());
            scan();
        }
    }
    @FXML
    public void quilinBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCardFromDeckByName("QUILIN")==null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("QUILIN").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getCardByName("QUILIN"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getCardByName("QUILIN").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
        else if(Constants.loggedInUser.getCardFromDeckByName("QUILIN")!=null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("QUILIN").getUpgradeCost()&&Constants.loggedInUser.getLevel()>Constants.getCardByName("QUILIN").getUpgradeLevel()){
            Constants.loggedInUser.upgradeCard("QUILIN");
            Constants.loggedInUser.setCoin(Constants.loggedInUser.getCoin()-Constants.getCardByName("QUILIN").getUpgradeCost());
            scan();
        }
    }
    @FXML
    public void nifflerBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCardFromDeckByName("NIFFLER")==null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("NIFFLER").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getCardByName("NIFFLER"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getCardByName("NIFFLER").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
        else if(Constants.loggedInUser.getCardFromDeckByName("NIFFLER")!=null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("NIFFLER").getUpgradeCost()&&Constants.loggedInUser.getLevel()>Constants.getCardByName("NIFFLER").getUpgradeLevel()){
            Constants.loggedInUser.upgradeCard("NIFFLER");
            Constants.loggedInUser.setCoin(Constants.loggedInUser.getCoin()-Constants.getCardByName("NIFFLER").getUpgradeCost());
            scan();
        }
    }
    @FXML
    public void thestralBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCardFromDeckByName("THESTRAL")==null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("THESTRAL").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getCardByName("THESTRAL"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getCardByName("THESTRAL").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
        else if(Constants.loggedInUser.getCardFromDeckByName("THESTRAL")!=null&&Constants.loggedInUser.getCoin()>Constants.getCardByName("THESTRAL").getUpgradeCost()&&Constants.loggedInUser.getLevel()>Constants.getCardByName("THESTRAL").getUpgradeLevel()){
            Constants.loggedInUser.upgradeCard("THESTRAL");
            Constants.loggedInUser.setCoin(Constants.loggedInUser.getCoin()-Constants.getCardByName("THESTRAL").getUpgradeCost());
            scan();
        }
    }
    @FXML
    public void goblinBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCoin()>Constants.getSpellByName("GOBLIN").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getSpellByName("GOBLIN"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getSpellByName("GOBLIN").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
    }
    @FXML
    public void peevesBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCoin()>Constants.getSpellByName("PEEVES").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getSpellByName("PEEVES"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getSpellByName("PEEVES").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
    }
    @FXML
    public void willowBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCoin()>Constants.getSpellByName("WHOMPING WILLOW").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getSpellByName("WHOMPING WILLOW"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getSpellByName("WHOMPING WILLOW").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
    }
    @FXML
    public void mermaidBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCoin()>Constants.getSpellByName("MERPEOPLE").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getSpellByName("MERPEOPLE"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getSpellByName("MERPEOPLE").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
    }
    @FXML
    public void madamBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCoin()>Constants.getSpellByName("MADAM POMFREY").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getSpellByName("MADAM POMFREY"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getSpellByName("MADAM POMFREY").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
    }
    @FXML
    public void hedwigBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCoin()>Constants.getSpellByName("HEDWIG").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getSpellByName("HEDWIG"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getSpellByName("HEDWIG").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
        }
    }
    @FXML
    public void pixieBtn(ActionEvent event) {
        if(Constants.loggedInUser.getCoin()>Constants.getSpellByName("CORNISH PIXIE").getPrice()){
            Constants.loggedInUser.addToDeck(Constants.getSpellByName("CORNISH PIXIE"));
            int coin = Constants.loggedInUser.getCoin();
            coin -= Constants.getSpellByName("CORNISH PIXIE").getPrice();
            Constants.loggedInUser.setCoin(coin);
            scan();
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

    public void scan(){
        Effect GaussianBlur=new GaussianBlur();
        if(Constants.loggedInUser.getSpellFromDeckByName("PEEVES")==null) {
            peeves.setEffect(GaussianBlur);
            peevesB.setVisible(true);
            peevesB.setText("Buy PEEVES: "+String.valueOf(Constants.getSpellByName("PEEVES").getPrice()));
        }else {
            peeves.setEffect(null);
            peevesB.setVisible(false);
        }if(Constants.loggedInUser.getSpellFromDeckByName("SECTUMSEMPRA")==null) {
            sectum.setEffect(GaussianBlur);
            sectumB.setVisible(true);
            sectumB.setText("Buy SECTUMSEMPRA: "+String.valueOf(Constants.getSpellByName("SECTUMSEMPRA").getPrice()));
        }else {
            sectum.setEffect(null);
            sectumB.setVisible(false);
        }if(Constants.loggedInUser.getSpellFromDeckByName("CORNISH PIXIE")==null) {
            pixie.setEffect(GaussianBlur);
            pixieB.setVisible(true);
            pixieB.setText("Buy CORNISH PIXIE: "+String.valueOf(Constants.getSpellByName("CORNISH PIXIE").getPrice()));
        }else {
            pixie.setEffect(null);
            pixieB.setVisible(false);
        }if(Constants.loggedInUser.getSpellFromDeckByName("HEDWIG")==null) {
            hedwig.setEffect(GaussianBlur);
            hedwigB.setVisible(true);
            hedwigB.setText("Buy HEDWIG: "+String.valueOf(Constants.getSpellByName("HEDWIG").getPrice()));
        }else {
            hedwig.setEffect(null);
            hedwigB.setVisible(false);
        }if(Constants.loggedInUser.getSpellFromDeckByName("MADAM POMFREY")==null) {
            madam.setEffect(GaussianBlur);
            madamB.setVisible(true);
            madamB.setText("Buy MADAM POMFREY: "+String.valueOf(Constants.getSpellByName("MADAM POMFREY").getPrice()));
        }else {
            madam.setEffect(null);
            madamB.setVisible(false);
        }if(Constants.loggedInUser.getSpellFromDeckByName("MERPEOPLE")==null) {
            merpeople.setEffect(GaussianBlur);
            mermaidB.setVisible(true);
            mermaidB.setText("Buy MERPEOPLE: "+String.valueOf(Constants.getSpellByName("MERPEOPLE").getPrice()));
        }else {
            merpeople.setEffect(null);
            mermaidB.setVisible(false);
        }if(Constants.loggedInUser.getSpellFromDeckByName("WHOMPING WILLOW")==null) {
            willow.setEffect(GaussianBlur);
            willowB.setVisible(true);
            willowB.setText("Buy WHOMPING WILLOW: "+String.valueOf(Constants.getSpellByName("WHOMPING WILLOW").getPrice()));
        }else {
            willow.setEffect(null);
            willowB.setVisible(false);
        }if(Constants.loggedInUser.getSpellFromDeckByName("GOBLIN")==null) {
            goblin.setEffect(GaussianBlur);
            goblinB.setVisible(true);
            goblinB.setText("Buy GOBLIN: "+String.valueOf(Constants.getSpellByName("GOBLIN").getPrice()));
        }else {
            goblin.setEffect(null);
            goblinB.setVisible(false);
        }if(Constants.loggedInUser.getSpellFromDeckByName("PATRONUS")==null) {
            patronus.setEffect(GaussianBlur);
            patronusB.setVisible(true);
            patronusB.setText("Buy PATRONUS: "+String.valueOf(Constants.getSpellByName("PATRONUS").getPrice()));
        }else {
            patronus.setEffect(null);
            patronusB.setVisible(false);
        }if(Constants.loggedInUser.getSpellFromDeckByName("FLUFFY")==null) {
            fluffy.setEffect(GaussianBlur);
            fluffyB.setVisible(true);
            fluffyB.setText("Buy FLUFFY: "+String.valueOf(Constants.getSpellByName("FLUFFY").getPrice()));
        }else {
            fluffy.setEffect(null);
            fluffyB.setVisible(false);
        }if(Constants.loggedInUser.getCardFromDeckByName("HIPPOGRIFF")==null){/////////////////////////////////////////////////////////
            hippogriff.setEffect(GaussianBlur);
            hippB.setText("Buy HIPPOGRIFF: "+String.valueOf(Constants.getCardByName("HIPPOGRIFF").getPrice()));
        }else {
            hippogriff.setEffect(null);
            hippB.setText("Upgrade HIPPOGRIFF: "+String.valueOf(Constants.getCardByName("HIPPOGRIFF").getUpgradeCost())+" / "+String.valueOf(Constants.getCardByName("HIPPOGRIFF").getUpgradeLevel()));
        }if(Constants.loggedInUser.getCardFromDeckByName("PHONIX")==null){
            phonix.setEffect(GaussianBlur);
            phonixB.setText("Buy PHONIX: "+String.valueOf(Constants.getCardByName("PHONIX").getPrice()));
        }else {
            phonix.setEffect(null);
            phonixB.setText("Upgrade PHONIX: "+String.valueOf(Constants.getCardByName("PHONIX").getUpgradeCost())+" / "+String.valueOf(Constants.getCardByName("PHONIX").getUpgradeLevel()));
        }if(Constants.loggedInUser.getCardFromDeckByName("CENTAUR")==null){
            centaur.setEffect(GaussianBlur);
            centaurB.setText("Buy CENTAUR: "+String.valueOf(Constants.getCardByName("CENTAUR").getPrice()));
        }else {
            centaur.setEffect(null);
            centaurB.setText("Upgrade CENTAUR: "+String.valueOf(Constants.getCardByName("CENTAUR").getUpgradeCost())+" / "+String.valueOf(Constants.getCardByName("CENTAUR").getUpgradeLevel()));
        }if(Constants.loggedInUser.getCardFromDeckByName("UNICORN")==null){
            unicorn.setEffect(GaussianBlur);
            unicornB.setText("Buy UNICORN: "+String.valueOf(Constants.getCardByName("UNICORN").getPrice()));
        }else {
            unicorn.setEffect(null);
            unicornB.setText("Upgrade UNICORN: "+String.valueOf(Constants.getCardByName("UNICORN").getUpgradeCost())+" / "+String.valueOf(Constants.getCardByName("UNICORN").getUpgradeLevel()));
        }if(Constants.loggedInUser.getCardFromDeckByName("NIFFLER")==null){
            niffler.setEffect(GaussianBlur);
            nifflerB.setText("Buy NIFFLER: "+String.valueOf(Constants.getCardByName("NIFFLER").getPrice()));
        }else {
            niffler.setEffect(null);
            nifflerB.setText("Upgrade NIFFLER: "+String.valueOf(Constants.getCardByName("NIFFLER").getUpgradeCost())+" / "+String.valueOf(Constants.getCardByName("NIFFLER").getUpgradeLevel()));
        }if(Constants.loggedInUser.getCardFromDeckByName("THESTRAL")==null){
            thestral.setEffect(GaussianBlur);
            thestralB.setText("Buy THESTRAL: "+String.valueOf(Constants.getCardByName("THESTRAL").getPrice()));
        }else {
            thestral.setEffect(null);
            thestralB.setText("Upgrade THESTRAL: "+String.valueOf(Constants.getCardByName("THESTRAL").getUpgradeCost())+" / "+String.valueOf(Constants.getCardByName("THESTRAL").getUpgradeLevel()));
        }if(Constants.loggedInUser.getCardFromDeckByName("HOUSEELF")==null){
            elf.setEffect(GaussianBlur);
            elfB.setText("Buy HOUSEELF: "+String.valueOf(Constants.getCardByName("HOUSEELF").getPrice()));
        }else {
            elf.setEffect(null);
            elfB.setText("Upgrade HOUSEELF: "+String.valueOf(Constants.getCardByName("HOUSEELF").getUpgradeCost())+" / "+String.valueOf(Constants.getCardByName("HOUSEELF").getUpgradeLevel()));
        }if(Constants.loggedInUser.getCardFromDeckByName("DEATHEATOR")==null){
            deatheator.setEffect(GaussianBlur);
            deatheatorB.setText("Buy DEATHEATOR: "+String.valueOf(Constants.getCardByName("DEATHEATOR").getPrice()));
        }else {
            deatheator.setEffect(null);
            deatheatorB.setText("Upgrade DEATHEATOR: "+String.valueOf(Constants.getCardByName("DEATHEATOR").getUpgradeCost())+" / "+String.valueOf(Constants.getCardByName("DEATHEATOR").getUpgradeLevel()));
        }if(Constants.loggedInUser.getCardFromDeckByName("BOGGART")==null){
            boggart.setEffect(GaussianBlur);
            boggartB.setText("Buy BOGGART: "+String.valueOf(Constants.getCardByName("BOGGART").getPrice()));
        }else {
            boggart.setEffect(null);
            boggartB.setText("Upgrade BOGGART: "+String.valueOf(Constants.getCardByName("BOGGART").getUpgradeCost())+" / "+String.valueOf(Constants.getCardByName("BOGGART").getUpgradeLevel()));
        }if(Constants.loggedInUser.getCardFromDeckByName("WERWOLF")==null){
            werwolf.setEffect(GaussianBlur);
            werwolfB.setText("Buy WERWOLF: "+String.valueOf(Constants.getCardByName("WERWOLF").getPrice()));
        }else {
            werwolf.setEffect(null);
            werwolfB.setText("Upgrade WERWOLF: "+String.valueOf(Constants.getCardByName("WERWOLF").getUpgradeCost())+" / "+String.valueOf(Constants.getCardByName("WERWOLF").getUpgradeLevel()));
        }if(Constants.loggedInUser.getCardFromDeckByName("ARAGOG")==null){
            aragog.setEffect(GaussianBlur);
            aragogB.setText("Buy ARAGOG: "+String.valueOf(Constants.getCardByName("ARAGOG").getPrice()));
        }else {
            aragog.setEffect(null);
            aragogB.setText("Upgrade ARAGOG: "+String.valueOf(Constants.getCardByName("ARAGOG").getUpgradeCost())+" / "+String.valueOf(Constants.getCardByName("ARAGOG").getUpgradeLevel()));
        }if(Constants.loggedInUser.getCardFromDeckByName("VAMPIRE")==null){
            vampire.setEffect(GaussianBlur);
            vampireB.setText("Buy VAMPIRE: "+String.valueOf(Constants.getCardByName("VAMPIRE").getPrice()));
        }else {
            vampire.setEffect(null);
            vampireB.setText("Upgrade VAMPIRE: "+String.valueOf(Constants.getCardByName("VAMPIRE").getUpgradeCost())+" / "+String.valueOf(Constants.getCardByName("VAMPIRE").getUpgradeLevel()));
        }if(Constants.loggedInUser.getCardFromDeckByName("BASILLISK")==null){
            basillisk.setEffect(GaussianBlur);
            basilliskB.setText("Buy BASILLISK: "+String.valueOf(Constants.getCardByName("BASILLISK").getPrice()));
        }else {
            basillisk.setEffect(null);
            basilliskB.setText("Upgrade BASILLISK: "+String.valueOf(Constants.getCardByName("BASILLISK").getUpgradeCost())+" / "+String.valueOf(Constants.getCardByName("BASILLISK").getUpgradeLevel()));
        }if(Constants.loggedInUser.getCardFromDeckByName("DEMENTOR")==null){
            dementor.setEffect(GaussianBlur);
            dementorB.setText("Buy DEMENTOR: "+String.valueOf(Constants.getCardByName("DEMENTOR").getPrice()));
        }else {
            dementor.setEffect(null);
            dementorB.setText("Upgrade DEMENTOR: "+String.valueOf(Constants.getCardByName("DEMENTOR").getUpgradeCost())+" / "+String.valueOf(Constants.getCardByName("DEMENTOR").getUpgradeLevel()));
        }if(Constants.loggedInUser.getCardFromDeckByName("QUILIN")==null){
            quilin.setEffect(GaussianBlur);
            quilinB.setText("Buy QUILIN: "+String.valueOf(Constants.getCardByName("QUILIN").getPrice()));
        }else {
            quilin.setEffect(null);
            quilinB.setText("Upgrade QUILIN: "+String.valueOf(Constants.getCardByName("QUILIN").getUpgradeCost())+" / "+String.valueOf(Constants.getCardByName("QUILIN").getUpgradeLevel()));
        }
        coin.setText(String.valueOf(Constants.loggedInUser.getCoin()));
        exp.setText(String.valueOf(Constants.loggedInUser.getExp()));

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Constants.loggedInUser.getCharacter()!=null)
            prof.setImage(getCharIm(Constants.loggedInUser.getCharacter()));
        name.setText(Constants.loggedInUser.getUsername());
        scan();
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
