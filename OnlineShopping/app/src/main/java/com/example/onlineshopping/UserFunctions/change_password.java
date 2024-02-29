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

import com.example.onlineshopping.DateBaseSql.Login_Register_DBHelper;
import com.example.onlineshopping.R;

public class change_password extends AppCompatActivity {


    TextView textView;
    EditText password , c_password;
    Button finish;
    TextView Login;
    Login_Register_DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Session session = Session.getInstance();
        textView = findViewById(R.id.welcome_msg);
        textView.setText("Welcome " + session.getUsername());
        password = findViewById(R.id.change_password);
        c_password = findViewById(R.id.change_confirmpassword);
        finish = findViewById(R.id.finish_btn);
        Login =(TextView) findViewById(R.id.gotologin);
        DB = new Login_Register_DBHelper(this);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = password.getText().toString();
                String c_pass = c_password.getText().toString();
                if(TextUtils.isEmpty(pass) || TextUtils.isEmpty(c_pass) )
                {
                    Toast.makeText(change_password.this,"All fields are required",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(c_pass))
                    {

                        boolean update = DB.update_password(session.getUsername(),pass,session.getAnswer());
                        if(update == true)
                        {
                            Toast.makeText(change_password.this,"updated",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(change_password.this,"try again",Toast.LENGTH_SHORT).show();
                        }
                    }else
                    {
                        Toast.makeText(change_password.this,"Password not matched",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intet = new Intent(getApplicationContext(), com.example.onlineshopping.UserFunctions.Login.class);
                startActivity(intet);

            }
        });
    }
}