package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Logon extends AppCompatActivity {
    private EditText id,pass;
    private Button login,sginup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);

        id=findViewById(R.id.edtid);
        pass=findViewById(R.id.edtpass);
        login=findViewById(R.id.login);
        sginup=findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle=new Bundle();
//                bundle.putString("namelogon",id.getText().toString());

                Intent intent=new Intent(Logon.this,First_layout.class);

                intent.putExtra("login",id.getText().toString());
                startActivity(intent);
            }
        });

    }
}