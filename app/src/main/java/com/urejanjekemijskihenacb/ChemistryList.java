package com.urejanjekemijskihenacb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.urejanjekemijskihenacb.SqlDataBase.ChemistryDBHelper;
import com.urejanjekemijskihenacb.SqlDataBase.DataProvider;
import com.urejanjekemijskihenacb.SqlDataBase.ListDataAdapter;

public class ChemistryList extends AppCompatActivity {
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    ChemistryDBHelper chemistryDBHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemistry_list);

        listView= (ListView)this.findViewById(R.id.listViewChemistry);
        chemistryDBHelper= new ChemistryDBHelper(this);
        sqLiteDatabase = chemistryDBHelper.getWritableDatabase();
        cursor = chemistryDBHelper.getInformations(sqLiteDatabase);
        listDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.custom_row_layout);
        listView.setAdapter(listDataAdapter);

        if(cursor.moveToFirst()){
            do {
                String name,equation,description;
                name = cursor.getString(0);
                equation = cursor.getString(1);
                description = cursor.getString(2);
                DataProvider dataProvider= new DataProvider(name, equation,description);
                listDataAdapter.add(dataProvider);
            }while (cursor.moveToNext());
        }
    }
}
