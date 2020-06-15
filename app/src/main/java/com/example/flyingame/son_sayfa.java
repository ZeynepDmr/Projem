package com.example.flyingame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class son_sayfa extends AppCompatActivity {
    private TextView puan;
    private Button menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_son_sayfa);

        puan = findViewById(R.id.puan);
        menu = findViewById(R.id.menu);

        String gelenSkor = getIntent().getStringExtra("sonuc");
        puan.setText(gelenSkor);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(son_sayfa.this,MainActivity.class));
            }
        });
    }
}