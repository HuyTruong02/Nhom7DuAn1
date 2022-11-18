package com.example.duan1.Model;

public class DichVu {
    private int MaDv;
    private String TenDV;
    private int GiaDV;
    private String Mota;

    public DichVu() {

    }

    public DichVu(int maDv, String tenDV, int giaDV, String mota) {
        MaDv = maDv;
        TenDV = tenDV;
        GiaDV = giaDV;
        Mota = mota;
    }

    public int getMaDv() {
        return MaDv;
    }

    public void setMaDv(int maDv) {
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

    @Override
    public String toString() {
        return "DichVu{" +
                "MaDv=" + MaDv +
                ", TenDV='" + TenDV + '\'' +
                ", GiaDV=" + GiaDV +
                ", Mota='" + Mota + '\'' +
                '}';
    }
}
