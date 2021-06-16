package com.example.ailatrieuphu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ailatrieuphu.SonClass.CauHoiALTP;
import com.example.ailatrieuphu.SonClass.DongHoDemNguoc;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int idCauhoi=0;
    private  ArrayList<CauHoiALTP> dsCauhoi= new ArrayList<CauHoiALTP>();
    private ArrayList<CauHoiALTP> namCauDau,muoiCauCuoi;
    private ArrayList<Integer> dsCaudachon;

    private Button namnam,goidienthoai,hoiykienkhangia,tuvantaicho;

    private DatabaseReference databaseReference;

    private LinearLayout tienthuongcau1,tienthuongcau15,tienthuongcau2,tienthuongcau3,tienthuongcau4,tienthuongcau5,
            tienthuongcau6,tienthuongcau7,tienthuongcau8,tienthuongcau9,tienthuongcau10,tienthuongcau11,tienthuongcau12,tienthuongcau13,tienthuongcau14,cautlA,cautlB,cautlC,cautlD;

    private  TextView cauhoi,cauA,cauB,cauC,cauD,dongHoDemNguoc,tiencau1,tiencau15,tiencau2,tiencau3,tiencau4,tiencau5,tiencau6,tiencau7,tiencau8,tiencau9,tiencau10,tiencau11,tiencau12,tiencau13,tiencau14;
    private ArrayList<TextView> dstienthuongTextview;
    private  ArrayList<TextView> viewDScauhoi=new ArrayList<TextView>();
    private CountDownTimer dongHo;


    //xem nguoi choi tl dung hay sai
    private boolean co=true;

    //luu vi tri dap an dung
         int dapAnDung = -1;

    private View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {



            for(TextView i:viewDScauhoi)
            {
                if(i.getId()==v.getId()){

                    i.setBackgroundResource(R.drawable.ketquachon);
                    dongHo.cancel();
                    //set background cau tl dung

                    new CountDownTimer(1000,1000)
                    {

                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {

                            for (TextView j:viewDScauhoi)
                            {


                                //doi background cho cau tl dung

                                if(j.getHint().toString().equals(dsCauhoi.get(idCauhoi).getCautlDung()))
                                {
                                    dapAnDung =j.getId();
                                    Log.d("AAA","dieu kien dung");

                                    //doi background cho cau tl dung
                                    new CountDownTimer(900,300){

                                        @Override
                                        public void onTick(long millisUntilFinished) {
                                            if((millisUntilFinished/300)%2==1){
                                                j.setBackgroundResource(R.drawable.ketquachon);
                                            }else
                                            {
                                                j.setBackgroundResource(R.drawable.ketquadung);
                                            }
                                        }

                                        @Override
                                        public void onFinish() {

                                        }
                                    }.start();


                                    //xet cau vua chon co phai dap an dung hay khong

                                    if(j.getId()==v.getId())
                                    {
                                        new CountDownTimer(900, 900) {
                                            @Override
                                            public void onTick(long millisUntilFinished) {

                                            }

                                            @Override
                                            public void onFinish() {
                                                //set lai cau hoi tiep theo
                                                dsTienThuong.get(idCauhoi).setBackgroundResource(R.drawable.ketquachon);
                                                idCauhoi++;
                                                setDataCauHoi(idCauhoi);

                                                //doi lai trang thai ban dau cho cac textview
                                                setTrangThaiTextView();

                                            }
                                        }.start();



                                    }
                                    else
                                    {
                                        if(idCauhoi>=9)
                                        {
                                            Intent intent=new Intent(MainActivity.this,FinishActivity.class);
                                            intent.putExtra("tienthuong",dstienthuongTextview.get(9).getText().toString());
                                            startActivity(intent);
                                        }
                                        else if(idCauhoi>=4)
                                        {
                                            Intent intent=new Intent(MainActivity.this,FinishActivity.class);
                                            intent.putExtra("tienthuong",dstienthuongTextview.get(4).getText().toString());
                                            startActivity(intent);
                                        }

                                        Intent intent=new Intent(MainActivity.this,FinishActivity.class);
                                        intent.putExtra("tienthuong","0");
                                        startActivity(intent);


                                    }

                                    break;

                                }







                            }


                        }
                    }.start();





                }else {
                   i.setEnabled(false);

                }
            }




            Log.d("AAA",v.getId()+"");
            Log.d("AAA", dapAnDung +"");


        }
    };

    private ArrayList<LinearLayout> dsTienThuong=new ArrayList<LinearLayout>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Log.d("AAA","Main onCreate");

        //tham chieu cac doi tuong tronglayout
        unit();

        getListquetion();
        setTrogiup();

        //add cau hoi vao danh sach
//        dsCauhoi.add(new CauHoiALTP("Theo một câu hát thì: \"Ba thương con vì con giống mẹ.Mẹ thương con vì con giống ...\" ai?","Ông hàng xóm","Chú cạnh nhà","Ba","Bác đầu ngõ","D"));
//        dsCauhoi.add(new CauHoiALTP("Giải Grand Slam đầu tiên trong năm là giải nào","Austrlia mở rộng"," Wimbledon","Roland Garos","Mỹ mở rộng","A"));
//        dsCauhoi.add(new CauHoiALTP("Trong môn quần vợt, giành được Grand Slam nghĩa là trong 1 năm đoạt chức vô địch cả 4 giải sau:","Austrlia mở rộng"," Wimbledon","Roland Garos","Mỹ mở rộng","B"));


    }




    //set cau hoi theo cau
    private void setDataCauHoi(int i){


        if(i<dsCauhoi.size())
        {
            cauhoi.setText(" \n   Câu "+(i+1)+": "+dsCauhoi.get(i).getCauhoi().toString());
            cauA.setText("\n   A: "+dsCauhoi.get(i).getCauA());
            cauB.setText("\n   B: "+dsCauhoi.get(i).getCauB());
            cauC.setText("\n   C: "+dsCauhoi.get(i).getCauC());
            cauD.setText("\n   D: "+dsCauhoi.get(i).getCauD());
            DongHoDemNguoc dh=new DongHoDemNguoc(viewDScauhoi);
            //tao dong ho dem nguoc


         dongHo=  new CountDownTimer(30000, 1000) {
                @Override
                public void onTick(long l) {
                    String sDuration=(l)/1000+"";
                    dongHoDemNguoc.setText(sDuration);

//                    if(l==0)
//                    {
//                        flag=false;
//                    }
                }

                @Override
                public void onFinish() {

                    if(i>=9)
                    {
                        Intent intent=new Intent(MainActivity.this,FinishActivity.class);
                        intent.putExtra("tienthuong",dstienthuongTextview.get(9).getText().toString());
                        startActivity(intent);
                    }
                    else if(i>=4)
                    {
                        Intent intent=new Intent(MainActivity.this,FinishActivity.class);
                        intent.putExtra("tienthuong",dstienthuongTextview.get(4).getText().toString());
                        startActivity(intent);
                    }

                    Intent intent=new Intent(MainActivity.this,FinishActivity.class);
                    intent.putExtra("tienthuong","0");
                    startActivity(intent);




                }
            };
            dongHo.start();
        }
        else
        {
            Intent intent=new Intent(MainActivity.this,FinishActivity.class);
            intent.putExtra("tienthuong",dstienthuongTextview.get(14).getText().toString());
            startActivity(intent);
        }
    }

    //set trang thai text view

    private void setTrangThaiTextView()
    {
        for(TextView i:viewDScauhoi)
        {
            i.setBackgroundResource(R.drawable.textviewlayout);
            i.setEnabled(true);
        }
    }

    // lay danh sach cau hoi tu Firebase

    private void  getListquetion()
    {

        Random random=new Random();
        namCauDau=new ArrayList<CauHoiALTP>();
        muoiCauCuoi=new ArrayList<CauHoiALTP>();
        dsCaudachon=new ArrayList<Integer>();

        databaseReference=FirebaseDatabase.getInstance().getReference();
//
        databaseReference.child("Namcaudau").push().setValue(new CauHoiALTP("Đâu là một loại hình chợ tạm tự phát thường xuất hiện trong các khu dân cư?","Ếch","Cóc","Thằn lằn","Nhái","B"));
//        databaseReference.child("Namcaudau").push().setValue(new CauHoiALTP("Đâu là tên một bãi biển ở Quảng Bình?","Đá Lăn","Đá Nhảy"," Đá Chạy","Đá Bò","B"));
//        databaseReference.child("Namcaudau").push().setValue(new CauHoiALTP("Theo một câu hát thì: \"Ba thương con vì con giống mẹ.Mẹ thương con vì con giống ...\" ai?","Ông hàng xóm","Chú cạnh nhà","Ba","Bác đầu ngõ","D"));
//        databaseReference.child("Namcaudau").push().setValue(new CauHoiALTP("Giải Grand Slam đầu tiên trong năm là giải nào","Austrlia mở rộng"," Wimbledon","Roland Garos","Mỹ mở rộng","A"));
//        databaseReference.child("Namcaudau").push().setValue(new CauHoiALTP("Có một tàu điện đi về hướng nam. Gió hướng tây bắc. Vậy khói từ con tàu sẽ theo hướng nào?","Đông","Tây","Bắc","Không hướng nào","D"));

        databaseReference.child("Namcaudau").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                CauHoiALTP altp= snapshot.getValue(CauHoiALTP.class);
                namCauDau.add(altp);

                if(namCauDau.size()>5 && dsCauhoi.size()<5)
                {
                    int cau=1;
                    boolean flag=true;
                    int ran= random.nextInt(namCauDau.size()-1);
                    dsCaudachon.add(ran);
                    dsCauhoi.add(namCauDau.get(ran));


                    while (cau<5)
                    {
                        flag=true;
                         ran= random.nextInt(namCauDau.size()-1);
                        for (int i=0;i<dsCaudachon.size();i++)
                        {
                            if(ran==dsCaudachon.get(i))
                            {
                                flag=false;
                                break;
                            }
                        }

                        if(flag==true)
                        {
                            dsCauhoi.add(namCauDau.get(ran));
                            dsCaudachon.add(ran);
                            cau++;
                        }

                        Log.d("BBB","ccc");

                    }

                    setDataCauHoi(0);
                }



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        dsCaudachon=new ArrayList<Integer>();
        databaseReference.child("Muoicaucuoi").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                CauHoiALTP altp= snapshot.getValue(CauHoiALTP.class);
                muoiCauCuoi.add(altp);

                if(muoiCauCuoi.size()>5 && dsCauhoi.size()<15)
                {
                    int cau=1;
                    boolean flag=true;
                    int ran= random.nextInt(muoiCauCuoi.size()-1);
                    dsCaudachon.add(ran);
                    dsCauhoi.add(muoiCauCuoi.get(ran));


                    while (cau<5)
                    {
                        flag=true;
                        ran= random.nextInt(muoiCauCuoi.size()-1);
                        for (int i=0;i<dsCaudachon.size();i++)
                        {
                            if(ran==dsCaudachon.get(i))
                            {
                                flag=false;
                                break;
                            }
                        }

                        if(flag==true)
                        {
                            dsCauhoi.add(namCauDau.get(ran));
                            dsCaudachon.add(ran);
                            cau++;
                        }

                        Log.d("BBB","ccc");

                    }

                    setDataCauHoi(0);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    //tham chieu cac doi tuong trong layout
    private void unit(){
        dstienthuongTextview=new ArrayList<TextView>();

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
        cauhoi=findViewById(R.id.tvCauhoi);
        cauA=findViewById(R.id.cauA);
        cauB=findViewById(R.id.cauB);
        cauC=findViewById(R.id.cauC);
        cauD=findViewById(R.id.cauD);
        cautlA=findViewById(R.id.cautlA);
        cautlA=findViewById(R.id.cautlB);
        cautlA=findViewById(R.id.cautlC);
        cautlA=findViewById(R.id.cautlD);
        dongHoDemNguoc=findViewById(R.id.dongHoDemNguoc);

        tiencau1=findViewById(R.id.tv1);
        dstienthuongTextview.add(tiencau1);
        tiencau2=findViewById(R.id.tv2);
        dstienthuongTextview.add(tiencau2);
        tiencau3=findViewById(R.id.tv3);
        dstienthuongTextview.add(tiencau3);
        tiencau4=findViewById(R.id.tv4);
        dstienthuongTextview.add(tiencau4);
        tiencau5=findViewById(R.id.tv5);
        dstienthuongTextview.add(tiencau5);
        tiencau6=findViewById(R.id.tv6);
        dstienthuongTextview.add(tiencau6);

        tiencau7=findViewById(R.id.tv7);
        dstienthuongTextview.add(tiencau7);
        tiencau8=findViewById(R.id.tv8);
        dstienthuongTextview.add(tiencau8);
        tiencau9=findViewById(R.id.tv9);
        dstienthuongTextview.add(tiencau9);

        tiencau10=findViewById(R.id.tv10);
        dstienthuongTextview.add(tiencau10);
        tiencau11=findViewById(R.id.tv11);
        dstienthuongTextview.add(tiencau11);
        tiencau12=findViewById(R.id.tv12);
        dstienthuongTextview.add(tiencau12);
        tiencau13=findViewById(R.id.tv13);
        dstienthuongTextview.add(tiencau13);
        tiencau14=findViewById(R.id.tv14);
        dstienthuongTextview.add(tiencau14);
        tiencau15=findViewById(R.id.tv15);
        dstienthuongTextview.add(tiencau15);
        namnam=findViewById(R.id.trogiupnamnam);
        goidienthoai=findViewById(R.id.trogiupgoidienthoai);
        tuvantaicho=findViewById(R.id.trogiuptuvantaicho);
        hoiykienkhangia=findViewById(R.id.trogiuphoiykien);






        viewDScauhoi.add(cauA);

        viewDScauhoi.add(cauB);

        viewDScauhoi.add(cauC);

        viewDScauhoi.add(cauD);
    }


    //xu li su kien dung su tro giup

    private void setTrogiup()
    {

        Random rand=new Random();
            namnam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    namnam.setEnabled(false);
                    if(dsCauhoi.get(idCauhoi).getCautlDung().toString().equals("A"))
                    {
                        Log.d("VCL","A");
                        int i= rand.nextInt(3)+1;

                        if(i==1)
                        {
                            viewDScauhoi.get(2).setText("");
                            viewDScauhoi.get(3).setText("");
                            viewDScauhoi.get(2).setEnabled(false);
                            viewDScauhoi.get(3).setEnabled(false);
                        } else  if(i==2)
                        {
                            viewDScauhoi.get(1).setText("");
                            viewDScauhoi.get(3).setText("");
                            viewDScauhoi.get(1).setEnabled(false);
                            viewDScauhoi.get(3).setEnabled(false);
                        } else
                        {
                            viewDScauhoi.get(2).setText("");
                            viewDScauhoi.get(1).setText("");
                            viewDScauhoi.get(2).setEnabled(false);
                            viewDScauhoi.get(1).setEnabled(false);
                        }
                    } else
                        if(dsCauhoi.get(idCauhoi).getCautlDung().toString().equals("B"))
                    {
                        int i= rand.nextInt(3)+1;
                        Log.d("VCL","B");

                        if(i==1)
                        {
                            viewDScauhoi.get(2).setText("");
                            viewDScauhoi.get(3).setText("");
                            viewDScauhoi.get(2).setEnabled(false);
                            viewDScauhoi.get(3).setEnabled(false);
                        } else  if(i==2)
                        {
                            viewDScauhoi.get(0).setText("");
                            viewDScauhoi.get(3).setText("");
                            viewDScauhoi.get(0).setEnabled(false);
                            viewDScauhoi.get(3).setEnabled(false);
                        } else
                        {
                            viewDScauhoi.get(2).setText("");
                            viewDScauhoi.get(0).setText("");
                            viewDScauhoi.get(2).setEnabled(false);
                            viewDScauhoi.get(0).setEnabled(false);
                        }
                    } else
                        if(dsCauhoi.get(idCauhoi).getCautlDung().toString().equals("C"))
                    {
                        int i= rand.nextInt(3)+1;
                        Log.d("VCL","C");

                        if(i==1)
                        {
                            viewDScauhoi.get(1).setText("");
                            viewDScauhoi.get(3).setText("");
                            viewDScauhoi.get(1).setEnabled(false);
                            viewDScauhoi.get(3).setEnabled(false);
                        } else  if(i==2)
                        {
                            viewDScauhoi.get(0).setText("");
                            viewDScauhoi.get(3).setText("");
                            viewDScauhoi.get(0).setEnabled(false);
                            viewDScauhoi.get(3).setEnabled(false);
                        } else
                        {
                            viewDScauhoi.get(0).setText("");
                            viewDScauhoi.get(1).setText("");
                            viewDScauhoi.get(0).setEnabled(false);
                            viewDScauhoi.get(1).setEnabled(false);
                        }
                    } else if(dsCauhoi.get(idCauhoi).getCautlDung().toString().equals("D"))
                        {
                            Log.d("VCL","D");
                            int i= rand.nextInt(3)+1;

                            if(i==1)
                            {
                                viewDScauhoi.get(2).setText("");
                                viewDScauhoi.get(1).setText("");
                                viewDScauhoi.get(2).setEnabled(false);
                                viewDScauhoi.get(1).setEnabled(false);
                            } else  if(i==2)
                            {
                                viewDScauhoi.get(0).setText("");
                                viewDScauhoi.get(2).setText("");
                                viewDScauhoi.get(0).setEnabled(false);
                                viewDScauhoi.get(2).setEnabled(false);
                            } else
                            {
                                viewDScauhoi.get(0).setText("");
                                viewDScauhoi.get(1).setText("");
                                viewDScauhoi.get(1).setEnabled(false);
                                viewDScauhoi.get(1).setEnabled(false);
                            }
                        }
                        else
                        {
                            Log.d("VCL","Vcl");
                        }
                }
            });
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d("AAA","Main onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("AAA","Main onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AAA","Main onResume");

        //         bat dau cho nguoi dung choi


//            set data cau hoi


            for(int j=0;j< 4;j++)
            {
                viewDScauhoi.get(j).setOnClickListener(clickListener);
            }

            //xu li khi nguoi dung chon dap an

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AAA","Main on Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("AAA","Main  onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AAA","Main onDestroy");
    }
}