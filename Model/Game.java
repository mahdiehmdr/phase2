package Model;

import util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Game {
   // private static User hostPlayer, guestPlayer;
    private static boolean doubleMood=false, betMood=false;
    private static Block[] hostTimeLine=new Block[21];
    private static Block[] guestTimeLine=new Block[21];
    private static Card[] hostCards=new Card[6];
    private static Card[] guestCards=new Card[6];
    private static int betCoin=0;
    Random random=new Random();
    private static String time, date;
    private static int round;
    private static String status;
    private static String secondUser;
    private static boolean endGame=false;
    private static boolean isHostPlaying;
    public boolean isBetMood(){return betMood;}
    public int getBetCoin(){return betCoin;}
    public Game(){
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        this.date = currentDate.format(dateFormatter);
        this.time = currentTime.format(timeFormatter);
    }
    public Game(String guestUsername, String date, String time, String status){
        secondUser = guestUsername;
        this.date = date;
        this.time = time;
        this.status = status;
    }
    public String getDateAndTime(){
        return date+" "+time;
    }
    public String getStatus(){
        return status;
    }
    public String getTime(){
        return time;
    }
    public String getDate(){
        return date;
    }
    public String getSecondUser(){
        return secondUser;
    }
    public void addBetToCoin(int amount){this.betCoin+=amount;}
    private Matcher getCommandMatcher(String input, String regex){
        Pattern pattern=Pattern.compile(regex);
        return pattern.matcher(input);
    }
    public Card[] getGuestCards(){return guestCards;}
    public Card[] getHostCards(){return hostCards;}
    public Block[] getHostTimeLine(){return hostTimeLine;}
    public Block[] getGuestTimeLine(){return guestTimeLine;}
    public int getRound(){return round;}

    public void chooseMood(String answer){
        if(answer.equals("double")){
            this.doubleMood=true;
        }
        else if(answer.equals("bet")){
            this.betMood=true;
        }
    }
    public String getBetCoinsFromHost(String s) throws IOException {
        int amount=Integer.parseInt(s);
        if(amount>Constants.loggedInUser.getCoin())
            return "Not enough money in "+Constants.loggedInUser.getUsername()+"'s wallet! suggest another amount.";
        if(amount<=0)
            return "must bet a positive amount! try again.";
        Constants.loggedInUser.addCoin(-1*amount);
        addBetToCoin(amount);
        return "";
    }
    public String getBetCoinsFromGuest(String s) throws IOException{
        int amount=Integer.parseInt(s);
        if(amount>Constants.secondUser.getCoin())
            return "Not enough money in "+Constants.secondUser.getUsername()+"'s wallet! suggest another amount.";
        if(amount<=0)
            return "must bet a positive amount! try again.";
        Constants.secondUser.addCoin(-1*amount);
        addBetToCoin(amount);
        return "";
    }
    public boolean chooseHostCharachter(String character){
        String answer=character;
        switch (answer){
            case "4": {
                Constants.loggedInUser.setCharacter("Harry Potter");
               // System.out.println(hostPlayer.getUsername()+"'s character is Harry Potter!");
                return true;
            }
            case "2": {
                Constants.loggedInUser.setCharacter("Ronald Weasley");
                //System.out.println(hostPlayer.getUsername()+"'s character is Ronald Weasley!");
                return true;
            }
            case "3": {
                Constants.loggedInUser.setCharacter("Hermione Granger");
                //System.out.println(hostPlayer.getUsername()+"'s character is Hermione Granger!");
                return true;

            }
            case "1": {
                Constants.loggedInUser.setCharacter("Draco Malfoy");
                //System.out.println(hostPlayer.getUsername()+"'s character is Draco Malfoy!");
                return true;
            }
        }
        return false;
    }
    public boolean chooseGuestCharachter(String character){
        String answer=character;
        switch (answer){
            case "4": {
                Constants.secondUser.setCharacter("Harry Potter");
                // System.out.println(hostPlayer.getUsername()+"'s character is Harry Potter!");
                return true;
            }
            case "2": {
                Constants.secondUser.setCharacter("Ronald Weasley");
                //System.out.println(hostPlayer.getUsername()+"'s character is Ronald Weasley!");
                return true;
            }
            case "3": {
                Constants.secondUser.setCharacter("Hermione Granger");
                //System.out.println(hostPlayer.getUsername()+"'s character is Hermione Granger!");
                return true;

            }
            case "1": {
                Constants.secondUser.setCharacter("Draco Malfoy");
                //System.out.println(hostPlayer.getUsername()+"'s character is Draco Malfoy!");
                return true;
            }
        }
        return false;
    }
    public void run(ArrayList<Damage_Heal> cards, ArrayList<Spell> spells, RegistryMenu registryMenu){
        if(Constants.loggedInUser.getCardDeck().isEmpty() && Constants.loggedInUser.getSpellDeck().isEmpty()) {
            Constants.loggedInUser.getRandDeck(cards, spells);
            System.out.println("Starterpack for " +Constants.loggedInUser.getUsername() +":");
            Constants.loggedInUser.showDeck();
        }
        if(Constants.secondUser.getCardDeck().isEmpty()&&Constants.secondUser.getSpellDeck().isEmpty()) {
            Constants.secondUser.getRandDeck(cards, spells);
            System.out.println("Starterpack for "+ Constants.secondUser.getUsername() +":");
            Constants.secondUser.showDeck();
        }
        for(int i=0; i<21; i++){
            if(i<5){
                this.hostCards[i]=Constants.loggedInUser.getDeck().get(random.nextInt(Constants.loggedInUser.getDeck().size()));
                this.guestCards[i]=Constants.secondUser.getDeck().get(random.nextInt(Constants.secondUser.getDeck().size()));
            }
            this.guestTimeLine[i]=new Block();
            this.hostTimeLine[i]=new Block();
        }
        hostCards[5]=null;
        guestCards[5]=null;
        hostTimeLine[random.nextInt(21)].setDestroyed();
        guestTimeLine[random.nextInt(21)].setDestroyed();
        round=0;
        isHostPlaying=false;
    }
    public void setHostPlaying(){isHostPlaying=true;}
    public void setGuestPlaying(){isHostPlaying=false;}
    public boolean isHostPlaying(){return isHostPlaying;}
    public void deploy(String command, String answer){
        if(command.equals("next round"))
            return;
        else if(command.equals("end game")){
            endGame=true;
            return;
        }
        String deployCard="^place\\s+card\\s+(?<name>\\w+(?: \\w+)*)\\s+in\\s+block\\s+(?<number>\\w+)$";
        String deploySpell="^deploy\\s+card\\s+(?<name>\\w+(?: \\w+)*)$";
        Matcher cardMatcher=getCommandMatcher(command,deployCard);
        Matcher spellMatcher=getCommandMatcher(command,deploySpell);
        if(cardMatcher.matches()){
            if(isHostPlaying){
                Damage_Heal card=(Damage_Heal) getHostCardByName(cardMatcher.group("name"));
                if(card.getDuration()+Integer.parseInt(cardMatcher.group("number"))-1>21||checkDestroyedOrFull(card.getDuration(),Integer.parseInt(cardMatcher.group("number")),true)){
                    return;
                }
                for(int i=0; i<card.getDuration(); i++)
                    hostTimeLine[i+Integer.parseInt(cardMatcher.group("number"))-1].setCard(card);
                Constants.loggedInUser.removeCardFromDeck(card);
                for(int i=0; i<6; i++)
                    if(hostCards[i]!=null&&hostCards[i].getName().equals(card.getName())) {
                        this.hostCards[i]=Constants.loggedInUser.getDeck().get(random.nextInt(Constants.loggedInUser.getDeck().size()));
                        break;
                    }
                //setGuestPlaying();
                round++;
            }
            if(!isHostPlaying){
                Damage_Heal card=(Damage_Heal) getGuestCardByName(cardMatcher.group("name"));
                if(card.getDuration()+Integer.parseInt(cardMatcher.group("number"))-1>21||checkDestroyedOrFull(card.getDuration(),Integer.parseInt(cardMatcher.group("number")),false)){
                    return;
                }
                for(int i=0; i<card.getDuration(); i++)
                    guestTimeLine[i+Integer.parseInt(cardMatcher.group("number"))-1].setCard(card);
                Constants.secondUser.removeCardFromDeck(card);
                for(int i=0; i<6; i++)
                    if(guestCards[i]!=null&&guestCards[i].getName().equals(card.getName())) {
                        this.guestCards[i]=Constants.secondUser.getDeck().get(random.nextInt(Constants.secondUser.getDeck().size()));
                        break;
                    }
                //setHostPlaying();
            }
        }
        else if(spellMatcher.matches()){
            if(isHostPlaying){
                Spell card=(Spell) getHostCardByName(spellMatcher.group("name"));
                card.Deploy(hostTimeLine,guestTimeLine,hostCards,guestCards,true,Constants.secondUser,Constants.loggedInUser,round,answer);
                Constants.loggedInUser.removeCardFromDeck(card);
                for(int i=0; i<6; i++)
                    if(hostCards[i]!=null&&hostCards[i].getName().equals(card.getName())) {
                        this.hostCards[i]=Constants.loggedInUser.getDeck().get(random.nextInt(Constants.loggedInUser.getDeck().size()));
                        break;
                    }
                //setGuestPlaying();
                round++;
            }
            if(!isHostPlaying){
                Spell card=(Spell) getGuestCardByName(spellMatcher.group("name"));
                card.Deploy(hostTimeLine,guestTimeLine,hostCards,guestCards,false,Constants.secondUser,Constants.secondUser,round,answer);
                Constants.secondUser.removeCardFromDeck(card);
                for(int i=0; i<6; i++)
                    if(guestCards[i]!=null&&guestCards[i].getName().equals(card.getName())) {
                        this.guestCards[i]=Constants.secondUser.getDeck().get(random.nextInt(Constants.secondUser.getDeck().size()));
                        break;
                    }
                //setHostPlaying();
            }
        }
    }
    public void checkTimeLine(){
        for(int i=0; i<21; i++){
            if(hostTimeLine[i].getCard()!=null&&guestTimeLine[i].getCard()!=null){
                Damage_Heal hostCard=(Damage_Heal) hostTimeLine[i].getCard();
                Damage_Heal guestCard=(Damage_Heal) guestTimeLine[i].getCard();
                if(hostCard.getDamage()/hostCard.getDuration()>guestCard.getDamage()/guestCard.getDuration())
                    guestTimeLine[i].setFailed();
                else if(hostCard.getDamage()/hostCard.getDuration()<guestCard.getDamage()/guestCard.getDuration())
                    hostTimeLine[i].setFailed();
                else {
                    guestTimeLine[i].setFailed();
                    hostTimeLine[i].setFailed();
                }
            }
        }
    }
    public int getHostDamage(){
        int hostDamage=0;
        for(int i=0; i<21; i++)
            if (hostTimeLine[i].getCard() != null && !hostTimeLine[i].hasFailed() && !hostTimeLine[i].isDestroyed()) {
                Damage_Heal hostCard = (Damage_Heal) hostTimeLine[i].getCard();
                if(Constants.loggedInUser.isCardCharacter(hostTimeLine[i].getCard()))
                    hostDamage += (hostCard.getDamage() / hostCard.getDuration()+5);
                else
                    hostDamage += hostCard.getDamage() / hostCard.getDuration();
            }
        return hostDamage;
    }
    public int getGuestDamage(){
        int guestDamage=0;
        for(int i=0; i<21; i++)
            if (guestTimeLine[i].getCard() != null && !guestTimeLine[i].hasFailed() && !guestTimeLine[i].isDestroyed()) {
                Damage_Heal guestCard = (Damage_Heal) guestTimeLine[i].getCard();
                if(Constants.secondUser.isCardCharacter(guestTimeLine[i].getCard()))
                    guestDamage+=(guestCard.getDamage() / guestCard.getDuration()+5);
                else
                    guestDamage+=guestCard.getDamage() / guestCard.getDuration();
            }
        return guestDamage;
    }
    public boolean checkDestroyedOrFull(int duration, int block, boolean isHostPlaying){
        if(isHostPlaying){
            for(int i=0; i<duration; i++)
                if(hostTimeLine[block+i-1].isDestroyed()||hostTimeLine[block+i-1].getCard()!=null)
                    return true;
            return false;
        }
        for(int i=0; i<duration; i++)
            if(guestTimeLine[block+i-1].isDestroyed()||guestTimeLine[block+i-1].getCard()!=null)
                return true;
        return false;
    }
    public Card getGuestCardByName(String name){
        for(int i=0; i<6; i++){
            if(guestCards[i]!=null&&guestCards[i].getName().equals(name))
                return guestCards[i];
        }
        return null;
    }
    public Card getHostCardByName(String name){
        for(int i=0; i<6; i++){
            if(hostCards[i]!=null&&hostCards[i].getName().equals(name))
                return hostCards[i];
        }
        return null;
    }

    @Override
    public String toString(){
        return "vs\t" + this.secondUser + " " + this.time + " " + this.date + " " + this.status;
    }
}
