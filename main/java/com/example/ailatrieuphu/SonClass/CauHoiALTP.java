package com.example.ailatrieuphu.SonClass;

public class CauHoiALTP {

    public String getCauhoi() {
        return cauhoi;
    }

    public String getCauA() {
        return cauA;
    }

    public String getCauB() {
        return cauB;
    }

    public String getCauC() {
        return cauC;
    }

    public String getCauD() {
        return cauD;
    }

    public String getCautlDung() {
        return CautlDung;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public void setCauA(String cauA) {
        this.cauA = cauA;
    }

    public void setCauB(String cauB) {
        this.cauB = cauB;
    }

    public void setCauC(String cauC) {
        this.cauC = cauC;
    }

    public void setCauD(String cauD) {
        this.cauD = cauD;
    }

    public void setCautlDung(String cautlDung) {
        CautlDung = cautlDung;
    }

    private String cauhoi;
    private String cauA;
    private String cauB;
    private String cauC;
    private String cauD;
    private String CautlDung;

    public CauHoiALTP(String cauhoi, String cauA, String cauB, String cauC, String cauD,String cautlDung) {
        this.cauhoi = cauhoi;
        this.cauA = cauA;
        this.cauB = cauB;
        this.cauC = cauC;
        this.cauD = cauD;
        this.CautlDung=cautlDung;
    }

    public CauHoiALTP() {
    }
}
