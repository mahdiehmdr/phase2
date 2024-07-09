
package util;

import Model.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;



public class Constants {
    public static final User hostPlayer = new User("mahdieh","mhd","email","mmm"), guestPlayer=new User("parnia","rzie","yahoo","ppp");
    public static User loggedInUser;
    public static User secondUser;
    public static void l(){hostPlayer.setCharacter("Harry Potter");
    guestPlayer.setCharacter("Harry Potter");}
    public static final RegistryMenu registryMenu=new RegistryMenu();
    public static final ProfileMenu profileMenu = new ProfileMenu();
    public static final MainMenu mainMenu = new MainMenu();
    public static final Outputs outputs=new Outputs();
    public static Game game = new Game();
    public static boolean login(String username, String password, Label label){
        User user = registryMenu.findUserByUsername(username);
        boolean problem = true;
        if(password.equals(user.getPassword())){
            loggedInUser = user;
            label.setText(outputs.loggedInSuccessfully);
            problem = false;
        }
        return problem;
    }
    public static MediaPlayer getMediaPlayer(String path){
        String mediaPath = Constants.class.getResource(path).toExternalForm();
        Media media = new Media(mediaPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        return mediaPlayer;
    }
    public static final boolean mute=false;
    public static final double volume=1;
    public static MediaPlayer BackGround=getMediaPlayer("/cards/01 - Prologue.mp3");
    public static final MediaPlayer GameBackGround=getMediaPlayer("/cards/16 - The Chess Game.mp3");
    public static final MediaPlayer Drop=getMediaPlayer("/cards/drop.mp3");
    public static Media drop=Drop.getMedia();
    public static Media gameBackGroung=GameBackGround.getMedia();
    public static void restartBackGroundMedia(){
        BackGround=getMediaPlayer("/cards/01 - Prologue.mp3");
        BackGround.play();
    }


    public static final Damage_Heal QUILIN = new Damage_Heal("QUILIN", 37, 4, 48, 5, 50, 245);
    public static final Damage_Heal HIPPOGRIFF = new Damage_Heal("HIPPOGRIFF", 28, 4, 32, 3, 30, 108);
    public static final Damage_Heal PHOENIX = new Damage_Heal("PHONIX", 35, 5, 50, 7, 91, 315);
    public static final Damage_Heal CENTAUR = new Damage_Heal("CENTAUR", 25, 3, 24, 2, 20, 66);
    public static final Damage_Heal UNICORN = new Damage_Heal("UNICORN", 31, 5, 50, 4, 45, 164);
    public static final Damage_Heal NIFFLER = new Damage_Heal("NIFFLER", 23, 1, 10, 2, 15, 66);
    public static final Damage_Heal THESTRAL = new Damage_Heal("THESTRAL", 24, 1, 11, 2, 10, 70);
    public static final Damage_Heal HOUSEELF = new Damage_Heal("HOUSEELF", 30, 3, 27, 4, 35, 156);
    public static final Damage_Heal DEATHEATER = new Damage_Heal("DEATHEATOR", 33, 4, 36, 6, 72, 256);
    public static final Damage_Heal BOGGART = new Damage_Heal("BOGGART", 21, 1, 10, 2, 20, 62);
    public static final Damage_Heal WEREWOLF = new Damage_Heal("WERWOLF", 24, 3, 21, 3, 30, 91);
    public static final Damage_Heal ARAGOG = new Damage_Heal("ARAGOG", 22, 2, 16, 2, 25, 60);
    public static final Damage_Heal VAMPIRE = new Damage_Heal("VAMPIRE", 26, 3, 30, 4, 40, 144);
    public static final Damage_Heal BASILISK = new Damage_Heal("BASILLISK", 35, 5, 40, 7, 84, 301);
    public static final Damage_Heal DEMENTOR = new Damage_Heal("DEMENTOR", 39, 5, 50, 8, 100, 392);

    public static final ArrayList<Damage_Heal> cards = new ArrayList<>(
            Arrays.asList(QUILIN, HIPPOGRIFF, PHOENIX, CENTAUR, UNICORN, NIFFLER, THESTRAL, HOUSEELF, DEATHEATER,
                    BOGGART, WEREWOLF, ARAGOG, VAMPIRE, BASILISK, DEMENTOR)
    );

    public static final Spell PEEVES = new Spell("PEEVES", 153);
    public static final Spell SECTUMSEMPRA = new Spell("SECTUMSEMPRA", 417);
    public static final Spell CORNISHPIXIE = new Spell("CORNISH PIXIE", 186);
    public static final Spell HEDWIG = new Spell("HEDWIG", 384);
    public static final Spell MADAM = new Spell("MADAM POMFREY", 285);
    public static final Spell MERPEOPLE = new Spell("MERPEOPLE", 318);
    public static final Spell WILLOW = new Spell("WHOMPING WILLOW", 252);
    public static final Spell GOBLIN = new Spell("GOBLIN", 351);
    public static final Spell PATRONUS = new Spell("PATRONUS", 450);
    public static final Spell FLUFFY = new Spell("FLUFFY", 219);

    public static final ArrayList<Spell> spells = new ArrayList<>(
            Arrays.asList(PEEVES, SECTUMSEMPRA, CORNISHPIXIE, HEDWIG, MADAM, MERPEOPLE, WILLOW,
                    GOBLIN, PATRONUS, FLUFFY)
    );
    public static final Random random=new Random();
    public static final Image quilin = new Image(Constants.class.getResourceAsStream("/cards/quilin.png"));
    public static final Image hippogriff= new Image(Constants.class.getResourceAsStream("/cards/hippogriff.png"));
    public static final Image phonix = new Image(Constants.class.getResourceAsStream("/cards/phonix.png"));
    public static final Image centaur = new Image(Constants.class.getResourceAsStream("/cards/centaur.png"));
    public static final Image unicorn = new Image(Constants.class.getResourceAsStream("/cards/unicorn.png"));
    public static final Image niffler = new Image(Constants.class.getResourceAsStream("/cards/niffler.png"));
    public static final Image thestral = new Image(Constants.class.getResourceAsStream("/cards/thestral.png"));
    public static final Image dobby = new Image(Constants.class.getResourceAsStream("/cards/elf.png"));
    public static final Image deatheator = new Image(Constants.class.getResourceAsStream("/cards/deatheator.png"));
    public static final Image boggart = new Image(Constants.class.getResourceAsStream("/cards/boggart.png"));
    public static final Image willow = new Image(Constants.class.getResourceAsStream("/cards/werwolf.png"));
    public static final Image aragog = new Image(Constants.class.getResourceAsStream("/cards/aragog.png"));
    public static final Image vampire = new Image(Constants.class.getResourceAsStream("/cards/vampire.png"));
    public static final Image basillisk = new Image(Constants.class.getResourceAsStream("/cards/basillisk.png"));
    public static final Image dementor = new Image(Constants.class.getResourceAsStream("/cards/dementor.png"));
    public static final Image peeves = new Image(Constants.class.getResourceAsStream("/cards/peeves.png"));
    public static final Image sectumsempra = new Image(Constants.class.getResourceAsStream("/cards/sectumsempra.png"));
    public static final Image pixie = new Image(Constants.class.getResourceAsStream("/cards/pixie.png"));
    public static final Image hedwig = new Image(Constants.class.getResourceAsStream("/cards/hedwig.png"));
    public static final Image madam = new Image(Constants.class.getResourceAsStream("/cards/madam.png"));
    public static final Image merpeople = new Image(Constants.class.getResourceAsStream("/cards/merpeople.png"));
    public static final Image goblin = new Image(Constants.class.getResourceAsStream("/cards/goblin.png"));
    public static final Image patronus = new Image(Constants.class.getResourceAsStream("/cards/patronus.png"));
    public static final Image fluffy = new Image(Constants.class.getResourceAsStream("/cards/fluffy.png"));
    public static final Image werwolf = new Image(Constants.class.getResourceAsStream("/cards/werwolf.png"));
    public static final Image block = new Image(Constants.class.getResourceAsStream("/cards/block.png"));
    public static final Image harryProf = new Image(Constants.class.getResourceAsStream("/cards/harryProf.png"));
    public static final Image ronProf = new Image(Constants.class.getResourceAsStream("/cards/ronProf.png"));
    public static final Image hermioneProf = new Image(Constants.class.getResourceAsStream("/cards/hermioneProf.png"));
    public static final Image dracoProf = new Image(Constants.class.getResourceAsStream("/cards/dracoProf.png"));
    public static void getInformationFromFile(){
        try{
            File myFile = new File("C:\\Users\\ASUS\\Desktop\\OOP\\Phase2\\src\\main\\resources\\Controller\\file.txt");
            File gameHistoryFile = new File("C:\\Users\\ASUS\\Desktop\\OOP\\Phase2\\src\\main\\resources\\Controller\\history.txt");
            Scanner scan = new Scanner(myFile);
            Scanner historyScanner = new Scanner(gameHistoryFile);
            ArrayList<User> users = new ArrayList<>();
            String[] parts;
            User myUser = new User();
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                if(line.equals("new user:")){
                    String information = scan.nextLine();
                    parts = information.split(" ");
                    User user = new User(parts[0], parts[1], parts[2], parts[3]);
                    user.setNumberOfQuestion(Integer.parseInt(parts[4]));
                    user.setAnswer(parts[5]);
                    user.setCoin(Integer.parseInt(parts[6]));
                    user.setExp(Integer.parseInt(parts[7]));
                    user.setHp(Integer.parseInt(parts[8]));
                    user.setScore(Integer.parseInt(parts[9]));
                    user.setLevel(Integer.parseInt(parts[10]));
                    users.add(user);
                    myUser = user;
                }
                else if(line.equals("user damage_heal cards:")){
                    String cardInformation;
                    while(true){
                        cardInformation = scan.nextLine();
                        if(cardInformation.equals("Done!")) break;
                        else{
                            parts = cardInformation.split(" ");
                            String name = parts[0];
                            int defenceAttack = Integer.parseInt(parts[1]);
                            int duration = Integer.parseInt(parts[2]);
                            int damage = Integer.parseInt(parts[3]);
                            int upgradeLevel = Integer.parseInt(parts[4]);
                            Damage_Heal card = new Damage_Heal(name, defenceAttack, duration, damage, upgradeLevel);
                            myUser.addToDeck(card);
                        }
                    }
                }
                else if(line.equals("Spell cards:")){
                    String cardNames;
                    while(true){
                        cardNames = scan.nextLine();
                        if(cardNames.equals("Done!")) break;
                        else{
                            Spell spell = new Spell(cardNames);
                            myUser.addToDeck(spell);
                        }
                    }
                }
                else if(line.equals("my games:")){
                    String game;
                    String guestUsername, date, time, status;
                    while(true){
                        game = scan.nextLine();
                        if(game.equals("Done!")) break;
                        else{
                            parts = game.split(" ");
                            guestUsername = parts[0];
                            date = parts[1];
                            time = parts[2];
                            status = parts[3];
                            Game myGame = new Game(guestUsername, date, time, status);
                            myUser.addGamesToGames(myGame);
                            myUser.addGame(game);
                        }
                    }
                }
            }
            while(historyScanner.hasNextLine()){
                String game = historyScanner.nextLine();
                registryMenu.addGame(game);
            }
            scan.close();
            historyScanner.close();
            registryMenu.setUsers(users);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public static void writeInformationInFile(){
        System.out.println(registryMenu.getUsers());
        ArrayList<User> users = registryMenu.getUsers();
        try{
            File myFile = new File("C:\\Users\\ASUS\\Desktop\\OOP\\Phase2\\src\\main\\resources\\Controller\\file.txt");
            File gameHistoryFile = new File("C:\\Users\\ASUS\\Desktop\\OOP\\Phase2\\src\\main\\resources\\Controller\\history.txt");
            FileWriter historyWriter = new FileWriter(gameHistoryFile);
            FileWriter writer = new FileWriter(myFile);
            for(User user : users){
                String str = user.toString();
                writer.write(str);
            }
            writer.close();
            for(String game : registryMenu.getGamesHistory()){
                historyWriter.write(game + '\n');
            }
            historyWriter.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public static Damage_Heal getCardByName(String name){
        for(Damage_Heal card: cards)
            if(card.getName().equals(name))
                return card;
        return null;
    }
    public static Spell getSpellByName(String name){
        for(Spell card : spells)
            if(card.getName().equals(name))
                return card;
        return null;
    }

}
