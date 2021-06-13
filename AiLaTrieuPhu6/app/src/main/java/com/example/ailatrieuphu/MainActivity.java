package com.example.ailatrieuphu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ailatrieuphu.SonClass.CauHoiALTP;
import com.example.ailatrieuphu.SonClass.DongHoDemNguoc;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private  ArrayList<CauHoiALTP> dsCauhoi= new ArrayList<CauHoiALTP>();

    private LinearLayout tienthuongcau1,tienthuongcau15,tienthuongcau2,tienthuongcau3,tienthuongcau4,tienthuongcau5,
            tienthuongcau6,tienthuongcau7,tienthuongcau8,tienthuongcau9,tienthuongcau10,tienthuongcau11,tienthuongcau12,tienthuongcau13,tienthuongcau14,cautlA,cautlB,cautlC,cautlD;

    private  TextView cauhoi,cauA,cauB,cauC,cauD,dongHoDemNguoc;
    private  ArrayList<TextView> dscauhoi=new ArrayList<TextView>();
    private CountDownTimer dongHo;

    private View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for(int i=0;i<4;i++)
            {
                if(dscauhoi.get(i).getId()==v.getId()){

                    dscauhoi.get(i).setBackgroundResource(R.drawable.ketquachon);
                    dongHo.cancel();




                }else {
                    dscauhoi.get(i).setEnabled(false);
                }
            }


        }
    };

    private ArrayList<LinearLayout> dsTienThuong=new ArrayList<LinearLayout>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
        dsCauhoi.add(new CauHoiALTP("Theo một câu hát thì: \"Ba thương con vì con giống mẹ.Mẹ thương con vì con giống ...\" ai?","Ông hàng xóm","Chú cạnh nhà","Ba","Bác đầu ngõ"));


        cauhoi=findViewById(R.id.tvCauhoi);
        cauA=findViewById(R.id.cauA);
        cauB=findViewById(R.id.cauB);
        cauC=findViewById(R.id.cauC);
        cauD=findViewById(R.id.cauD);

        cauhoi.setText(dsCauhoi.get(0).getCauhoi().toString());
        cauA.setText(cauA.getText()+" "+dsCauhoi.get(0).getCauA());
        cauB.setText(dsCauhoi.get(0).getCauB());
        cauC.setText(dsCauhoi.get(0).getCauC());
        cauD.setText(dsCauhoi.get(0).getCauD());

        cautlA=findViewById(R.id.cautlA);
        dscauhoi.add(cauA);
        cautlA=findViewById(R.id.cautlB);
        dscauhoi.add(cauB);
        cautlA=findViewById(R.id.cautlC);
        dscauhoi.add(cauC);
        cautlA=findViewById(R.id.cautlD);
        dscauhoi.add(cauD);

        dongHoDemNguoc=findViewById(R.id.dongHoDemNguoc);


        DongHoDemNguoc dh=new DongHoDemNguoc(dscauhoi);



        //tao dong ho dem nguoc
         dongHo= dh.dongHoDemNguoc(dongHoDemNguoc);
         dongHo.start();


        //xu li khi nguoi dung chon dap an

        for(int i=0;i< 4;i++)
        {
            dscauhoi.get(i).setOnClickListener(clickListener);
        }






    }
}