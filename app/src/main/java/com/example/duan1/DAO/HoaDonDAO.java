package com.example.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Model.HoaDon;
import com.example.duan1.database.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public HoaDonDAO(Context mContext) {
        DBHelper dbHelper = new DBHelper(mContext);
        db= dbHelper.getWritableDatabase();
    }
    //insert
    public long insertHoaDon(HoaDon obj){
        ContentValues values = new ContentValues();
        values.put("soPhong",obj.getSoPhong());
        values.put("ngayBatDau", sdf.format(obj.getNgayBatDau()));
        values.put("ngayHetHan",sdf.format(obj.getNgayHetHan()));
        values.put("tienDien",obj.getTienDien());
        values.put("tienNuoc",obj.getTienNuoc());
        values.put("tienPhong",obj.getTienPhong());
        values.put("chiPhiKhac",obj.getChiPhiKhac());
        values.put("tongTien",obj.getTongTien());
        return db.insert("HoaDon",null,values);
    }

    //delete by object
    public int deleteHoaDon(HoaDon obj){
        String Id = String.valueOf(obj.getMaHoaDon());
        return db.delete("HoaDon","maHoaDon=?",new String[]{Id});
    }
    //update
    public int updateHoaDon(HoaDon obj){
        ContentValues values = new ContentValues();
        values.put("soPhong",obj.getSoPhong());
        values.put("ngayBatDau", String.valueOf(obj.getNgayBatDau()));
        values.put("ngayBatDau", String.valueOf(obj.getNgayBatDau()));
        values.put("ngayHetHan",String.valueOf(obj.getNgayHetHan()));
        values.put("tienDien",obj.getTienDien());
        values.put("tienNuoc",obj.getTienNuoc());
        values.put("tienPhong",obj.getTienPhong());
        values.put("chiPhiKhac",obj.getChiPhiKhac());
        values.put("tongTien",obj.getTongTien());
        String Id = String.valueOf(obj.getMaHoaDon());
        return db.update("HoaDon",values,"maHoaDon=?",new String[]{Id});
    }
    //getAll
    public List<HoaDon> getAll(){
        String sql="SELECT * FROM HoaDon";
        return getData(sql);
    }
    //get user by id
    public HoaDon getUserById(String Id){
        String sql="SELECT * FROM HoaDon WHERE maHoaDon=?";
        List<HoaDon> list = getData(sql,Id);
        if(list!=null){
            return list.get(0);
        }
        return null;
    }
    @SuppressLint("Range")
    public List<HoaDon>getData(String sql, String...SelectArgs){
        List<HoaDon> list= new ArrayList<>();
        Cursor cursor= db.rawQuery(sql,SelectArgs);
        while (cursor.moveToNext()){
            HoaDon user= new HoaDon();
            user.setMaHoaDon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maHoaDon"))));
            user.setSoPhong(Integer.parseInt(String.valueOf(cursor.getColumnIndex("soPhong"))));
            user.setNgayBatDau(cursor.getString(cursor.getColumnIndex("ngayBatDau")));
            user.setNgayHetHan(cursor.getString(cursor.getColumnIndex("ngayHetHan")));
            user.setTienDien(Integer.parseInt(String.valueOf(cursor.getColumnIndex("tienDien"))));
            user.setTienNuoc(Integer.parseInt(String.valueOf(cursor.getColumnIndex("tienNuoc"))));
            user.setTienPhong(Integer.parseInt(String.valueOf(cursor.getColumnIndex("tienPhong"))));
            user.setChiPhiKhac(Integer.parseInt(String.valueOf(cursor.getColumnIndex("chiPhiKhac"))));
            user.setTongTien(Integer.parseInt(String.valueOf(cursor.getColumnIndex("tongTien"))));
            list.add(user);
        }
        if(list!=null||list.size()!=0){
            return list;
        }
        return null;
    }
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhthu="SELECT SUM(tongTien) AS DOANHTHU FROM HoaDon WHERE ngayBatDau BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor c=db.rawQuery(sqlDoanhthu,new String[]{tuNgay,denNgay});
        while (c.moveToNext()){
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("DOANHTHU"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }

}
