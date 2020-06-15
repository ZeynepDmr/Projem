package com.example.flyingame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public Button btn_Basla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Basla=findViewById(R.id.btn_Basla);
        btn_Basla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent basla = new Intent(MainActivity.this, ilk_sayfa.class);
                startActivity(basla);

            }
        });
    }
}