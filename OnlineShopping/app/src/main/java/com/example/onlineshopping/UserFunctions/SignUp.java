package com.example.onlineshopping.UserFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshopping.DateBaseSql.Login_Register_DBHelper;
import com.example.onlineshopping.R;

import java.util.Calendar;

public class SignUp extends AppCompatActivity {

    EditText username , password , c_password, answer, BirthDate, ssn, creditrcard;
    Button signup;
    Login_Register_DBHelper DB;
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.signup_username);
        password = findViewById(R.id.signup_password);
        c_password = findViewById(R.id.signup_confirmpassword);
        BirthDate = findViewById(R.id.birthdate);
        ssn = findViewById(R.id.ssn);
        creditrcard = findViewById(R.id.creditcard);
        answer = findViewById(R.id.signup_question);
        signup = findViewById(R.id.signup_btn);
        DB = new Login_Register_DBHelper(this);
        login = findViewById(R.id.gotologin);

        Calendar calendar = Calendar.getInstance();
        final int Year = calendar.get(Calendar.YEAR);
        final int Month = calendar.get(Calendar.MONTH);
        final int Day = calendar.get(Calendar.DAY_OF_MONTH);
        BirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog date = new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                        month += 1;
                        String myDate = day + "/" + month + "/" + year;
                        BirthDate.setText(myDate);
                    }
                }, Year, Month, Day);
                date.show();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Ssn = ssn.getText().toString();
                String Credit = creditrcard.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String c_pass = c_password.getText().toString();
                String ans = answer.getText().toString();
                String BirthDatee = BirthDate.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) ||TextUtils.isEmpty(c_pass) ||TextUtils.isEmpty(ans) ){
                    Toast.makeText(SignUp.this,"All fields are required",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (pass.length()>=6 && c_pass.length()>=6) {
                        if (pass.equals(c_pass)) {
                            Boolean checkuser = DB.checkUserName(user);
                            if (checkuser == false) {
                                Boolean insert = DB.inserData(user, pass, ans, BirthDatee, Ssn, Credit);
                                if (insert == true) {
                                    Toast.makeText(SignUp.this, "Signed up succesfully", Toast.LENGTH_SHORT).show();

                                    //place the home page here
                                    //Intent intet = new Intent(getApplicationContext(),);
                                    //startActivity(intet);
                                } else {
                                    Toast.makeText(SignUp.this, "Try again", Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                Toast.makeText(SignUp.this, "username exists", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(SignUp.this, "Password not matched", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else {
                        Toast.makeText(SignUp.this, "Password must be 6 digits or more", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });


    }
}