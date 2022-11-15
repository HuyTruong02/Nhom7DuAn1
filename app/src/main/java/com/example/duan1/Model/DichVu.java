package com.example.duan1.Model;

public class DichVu {
private  String MaDv;
private String TenDV;
private int GiaDV;
private String Mota;

    public DichVu() {

    }

    public DichVu(String maDv, String tenDV, int giaDV, String mota) {
        MaDv = maDv;
        TenDV = tenDV;
        GiaDV = giaDV;
        Mota = mota;
    }

    public String getMaDv() {
        return MaDv;
    }

    public void setMaDv(String maDv) {
        MaDv = maDv;
    }

    public String getTenDV() {
        return TenDV;
    }

    public void setTenDV(String tenDV) {
        TenDV = tenDV;
    }

    public int getGiaDV() {
        return GiaDV;
    }

    public void setGiaDV(int giaDV) {
        GiaDV = giaDV;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }


}
