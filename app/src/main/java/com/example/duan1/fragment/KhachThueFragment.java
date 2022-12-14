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
                    Toast.makeText(context, "H??? t??n kh??ng ???????c ????? tr???ng", Toast.LENGTH_SHORT).show();
                    check=false;
                }
          /* if(!hoTen.matches("[a-z;A-Z]*")){
               Toast.makeText(context, "H??? t??n kh??ng ???????c c?? k?? t??? s??? ho???c k?? t??? ?????c bi???t", Toast.LENGTH_SHORT).show();
               check=false;
           }*/
                if(hoTen.length()<5){
                    Toast.makeText(context, "H??? t??n kh??ng ???????c nh??? h??n 5 k?? t???", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                else if(hoTen.length()>100){
                    Toast.makeText(context, "H??? t??n kh??ng ???????c l???n h??n 100 k?? t???", Toast.LENGTH_SHORT).show();
                    check=false;
                }


                /// h???t t??n
                ///s??? ??i???n tho???i
                if((edSdt.getText().toString()).length()==0){
                    Toast.makeText(context, "S??? ??i???n tho???i ??ang ????? tr???ng", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                else if((edSdt.getText().toString()).length()!=10){
                    Toast.makeText(context, "S??? ??i???n tho???i ph???i l?? 10 ch??? s???", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                try {
                    Sdt= Integer.parseInt(edSdt.getText().toString());
                    if(Sdt<0){
                        Toast.makeText(context, "S??? ??i???n tho???i kh??ng ???????c ??m", Toast.LENGTH_SHORT).show();
                        check=false;
                    }
                }catch (Exception e){
                    Toast.makeText(context, "S??? ??i???n tho???i ph???i l?? s??? nguy??n", Toast.LENGTH_SHORT).show();
                    check=false;
                }


                if((edCccd.getText().toString()).length()==0){
                    Toast.makeText(context, "Cmnd ??ang ????? tr???ng", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                else if((edCccd.getText().toString()).length()!=12){
                    Toast.makeText(context, "Cmnd ph???i l?? 12 s???", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                try {
                    Cccd= Integer.parseInt(edCccd.getText().toString());
                    if(Sdt<0){
                        Toast.makeText(context, "CMND kh??ng ???????c ??m", Toast.LENGTH_SHORT).show();
                        check=false;
                    }
                }catch (Exception e){

                }

                if((edSoPhong.getText().toString()).length()==0){
                    Toast.makeText(context, "S??? ph??ng ??ang ????? tr???ng", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                else if((edSoPhong.getText().toString()).length()!=3){
                    Toast.makeText(context, "s??? ph??ng ph???i l?? 3 s???", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                try {
                    SoPhong  =Integer.parseInt(edSoPhong.getText().toString()) ;

                    if(SoPhong<0){
                        Toast.makeText(context, "S??? ph??ng kh??ng ???????c ??m", Toast.LENGTH_SHORT).show();
                        check=false;
                    }
                }catch (Exception e){
                    Toast.makeText(context, "s??? ph??ng ph???i l?? s??? nguy??n", Toast.LENGTH_SHORT).show();
                    check=false;
                }

                if (validate() > 0){
                    if (type == 0){
                        if(check==true){
                            if (dao.insert(hoTen, Sdt, Cccd, SoPhong) > 0){
                                Toast.makeText(context, "Th??m th??nh c??ng !!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "Th??m th???t b???i !!", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                    else {
                        if(check==true){
                            item.Id = Integer.parseInt(edId.getText().toString());
                            if (dao.update(item) > 0){
                                Toast.makeText(context, "S???a th??nh c??ng !!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "S???a th???t b???i !!", Toast.LENGTH_SHORT).show();
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
        builder.setTitle("X??a kh??ch thu?? !!");
        builder.setMessage("B???n c?? mu???n x??a kh??ch thu?? n??y kh??ng ?");
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
        builder.setTitle("TH??NG TIN KH??CH THU??");
        builder.setMessage("M?? kh??ch thu?? : k001 \n H??? t??n : Nguy???n v??n kh??nh \n S??? ??i???n thoai : 0395748834 \n S??? CCCD : 438588484565 \n S??? ph??ng : 101");
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
        builder.setTitle("X??a kh??ch thu?? !!");
        builder.setMessage("B???n c?? mu???n x??a kh??ch thu?? n??y kh??ng ?");
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
            Toast.makeText(getContext(), "B???n ph???i nh???p ????? th??ng tin kh??ch h??ng", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}