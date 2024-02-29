package com.example.onlineshopping.UserFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlineshopping.DateBaseSql.Login_Register_DBHelper;
import com.example.onlineshopping.R;

public class passwordrecovery extends AppCompatActivity {

    EditText username,answer;
    Button change_pass;
    Login_Register_DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordrecovery);


        username = findViewById(R.id.recov_username);
        answer = findViewById(R.id.recov_answer);
        change_pass = findViewById(R.id.recov_login_btn);
        DB = new Login_Register_DBHelper(this);
        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String ans = answer.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(ans)){
                    Toast.makeText(passwordrecovery.this,"All fields are required",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass = DB.checkUserName_question(user,ans);
                    if(checkuserpass == true){
                        Toast.makeText(passwordrecovery.this,"Logged in succesfully",Toast.LENGTH_SHORT).show();
                        Session session = Session.getInstance();
                        session.setUsername(user);
                        session.setAnswer(ans);
                        //place the home page here
                        Intent intet = new Intent(getApplicationContext(), change_password.class);
                        startActivity(intet);
                    }
                    else{
                        Toast.makeText(passwordrecovery.this,"Login failed",Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });


    }
}