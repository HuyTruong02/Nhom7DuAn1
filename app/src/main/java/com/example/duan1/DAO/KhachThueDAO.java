package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.duan1.Model.KhachThue;
import com.example.duan1.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class KhachThueDAO {
    private SQLiteDatabase db;

    public KhachThueDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(String hoTen,int sdt,int cccd,int sophong) {
        ContentValues values = new ContentValues();
        values.put("hoTen ",hoTen);
        values.put("Sdt ", sdt);
        values.put("Cccd ", cccd);
        values.put("SoPhong ", sophong);
        return db.insert("KhachThue", null, values);
    }

    public int update(KhachThue obj) {
        ContentValues values = new ContentValues();

        values.put("hoTen ", obj.hoTen);
        values.put("Sdt ", obj.Sdt);
        values.put("Cccd ", obj.Cccd);
        values.put("SoPhong ", obj.SoPhong);
        return db.update("KhachThue", values, "Id=?", new String[]{String.valueOf(obj.Id)});
    }

    public int delete(String id) {
        return db.delete("KhachThue", "Id=?", new String[]{id});
    }

    private List<KhachThue> getData(String sql, String... strings) {
        List<KhachThue> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, strings);
        while (c.moveToNext()) {
            KhachThue obj = new KhachThue();
            obj.Id = Integer.parseInt(c.getString(0));
            obj.hoTen = c.getString(1);
            obj.Sdt = Integer.parseInt(c.getString(2));
            obj.Cccd = Integer.parseInt(c.getString(3));
            obj.SoPhong = Integer.parseInt(c.getString(4));
            list.add(obj);
        }
        return list;
    }

    public List<KhachThue> getAll() {
        String sql = "SELECT * FROM KhachThue";
        return getData(sql);
    }

    public KhachThue getId(String id) {
        String sql = "SELECT * FROM KhachThue WHERE Id=?";
        List<KhachThue> list = getData(sql, id);
        return list.get(1);
    }
}