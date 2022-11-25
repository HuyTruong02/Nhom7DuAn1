package com.example.duan1.Model;

import android.widget.ArrayAdapter;
import android.widget.PopupMenu;

import java.util.ArrayList;

public class Phong {
    private ArrayList<Phong> phongArraylist = new ArrayList<>();

    private int maPhong;
    private int soPhong;
    private String moTa ;
    private int hinhanh;

    public Phong() {

    }

    public Phong(int maPhong, int soPhong, String moTa, int hinhanh) {
        this.maPhong = maPhong;
        this.soPhong = soPhong;
        this.moTa = moTa;
        this.hinhanh = hinhanh;
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

    public int getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(int hinhanh) {
        this.hinhanh = hinhanh;
    }

    public ArrayList<Phong> getPhongArraylist() {

        return phongArraylist;
    }
}
