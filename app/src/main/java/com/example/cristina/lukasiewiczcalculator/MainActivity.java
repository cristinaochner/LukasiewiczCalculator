package com.example.cristina.lukasiewiczcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public ArrayList<String> arrayElementi = new ArrayList<>();
    private TextView Spiegazione, Res;
    private Button ok_btn;
    private EditText insert, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        valutaEspressione(" ");
        super.onCreate(savedInstanceState);
        //mediante l'istruzione SETCONTENENTVIEW si va a recuperare
        // il layaut activity_main da mostrare al navigatore
        //recupera(larisorsa.della cartella layaut.il cui nome è activity main);
        setContentView(R.layout.activity_main);

        Button ok_btn= (Button) findViewById(R.id.ok_btn);

        Spiegazione = (TextView) findViewById(R.id.Spiegazione);
        Res = (TextView) findViewById(R.id.Res);

        insert = (EditText) findViewById(R.id.insert);
        result = (EditText) findViewById(R.id.result);

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valutaEspressione(insert.getText().toString());
                result.setText(arrayElementi.get(0));
            }
        });

    }

    boolean isNumber(String e) {
        if (!e.equalsIgnoreCase("+") &&
                !e.equalsIgnoreCase("-") &&
                !e.equalsIgnoreCase("*") &&
                !e.equalsIgnoreCase("/")) {
            return true;
        } else {
            return false;
        }
    }

    public void valutaElemento(String elemento) {
        boolean isNumber = isNumber(elemento);
        if (isNumber) {
            salvaElemento(elemento);
        } else {
            eseguoElemento(elemento);
        }
    }

    public void valutaEspressione(String elementi) {
        ArrayList<String> elementiSeparati = new ArrayList<>();
        elementiSeparati.addAll(Arrays.asList(elementi.split(" ")));
        valutaEspressione(elementiSeparati);
    }

    public void valutaEspressione(ArrayList<String> elementi) {
        for (String elemento : elementi) {
            valutaElemento(elemento);
        }
    }

    void eseguoElemento(String elemento) {
        Double right = Double.parseDouble(arrayElementi.remove(0));
        Double left = Double.parseDouble(arrayElementi.remove(0));

        switch (elemento) {
            case "+":
                String somma = String.valueOf(left + right);
                salvaElemento(somma);
                break;
            case "-":
                somma = String.valueOf(left - right);
                salvaElemento(somma);
                break;
            case "*":
                somma = String.valueOf(left * right);
                salvaElemento(somma);
                break;
            case "/":
                somma = String.valueOf(left / right);
                salvaElemento(somma);
                break;
        }

    }

    public void salvaElemento(String elemento) {
        arrayElementi.add(0, elemento);
    }


}