package Model;
public class Block {
    private boolean isDestroyed, hasFailed;
    private Card card;
    public Block(){
        this.card=null;
        this.isDestroyed=false;
        this.hasFailed=false;
    }
    public void removeCard(){this.card=null;}
    public Card getCard(){return card;}
    public boolean isDestroyed(){return isDestroyed;}
    public void setDestroyed(){this.isDestroyed=true;}
    public void repairDestroyed(){this.isDestroyed=false;}
    public boolean hasFailed(){return hasFailed;}
    public void setFailed(){this.hasFailed=true;}
    public void unsetFailed(){this.hasFailed=false;}
    public void setCard(Damage_Heal card){
        this.card=card;
    }
    public void printBlock(){
        if(isDestroyed)
            System.out.print("destroyed");
        else if(card!=null)
            System.out.print(card.getName());
        else
            System.out.print("\t");
    }
}
