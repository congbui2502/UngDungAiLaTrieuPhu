package com.example.ailatrieuphu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InforActivity extends AppCompatActivity {
    private TextView youremails,telphones;
    private Button logout,cancel;
    private FirebaseUser user;
    private DatabaseReference reference;
//    private FirebaseAuth mAuth;
    private  String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
//        mAuth = FirebaseAuth.getInstance();

        youremails = findViewById(R.id.youremail);
//        passwords = findViewById(R.id.password);
        telphones = findViewById(R.id.telphone);
        logout = findViewById(R.id.Logout);
        cancel = findViewById(R.id.cancel);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(InforActivity.this,"dang xuat thanh cong",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(InforActivity.this, Logon.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();




            }
        });
        user =FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userinfo = snapshot.getValue(User.class);
                if(userinfo != null){
                    String email = userinfo.email;
                    String phone= userinfo.phone;
                    youremails.setText(email);
                    telphones.setText(phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(InforActivity.this,"khong lay duoc thong tin",Toast.LENGTH_LONG).show();
            }
        });

    }
}