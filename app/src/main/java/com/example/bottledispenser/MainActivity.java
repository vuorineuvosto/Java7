package com.example.bottledispenser;
/*
 * Tekijä: Joona Haikonen
 * Opiskelijanumero: 0541106
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    BottleDispenser toimi = BottleDispenser.getInstance();
    TextView text, moneyAmount, amount;
    SeekBar seekBar;
    Spinner menu;
    String item;
    Context context = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.teksti);
        moneyAmount = findViewById(R.id.moneyAmount);
        seekBar = findViewById(R.id.seekBar);
        amount = findViewById(R.id.amount);
        menu = findViewById(R.id.spinner2);
        context = MainActivity.this;

        ArrayList<String> lista = new ArrayList<>();
        for(int i = 1; i<8; i++){
            lista.add(Integer.toString(i));
        }
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menu.setAdapter(adapter);

        seek();
        seek2();

    }
    public void seek2(){

        menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            int x;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public int seek(){
        amount.setText("Add: " + seekBar.getProgress()/10 + " / " + seekBar.getMax()/10);


        seekBar.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {
                    int x;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        x = progress/10;
                        amount.setText("Add: " + x + " / " + seekBar.getMax()/10);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        amount.setText("Add: " + x + " / " + seekBar.getMax()/10);

                    }
                }

        );
    return seekBar.getProgress()/10;
    }

    public void addMoney(View a) {
        toimi.addMoney(moneyAmount, seek());
        seekBar.setProgress(0);
    }
    public void buyBottle(View a) {
        receit(Integer.parseInt(item));
        toimi.buyBottle(Integer.parseInt(item), text, moneyAmount);

    }
    public void returnMoney(View a) {
        toimi.returnMoney(text, moneyAmount);
    }
    public void listBottles(View a) {
        toimi.listBottles(text);
    }
    public void quit(View a){
        System.exit(0);
    }
    public void receit(int choice2){

        try {
            OutputStreamWriter file = new OutputStreamWriter(context.openFileOutput("receit.txt", Context.MODE_APPEND));

            Bottle pullo = toimi.pulloList.get(choice2-1);
            String name = pullo.getName();
            String manuf = pullo.getManufacturer();
            Double totE = pullo.getEnergy();
            Double price = pullo.getPrice();
            Double vol = pullo.getVol();

            file.write("name---manufacturer---totalEnergy---price---volume\n"+"-"+name+"-"+manuf+"-"+totE+"-"+price+"-"+vol+"\n");
            file.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}


