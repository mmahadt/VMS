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
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPhone(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }


    private boolean validateEditTexts(EditText emailField, EditText phoneField)
    {
        boolean valid = true;

        if (!isValidEmail(emailField.getText().toString())) {
            emailField.setError("In valid email. Please type again");
            valid=false;
        } else {
            emailField.setError(null);
        }

        if (!isValidPhone(phoneField.getText().toString())) {
            phoneField.setError("In valid phone. Please type again");
            valid=false;
        } else {
            phoneField.setError(null);
        }

        return valid;

    }

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

//        if (validateEditTexts(emailField,phoneField)) {

        if (!isValidEmail(emailField.getText().toString())) {
            emailField.setError("In valid email. Please type again");
        } else {
            emailField.setError(null);
        }

        if (!isValidPhone(phoneField.getText().toString())) {
            phoneField.setError("In valid phone. Please type again");
        } else {
            phoneField.setError(null);
        }

        if( TextUtils.isEmpty(nameField.getText())){
            nameField.setError( "First name is required!" );
        }

        if( TextUtils.isEmpty(emailField.getText())){
            nameField.setError( "Email is required!" );
        }

        if( TextUtils.isEmpty(phoneField.getText())){
            nameField.setError( "phone number is required!" );
        }

        if( TextUtils.isEmpty(expertiseField.getText())){
            nameField.setError( "expertise is required!" );
        }

        if( TextUtils.isEmpty(hoursField.getText())){
            nameField.setError( "Hours is required!" );
        }

        sendDataToFirebase(nameField, emailField, phoneField, expertiseField, hoursField, name, email, phone, expertise, Weeklyhours);
    }

    private void sendDataToFirebase(EditText nameField, EditText emailField, EditText phoneField, EditText expertiseField, EditText hoursField, String name, String email, String phone, String expertise, int weeklyhours) {
        final Volunteer volunteer = new Volunteer(name, email, phone, expertise, weeklyhours);

        DatabaseReference ref = database.getReference("volunteers");

//        DatabaseReference volunteersRef = ref.child("volunteers");

        ref.push().setValue(volunteer);

        nameField.setText(null);
        emailField.setText(null);
        phoneField.setText(null);
        expertiseField.setText(null);
        hoursField.setText(null);

        Toast.makeText(getApplicationContext(), "Your data has been recorded.", Toast.LENGTH_SHORT).show();
    }

}
