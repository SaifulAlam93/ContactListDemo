package com.example.contactlistdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddContact extends AppCompatActivity {
    EditText name,address,contact;
    Button save;
    int pos=0;
    ArrayList<Contact> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        name = findViewById(R.id.cname);
        address = findViewById(R.id.caddress);
        contact=findViewById(R.id.ccontact);
        save = findViewById(R.id.update);
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
                        pos = value.size();
                    }
                    Toast.makeText(AddContact.this, "Data Received: "+ list.get(1).getName(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AddContact.this, "Data Received failed ", Toast.LENGTH_SHORT).show();
            }
        });

        pos =Integer.parseInt(getIntent().getStringExtra("position"));




save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        DatabaseReference myRef = database.getReference("contacts/"+pos);
        final Contact person = new Contact();
        person.setName(name.getText().toString());
        person.setAddress(address.getText().toString());
        person.setContactno(contact.getText().toString());
        myRef.setValue(person);
        startActivity(new Intent(AddContact.this, Homepage.class));
    }
});


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        startActivity(new Intent(AddContact.this, Homepage.class));
        return true;

    }

}
