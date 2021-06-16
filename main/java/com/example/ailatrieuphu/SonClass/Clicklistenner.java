package com.example.ailatrieuphu.SonClass;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Clicklistenner {

    public Clicklistenner() {
    }

    public void ChonTroGiup5050(CauHoiALTP cb, ArrayList<TextView> dsch){

        //tao ds chua cac text view co cau tl sai
        ArrayList<TextView> dsAo=new ArrayList<TextView>();

        if(cb.getCauA().equals(cb.getCautlDung()))
        {
            dsAo.add(dsch.get(0));
        }
        if(cb.getCauB().equals(cb.getCautlDung()))
        {
            dsAo.add(dsch.get(1));
        }
        if(cb.getCauC().equals(cb.getCautlDung()))
        {
            dsAo.add(dsch.get(2));
        }
        if(cb.getCauD().equals(cb.getCautlDung()))
        {
            dsAo.add(dsch.get(3));
        }

        //tao ra so ngau nhien de xoa xau tl sai

        Random rand=new Random();
        int soAo=rand.nextInt(3)+1;

        //chon xoa 2 cau tl bat ky trong 3 cau sai
        if(soAo%3==0)
        {
            dsAo.remove(2);
        }else  if(soAo%2==0)
        {
            dsAo.remove(1);
        }else
        {
            dsAo.remove(0);
        }

        for (TextView abc : dsAo) {
            for (TextView hienthi : dsch) {
                if(abc.getText().equals(hienthi)){
                    hienthi.setText("");
                    hienthi.setEnabled(true);
                }
            }
        }
    }
}
