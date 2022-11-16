package com.example.duan1.Model;

public class Phong {
    private int maPhong;
    private int soPhong;
    private String moTa ;

    public Phong() {
    }

    public Phong(int maPhong, int soPhong, String moTa) {
        this.maPhong = maPhong;
        this.soPhong = soPhong;
        this.moTa = moTa;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
