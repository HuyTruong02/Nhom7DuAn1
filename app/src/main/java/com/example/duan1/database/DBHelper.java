package com.example.duan1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "QLPT";
    private static final int DB_VERSION = 4;
    //---------------------------------
    //duyettao
    static final String CREATE_TABLE_PHONG =
            "create table Phong (maPhong integer primary key autoincrement,"+
                    "soPhong INTERGER, moTa TEXT,hinhanh INTERGER)";

    //---------------------------------
    static final String createTableKhachThue = " create table KhachThue (" +
            "Id INTEGER PRIMARY KEY autoincrement," +
            "hoTen TEXT NOT NULL," +
            "Sdt INTERGER NOT NULL," +
            "Cccd INTERGER NOT NULL," +
            "SoPhong INTERGER  NOT NULL)";
    //---------------------------------
    static final String CREATE_TABLE_DICH_VU =
            "create table DichVu (MaDV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "TenDV TEXT NOT NULL, " +
                    "GiaDV INTEGER NOT NULL, " +
                    "MoTa TEXT NOT NULL)";
    //---------------------------------
    static final String CREATE_TABLE_HOP_DONG =
            "create table HopDong (maHopDong INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "giaThue INTEGER NOT NULL, " +
                    "tienCoc INTEGER NOT NULL," +
                    "ngayBatDau TEXT NOT NULL, " +
                    "ngayHetHan TEXT NOT NULL," +
                    "maPhong INTEGER REFERENCES Phong(maPhong), " +
                    "maKhach INTEGER REFERENCES KhachThue(maKhach), " +
                    "maDV INTEGER REFERENCES DichVu(maDV))";
    //---------------------------------
    static final String CREATE_TABLE_HOA_DON =
            "create table HoaDon (maHoaDon INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "soPhong INTEGER NOT NULL, " +
                    "ngayBatDau TEXT NOT NULL, " +
                    "ngayHetHan TEXT NOT NULL," +
                    "tienDien TEXT NOT NULL, " +
                    "tienNuoc TEXT NOT NULL," +
                    "tienPhong TEXT NOT NULL, " +
                    "chiPhiKhac TEXT NOT NULL, " +
                    "tongtien TEXT NOT NULL," +
                    "maPhong INTEGER REFERENCES Phong(maPhong)," +
                    "maDV INTEGER REFERENCES DichVu(maDV)," +
                    "maHopDong INTEGER REFERENCES HopDong(maHopDong))";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao bang Phong
        db.execSQL(CREATE_TABLE_PHONG);
        //Tao bang Khach Thue
        db.execSQL(createTableKhachThue);
        //Tao bang Dich Vu
        db.execSQL(CREATE_TABLE_DICH_VU);
        //Tao bang HopDong
        db.execSQL(CREATE_TABLE_HOP_DONG);
        //Tao bang Hoa Don
        db.execSQL(CREATE_TABLE_HOA_DON);
        //add du lieu vao bang
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTablePhong = "drop table if exists Phong";
        db.execSQL(dropTablePhong);
        String dropTableKhachThue = "drop table if exists KhachThue";
        db.execSQL(dropTableKhachThue);
        String dropTableDichVu = "drop table if exists DichVu";
        db.execSQL(dropTableDichVu);
        String dropTableHopDong = "drop table if exists HopDong";
        db.execSQL(dropTableHopDong);
        String dropTableHoaDon = "drop table if exists HoaDon";
        db.execSQL(dropTableHoaDon);
        onCreate(db);
    }
}
