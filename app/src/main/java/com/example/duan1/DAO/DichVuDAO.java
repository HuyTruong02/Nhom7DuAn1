package com.example.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.duan1.Model.DichVu;
import com.example.duan1.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class DichVuDAO {
    private SQLiteDatabase db;

    public DichVuDAO(Context mContext) {
        DBHelper dbHelper = new DBHelper(mContext);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public long insertDichVu(DichVu obj) {
        ContentValues values = new ContentValues();
        values.put("tenDV", obj.getTenDV());
        values.put("giaDV", obj.getGiaDV());
        values.put("moTa", obj.getMota());
        return db.insert("DICHVU", null, values);
    }

    //delete by object
    public int deleteDichVu(DichVu obj) {
        String Id = String.valueOf(obj.getMaDv());
        return db.delete("DICHVU", "maDV=?", new String[]{Id});
    }

    //update
    public int updateDichVu(DichVu obj) {
        ContentValues values = new ContentValues();
        values.put("tenDV", obj.getTenDV());
        values.put("giaDV", obj.getGiaDV());
        values.put("moTa", obj.getMaDv());

        String Id = String.valueOf(obj.getMaDv());
        return db.update("DICHVU", values, "maDV=?", new String[]{Id});
    }

    //getAll
    public List<DichVu> getAll() {
        String sql = "SELECT * FROM DICHVU";
        return getData(sql);
    }

    //get user by id
    public DichVu getUserById(String Id) {
        String sql = "SELECT * FROM DICHVU WHERE maDV=?";
        List<DichVu> list = getData(sql, Id);
        if (list != null) {
            return list.get(0);
        }
        return null;
    }

    @SuppressLint("Range")
    public List<DichVu> getData(String sql, String... SelectArgs) {
        List<DichVu> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, SelectArgs);
        while (cursor.moveToNext()) {
            DichVu user = new DichVu();
            user.setMaDv(Integer.parseInt(cursor.getString(0)));
            user.setTenDV(cursor.getString(1));
            user.setGiaDV(Integer.parseInt(cursor.getString(2)));
            user.setMota(cursor.getString(3));
            list.add(user);
        }
        if (list != null || list.size() != 0) {
            return list;
        }
        return null;
    }

//    @SuppressLint("Range")
//    public int getTTDichVu(String sqlDichVu){
//        sqlDichVu= "select sum(giaDV) as doanhthudv from DICHVU where maDV";
//        List<Integer> list = new ArrayList<Integer>();
//        Cursor c = db.rawQuery(sqlDichVu,new String[]{sqlDichVu});
//        while (c.moveToNext()) {
//            try {
//                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhthu"))));
//            } catch (Exception e) {
//                list.add(0);
//            }
//        }
//        return list.get(0);
//    }
}
