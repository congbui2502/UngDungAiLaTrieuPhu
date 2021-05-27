package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class First_layout extends AppCompatActivity {

    private ImageButton imageButton;
    private TextView tvLogon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_layout);
        imageButton=findViewById(R.id.btn_play);
        tvLogon=findViewById(R.id.dangnhap);

        Intent intent=getIntent();
        if(intent.getStringExtra("login")!=null)
        {
            String login = intent.getStringExtra("login");
            tvLogon.setBackgroundResource(R.drawable.sansang_click);
            tvLogon.setText(login);
        }


        tvLogon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(First_layout.this,Logon.class);
                startActivity(intent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(First_layout.this,StartActivity.class);
                startActivity(intent);
            }
        });

    }
}