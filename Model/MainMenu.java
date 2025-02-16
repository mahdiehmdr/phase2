package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MainMenu {
    private ArrayList<Damage_Heal> shopCards = new ArrayList<>();
    private ArrayList<Spell> shopSpells = new ArrayList<>();
    public ArrayList<Damage_Heal> getShopCards(){return shopCards;}
    final private ProfileMenu profileMenu = new ProfileMenu();
    public void addDamage_Heal(Damage_Heal card){
        shopCards.add(card);
    }
    public Damage_Heal getCardByName(String name){
        for(Damage_Heal card : shopCards)
            if (card.getName().equals(name))
                return card;
        return null;
    }
    public Spell getSpellByName(String name){
        for(Spell card : shopSpells)
            if (card.getName().equals(name))
                return card;
        return null;
    }
    public void addSpell(){
        Spell peeves=new Spell("PEEVES", 153);
        Spell sectumsempra=new Spell("SECTUMSEMPRA", 417);
        Spell cornishPixie=new Spell("CORNISH PIXIE", 186);
        Spell hedwig=new Spell("HEDWIG", 384);
        Spell madamPomfrey=new Spell("MADAM POMFREY", 285);
        Spell merpeople=new Spell("MERPEOPLE", 318);
        Spell whompingWillow=new Spell("WHOMPING WILLOW", 252);
        Spell goblin=new Spell("GOBLIN", 351);
        Spell patronus=new Spell("PATRONUS", 450);
        Spell fluffy=new Spell("FLUFFY", 219);
        Collections.addAll(shopSpells,peeves,sectumsempra,cornishPixie,hedwig,madamPomfrey,merpeople,whompingWillow,goblin,patronus,fluffy);
    }
    private Matcher getCommandMatcher(String input, String regex){
        Pattern pattern=Pattern.compile(regex);
        return pattern.matcher(input);
    }
    public void showShopCards(){
        int num=0;
        for(Damage_Heal card :shopCards)
            System.out.println(++num+". name: "+card.getName()+" defence_attack: "+card.getDefence_attack()+" duration: "+card.getDuration()+" damage: "+card.getDamage()+" upgradeLeve: "+card.getUpgradeLevel()+" upgradeCost: "+card.getUpgradeCost()+" price: "+card.getPrice());
    }
    public void showCardsNotInDeck(User user){
        int num=0;
        System.out.println("Damage/Heal Cards:");
        for(Damage_Heal card : shopCards)
            if(user.getCardFromDeckByName(card.getName())==null)
                System.out.println(++num+". name: "+card.getName()+" defence_attack: "+card.getDefence_attack()+" duration: "+card.getDuration()+" damage: "+card.getDamage()+" upgradeLeve: "+card.getUpgradeLevel()+" upgradeCost: "+card.getUpgradeCost()+" price: "+card.getPrice());
        System.out.println("Spell Cards:");
        for(Spell card: shopSpells)
            if(user.getCardFromDeckByName(card.getName())==null)
                System.out.println(++num+". name: "+card.getName()+" price: "+card.getPrice());
    }
    public void run(Scanner scanner, User user, RegistryMenu registryMenu, Outputs outputs) {
        System.out.println(outputs.mainMenuManual);
        while(true){
            String input = scanner.nextLine();
            if (input.equals("start game")) {
                System.out.println(outputs.gameManual);
                //Game game=new Game(user,registryMenu,outputs);
                //game.run(shopCards,shopSpells, scanner, registryMenu);
            }
            else if (input.equals("show my deck")) {
                user.showDeck();
            }
            else if (input.equals("show history")) {
                showHistory(scanner, registryMenu);
            }
            else if(input.equals("show scoreboard")){
                showScoreBoard(registryMenu);
            }
            else if (input.equals("shop menu")) {
                System.out.println("entered shop menu");
                System.out.println(outputs.shopMenuManual);
                showCardsNotInDeck(user);
                while (true) {
                    String command = scanner.nextLine();
                    if (command.equals("back")){
                        System.out.println("Returned to main menu");
                        break;
                    }
                    String buyCard = "^buy card ([A-Z ]+)$";
                    String upgradeCard = "^upgrade card ([A-Z ]+)$";
                    Matcher buy = getCommandMatcher(command, buyCard);
                    Matcher upgrade = getCommandMatcher(command, upgradeCard);
                    if (!buy.matches() && !upgrade.matches()) System.out.println("invalid command!");
                    else if (buy.matches()) {
                        String name = buy.group(1);
                        if (getCardByName(name) != null) {
                            if (user.getCoin() < getCardByName(name).getPrice())
                                System.out.println("not enough money!");
                            else{
                                user.addToDeck(getCardByName(name));
                                int coin = user.getCoin();
                                coin -= getCardByName(name).getPrice();
                                user.setCoin(coin);
                                System.out.println("The card has been added to your table.");
                            }
                        }
                        else if (getSpellByName(name) != null) {
                            if (user.getCoin() < getSpellByName(name).getPrice())
                                System.out.println("not enough money!");
                            else{
                                user.addToDeck(getSpellByName(name));
                                int coin = user.getCoin();
                                coin -= getSpellByName(name).getPrice();
                                user.setCoin(coin);
                            }
                        }
                        else System.out.println("card doens't exist!");
                    }
                    else if (upgrade.matches()) {
                        String name = upgrade.group(1);
                        user.upgradeCard(name);
                    }
                    else if(command.equals("command prompt")) System.out.println(outputs.shopMenuManual);
                    else System.out.println("invalid input!");
                }
            }
            else if (input.equals("Profile menu")) {
                System.out.println("Entered profile menu successfully.");
                System.out.println(outputs.profileMenuManual);
                profileMenu.myProfile(scanner, user);
            }
            else if(input.equals("back")){
                System.out.println("Returned to signup menu successfully!");
                break;
            }
            else if (input.equals("command prompt")) {
                System.out.println(outputs.mainMenuManual);
            }
            else System.out.println("invalid input!");
        }
    }
    private void showHistory(Scanner scanner, RegistryMenu registryMenu){
        ArrayList<String> games = registryMenu.getGamesHistory();
        if(games.isEmpty()) System.out.println("There is no game to show");
        else{
            System.out.println("newest or oldest?");
            String order = scanner.nextLine();
            if(order.equals("newest")){
                String[] upsideDown = new String[games.size()];
                int j = games.size() - 1;
                for(int i = 0; i < games.size(); i++){
                    upsideDown[j] = games.get(i);
                    j -= 1;
                } //newest first
                if(games.size() <= 5){
                    for(int i = 0; i < games.size(); i++)
                        System.out.println((i + 1) + ". " + upsideDown[i]);
                    System.out.println("You are in page 1 / 1");
                } //less than 5 games
                else{
                    for(int i = 0; i < 5; i++)
                        System.out.println((i + 1) + ". " + upsideDown[i]);
                    int counter = 0;
                    int pageNumber = 1;
                    int maximumPages;
                    if(upsideDown.length % 5 == 0) maximumPages = upsideDown.length / 5;
                    else maximumPages = (upsideDown.length / 5 + 1);
                    System.out.println("You are in page 1 / " + maximumPages);
                    System.out.println("If you want to see other games:\n\tnext page\nTo return to main menu:\n\tback");
                    while(true){
                        String command = scanner.nextLine();
                        if(command.equals("back")){
                            System.out.println("Returned to main menu successfully");
                            break;
                        }
                        else if(command.equals("next page")){
                            if(pageNumber == maximumPages)
                                System.out.println("invalid command!");
                            else{
                                counter += 1;
                                pageNumber += 1;
                                if(((counter + 1) * 5) <= upsideDown.length){
                                    for(int i = counter * 5; i < (counter + 1) * 5; i++){
                                        System.out.println((i + 1) + ". " + upsideDown[i]);
                                    }
                                    System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                    System.out.println("next page or previous?\nIf you want to go to the previous page:\n\tprevious page");
                                }
                                else{
                                    for(int i = counter * 5; i < upsideDown.length; i++){
                                        System.out.println((i + 1) + ". " + upsideDown[i]);
                                    }
                                    System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                    System.out.println("end.\nIf you want to go to the previous page:\n\tprevious page");
                                }
                            }
                        }
                        else if(command.equals("previous page")){
                            if(pageNumber == 1) System.out.println("invalid command!");
                            else{
                                counter -= 1;
                                pageNumber -= 1;
                                for(int i = (counter * 5); i < (counter + 1) * 5; i++){
                                    System.out.println((i + 1) + ". " + upsideDown[i]);
                                }
                                System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                System.out.println("next page or previous?\nIf you want to go to the previous page:\n\tprevious page");
                            }
                        }
                    }
                }
            }
            else if(order.equals("oldest")){
                if(games.size() <= 5) {
                    int i = 1;
                    for(String game : games){
                        System.out.println(i + ". " + game);
                        i += 1;
                    }
                    System.out.println("You are in page 1 / 1");
                }
                else{
                    for(int i = 0; i < 5; i++){
                        System.out.println((i + 1) + ". " + games.get(i));
                    }
                    int counter = 0;
                    int pageNumber = 1;
                    int maximumPages;
                    if(games.size() % 5 == 0) maximumPages = games.size() / 5;
                    else maximumPages = (games.size() / 5 + 1);
                    System.out.println("You are in page 1 / " + maximumPages);
                    System.out.println("If you want to see other games:\n\tnext page\nTo return to main menu:\n\tback");
                    while(true){
                        String command = scanner.nextLine();
                        if(command.equals("back")){
                            System.out.println("Returned to main menu successfully");
                            break;
                        }
                        else if(command.equals("next page")){
                            if(pageNumber == maximumPages)
                                System.out.println("invalid command!");
                            else{
                                counter += 1;
                                pageNumber += 1;
                                if(((counter + 1) * 5) <= games.size()){
                                    for(int i = (counter * 5); i < (counter + 1) * 5; i++){
                                        System.out.println((i + 1) + ". " + games.get(i));
                                    }
                                    System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                    System.out.println("next page or previous?\nIf you want to go to the previous page:\n\tprevious page");
                                }
                                else{
                                    for(int i = counter * 5; i < games.size(); i++){
                                        System.out.println((i + 1) + ". " + games.get(i));
                                    }
                                    System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                    System.out.println("end.\nIf you want to go to the previous page:\n\tprevious page");
                                }
                            }
                        }
                        else if(command.equals("previous page")){
                            if(pageNumber == 1) System.out.println("invalid command!");
                            else{
                                counter -= 1;
                                pageNumber -= 1;
                                for(int i = (counter * 5); i < (counter + 1) * 5; i++){
                                    System.out.println((i + 1) + ". " + games.get(i));
                                }
                                System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                System.out.println("next page or previous?\nIf you want to go to the previous page:\n\tprevious page");
                            }
                        }
                    }
                }
            }
            else System.out.println("invalid input! Returned to the main menu.");
        }
    }
    private void showScoreBoard(RegistryMenu registryMenu){
        ArrayList<User> users = registryMenu.getUsers();
        if(users.isEmpty()) System.out.println("There are no users to sort. Returned to main menu...");
        else{
            registryMenu.sortUsers(users);
            int i = 1;
            for(int j = users.size() - 1; 0 <= j; j--){
                System.out.println(i + ". " + users.get(j).getUsername() + '\t' + users.get(j).getEmail() + "    score: " +users.get(j).getScore());
                i += 1;
            }
        }
    }
}