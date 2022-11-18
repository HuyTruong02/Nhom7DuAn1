package com.example.duan1.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duan1.DAO.KhachThueDAO;
import com.example.duan1.Model.KhachThue;
import com.example.duan1.R;
import com.example.duan1.adapter.KhachThueAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class KhachThueFragment extends Fragment {
    KhachThue item;
    KhachThueAdapter adapter;
    static KhachThueDAO dao;
    ListView lv;
    ArrayList<KhachThue> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edId ,edhoTen,edSdt,edCccd , edSoPhong;
    Button btnSave, btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_khach_thue,container, false);
        lv = v.findViewById(R.id.lvKhachThue);
        fab = v.findViewById(R.id.fab);
        dao = new KhachThueDAO(getActivity());
        capNhatLV();
        fab.setOnClickListener(v1 -> {
            openDialog(getActivity(),0);

            lv.setOnItemClickListener(((parent, view, position, id) -> {
                item = list.get(position);

                return;
            }));
        });
        return v;
    }

    protected void openDialog(final Context context, final  int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.khachthue_dialog);

        edhoTen = dialog.findViewById(R.id.edTenKT);
        edSdt = dialog.findViewById(R.id.edSdt);
        edCccd = dialog.findViewById(R.id.edCccd);
        edSoPhong = dialog.findViewById(R.id.edSoPhong);

        btnSave = dialog.findViewById(R.id.btnSave);
        btnCancel = dialog.findViewById(R.id.btnCancel);

        if (type != 0){
            edId.setText(String.valueOf(item.Id));
            edhoTen.setText(item.Id);
            edSdt.setText(String.valueOf(item.Sdt));
            edCccd.setText(String.valueOf(item.Cccd));
            edSoPhong.setText(item.SoPhong);
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean check=true;

                String   hoTen = edhoTen.getText().toString();
                int Sdt =0;
                int  Cccd = 0;
                int SoPhong=0 ;
                if(hoTen.length()==0){
                    Toast.makeText(context, "Họ tên không được để trống", Toast.LENGTH_SHORT).show();
                    check=false;
                }
          /* if(!hoTen.matches("[a-z;A-Z]*")){
               Toast.makeText(context, "Họ tên không được có kí tự số hoặc kí tự đặc biệt", Toast.LENGTH_SHORT).show();
               check=false;
           }*/
                if(hoTen.length()<5){
                    Toast.makeText(context, "Họ tên không được nhỏ hơn 5 kí tự", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                else if(hoTen.length()>100){
                    Toast.makeText(context, "Họ tên không được lớn hơn 100 kí tự", Toast.LENGTH_SHORT).show();
                    check=false;
                }


                /// hết tên
                ///số điện thoại
                if((edSdt.getText().toString()).length()==0){
                    Toast.makeText(context, "Số điện thoại đang để trống", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                else if((edSdt.getText().toString()).length()!=10){
                    Toast.makeText(context, "Số điện thoại phải là 10 chữ số", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                try {
                    Sdt= Integer.parseInt(edSdt.getText().toString());
                    if(Sdt<0){
                        Toast.makeText(context, "Số điện thoại không được âm", Toast.LENGTH_SHORT).show();
                        check=false;
                    }
                }catch (Exception e){
                    Toast.makeText(context, "Số điện thoại phải là số nguyên", Toast.LENGTH_SHORT).show();
                    check=false;
                }


                if((edCccd.getText().toString()).length()==0){
                    Toast.makeText(context, "Cmnd đang để trống", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                else if((edCccd.getText().toString()).length()!=12){
                    Toast.makeText(context, "Cmnd phải là 12 số", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                try {
                    Cccd= Integer.parseInt(edCccd.getText().toString());
                    if(Sdt<0){
                        Toast.makeText(context, "CMND không được âm", Toast.LENGTH_SHORT).show();
                        check=false;
                    }
                }catch (Exception e){

                }

                if((edSoPhong.getText().toString()).length()==0){
                    Toast.makeText(context, "Số phòng đang để trống", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                else if((edSoPhong.getText().toString()).length()!=3){
                    Toast.makeText(context, "số phòng phải là 3 số", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                try {
                    SoPhong  =Integer.parseInt(edSoPhong.getText().toString()) ;

                    if(SoPhong<0){
                        Toast.makeText(context, "Số phòng không được âm", Toast.LENGTH_SHORT).show();
                        check=false;
                    }
                }catch (Exception e){
                    Toast.makeText(context, "số phòng phải là số nguyên", Toast.LENGTH_SHORT).show();
                    check=false;
                }

                if (validate() > 0){
                    if (type == 0){
                        if(check==true){
                            if (dao.insert(hoTen, Sdt, Cccd, SoPhong) > 0){
                                Toast.makeText(context, "Thêm thành công !!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "Thêm thất bại !!", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                    else {
                        if(check==true){
                            item.Id = Integer.parseInt(edId.getText().toString());
                            if (dao.update(item) > 0){
                                Toast.makeText(context, "Sửa thành công !!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "Sửa thất bại !!", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                    capNhatLV();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void update(){
        openDialog(getContext(),0);
    }

    public void xoa(final String id ){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa khách thuê !!");
        builder.setMessage("Bạn có muốn xóa khách thuê này không ?");
        builder.setCancelable(true);
        builder.setPositiveButton(" < Yes > ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                capNhatLV();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    public void view( ){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("THÔNG TIN KHÁCH THUÊ");
        builder.setMessage("Mã khách thuê : k001 \n Họ tên : Nguyễn văn khánh \n Số điện thoai : 0395748834 \n Số CCCD : 438588484565 \n Số phòng : 101");
        builder.setCancelable(true);
        builder.setPositiveButton(" < Yes > ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    public void del( ){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa khách thuê !!");
        builder.setMessage("Bạn có muốn xóa khách thuê này không ?");
        builder.setCancelable(true);
        builder.setPositiveButton(" < Yes > ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    void capNhatLV(){
        list = (ArrayList<KhachThue>) dao.getAll();
        adapter = new KhachThueAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }

    private  int validate(){
        int check = 1;
        if (edhoTen.getText().length() == 0 ){
            Toast.makeText(getContext(), "Bạn phải nhập đủ thông tin khách hàng", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}