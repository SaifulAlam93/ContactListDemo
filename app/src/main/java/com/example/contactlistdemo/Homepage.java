package com.example.contactlistdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity {

    ListView listView;
    Button add;
    ArrayList<Contact> list = new ArrayList<>();
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        listView = findViewById(R.id.contact_list);
        add = findViewById(R.id.addbtn);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef = myRef.child("contacts");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Contact> value =  dataSnapshot.getValue(new GenericTypeIndicator<ArrayList<Contact>>(){});
                if (value.size()!=0){
                    for (Contact contact: value) {
                        list.add(contact);
                    }
                    adapter = new CustomAdapter(Homepage.this,list);
                    listView.setAdapter(adapter);
                    Toast.makeText(Homepage.this, "Data Received: "+ list.get(1).getName(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Homepage.this, "Data Received failed ", Toast.LENGTH_SHORT).show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, AddContact.class);
                intent.putExtra("position",""+list.size());
                startActivity(intent);

            }
        });


    }


}