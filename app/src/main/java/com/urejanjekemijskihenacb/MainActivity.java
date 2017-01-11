package com.urejanjekemijskihenacb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.urejanjekemijskihenacb.BalancingEquationLogic.InvalidUserInputException;
import com.urejanjekemijskihenacb.BalancingEquationLogic.chEquation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemical_equation);

        Intent intent=new Intent(this,ChemicalEquation.class);
        startActivity(intent);
/*
        EditText insertEquation = (EditText) this.findViewById(R.id.insertEquation);
        String test1 = "Li + O2 -> Li2O";
        TextView resultEquation = (TextView) this.findViewById(R.id.resultEquation);
        Button test = (Button) this.findViewById(R.id.buttonTest);
        String s = test1;
        try {
            String rez = balance(s);
            resultEquation.setText(rez);

            Toast.makeText(getApplication(), rez, Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(getApplication(), e.toString(), Toast.LENGTH_LONG).show();
        }
*/
    }

    public static String balance(String s) throws InvalidUserInputException
    {
        s = s.replaceAll("=",":");
        s = s.replaceAll("-->",":");
        s = s.replaceAll("->",":");
        int solns[][];
        chEquation newEqu;

        newEqu = new chEquation(s);
        solns = newEqu.Balance();
        //this can throw an error
        //it's to be caught by an error hander
        //in the function that calls this

        String output = "";
        String answ[] = new String[solns[0].length];
        for(int i = 0; i < solns[0].length; i++)
        {
            answ[i] = "";
            for(int j = 0; j < solns.length; j++)
            {
                if(solns[j][i] <= 0)
                    continue;
                if(solns[j][i] != 1)
                    answ[i] = answ[i] + solns[j][i] + newEqu.getItem(j).getSymbol() + " + ";
                else
                    answ[i] = answ[i] + newEqu.getItem(j).getSymbol() + " + ";
            }

            answ[i] = answ[i].substring(0, answ[i].length() - 3);
            answ[i] = answ[i] + " --> ";    //will be made \u2192 later


            for(int j = 0; j < solns.length; j++)
            {
                if(solns[j][i] >= 0)
                    continue;
                if(solns[j][i] != -1)
                    answ[i] = answ[i] + solns[j][i] * -1 + newEqu.getItem(j).getSymbol() + " + ";
                else
                    answ[i] = answ[i] + newEqu.getItem(j).getSymbol() + " + ";
            }

            answ[i] = answ[i].substring(0, answ[i].length() - 3);
            output += answ[i];
            output += "\n";

        }
        return output.substring(0,output.length()-1);
    }
}
