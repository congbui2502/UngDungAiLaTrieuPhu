package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {
    private Button btnStart;
    private LinearLayout tienthuongcau1,tienthuongcau15,tienthuongcau2,tienthuongcau3,tienthuongcau4,tienthuongcau5,
            tienthuongcau6,tienthuongcau7,tienthuongcau8,tienthuongcau9,tienthuongcau10,tienthuongcau11,tienthuongcau12,tienthuongcau13,tienthuongcau14;

    private ArrayList<LinearLayout> dsTienThuong=new ArrayList<LinearLayout>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

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

        new CountDownTimer(3000,200) {
            @Override
            public void onTick(long millisUntilFinished) {
                int soDem= (int) (3000-millisUntilFinished)/200;
                for(int i=0;i<dsTienThuong.size();i++)
                {
                    if(i==soDem){
                        dsTienThuong.get(i).setBackgroundResource(R.drawable.ketquachon);
                    }else {
                        dsTienThuong.get(i).setBackgroundResource(R.drawable.textviewlayout);
                    }
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
//
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
}