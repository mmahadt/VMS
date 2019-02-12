package com.bytexcite.alburhanvolunteers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sendData(View button) {
        final EditText nameField =  findViewById(R.id.editText1);
        final EditText emailField =  findViewById(R.id.editText2);
        final EditText phoneField =  findViewById(R.id.editText3);
        final EditText expertiseField = findViewById(R.id.editText4);
        final EditText hoursField = findViewById(R.id.editText5);

        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String phone = phoneField.getText().toString();
        String expertise = expertiseField.getText().toString();

        String hours = hoursField.getText().toString();

        int Weeklyhours = Integer.parseInt(hours);

        final Volunteer volunteer = new Volunteer(name, email, phone, expertise, Weeklyhours);

        DatabaseReference ref = database.getReference("volunteers");

//        DatabaseReference volunteersRef = ref.child("volunteers");

        ref.push().setValue(volunteer);

        nameField.setText(null);
        emailField.setText(null);
        phoneField.setText(null);
        expertiseField.setText(null);
        hoursField.setText(null);

    }

}
