package com.example.sam.bucketlist.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sam.bucketlist.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by sam on 11/7/17.
 */

public class CustomBucketListAdapter extends ArrayAdapter<HashMap> {

private final Context context;
private final ArrayList<HashMap> values;

public CustomBucketListAdapter(Context context,ArrayList<HashMap> values){
    super(context,-1,values);

    this.context = context;
    this.values = values;


}
// View holder
    static class ViewHolder {
    public TextView idText,nameText;
}


    @Override
    public View getView(int position,View convertView, ViewGroup parent){

        LayoutInflater inflater =(LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.custom_adapter, parent,false);

        TextView bucketListID = (TextView) rowView.findViewById(R.id.bucket_list_id);
        TextView bucketListName =(TextView) rowView.findViewById(R.id.bucket_list_name);


            HashMap<String,String> data= values.get(position);
            bucketListID.setText(data.get("id"));
            bucketListName.setText(data.get("name"));

        return rowView;

    }

}
