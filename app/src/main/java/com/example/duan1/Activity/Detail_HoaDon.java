package com.example.duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duan1.MainActivity;
import com.example.duan1.R;

public class Detail_HoaDon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial_hoa_don);
        Button btnquaylai = findViewById(R.id.btn_quayLai_detail);

        btnquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detail_HoaDon.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}