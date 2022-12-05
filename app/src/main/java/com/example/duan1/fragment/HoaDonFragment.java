package com.example.duan1.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1.Activity.interfaceDeleteClickdistioner;
import com.example.duan1.DAO.HoaDonDAO;
import com.example.duan1.DAO.PhongDAO;
import com.example.duan1.Model.HoaDon;
import com.example.duan1.Model.Phong;
import com.example.duan1.Model.ResetData;
import com.example.duan1.R;
import com.example.duan1.adapter.HoaDonAdapter;
import com.example.duan1.adapter.SpinnerAdapter;
import com.example.duan1.database.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class HoaDonFragment extends Fragment  implements interfaceDeleteClickdistioner, ResetData {
    FloatingActionButton fab;
    Button btn_them_HD,btn_huy_HD;
    ImageView image_ngaybatdau,image_ngayketthuc;
    EditText edt_batdau_hd;
    EditText edt_hethan_hd;
    EditText ed_tienNuoc_HDon,ed_tienDien_HDon,ed_tienPhong_HDon,ed_chiPhiKhac_HDon,ed_tongTien_HDon;
    HoaDon hoaDon;
    HoaDonDAO hoaDonDAO;
    ArrayList<HoaDon> list;
    HoaDonAdapter hoaDonAdapter;
    Context context;
    ListView rcv;
    Button btntong;
    ArrayAdapter<Phong> adapter;
    ArrayList<Phong> listphong;
    Spinner spinner;
    DBHelper db;
    PhongDAO phongdao;









    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab_addHoaDon);
        rcv = view.findViewById(R.id.rec_HoaDon);


        context = this.getActivity();

        hoaDonDAO = new HoaDonDAO(context);
        list = (ArrayList<HoaDon>) hoaDonDAO.getAll();




        hoaDonAdapter = new HoaDonAdapter(context,list,this::OnClickDelete);

        rcv.setAdapter(hoaDonAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getContext());
            }
        });
    }

    @SuppressLint("MissingInflatedId")
    private void openDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_hoadon, null);

        btn_them_HD = view.findViewById(R.id.btn_them_HDon);
        btn_huy_HD = view.findViewById(R.id.btn_huy_HDon);
        image_ngaybatdau = view.findViewById(R.id.image_ngaybatdau);
        image_ngayketthuc = view.findViewById(R.id.image_ngayketthuc);
        ed_tienDien_HDon = view.findViewById(R.id.ed_TienDien_HDon);
        ed_tienNuoc_HDon = view.findViewById(R.id.ed_TienNuoc_HDon);
        ed_tienPhong_HDon = view.findViewById(R.id.ed_TienPhong_HDon);
        ed_tongTien_HDon = view.findViewById(R.id.ed_TongTien_HDon);
        ed_chiPhiKhac_HDon = view.findViewById(R.id.ed_ChiPhiKhac_HDon);
        edt_batdau_hd = view.findViewById(R.id.ed_batDau_HDon);
        edt_hethan_hd = view.findViewById(R.id.ed_ketThuc_HDon);
        spinner = view.findViewById(R.id.spinner);
        btntong = view.findViewById(R.id.tongtien);
        hoaDonDAO = new HoaDonDAO(getContext());
        list = new ArrayList<HoaDon>();
        list = (ArrayList<HoaDon>) hoaDonDAO.getAll();

        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        //----------------
        Calendar calendar = Calendar.getInstance();//Lay time
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);
        image_ngaybatdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        edt_batdau_hd.setText(d + "/" + (m + 1) + "/" + y);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        image_ngayketthuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        edt_hethan_hd.setText(d + "/" + (m + 1) + "/" + y);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


        List<Phong> listt = new ArrayList<>();
        phongdao = new PhongDAO(context);
        listt = phongdao.getAll();
        Spinner spinner = view.findViewById(R.id.spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(listt,context);
        spinner.setAdapter(adapter);
        btn_them_HD.setOnClickListener(new View.OnClickListener() {


            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {



                Phong phongg = (Phong) spinner.getSelectedItem();
                int soPHongOfHd = phongg.getSoPhong();

                String dien = ed_tienDien_HDon.getText().toString();
                String nuoc = ed_tienNuoc_HDon.getText().toString();
                String phong = ed_tienPhong_HDon.getText().toString();
                String khac = ed_chiPhiKhac_HDon.getText().toString();


                if(ed_tienDien_HDon.getText().toString().equals("")){
                    ed_tienDien_HDon.setError("Không được để trống");
                    return;
                }else ed_tienDien_HDon.setError("");


                if(ed_tienNuoc_HDon.getText().toString().equals("")){
                    ed_tienNuoc_HDon.setError("Không được để trống");
                    return;
                }else ed_tienNuoc_HDon.setError("");


                if(ed_tienPhong_HDon.getText().toString().equals("")){
                    ed_tienPhong_HDon.setError("Không được để trống");
                    return;
                }else ed_tienPhong_HDon.setError("");


                if(ed_chiPhiKhac_HDon.getText().toString().equals("")){
                    ed_chiPhiKhac_HDon.setError("Không được để trống");
                    return;
                }else ed_chiPhiKhac_HDon.setError("");



                    hoaDon = new HoaDon();
                    hoaDon.setSoPhong(Integer.parseInt(String.valueOf(soPHongOfHd)));
                    hoaDon.setNgayBatDau(edt_batdau_hd.getText().toString());
                    hoaDon.setNgayHetHan(edt_hethan_hd.getText().toString());
                    hoaDon.setTienDien(Integer.parseInt(dien));
                    hoaDon.setTienNuoc(Integer.parseInt(nuoc));
                    hoaDon.setTienPhong(Integer.parseInt(phong));
                    hoaDon.setChiPhiKhac(Integer.parseInt(khac));
                    hoaDon.setTongTien(Integer.parseInt(ed_tongTien_HDon.getText().toString()));

                    long insrt = hoaDonDAO.insertHoaDon(hoaDon);
                    if (insrt > 0) {
                        resetShowData();
                    }
                    dialog.dismiss();

            }









        });

        btn_huy_HD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btntong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dien =ed_tienDien_HDon.getText().toString();
                int dien1 = Integer.parseInt(dien);
                String nuoc =ed_tienNuoc_HDon.getText().toString();
                int nuoc1 = Integer.parseInt(nuoc);
                String phong =ed_tienPhong_HDon.getText().toString();
                int phong1 = Integer.parseInt(phong);
                String khac =ed_chiPhiKhac_HDon.getText().toString();
                int khac1 = Integer.parseInt(khac);
                int tong = (dien1*4000)+(nuoc1*3000)+phong1+khac1;
                ed_tongTien_HDon.setText(String.valueOf(tong));

            }
        });


    }
    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        list.addAll(hoaDonDAO.getAll());
        hoaDonAdapter.notifyDataSetChanged();
    }

    @Override
    public void resetShowData() {
        list.clear();
        list.addAll(hoaDonDAO.getAll());
        hoaDonAdapter = new HoaDonAdapter(context,list,this::OnClickDelete);
        rcv.setAdapter(hoaDonAdapter);
        Toast.makeText(getContext(), "Them moi thanh cong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hoa_don, container, false);
    }

    @Override
    public void OnClickDelete(int index) {
        deletedialog(index);
    }
    public void deletedialog(int index){
        androidx.appcompat.app.AlertDialog.Builder builder= new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle("bạn có chắc chắn muốn xóa không?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(hoaDonDAO.deleteHoaDon(list.get(index))>0){
                    list.remove(index);
                    hoaDonAdapter.setData(list);
                    Toast.makeText(context,"xoa thành công",
                            Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(context,"xóa không thành công",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

}