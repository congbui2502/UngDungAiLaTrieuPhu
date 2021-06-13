package com.example.ailatrieuphu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
private EditText  email;
private EditText password;
private  EditText phones ;
private Button signup,cancel;

 private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();

        email =   findViewById(R.id.email);
        password  = findViewById(R.id.password);
        phones = findViewById(R.id.phone);
        signup = findViewById(R.id.signup);
        cancel = findViewById(R.id.cancel);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               cancel();
            }
        });
    }
    private void cancel(){
        Intent i =new Intent(Signup.this,Logon.class);
        startActivity(i);
    }
    private void register( ) {
        String emails,pass,phone;
        emails=email.getText().toString().trim();
        pass=password.getText().toString().trim();
        phone= phones.getText().toString().trim();
        if(emails.isEmpty()){
           email.setError("Vui Long nhap email");
           email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emails).matches()){
            email.setError("Vui Long Nhap Dung Email");
            email.requestFocus();
            return;
        }
        if(pass.isEmpty()){
           password.setError("Vui Long Nhap PassWord");
           password.requestFocus();
            return;
        }
        if(pass.length()<6){
            password.setError("Passwords lớn hơn 6 kí tự");
            password.requestFocus();
            return;
        }

        if(phone.isEmpty()){
            phones.setError("vui Long Nhap Phone");
            phones.requestFocus();
            return;
        }
//        if(phone.length()<11 || phone.length() >9){
//            phones.setError("số điện thoai không đúng form");
//            phones.requestFocus();
//            return;
//        }
        if(!Patterns.PHONE.matcher(phone).matches()){
            phones.setError("số điện thoai không đúng form");
            phones.requestFocus();
            return;
        }

            mAuth.createUserWithEmailAndPassword(emails,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                    User user = new User(phone,emails,pass);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(Signup.this,"dang ki thanh cong!!",Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(Signup.this ,Logon.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }
                                        else{
                                            Toast.makeText(Signup.this,"dang ki khong thanh cong!!",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                            else {
                                Toast.makeText(Signup.this,"dang ki khong thanh cong!!",Toast.LENGTH_LONG).show();
                            }


                        }
                    });


    }

}