package com.example.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Model.DichVu;
import com.example.duan1.Sqlite.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class DichVuDAO {
    private SQLiteDatabase db;

    public DichVuDAO(Context mContext) {
        DBHelper dbHelper = new DBHelper(mContext);
        db= dbHelper.getWritableDatabase();
    }
    //insert
    public long insertDichVu(DichVu obj){
        ContentValues values = new ContentValues();
        values.put("tenDV",obj.getTenDV());
        values.put("giaDV",obj.getGiaDV());
        values.put("moTa",obj.getMota());
        return db.insert("DichVu",null,values);
    }

    //delete by object
    public int deleteDichVu(DichVu obj){
        String Id = String.valueOf(obj.getMaDv());
        return db.delete("DichVu","maDV=?",new String[]{Id});
    }

    //update
    public int updateDichVu(DichVu obj){
        ContentValues values = new ContentValues();
        values.put("tenDV",obj.getTenDV());
        values.put("giaDV",obj.getGiaDV());
        values.put("moTa",obj.getMota());
        String Id = String.valueOf(obj.getMaDv());
        return db.update("DichVu",values,"maDV=?",new String[]{Id});
    }
    //getAll
    public List<DichVu> getAll(){
        String sql="SELECT * FROM DichVu";
        return getData(sql);
    }
    //get user by id
    public DichVu getUserById(String Id){
        String sql="SELECT * FROM DichVu WHERE maDV=?";
        List<DichVu> list = getData(sql,Id);
        if(list!=null){
            return list.get(0);
        }
        return null;
    }
    @SuppressLint("Range")
    public List<DichVu>getData(String sql, String...SelectArgs){
        List<DichVu> list= new ArrayList<>();
        Cursor cursor= db.rawQuery(sql,SelectArgs);
        while (cursor.moveToNext()){
            DichVu user= new DichVu();
            user.setMaDv(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maDV"))));
            user.setGiaDV(Integer.parseInt(cursor.getString(cursor.getColumnIndex("GiaDV"))));
            user.setTenDV(cursor.getString(cursor.getColumnIndex("tenDV")));
            user.setMota(cursor.getString(cursor.getColumnIndex("moTa")));
            list.add(user);
        }
        if(list!=null||list.size()!=0){
            return list;
        }
        return null;
    }
}
