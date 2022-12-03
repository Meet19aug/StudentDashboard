package com.example.studentdashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button loginBTN;
    private TextView forgotPassword, noAccount;

    private TextInputEditText email,password;

    String studentEmailPattern = "[a-zA-Z0-9._-]+@[bvm]+\\.+[ac]+\\.+[in]+";
    String passwordPattern = "^(?=\\S+$).{6,}$";        // No white space and at least 6 digit
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new DBHelper(this);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        email = findViewById(R.id.textInputEditTextEmail);
        password = findViewById(R.id.textInputEditTextPassword);
        forgotPassword = findViewById(R.id.textViewForgetPassword);
        loginBTN = findViewById(R.id.btnLOGIN);
        noAccount = findViewById(R.id.dontHaveAnAccount);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "It's Your problem.", Toast.LENGTH_SHORT).show();
            }
        });

        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.studentdashboard.registration_activity.class);
                startActivity(intent);
            }
        });

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString().trim();
                System.out.println("User Email is : " +  userEmail);
                String userPassword = password.getText().toString();
                System.out.println("User Password is : " + userPassword);

                if(userEmail.isEmpty() || userPassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "All fields are Required!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkemailpassword = DB.checkemailpassword(userEmail,userPassword);
                    System.out.println("checkemailpassword value is " + checkemailpassword);
                    if(CheckPattern(1)) {
                        if (checkemailpassword == true) {
                            finish();
                            Toast.makeText(MainActivity.this, "Sign in Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, home_activity.class);
                            intent.putExtra("emailofuser",userEmail);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Invalid Credentials - formate not match.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    private boolean CheckPattern(int role)
    {
        if(role == 1 && (!email.getText().toString().trim().matches(studentEmailPattern))){
            Toast.makeText(MainActivity.this, "Enter Valid Email Id", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!password.getText().toString().trim().matches(passwordPattern))
        {
            Toast.makeText(MainActivity.this, "Make sure that password length is minimum 6 and white spaces are not allowed.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}