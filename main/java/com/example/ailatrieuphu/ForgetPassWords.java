package com.example.ailatrieuphu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class ForgetPassWords extends AppCompatActivity {
    private EditText emails;
    private Button getpass,bntout;
    FirebaseAuth mAuth;
    private ProgressBar progressBar;
    //    private ImageButton outfirts;
    private FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);
        mAuth = FirebaseAuth.getInstance();

        emails = findViewById(R.id.youremail);

        getpass = findViewById(R.id.getpass);
        bntout = findViewById(R.id.cancel);
        progressBar = findViewById(R.id.progressbar);

        getpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPassWords();
            }
        });


    }
    private void ResetPassWords(){

        String email = emails.getText().toString().trim();
        if(email.isEmpty()){
            emails.setError("Email chưa có");
            emails.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emails.setError("Sai Cú Pháp Email");
            emails.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgetPassWords.this,"Vui Lòng Check email ",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(ForgetPassWords.this,"Vui Long Thử lại ",Toast.LENGTH_LONG).show();
                }
            }

        });
    }



}