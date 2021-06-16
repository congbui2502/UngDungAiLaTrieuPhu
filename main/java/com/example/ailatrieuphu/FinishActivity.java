package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FinishActivity extends AppCompatActivity {
    private EditText tienThuong;
    private Button choiLai,thoat;
    private String tienTong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        Intent intent =getIntent();

        if(intent!=null)
        {
            tienTong= intent.getStringExtra("tienthuong");

        }


        tienThuong=findViewById(R.id.tienThuong);
        thoat=findViewById(R.id.thoat);
        choiLai=findViewById(R.id.choilai);

        tienThuong.setText("Bạn đạt được "+tienTong+"$");

        choiLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FinishActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(homeIntent);

//                System.exit(0);
            }
        });




    }
}