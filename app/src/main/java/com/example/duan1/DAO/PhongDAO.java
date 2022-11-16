package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Model.Phong;
import com.example.duan1.database.DBHelper;

public class PhongDAO {
    DBHelper helper;

    public PhongDAO(Context context) {
        helper = new DBHelper(context);
    }
    public boolean insert(int soPhong, String moTa) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("soPhong", soPhong);
        values.put("moTa", moTa);
        long row = db.insert("PHONG", null, values);

        return (row > 0);
    }

    public boolean update(Phong phong) {
        //  String sql="update phong set sophong=?,mota=? where maPhong";
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soPhong", phong.getSoPhong());
        values.put("mota", phong.getMoTa());
        int row = db.update("phong", values, " maPhong=?", new String[]{phong.getMaPhong() + ""});
        return (row > 0);
    }

    public boolean delete(int maPhong) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int row = db.delete("phong", "maPhong=?", new String[]{maPhong + ""});
        return (row > 0);
    }
}
