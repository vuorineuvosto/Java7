package com.example.bottledispenser;
/*
 * Tekijä: Joona Haikonen
 * Opiskelijanumero: 0541106
 */
import android.content.Context;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BottleDispenser {
    private int bottles;
    public float money;
    ArrayList<Bottle> pulloList = new ArrayList<Bottle>();

    private static BottleDispenser bd = new BottleDispenser();


    public BottleDispenser() {
        bottles = 5;
        money = 0;
        //name, manuf, totE, price, vol
        Bottle pullo1 = new Bottle("Pepsi Max", "Pepsi", 0.3, 1.8, 0.5);
        pulloList.add(pullo1);
        Bottle pullo2 = new Bottle("Pepsi Max", "Pepsi", 0.9, 2.2, 1.5);
        pulloList.add(pullo2);
        Bottle pullo3 = new Bottle("Coca-Cola Zero", "Pepsi", 0.3, 2, 0.5);
        pulloList.add(pullo3);
        Bottle pullo4 = new Bottle("Coca-Cola Zero", "Pepsi", 0.9, 2.5, 1.5);
        pulloList.add(pullo4);
        Bottle pullo5 = new Bottle("Fanta Zero", "Pepsi", 0.5, 1.95, 0.5);
        pulloList.add(pullo5);

    }
    public static BottleDispenser getInstance() {
        return bd;
    }

    public void addMoney(TextView moneyAmount, int seek) {
        money += seek;
        moneyAmount.setText("Money: "+money);
    }

    public void buyBottle(int choice2, TextView text, TextView moneyAmount) {
        System.out.println(choice2);
        if (choice2 > pulloList.size()){
            text.setText("Invalid choice!");
        }
        else if (bottles < 1){

            text.setText("Machine out of bottles!");
        }else if (money < pulloList.get(choice2-1).getPrice()){

            text.setText("Add money first!");
        }
        else {
            bottles -= 1;
            money = (float) (money - pulloList.get(choice2-1).getPrice());
            moneyAmount.setText("Money: "+money);
            text.setText("KACHUNK! " + pulloList.get(choice2-1).getName()  + " came out of the dispenser!" );
            removeBottle(choice2-1);
        }
    }
    public void returnMoney(TextView text, TextView moneyAmount) {
        if(money > 0) {
            text.setText("You got " + money + " out of the machine!");
            moneyAmount.setText("Money: 0");
            money = 0;
        }else {
            text.setText("No money in the machine!");
        }
    }
    public void listBottles(TextView text) {
        String teksti = "";
        int i = 1;
        for(Bottle pullo : pulloList) {
            teksti = teksti + (String) (i + ". Name: " + pullo.getName() + "\n	Size: " + pullo.getVol() + "	" + "Price: " + pullo.getPrice() + "\n");
            i++;
        }
        text.setText(teksti);

    }
    public void removeBottle(int choice2) {
        pulloList.remove(choice2);
    }


}
////////////////////////////////////