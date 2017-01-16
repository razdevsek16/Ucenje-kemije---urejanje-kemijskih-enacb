package com.urejanjekemijskihenacb.SqlDataBase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.urejanjekemijskihenacb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aljaz on 16. 01. 2017.
 */

public class ListDataAdapter extends ArrayAdapter{

    List list= new ArrayList();
    public ListDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandeler{
        TextView name;
        TextView equation;
        TextView description;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row= convertView;
        LayoutHandeler layoutHandeler;
        if(row == null){
            LayoutInflater layoutInflater=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.custom_row_layout,parent, false);
            layoutHandeler = new LayoutHandeler();
            layoutHandeler.name= (TextView) row.findViewById(R.id.text_equation_name);
            layoutHandeler.equation =(TextView) row.findViewById(R.id.text_equation);
            layoutHandeler.description = (TextView)row.findViewById(R.id.text_description);
            row.setTag(layoutHandeler);
        }
        else{
            layoutHandeler =(LayoutHandeler) row.getTag();
            }

        DataProvider dataProvider = (DataProvider) this.getItem(position);
        layoutHandeler.name.setText(dataProvider.getName());
        layoutHandeler.equation.setText(dataProvider.getEquation());
        layoutHandeler.description.setText(dataProvider.getDescription());

        return row;

    }
}
