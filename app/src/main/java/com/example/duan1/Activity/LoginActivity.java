package com.example.duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1.MainActivity;
import com.example.duan1.R;

public class LoginActivity extends AppCompatActivity {
EditText user, pass;
Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhxa();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.getText().toString().equals("admin")&& pass.getText().toString().equals("admin")){
                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"sai tên đăng nhập, mật khẩu",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void anhxa(){
        user = findViewById(R.id.user);
        pass = findViewById(R.id.Pass);
        btnLogin = findViewById(R.id.Login);
    }
}