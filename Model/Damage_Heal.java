package Model;

public class Damage_Heal extends Card{
    private int defence_attack, duration, damage, upgradeLevel, upgradeCost, price;
    public Damage_Heal(String name, int defence_attack, int duration, int damage, int upgradeLevel, int upgradeCost,int price) {
        super(name);
        this.defence_attack=defence_attack;
        this.damage=damage;
        this.duration=duration;
        this.upgradeCost=upgradeCost;
        this.upgradeLevel=upgradeLevel;
        this.price=price;
    }
    //Hello I'm new here: :)
    public Damage_Heal(String name, int defence_attack, int duration, int damage, int upgradeLevel) {
        super(name);
        this.defence_attack=defence_attack;
        this.damage=damage;
        this.duration=duration;
        this.upgradeLevel=upgradeLevel;
    }
    //and here:
    @Override
    public String toString() {
        String str = this.getName() + " " + defence_attack + " " + duration + " " + damage + " " + upgradeLevel;
        return str;
    }

    public int getDamage(){return damage;}
    public void reduceDamage(){
        this.damage-=5*duration;
    }
    public int getDefence_attack(){return defence_attack;}
    public int getDuration(){return duration;}
    public int getUpgradeLevel(){return upgradeLevel;}
    public int getUpgradeCost(){return upgradeCost;}
    public int getPrice(){return price;}
    public void edit(int defence_attack, int duration, int damage, int upgradeLevel, int upgradeCost, int price){
        this.defence_attack = defence_attack;
        this.damage = damage;
        this.duration = duration;
        this.upgradeCost = upgradeCost;
        this.upgradeLevel = upgradeLevel;
        this.price = price;
    }
    public void upgrade(){
        damage += 10;
        upgradeCost *= 1.25;
    }
}
