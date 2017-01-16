package com.urejanjekemijskihenacb;

import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.urejanjekemijskihenacb.BalancingEquationLogic.InvalidUserInputException;
import com.urejanjekemijskihenacb.BalancingEquationLogic.chEquation;
import com.urejanjekemijskihenacb.SqlDataBase.ChemistryDBHelper;

public class ChemicalEquation extends AppCompatActivity {

    Context context=this;
    ChemistryDBHelper chemistryDBHelper;
    SQLiteDatabase sqLiteDatabase;
    EditText txtName;
    TextView txtEqu;
    EditText txtDescrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemical_equation);

        final EditText insertEquation = (EditText) this.findViewById(R.id.insertEquation);
        final TextView resultEquation = (TextView) this.findViewById(R.id.resultEquation);
        final ImageView brain=(ImageView)this.findViewById(R.id.brainImage);
        Button calculate = (Button) this.findViewById(R.id.buttonCalculate);
        final Button saveData= (Button)this.findViewById(R.id.buttonSave);
        saveData.setVisibility(View.INVISIBLE);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = insertEquation.getText().toString();
                try {
                    String rez = balance(s);
                    resultEquation.setText(rez);
                    resultEquation.setTextColor(Color.GREEN);
                    resultEquation.setTextSize(20);
                    brain.setImageResource(R.mipmap.brain_know);
                    saveData.setVisibility(View.VISIBLE);
                    //Toast.makeText(getApplication(), rez, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    //Toast.makeText(getApplication(), e.toString(), Toast.LENGTH_LONG).show();
                    resultEquation.setText("This equation doesn't have any solution!");
                    resultEquation.setTextColor(Color.RED);

                    brain.setImageResource(R.mipmap.brain_dont_know);
                }
            }
        });
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            saveEquation(resultEquation.getText().toString());

            }
        });
    }
    public void saveEquation(String equationResult){
        final Dialog saveDialog=new Dialog(this);
        //saveDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        saveDialog.setContentView(R.layout.dialog_save_equation);
        saveDialog.show();

        final TextView equationRes=(TextView)saveDialog.findViewById(R.id.equationResult);
        equationRes.setText(equationResult);

        Button btnSaveEquation=(Button)saveDialog.findViewById(R.id.saveEquationDialogButton);
        btnSaveEquation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //equationRes.setText("Dela neki!!");
                txtName=(EditText)saveDialog.findViewById(R.id.nameEquationEditText);
                txtEqu=(TextView)saveDialog.findViewById(R.id.equationResult);
                txtDescrip=(EditText)saveDialog.findViewById(R.id.descriptionEditText);
                addEquationInfo();
                saveDialog.dismiss();
            }
        });
    }

    public void addEquationInfo(){
        String name=txtName.getText().toString();
        String equation=txtEqu.getText().toString();
        String description=txtDescrip.getText().toString();
        chemistryDBHelper = new ChemistryDBHelper(context);
        sqLiteDatabase = chemistryDBHelper.getWritableDatabase();
        chemistryDBHelper.addInformations(name,equation,description,sqLiteDatabase);
        Toast.makeText(getBaseContext(),"Equation has been saved!!!",Toast.LENGTH_SHORT).show();
        chemistryDBHelper.close();
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


