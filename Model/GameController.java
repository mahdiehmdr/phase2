package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
public class GameController {
    final private RegistryMenu registryMenu = new RegistryMenu();
    final private MainMenu mainMenu = new MainMenu();
    private User loggedInUser = null;
    final private Outputs output = new Outputs();
    public void run(){
        mainMenu.addSpell();
    }
    public void getInformationFromFile(){
        try{
            File myFile = new File("C:\\Users\\ASUS\\Desktop\\Phase2\\src\\main\\java\\User Information.txt");
            File gameHistoryFile = new File("C:\\Users\\ASUS\\Desktop\\Phase2\\src\\main\\java\\Game History.txt");
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
    public void writeInformationInFile(){
        ArrayList<User> users = registryMenu.getUsers();
        try{
            File myFile = new File("C:\\Users\\ASUS\\Desktop\\Phase2\\src\\main\\java\\User Information.txt");
            File gameHistoryFile = new File("C:\\Users\\ASUS\\Desktop\\Phase2\\src\\main\\java\\Game History.txt");
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
    private void showPlayers(){
        ArrayList<User> users = registryMenu.getUsers();
        System.out.println("Players without sorting:");
        System.out.println("|------------------------------------------------------------------------------|");
        for(User user : users) {
            System.out.println("| Username: " + user.getUsername() + " | Email: " + user.getEmail() + " | Nickname: " + user.getNickname() + " | Exp: " + user.getExp());
            System.out.println("|------------------------------------------------------------------------------|");
        }
    }
}
