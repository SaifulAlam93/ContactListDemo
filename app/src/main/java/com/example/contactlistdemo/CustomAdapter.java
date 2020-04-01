package com.example.contactlistdemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Contact> arrayList;
    private TextView name, address, contact;
    private Button edit;
    public CustomAdapter(Context context, ArrayList<Contact> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }


    @Override
    public Object getItem(int position) {
        return position;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        name = convertView.findViewById(R.id.name);
        address = convertView.findViewById(R.id.address);
        contact = convertView.findViewById(R.id.contact);
        edit = convertView.findViewById(R.id.editbtn);
        name.setText( arrayList.get(position).getName());
        address.setText(arrayList.get(position).getAddress());
        contact.setText(arrayList.get(position).getContactno());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Data Received: "+ position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, AddContact.class);

                intent.putExtra("position",""+position);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
