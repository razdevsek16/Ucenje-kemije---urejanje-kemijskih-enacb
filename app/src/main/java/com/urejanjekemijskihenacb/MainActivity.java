package com.urejanjekemijskihenacb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        setContentView(R.layout.activity_main);
        Button equationBalancer=(Button)this.findViewById(R.id.equationButton);
        Button save=(Button)this.findViewById(R.id.savedEquationButton);
        Button animate=(Button)this.findViewById(R.id.animatedExamplesButton);


        equationBalancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Equation();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        animate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
    public void Equation(){
        Intent intent=new Intent(this,ChemicalEquation.class);
        startActivity(intent);
    }

}
