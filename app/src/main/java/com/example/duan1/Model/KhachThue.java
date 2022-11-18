package com.example.duan1.Model;

public class KhachThue {
    public int Id;
    public String hoTen;
    public int Sdt;
    public int Cccd;
    public int SoPhong;

    public KhachThue() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getSdt() {
        return Sdt;
    }

    public void setSdt(int sdt) {
        Sdt = sdt;
    }

    public int getCccd() {
        return Cccd;
    }

    public void setCccd(int cccd) {
        Cccd = cccd;
    }

    public int getSoPhong() {
        return SoPhong;
    }

    public void setSoPhong(int soPhong) {
        SoPhong = soPhong;
    }

    public KhachThue(int id, String hoTen, int sdt, int cccd, int soPhong) {
        Id = id;
        this.hoTen = hoTen;
        Sdt = sdt;
        Cccd = cccd;
        SoPhong = soPhong;
    }
}
