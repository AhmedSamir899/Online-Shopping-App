package com.example.onlineshopping.UserFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshopping.AdminFunctions.Admin;
import com.example.onlineshopping.DateBaseSql.Login_Register_DBHelper;
import com.example.onlineshopping.R;

public class Login extends AppCompatActivity {

    EditText username,password;
    TextView forgerpass;
    TextView signup;
    Button login;
    Login_Register_DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.login_btn);
        forgerpass = findViewById(R.id.textView4);
        signup = findViewById(R.id.Signup_here);
        DB = new Login_Register_DBHelper(this);
        login.setOnClickListener(view -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                Toast.makeText(Login.this,"All fields are required",Toast.LENGTH_SHORT).show();
            }
            else{
                Boolean checkuserpass = DB.checkUserName_password(user,pass);
                if(checkuserpass == true){
                    Toast.makeText(Login.this,"Logged in succesfully",Toast.LENGTH_SHORT).show();
                    Session session = Session.getInstance();
                    session.setUsername(user);
                    Intent inte = new Intent(Login.this, Categories_Activity.class);
                    startActivity(inte);
                }
                else if(user.equals("admin") && pass.equals("admin")){
                    Session session = Session.getInstance();
                    session.setUsername(user);
                    Intent inte = new Intent(Login.this, Admin.class);
                    startActivity(inte);
                    Toast.makeText(Login.this,"Welcome admin",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(Login.this,"Login failed",Toast.LENGTH_SHORT).show();

                }
            }
        });

        forgerpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intet = new Intent(getApplicationContext(),passwordrecovery.class);
                startActivity(intet);
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intet = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intet);
            }
        });
    }
}