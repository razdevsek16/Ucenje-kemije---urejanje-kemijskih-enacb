package com.urejanjekemijskihenacb.SqlDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by Aljaz on 16. 01. 2017.
 */

public class ChemistryDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ChemistryEquationInfo.DB";
    private  static  final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE "+ChemistryEquationSave.NewChemistryEquation.TABLE_NAME+" ("+ChemistryEquationSave.NewChemistryEquation.EQUATION_NAME+" TEXT,"+ChemistryEquationSave.NewChemistryEquation.EQUATION+" TEXT,"+
                    ChemistryEquationSave.NewChemistryEquation.EQUATION_DESCRIPTION+" TEXT);";
    public  ChemistryDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e("DATABASE_OPERATIONS","Database created / opened");
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
        Log.e("DATABASE_OPERATIONS","Table created...");
    }

    public void addInformations(String name, String equation, String description, SQLiteDatabase db){
        ContentValues contentValues= new ContentValues();
        contentValues.put(ChemistryEquationSave.NewChemistryEquation.EQUATION_NAME,name);
        contentValues.put(ChemistryEquationSave.NewChemistryEquation.EQUATION,equation);
        contentValues.put(ChemistryEquationSave.NewChemistryEquation.EQUATION_DESCRIPTION,description);
        db.insert(ChemistryEquationSave.NewChemistryEquation.TABLE_NAME,null,contentValues);
        Log.e("DATABASE_OPERATIONS","One row inserted...");
    }

    public Cursor getInformations(SQLiteDatabase db){
        Cursor cursor;
        String[] projections= {ChemistryEquationSave.NewChemistryEquation.EQUATION_NAME, ChemistryEquationSave.NewChemistryEquation.EQUATION, ChemistryEquationSave.NewChemistryEquation.EQUATION_DESCRIPTION};
        cursor = db.query(ChemistryEquationSave.NewChemistryEquation.TABLE_NAME,projections,null, null, null, null, null);
        return  cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
