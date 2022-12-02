package com.example.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Model.HoaDon;
import com.example.duan1.Model.Phong;
import com.example.duan1.database.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private SQLiteDatabase db;


    private List<Phong> listPhong;
    public HoaDonDAO(Context mContext) {
        DBHelper dbHelper = new DBHelper(mContext);
        db= dbHelper.getWritableDatabase();
    }

    //insert
    public long insertHoaDon(HoaDon obj){
        ContentValues values = new ContentValues();
        values.put("soPhong",obj.getSoPhong());
        values.put("ngayBatDau", String.valueOf(obj.getNgayBatDau()));
        values.put("ngayHetHan",String.valueOf(obj.getNgayHetHan()));
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
//    public HoaDon getUserById(String Id){
//        String sql="SELECT * FROM HoaDon WHERE maHoaDon=?";
//        List<HoaDon> list = getData(sql,Id);
//        if(list!=null){
//            return list.get(0);
//        }
//        return null;
//    }





//    @SuppressLint("Range")
//    public List<HoaDon>getData(String sql, String...SelectArgs){
//        List<HoaDon> list= new ArrayList<>();
//        Cursor cursor= db.rawQuery(sql,SelectArgs);
//        while (cursor.moveToNext()){
//            HoaDon user= new HoaDon();
//            user.setMaHoaDon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maHoaDon"))));
//            user.setSoPhong(cursor.getInt(1));
//            user.setNgayBatDau(cursor.getString(cursor.getColumnIndex("ngayBatDau")));
//            user.setNgayHetHan(cursor.getString(cursor.getColumnIndex("ngayHetHan")));
//            user.setTienDien(Integer.parseInt(String.valueOf(cursor.getColumnIndex("tienDien"))));
//            user.setTienNuoc(Integer.parseInt(String.valueOf(cursor.getColumnIndex("tienNuoc"))));
//            user.setTienPhong(Integer.parseInt(String.valueOf(cursor.getColumnIndex("tienPhong"))));
//            user.setChiPhiKhac(Integer.parseInt(String.valueOf(cursor.getColumnIndex("chiPhiKhac"))));
//            user.setTongTien(Integer.parseInt(String.valueOf(cursor.getColumnIndex("tongTien"))));
//            list.add(user);
//        }
//        if(list!=null||list.size()!=0){
//            return list;
//        }
//        return null;
//
//    }
    public ArrayList<HoaDon>getData(String sql){
        ArrayList<HoaDon> list = new ArrayList<>();
         sql ="SELECT * FROM HoaDon";
        Cursor cursor =db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHoaDon(cursor.getInt(0));
            hoaDon.setSoPhong(cursor.getInt(1));
            hoaDon.setNgayBatDau(cursor.getString(2));
            hoaDon.setNgayHetHan(cursor.getString(3));
            hoaDon.setTienDien(Integer.parseInt(cursor.getString(4)));
            hoaDon.setTienNuoc(Integer.parseInt(cursor.getString(5)));
            hoaDon.setTienPhong(Integer.parseInt(cursor.getString(6)));
            hoaDon.setChiPhiKhac(Integer.parseInt(cursor.getString(7)));
            hoaDon.setTongTien(Integer.parseInt(cursor.getString(8)));
            list.add(hoaDon);
            cursor.moveToNext();
        }

        return list;
    }
    @SuppressLint("Range")
    public int getDanhThu(String ngayBatDau, String ngayHetHan) {
        String sqlDoanhThu = "SELECT SUM(tongtien) as doanhthu FROM HoaDon WHERE ngayBatDau BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor c = db.rawQuery(sqlDoanhThu, new String[]{ngayBatDau, ngayHetHan});
        while (c.moveToNext()) {
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhthu"))));
            } catch (Exception e) {
                list.add(0);
            }
        }
        return list.get(0);
    }
}
