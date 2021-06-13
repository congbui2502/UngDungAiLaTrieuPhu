package com.example.ailatrieuphu;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class Logon extends AppCompatActivity {
    private EditText emails,password;
    private Button login,sginup;
    private FirebaseAuth mAuth;
    private ImageButton outfirts;
    private TextView forget;
    public static final String KEY_USER = "KEY_EMAIL_TO_FIRSTLAYOUT";
    public static final String KEY_PASSWORD_TO_MAIN = "KEY_PASSWORD_TO_FIRSTLAYOUT";
//    public static final String KEY_USER_FROM_REGISTER = "KEY_USER_FROM_REGISTER";
//    public static final int REQUEST_CODE_REGISTER = 1;
//    private Context context;

    private FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        mAuth = FirebaseAuth.getInstance();

        emails=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        sginup=findViewById(R.id.signup);
//        outfirts = findViewById(R.id.outfirts);
        forget = findViewById(R.id.forget);
        //out firtlayout ra khoi login
//        outfirts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Logon.this,First_layout.class);
//                startActivity(intent);
//            }
//        });
            //viet su kien cho ham login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });


        //viet su kien cho ham dang ki
        sginup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register();
            }
        });

        //viet su kien cho hàm quên mật khẩu
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Logon.this,ForgetPassWords.class);
                startActivity(intent);

            }
        });
    }
    //ham dangki
        private void register( ) {
            Intent i =new Intent(Logon.this,Signup.class);
            startActivity(i);
        }
        //ham login
        private void login() {

        String email,pass;
        email=emails.getText().toString();
        pass=password.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Vui lòng nhập email!!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Vui lòng nhập password!!",Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser  user =  FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        Toast.makeText(getApplicationContext(),"Đăng nhập thành công!", Toast.LENGTH_SHORT).show();


                        Bundle bundle=new Bundle();
                        bundle.putString("namelogon",emails.getText().toString());

                        Intent intent=new Intent(Logon.this,First_layout.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);


                        intent.putExtra("login",emails.getText().toString());
                        startActivity(intent);
                        finish();



                    }
                    else {
                        user.sendEmailVerification();
                        Toast.makeText(getApplicationContext(),"Vui Lòng Xác Minh Email!", Toast.LENGTH_SHORT).show();

                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Nhập sai email hoặc Passwords", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}