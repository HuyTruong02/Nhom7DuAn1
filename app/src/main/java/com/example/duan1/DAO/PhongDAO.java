package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Model.Phong;
import com.example.duan1.database.DBHelper;

import java.util.ArrayList;

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
    public ArrayList<Phong> getAll() {
        ArrayList<Phong> ds = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "Select * from Phong";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while (cs.isAfterLast() == false) {
            int maPhong = cs.getInt(0);
            int soPhong = cs.getInt(1);
            String moTa = cs.getString(2);
            ds.add(new Phong(maPhong,soPhong,moTa));
            ds.add(new Phong(102,204,"aoaoaooa"));
            ds.add(new Phong(103,206,"aoaoaooa"));
            ds.add(new Phong(104,207,"aoaoaooa"));
            ds.add(new Phong(105,205,"aoaoaooa"));

            cs.moveToNext();
        }
        cs.close();
        db.close();
        return ds;
    }
}
