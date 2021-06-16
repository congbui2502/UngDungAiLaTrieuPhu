package com.example.ailatrieuphu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class First_layout extends AppCompatActivity {

    private ImageButton imageButton;
    private TextView tvLogon;

    private ImageView image_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();


        setContentView(R.layout.activity_first_layout);
        imageButton=findViewById(R.id.btn_play);
        tvLogon=findViewById(R.id.dangnhap);
        image_user = findViewById(R.id.image_user);

        Intent intent=getIntent();
        if(intent.getStringExtra("login")!=null)
        {
            String login = intent.getStringExtra("login");


            tvLogon.setBackgroundResource(R.drawable.vientron);

            tvLogon.setText(login);
        }


        tvLogon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(First_layout.this,InforActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
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