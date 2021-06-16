package com.example.ailatrieuphu.SonClass;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ailatrieuphu.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DongHoDemNguoc {
    private  ArrayList<TextView> dscauhoi=new ArrayList<TextView>();

    private View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for(int i=0;i<4;i++)
            {
                if(dscauhoi.get(i).getId()==v.getId()){

                    dscauhoi.get(i).setBackgroundResource(R.drawable.ketquachon);


                }else {
                    dscauhoi.get(i).setEnabled(false);
                }
            }


        }
    };

    public DongHoDemNguoc(ArrayList<TextView> dscauhoi) {
        this.dscauhoi=dscauhoi;
    }


    public CountDownTimer dongHoDemNguoc(TextView textView){

//        long duration = TimeUnit.MINUTES.toMillis(1);

         CountDownTimer abc=  new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                String sDuration=(l)/1000+"";
                textView.setText(sDuration);
            }

            @Override
            public void onFinish() {


            }
        };
         return abc;
    }


    public  void  setBackgroundTienThuong(ArrayList<LinearLayout> dsTienThuong){
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

                for(int i=0;i< 4;i++)
                {
                    dscauhoi.get(i).setOnClickListener(clickListener);
                }

            }

            @Override
            public void onFinish() {
                for(int i=0;i<dsTienThuong.size();i++)
                {

                    dsTienThuong.get(i).setBackgroundResource(R.drawable.textviewlayout);

                }
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
