package Model;

import java.util.ArrayList;
import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.Scanner;

public abstract class Card {
    protected String name;
    public Card(String name){this.name=name;}
    //new down here:
    public Card(){
        this.name = "";
    }
    public String getName(){return name;}
}

