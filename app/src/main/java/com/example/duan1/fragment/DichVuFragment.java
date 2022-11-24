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

    EditText ed_tendichvu_dv,ed_giadichvu_dv,ed_mota_dv;
    Button btnthem_dv,btnhuy_dv;
    FloatingActionButton fab;
    ListView rcv_dichvu;

    DichVuAdapter dichVuAdapter;
    private DichVuDAO dichVuDAO;
    private ArrayList<DichVu> list = new ArrayList<>();
    Context context;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab_addDichVu);
        rcv_dichvu = view.findViewById(R.id.rec_DichVu);

        context = this.getActivity();

        dichVuDAO = new DichVuDAO(context);
        list = (ArrayList<DichVu>) dichVuDAO.getAll();
        dichVuAdapter = new DichVuAdapter(context,this::OnClickDelete);
        dichVuAdapter.setData(list);
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
                Boolean check=true;
                String name =ed_tendichvu_dv.getText().toString();
                String gia =ed_giadichvu_dv.getText().toString();

                if(name.length()==0){
                    ed_tendichvu_dv.requestFocus();
                    Toast.makeText(context, "tên dịch vụ không được để trống", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                if (name.matches("[a-z,A-Z ]*")){
                    Toast.makeText(context, "tên dịch vụ không được chứa số và kí tự đặc biệt", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                if(name.length()>50){
                    Toast.makeText(context, "tên dịch vụ không vượt quá 50 ký tự", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                //==---------------------------------------------=----------------------------
                if (gia.length()==0) {
                    Toast.makeText(context, "tên dịch vụ không được để trống", Toast.LENGTH_SHORT).show();
                    check=false;
                }
                if(!gia.matches("[0-99999]*")){
                    Toast.makeText(context, "nhập sai vui lòng nhập lại từ 0 trở lên", Toast.LENGTH_SHORT).show();
                    check=false;
                }

                if (gia.length() >8) {
                    Toast.makeText(context, "giá dịch vụ không vượt quá 8 ký tự", Toast.LENGTH_SHORT).show();
                    check=false;
                }

                //============-------------------------------------------------------------------

                if(check==true){
                    dichVu = new DichVu();
                    dichVu.setTenDV(name);
                    dichVu.setGiaDV(Integer.parseInt(ed_giadichvu_dv.getText().toString()));
                    dichVu.setMota(ed_mota_dv.getText().toString());
                    dichVuDAO = new com.example.duan1.DAO.DichVuDAO(context);
                    dichVuDAO.insertDichVu(dichVu);
                    Toast.makeText(context,"thêm mới thành công",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    list.clear();
                    list.addAll(dichVuDAO.getAll());
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
    public void deletedialog(int index){
        androidx.appcompat.app.AlertDialog.Builder builder= new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle("bạn có chắc chắn muốn xóa không?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(dichVuDAO.deleteDichVu(list.get(index))>0){
                    list.remove(index);
                    dichVuAdapter.setData(list);
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