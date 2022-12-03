package com.example.studentdashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class registration_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button register;
    TextInputEditText name,email,id,phone,password,confPass;

    String emailPattern = "[a-zA-Z0-9._-]+@[bvm]+\\.+[ac]+\\.+[in]+";
    String phonePattern = "^[+]?[0-9]{10}$";
    String passwordPattern = "^(?=\\S+$).{6,}$";        // No white space and at least 6 digit
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        name = findViewById(R.id.textInputEditName);
        email = findViewById(R.id.textInputEditEmail);
        id = findViewById(R.id.textInputEditIdNo);
        phone = findViewById(R.id.textInputEditContactNo);
        password = findViewById(R.id.textInputEditPassword);
        confPass = findViewById(R.id.textInputEditConformPassword);
        register = findViewById(R.id.register);

        Spinner deptSpinner = findViewById(R.id.department);
        ArrayAdapter<CharSequence> deptAdapter = ArrayAdapter.createFromResource(this, R.array.SelectDepartment, android.R.layout.simple_spinner_item);
        deptAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deptSpinner.setAdapter(deptAdapter);
        deptSpinner.setOnItemSelectedListener(this);

        Spinner semSpinner = findViewById(R.id.semester);
        ArrayAdapter<CharSequence> semAdapter = ArrayAdapter.createFromResource(this, R.array.SelectSemester, android.R.layout.simple_spinner_item);
        semAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semSpinner.setAdapter(semAdapter);
        semSpinner.setOnItemSelectedListener(this);

        Spinner divSpinner = findViewById(R.id.division);
        ArrayAdapter<CharSequence> divAdapter = ArrayAdapter.createFromResource(this, R.array.SelectDivision, android.R.layout.simple_spinner_item);
        divAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        divSpinner.setAdapter(divAdapter);
        divSpinner.setOnItemSelectedListener(this);

        register.setOnClickListener(view -> {
            StartRegistration(deptSpinner,semSpinner,divSpinner);
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        System.out.println(text+"Selected");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void StartRegistration(Spinner deptSpinner,Spinner semSpinner,Spinner divSpinner){

        String userName = name.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String userDept = deptSpinner.getSelectedItem().toString().trim();
        String userSem = semSpinner.getSelectedItem().toString().trim();
        String userDiv = divSpinner.getSelectedItem().toString().trim();
        String userId = id.getText().toString();
        String userPhone = phone.getText().toString();
        String userPassword = password.getText().toString().trim();
        String userConfPassword = confPass.getText().toString().trim();
        DBHelper DB =  new DBHelper(this);;

        if(userName.isEmpty() || userEmail.isEmpty() || userDept.isEmpty() ||  userSem.isEmpty() || userDiv.isEmpty() ||userId.isEmpty() ||userPhone.isEmpty() || userConfPassword.isEmpty() || userPassword.isEmpty()){
            Toast.makeText(registration_activity.this, "All fields are Required!", Toast.LENGTH_SHORT).show();
        }
        else{
            if(CheckPattern())
            {
                if(!userPassword.equals(userConfPassword))
                {
                    Toast.makeText(registration_activity.this, "Passwords are not matching.\nPlease try again..", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(userPassword.equals(userConfPassword)){
                        Boolean checkemail = DB.checkemail(userEmail);
                        if(checkemail==false){
                            Boolean insert = DB.insertData(userName,userEmail,userDept,userSem,userDiv,userId,userPhone,userPassword);
                            if(insert==true){
                                System.out.println(userName + "\t" + userEmail+ "\t" + userDept+ "\t" + userSem+ "\t" + userDiv+ "\t" + userId+ "\t" + userPhone+ "\t" + userPassword);
                                Toast.makeText(registration_activity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent intent = new Intent(getApplicationContext(),home_activity.class);
                                intent.putExtra("emailofuser",userEmail);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Registration failed",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(registration_activity.this,"User Already Exists!!! ",Toast.LENGTH_SHORT).show();
                            finish();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(registration_activity.this,"Password and RePassword Not Matched. ",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private boolean CheckPattern()
    {
        if(!email.getText().toString().trim().matches(emailPattern))
        {
            Toast.makeText(registration_activity.this, "Enter Valid Email Id", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!phone.getText().toString().trim().matches(phonePattern))
        {
            Toast.makeText(registration_activity.this, "Enter Valid Phone Number.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!password.getText().toString().trim().matches(passwordPattern))
        {
            Toast.makeText(registration_activity.this, "Make sure that password length is minimum 4 and white spaces are not allowed.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}