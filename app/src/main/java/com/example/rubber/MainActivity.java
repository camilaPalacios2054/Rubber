package com.example.rubber;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.*;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    TextView registro;
    Button loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.Username);
        password=findViewById(R.id.Password);
        loginButton=findViewById(R.id.LoginButton);
        registro=findViewById(R.id.SignupText);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CrearUsuarioActivity.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("user")&&password.getText().toString().equals("123"))
                {
                    Toast.makeText(MainActivity.this,"Login Successful!",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login Failed!",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}