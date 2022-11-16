package com.example.duan1.Model;

import java.io.Serializable;

public class HoaDon implements Serializable {
    private int maHoaDon;
    private int soPhong;
    private String ngayBatDau;
    private String ngayHetHan;
    private int tienDien;
    private int tienNuoc;
    private int tienPhong;
    private int chiPhiKhac;
    private int tongTien;


    public HoaDon() {
    }

    public HoaDon(int maHoaDon, int soPhong, String ngayBatDau, String ngayHetHan, int tienDien, int tienNuoc, int tienPhong, int chiPhiKhac, int tongTien) {
        this.maHoaDon = maHoaDon;
        this.soPhong = soPhong;
        this.ngayBatDau = ngayBatDau;
        this.ngayHetHan = ngayHetHan;
        this.tienDien = tienDien;
        this.tienNuoc = tienNuoc;
        this.tienPhong = tienPhong;
        this.chiPhiKhac = chiPhiKhac;
        this.tongTien = tongTien;


    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(String ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public int getTienDien() {
        return tienDien;
    }

    public void setTienDien(int tienDien) {
        this.tienDien = tienDien;
    }

    public int getTienNuoc() {
        return tienNuoc;
    }

    public void setTienNuoc(int tienNuoc) {
        this.tienNuoc = tienNuoc;
    }

    public int getTienPhong() {
        return tienPhong;
    }

    public void setTienPhong(int tienPhong) {
        this.tienPhong = tienPhong;
    }

    public int getChiPhiKhac() {
        return chiPhiKhac;
    }

    public void setChiPhiKhac(int chiPhiKhac) {
        this.chiPhiKhac = chiPhiKhac;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
