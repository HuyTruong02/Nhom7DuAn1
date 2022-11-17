package com.example.duan1.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duan1.Activity.interfaceDeleteClickdistioner;
import com.example.duan1.DAO.HoaDonDAO;
import com.example.duan1.Model.HoaDon;
import com.example.duan1.R;
import com.example.duan1.adapter.HoaDonAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;


public class HoaDonFragment extends Fragment  implements interfaceDeleteClickdistioner {
    FloatingActionButton fab;
    Button btn_them_HD,btn_huy_HD;
    ImageView image_ngaybatdau,image_ngayketthuc;
    EditText edt_batdau_hd;
    EditText edt_hethan_hd,abc;
    EditText ed_soPhong_HDon,ed_tienNuoc_HDon,ed_tienDien_HDon,ed_tienPhong_HDon,ed_chiPhiKhac_HDon,ed_tongTien_HDon;
    HoaDon hoaDon;
    HoaDonDAO hoaDonDAO;
    private ArrayList<HoaDon> list = new ArrayList<HoaDon>();
    HoaDonAdapter hoaDonAdapter;
    Context context;
    ListView rcv;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab_addHoaDon);
        rcv = view.findViewById(R.id.rec_HoaDon);


        context = this.getActivity();

        hoaDonDAO = new HoaDonDAO(context);
        list = (ArrayList<HoaDon>) hoaDonDAO.getAll();
        hoaDonAdapter = new HoaDonAdapter(context,this::OnClickDelete);
        hoaDonAdapter.setData(list);
        rcv.setAdapter(hoaDonAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getContext());
            }
        });
    }

    private void openDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_hoadon, null);

        btn_them_HD = view.findViewById(R.id.btn_them_HDon);
        btn_huy_HD = view.findViewById(R.id.btn_huy_HDon);

        image_ngaybatdau = view.findViewById(R.id.image_ngaybatdau);
        image_ngayketthuc = view.findViewById(R.id.image_ngayketthuc);
        ed_soPhong_HDon = view.findViewById(R.id.ed_soPhong_HDon);
        ed_tienDien_HDon = view.findViewById(R.id.ed_TienDien_HDon);
        ed_tienNuoc_HDon = view.findViewById(R.id.ed_TienNuoc_HDon);
        ed_tienPhong_HDon = view.findViewById(R.id.ed_TienPhong_HDon);
        ed_tongTien_HDon = view.findViewById(R.id.ed_TongTien_HDon);
        ed_chiPhiKhac_HDon = view.findViewById(R.id.ed_ChiPhiKhac_HDon);

        edt_batdau_hd = view.findViewById(R.id.ed_batDau_HDon);
        edt_hethan_hd = view.findViewById(R.id.ed_ketThuc_HDon);

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


        btn_them_HD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean check=true;
                String name =ed_soPhong_HDon.getText().toString();
                String dien =ed_tienDien_HDon.getText().toString();
                String nuoc =ed_tienNuoc_HDon.getText().toString();
                String phong =ed_tienPhong_HDon.getText().toString();
                String khac =ed_chiPhiKhac_HDon.getText().toString();

                if(name.length()==0){
                    ed_soPhong_HDon.requestFocus();
                    Toast.makeText(context, "số phòng không được để trống", Toast.LENGTH_SHORT).show();
                    check=false;
                }

                if (!name.matches("[0-999]*")){
                    ed_soPhong_HDon.requestFocus();
                    Toast.makeText(context, "số phòng không để âm được,chứa chữ và kí tự đặc biệt", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                if(name.length()>5){
                    ed_soPhong_HDon.requestFocus();
                    Toast.makeText(context, "số phòng không vượt quá 3 ký tự", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                //==---------------------------------------------=----------------------------
                if(dien.length()==0){
                    ed_tienDien_HDon.requestFocus();
                    Toast.makeText(context, "tiền điện không được để trống", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                if (!dien.matches("[0-999999999999]*")){
                    ed_tienDien_HDon.requestFocus();
                    Toast.makeText(context, "tiền điện không âm ,không chứa chữ và kí tự đặc biệt", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                if(dien.length()>10){
                    ed_tienDien_HDon.requestFocus();
                    Toast.makeText(context, "tiền điện không vượt quá 10 ký tự", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                //==---------------------------------------------=----------------------------
                if(nuoc.length()==0){
                    ed_tienNuoc_HDon.requestFocus();
                    Toast.makeText(context, "tiền nước không được để trống", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                if (!nuoc.matches("[0-9999999999]*")){
                    ed_tienNuoc_HDon.requestFocus();
                    Toast.makeText(context, "tiền nước không âm ,không chứa chữ và kí tự đặc biệt", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                if(nuoc.length()>10){
                    ed_tienNuoc_HDon.requestFocus();
                    Toast.makeText(context, "tiền nước không vượt quá 10 ký tự", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                //==---------------------------------------------=----------------------------
                if(phong.length()==0){
                    ed_tienPhong_HDon.requestFocus();
                    Toast.makeText(context, "tiền phòng không được để trống", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                if (!phong.matches("[0-9999999999]*")){
                    ed_tienPhong_HDon.requestFocus();
                    Toast.makeText(context, "tiền phòng không âm ,không chứa chữ và kí tự đặc biệt", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                if(phong.length()>10){
                    ed_tienPhong_HDon.requestFocus();
                    Toast.makeText(context, "tiền phòng không vượt quá 10 ký tự", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                //==---------------------------------------------=----------------------------
                if(khac.length()==0){
                    ed_chiPhiKhac_HDon.requestFocus();
                    Toast.makeText(context, "chi phí khác không được để trống", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                if (!khac.matches("[0-9999999999]*")){
                    ed_chiPhiKhac_HDon.requestFocus();
                    Toast.makeText(context, "chi phí khác không âm ,không chứa chữ và kí tự đặc biệt", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                if(khac.length()>10){
                    ed_chiPhiKhac_HDon.requestFocus();
                    Toast.makeText(context, "chi phí khác không vượt quá 10 ký tự", Toast.LENGTH_SHORT).show();
                    check=false;
                }


                if (check=true){
                    hoaDon = new HoaDon();
                    hoaDon.setSoPhong(Integer.parseInt(name));
                    hoaDon.setNgayBatDau(edt_batdau_hd.getText().toString());
                    hoaDon.setNgayBatDau(edt_hethan_hd.getText().toString());
                    hoaDon.setTienDien(Integer.parseInt(dien));
                    hoaDon.setTienNuoc(Integer.parseInt(nuoc));
                    hoaDon.setTienPhong(Integer.parseInt(phong));
                    hoaDon.setChiPhiKhac(Integer.parseInt(khac));
                    hoaDon.setTongTien(Integer.parseInt(ed_tongTien_HDon.getText().toString()));

                    hoaDonDAO = new HoaDonDAO(context);
                    hoaDonDAO.insertHoaDon(hoaDon);
                    Toast.makeText(context,"thêm mới thành công",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    list.clear();
                    list.addAll(hoaDonDAO.getAll());
                    hoaDonAdapter.notifyDataSetChanged();
                }
            }
        });
        btn_huy_HD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

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