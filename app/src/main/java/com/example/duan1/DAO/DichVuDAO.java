package com.example.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Model.DichVu;
import com.example.duan1.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class DichVuDAO {
    private SQLiteDatabase db;

    public DichVuDAO(Context mContext) {
        DbHelper dbHelper = new DbHelper(mContext);
        db= dbHelper.getWritableDatabase();
    }
   @SuppressLint("Range")
   public List<DichVu>  get(String sql, String...selectArgs){
    List<DichVu> listdv = new ArrayList<>();
    Cursor cursor = db.rawQuery(sql, selectArgs);
    while (cursor.moveToNext()){
        DichVu dichVu = new DichVu();
        dichVu.setMaDv(cursor.getString(cursor.getColumnIndex("MaDv")));
        dichVu.setTenDV(cursor.getString(cursor.getColumnIndex("TenDv")));
        dichVu.setMota(cursor.getString(cursor.getColumnIndex("MoTa")));
        dichVu.setGiaDV(cursor.getInt(cursor.getColumnIndex("GiaDv")));

        listdv.add(dichVu);

    }
    return listdv;
   }
   public List<DichVu> getAll(){
        String sql = "SELECT * FROM DICHVU";

        return get(sql);
   }

   public DichVu getById(String MaDv){
        String sql = "SELECT * FROM DICHVU WHERE MaDv = ?";
        List<DichVu> listdv = get(sql,MaDv);
        return listdv.get(0);
   }

   public  long insert (DichVu dichVu){
        ContentValues values = new ContentValues();
        values.put("MaDv",dichVu.getMaDv());
        values.put("TenDv",dichVu.getTenDV());
        values.put("GiaDv",dichVu.getGiaDV());
        values.put("MoTa",dichVu.getMota());

        return db.insert("DICHVU",null,values);
   }

    public  long update (DichVu dichVu){
        ContentValues values = new ContentValues();
        values.put("TenDv",dichVu.getTenDV());
        values.put("GiaDv",dichVu.getGiaDV());
        values.put("MoTa",dichVu.getMota());

        return db.update("DICHVU",values,"MaDv=?",new String[]{dichVu.getMaDv()});

    }
    public  long delete (String MaDv){
        return db.delete("DichVu","maDV=?",new String[]{MaDv});
    }



}
