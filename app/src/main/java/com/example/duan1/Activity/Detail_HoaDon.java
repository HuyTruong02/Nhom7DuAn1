package com.example.duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.duan1.DAO.HoaDonDAO;
import com.example.duan1.DAO.PhongDAO;
import com.example.duan1.MainActivity;
import com.example.duan1.Model.HoaDon;
import com.example.duan1.Model.Phong;
import com.example.duan1.R;
import com.example.duan1.adapter.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Detail_HoaDon extends AppCompatActivity {
    HoaDon hoaDon;
    private ArrayList<HoaDon> list = new ArrayList<>();
    PhongDAO phongDAO;
    Context context;

    EditText ed_phong,ed_ngaybd,ed_ngaykt,edtienphong,edtiennuoc,edtiendien,edchiphikhac,edtongtien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial_hoa_don);

        ed_ngaybd = findViewById(R.id.ed_batDau_HDon_dt);
        edtongtien = findViewById(R.id.ed_TongTien_HDon_dt);
        edchiphikhac = findViewById(R.id.ed_ChiPhiKhac_HDon_dt);
        ed_phong = findViewById(R.id.ed_soPhong_HDon_dt);
        ed_ngaykt = findViewById(R.id.ed_ketThuc_HDon_dt);
        edtiendien = findViewById(R.id.ed_TienDien_HDon_dt);
        edtiennuoc = findViewById(R.id.ed_TienNuoc_HDon_dt);
        edtienphong = findViewById(R.id.ed_TienPhong_HDon_dt);
        Phong phong = new Phong();
        phongDAO = new PhongDAO(context);
        phongDAO.open();
        ArrayList<Phong> listt= phongDAO.getAll();

        ed_phong.setText(hoaDon.getSoPhong()+"");

        HoaDon hoaDon1 = new HoaDon();
        ed_ngaybd.setText(hoaDon1.getNgayBatDau());
        ed_ngaykt.setText(hoaDon1.getNgayHetHan());
        edtiendien.setText(hoaDon1.getTienDien()+"");


        edtiennuoc.setText(hoaDon1.getTienNuoc()+"");
        edtienphong.setText(hoaDon1.getTienPhong()+"");
        edchiphikhac.setText(hoaDon1.getChiPhiKhac()+"");
        edtongtien.setText(hoaDon1.getTongTien()+"");


        Button btnquaylai = findViewById(R.id.btn_quayLai_detail);
        btnquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detail_HoaDon.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}