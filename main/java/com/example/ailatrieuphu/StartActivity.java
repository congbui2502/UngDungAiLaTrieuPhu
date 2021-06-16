package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {
    private Button btnStart;
    private LinearLayout tienthuongcau1,tienthuongcau15,tienthuongcau2,tienthuongcau3,tienthuongcau4,tienthuongcau5,
            tienthuongcau6,tienthuongcau7,tienthuongcau8,tienthuongcau9,tienthuongcau10,tienthuongcau11,tienthuongcau12,tienthuongcau13,tienthuongcau14;

    private ArrayList<LinearLayout> dsTienThuong=new ArrayList<LinearLayout>();

    private ImageView namnam,goidienthoai,hoiykienkhangia,tuvantaicho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Log.d("AAA","Start onCreate");


        //Lai Van Sam mo dau
        MediaPlayer cb=MediaPlayer.create(StartActivity.this,R.raw.ailatrieuphu);
        cb.start();

        btnStart=findViewById(R.id.btnStart);

        tienthuongcau1=findViewById(R.id.lncau1);
        dsTienThuong.add(tienthuongcau1);
        tienthuongcau2=findViewById(R.id.lncau2);
        dsTienThuong.add(tienthuongcau2);
        tienthuongcau3=findViewById(R.id.lncau3);
        dsTienThuong.add(tienthuongcau3);
        tienthuongcau4=findViewById(R.id.lncau4);
        dsTienThuong.add(tienthuongcau4);
        tienthuongcau5=findViewById(R.id.lncau5);
        dsTienThuong.add(tienthuongcau5);
        tienthuongcau6=findViewById(R.id.lncau6);
        dsTienThuong.add(tienthuongcau6);
        tienthuongcau7=findViewById(R.id.lncau7);
        dsTienThuong.add(tienthuongcau7);
        tienthuongcau8=findViewById(R.id.lncau8);
        dsTienThuong.add(tienthuongcau8);
        tienthuongcau9=findViewById(R.id.lncau9);
        dsTienThuong.add(tienthuongcau9);
        tienthuongcau10=findViewById(R.id.lncau10);
        dsTienThuong.add(tienthuongcau10);
        tienthuongcau11=findViewById(R.id.lncau11);
        dsTienThuong.add(tienthuongcau11);
        tienthuongcau12=findViewById(R.id.lncau12);
        dsTienThuong.add(tienthuongcau12);
        tienthuongcau13=findViewById(R.id.lncau13);
        dsTienThuong.add(tienthuongcau13);
        tienthuongcau14=findViewById(R.id.lncau14);
        dsTienThuong.add(tienthuongcau14);
        tienthuongcau15=findViewById(R.id.lncau15);
        dsTienThuong.add(tienthuongcau15);

        //tham chieu su tro giup
        namnam=findViewById(R.id.trogiupnamnam);
        goidienthoai=findViewById(R.id.trogiupgoidienthoai);
        hoiykienkhangia=findViewById(R.id.trogiuphoiykien);
        tuvantaicho=findViewById(R.id.trogiuptuvantaicho);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dung phat nhac
                cb.stop();
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        btnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //tat nhac
//
//                cb.stop();
//                Intent intent=new Intent(StartActivity.this,MainActivity.class);
//                startActivity(intent);
////                new CountDownTimer(500, 500) {
////                    @Override
////                    public void onTick(long millisUntilFinished) {
////                        v.setBackgroundResource(R.drawable.sansang_click);
////
////                    }
////
////                    @Override
////                    public void onFinish() {
////                        v.setBackgroundResource(R.drawable.sansang_layout);
////
////                    }
////                }.start();
//
//
//            }
//        });


        new CountDownTimer(15000,250) {
            @Override
            public void onTick(long millisUntilFinished) {
                int soDem= (int) (15000-millisUntilFinished)/250;
                for(int i=0;i<dsTienThuong.size();i++)
                {
                    if(i==soDem){
                        dsTienThuong.get(i).setBackgroundResource(R.drawable.ketquachon);
                    }else {
                        dsTienThuong.get(i).setBackgroundResource(R.drawable.textviewlayout);
                    }
                }
                if(millisUntilFinished==5000){

                    namnam.setBackgroundResource(R.mipmap.namnam_uncheck);
                }
                if(millisUntilFinished==3000){
                    goidienthoai.setBackgroundResource(R.mipmap.phone_nochecked);
                }
                if(millisUntilFinished==2000){
                    hoiykienkhangia.setBackgroundResource(R.mipmap.hoiykien_nochecked);
                }




            }

            @Override
            public void onFinish() {
                for(int i=0;i<dsTienThuong.size();i++)
                {

                    dsTienThuong.get(i).setBackgroundResource(R.drawable.textviewlayout);

                }
                btnStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(StartActivity.this,MainActivity.class);
                        startActivity(intent);
                        new CountDownTimer(500, 500) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                v.setBackgroundResource(R.drawable.sansang_click);

                            }

                            @Override
                            public void onFinish() {
                                v.setBackgroundResource(R.drawable.sansang_layout);

                            }
                        }.start();


                    }
                });


                new CountDownTimer(2400, 800) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int soDem=(int) (2400-millisUntilFinished)/800;

                        dsTienThuong.get((soDem+1)*5-1).setBackgroundResource(R.drawable.ketquachon);

                    }

                    @Override
                    public void onFinish() {
                        for(int i=0;i<dsTienThuong.size();i++)
                        {

                            dsTienThuong.get(i).setBackgroundResource(R.drawable.textviewlayout);

                        }


                    }
                }.start();

//

            }
        }.start();



    }

    protected void onStart() {
        super.onStart();
        Log.d("AAA","Start onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("AAA","Start onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AAA","Start onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AAA","Start on Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("AAA","Start  onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AAA","Start onDestroy");
    }
}