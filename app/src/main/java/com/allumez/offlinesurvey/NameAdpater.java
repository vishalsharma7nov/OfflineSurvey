package com.allumez.offlinesurvey;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



public class NameAdpater extends ArrayAdapter<Name> {

    //storing all the names in the list
    private List<Name> names;

    //context object
    private Context context;
    //constructor
    public NameAdpater(Context context, int resource, List<Name> names) {
        super(context, resource, names);
        this.context = context;
        this.names = names;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //getting the layoutinflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //getting listview itmes
        View listViewItem = inflater.inflate(R.layout.names, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewId);
        TextView textViewPhone = (TextView) listViewItem.findViewById(R.id.textViewPhone);
        ImageView imageViewPhoto = (ImageView) listViewItem.findViewById(R.id.imageViewPhoto);

        //getting the current names
        Name name = names.get(position);

        //setting the names to textview
        textViewName.setText(name.getName());
        textViewId.setText(name.getId());
        textViewPhone.setText(name.getPhone());


        return listViewItem;
    }


}