package com.example.bottledispenser;
/*
 * Tekijä: Joona Haikonen
 * Opiskelijanumero: 0541106
 */
public class Bottle {

    private String name;
    private String manufacturer;
    private double total_energy;
    private double vol;
    private double price;

    //name, manuf, totE, price, vol
    public Bottle(String name, String manufacturer, double total_energy, double price, double vol){
        this.name = name;
        this.manufacturer = manufacturer;
        this.total_energy = total_energy;
        this.price = price;
        this.vol = vol;
    }

    public String getName(){
        return name;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public double getEnergy(){
        return total_energy;
    }
    public double getVol() {
        return vol;
    }
    public double getPrice() {
        return price;
    }

}
////////////////////////////////////
