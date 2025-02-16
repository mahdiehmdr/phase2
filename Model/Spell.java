package Model;

import java.util.Random;
import java.util.Scanner;

public class Spell extends Card{
    private int price;
    Random random=new Random();
    public Spell(String name, int price){super(name);
        this.price=price;}
    //look at me. I'm new love! //hello love:)
    public Spell(String name){
        this.name = name;
    }
    public void Deploy(Block[] hostTimeLine, Block[] guestTimeLine, Card[] hostCards, Card[] guestCards, boolean isHostPlaying, User guestPlayer, User hostPlayer, int round, String answer){
        if(name.equals("PEEVES")){//تغییردهنده مکان حفره
            for(int i=0; i<21; i++){
                if(isHostPlaying)
                    if(hostTimeLine[i].isDestroyed())
                        hostTimeLine[i].repairDestroyed();
                    else if(guestTimeLine[i].isDestroyed())
                        guestTimeLine[i].repairDestroyed();
            }
            while (true){
                int rand=random.nextInt(21);
                if(hostTimeLine[rand].getCard()==null) {
                    hostTimeLine[rand].setDestroyed();
                    break;
                }
            }
            while (true){
                int rand=random.nextInt(21);
                if(guestTimeLine[rand].getCard()==null) {
                    guestTimeLine[rand].setDestroyed();
                    break;
                }
            }
        } else if(name.equals("SECTUMSEMPRA")){//تضعیف کننده کارت حریف
            if(isHostPlaying){
                for(int i=0; i<5; i++)
                    if(guestCards[i]!=null&&guestCards[i].getClass().equals(Damage_Heal.class)){
                        ((Damage_Heal)guestCards[i]).reduceDamage();
                    }
            }
            else {
                for(int i=0; i<5; i++)
                    if(guestCards[i]!=null&&hostCards[i].getClass().equals(Damage_Heal.class)){
                        ((Damage_Heal)hostCards[i]).reduceDamage();
                    }
            }
        } else if(name.equals("CORNISH PIXIE")){//کپی کننده
            System.out.println("which card do you wish to copy?");
            if(isHostPlaying){
                while (true) {
                    if((answer.equals("1")||answer.equals("2")||answer.equals("3")||answer.equals("4")||answer.equals("5"))&&hostCards[Integer.parseInt(answer)-1]!=null)
                        break;
                    System.out.println("invalid number! try again.");
                }
                hostCards[5]=hostCards[Integer.parseInt(answer)-1];
            }
            else {
                while (true) {
                    if((answer.equals("1")||answer.equals("2")||answer.equals("3")||answer.equals("4")||answer.equals("5"))&&guestCards[Integer.parseInt(answer)-1]!=null)
                        break;
                    System.out.println("invalid number! try again.");
                }
                guestCards[5]=guestCards[Integer.parseInt(answer)-1];
            }
        }else if(name.equals("HEDWIG")){//تعمیرکننده
            for(int i=0; i<21; i++){
                if(isHostPlaying)
                    if(hostTimeLine[i].isDestroyed())
                        hostTimeLine[i].repairDestroyed();
                else if(guestTimeLine[i].isDestroyed())
                    guestTimeLine[i].repairDestroyed();
            }
        }else if(name.equals("MADAM POMFREY")){//شفا
            if(isHostPlaying)
                hostPlayer.reduceHP(-30);
            else
                guestPlayer.reduceHP(-30);
        }else if(name.equals("MERPEOPLE")){//حذف کارت از دست حریف
            System.out.println("which of you opponent's cards do you wish to have?");
            if(isHostPlaying){
                while (true){
                    if(guestCards[Integer.parseInt(answer)-1]!=null||(answer.equals("1")||answer.equals("2")||answer.equals("3")||answer.equals("4")||answer.equals("5")||answer.equals("6")))
                        break;
                    System.out.println("invalid number! try again.");
                }
                hostCards[5]=guestCards[Integer.parseInt(answer)-1];
                guestCards[Integer.parseInt(answer)-1]=null;
            }
            else {
                while (true){
                    if(hostCards[Integer.parseInt(answer)-1]!=null||(answer.equals("1")||answer.equals("2")||answer.equals("3")||answer.equals("4")||answer.equals("5")||answer.equals("6")))
                        break;
                    System.out.println("invalid number! try again.");
                }
                guestCards[5]=hostCards[Integer.parseInt(answer)-1];
                hostCards[Integer.parseInt(answer)-1]=null;
            }
        }else if(name.equals("WHOMPING WILLOW")){//کم کننده راند
            round++;
        }else if(name.equals("GOBLIN")){//سکه میده
            if(isHostPlaying)
                hostPlayer.addCoin(40);
            else
                guestPlayer.addCoin(40);
        }else if(name.equals("PATRONUS")){//سپر
            if(isHostPlaying){
                for(int i=0; i<21; i++)
                    if(hostTimeLine[i].getCard()!=null&&hostTimeLine[i].hasFailed()&&!hostTimeLine[i].isDestroyed())
                        hostTimeLine[i].unsetFailed();
            }
            else {
                for(int i=0; i<21; i++)
                    if(guestTimeLine[i].getCard()!=null&&guestTimeLine[i].hasFailed()&&!guestTimeLine[i].isDestroyed())
                        guestTimeLine[i].unsetFailed();
            }
        }else if(name.equals("FLUFFY")){//غیر فعال کننده همه کارت های تایم لاین حریف جز PHONIX
            if(isHostPlaying){
                for(int i=0; i<21; i++)
                    if(!guestTimeLine[i].isDestroyed()&&guestTimeLine[i].getCard()!=null&&!guestTimeLine[i].hasFailed()&&!guestTimeLine[i].getCard().getName().equals("PHONIX"))
                        guestTimeLine[i].setFailed();
            }
            else {
                for(int i=0; i<21; i++)
                    if(!hostTimeLine[i].isDestroyed()&&hostTimeLine[i].getCard()!=null&&!hostTimeLine[i].hasFailed()&&!hostTimeLine[i].getCard().getName().equals("PHONIX"))
                        hostTimeLine[i].setFailed();
            }
        }
    }
    public int getPrice(){return price;}
}
