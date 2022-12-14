package com.example.duan1.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1.Activity.interfaceDeleteClickdistioner;
import com.example.duan1.DAO.DichVuDAO;
import com.example.duan1.Model.DichVu;

import com.example.duan1.R;

import com.example.duan1.adapter.DichVuAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class DichVuFragment extends Fragment implements interfaceDeleteClickdistioner {

    EditText ed_tendichvu_dv, ed_giadichvu_dv, ed_mota_dv;
    Button btnthem_dv, btnhuy_dv;
    FloatingActionButton fab;
    ListView rcv_dichvu;
    Spinner spinner;

    DichVuAdapter dichVuAdapter;
    private DichVuDAO dichVuDAO;


    private ArrayList<DichVu> listt = new ArrayList<>();
    Context context;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab_addDichVu);
        rcv_dichvu = view.findViewById(R.id.rec_DichVu);
        spinner = view.findViewById(R.id.Spiner);
        context = this.getActivity();

        dichVuDAO = new DichVuDAO(context);
        listt = (ArrayList<DichVu>) dichVuDAO.getAll();
        dichVuAdapter = new DichVuAdapter(context, this::OnClickDelete);
        dichVuAdapter.setData(listt);
        rcv_dichvu.setAdapter(dichVuAdapter);


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
        View view = inflater.inflate(R.layout.dialog_add_dichvu, null);

        ed_tendichvu_dv = view.findViewById(R.id.ed_TenDichVu_DV);
        ed_giadichvu_dv = view.findViewById(R.id.ed_GiaDichVu_DV);
        ed_mota_dv = view.findViewById(R.id.ed_MoTa_DV);

        btnthem_dv = view.findViewById(R.id.btn_them_DV);
        btnhuy_dv = view.findViewById(R.id.btn_huy_DV);

        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        btnthem_dv.setOnClickListener(new View.OnClickListener() {
            DichVu dichVu;

            @Override
            public void onClick(View view) {
                Boolean check = true;
                String name = ed_tendichvu_dv.getText().toString();
                String gia = ed_giadichvu_dv.getText().toString();

                if (name.length() == 0) {
                    ed_tendichvu_dv.requestFocus();
                    Toast.makeText(context, "t??n d???ch v??? kh??ng ???????c ????? tr???ng", Toast.LENGTH_SHORT).show();
                    check = false;
                }
                if (!name.matches("[a-z,A-Z ]*")) {
                    Toast.makeText(context, "t??n d???ch v??? kh??ng ???????c ch???a s??? v?? k?? t??? ?????c bi???t", Toast.LENGTH_SHORT).show();
                    check = false;
                }
                if (name.length() > 50) {
                    Toast.makeText(context, "t??n d???ch v??? kh??ng v?????t qu?? 50 k?? t???", Toast.LENGTH_SHORT).show();
                    check = false;
                }
                //==---------------------------------------------=----------------------------
                if (gia.length() == 0) {
                    Toast.makeText(context, "t??n d???ch v??? kh??ng ???????c ????? tr???ng", Toast.LENGTH_SHORT).show();
                    check = false;
                }
                if (!gia.matches("[0-99999]*")) {
                    Toast.makeText(context, "nh???p sai vui l??ng nh???p l???i t??? 0 tr??? l??n", Toast.LENGTH_SHORT).show();
                    check = false;
                }

                if (gia.length() > 8) {
                    Toast.makeText(context, "gi?? d???ch v??? kh??ng v?????t qu?? 8 k?? t???", Toast.LENGTH_SHORT).show();
                    check = false;
                }

                //============-------------------------------------------------------------------

                if (check == true) {
                    dichVu = new DichVu();
                    dichVu.setTenDV(name);
                    dichVu.setGiaDV(Integer.parseInt(ed_giadichvu_dv.getText().toString()));
                    dichVu.setMota(ed_mota_dv.getText().toString());
                    dichVuDAO = new com.example.duan1.DAO.DichVuDAO(context);
                    dichVuDAO.insertDichVu(dichVu);
                    Toast.makeText(context, "th??m m???i th??nh c??ng", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    listt.clear();
                    listt.addAll(dichVuDAO.getAll());
                    dichVuAdapter.notifyDataSetChanged();
                }

            }
        });
        btnhuy_dv.setOnClickListener(new View.OnClickListener() {
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
        return inflater.inflate(R.layout.fragment_dich_vu, container, false);

    }


    @Override
    public void OnClickDelete(int index) {
        deletedialog(index);
    }

    public void deletedialog(int index) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle("b???n c?? ch???c ch???n mu???n x??a kh??ng?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dichVuDAO.deleteDichVu(listt.get(index)) > 0) {
                    listt.remove(index);
                    dichVuAdapter.setData(listt);
                    Toast.makeText(context, "xoa th??nh c??ng",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "x??a kh??ng th??nh c??ng",
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