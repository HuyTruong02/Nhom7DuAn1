package com.example.duan1.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duan1.DAO.PhongDAO;
import com.example.duan1.Model.Phong;
import com.example.duan1.R;
import com.example.duan1.adapter.PhongAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PhongFragment extends Fragment {

    FloatingActionButton fab;
    Button btnadd;
    TextView txtloi_sophong, txtloi_mota;
    EditText ed_soPhong;
    EditText ed_moTa;
    EditText ed_timkiem;
    ImageView ivbtn;
    PhongAdapter adapter;
    PhongDAO dao;
    ArrayList<Phong> list = new ArrayList<>();
    RecyclerView rec;

    public PhongFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab_addPhong);
        rec = view.findViewById(R.id.rec_phong);
        dao = new PhongDAO(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(layoutManager);
        list = dao.getAll();
        adapter = new PhongAdapter(getContext(), list);
        rec.setAdapter(adapter);

        ed_timkiem = view.findViewById(R.id.edtimkiem);
        ivbtn = view.findViewById(R.id.btntimkiem);

        ivbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timkiem = ed_timkiem.getText().toString();
                int tk = 0;
                if (timkiem.length() == 0) {
                    Toast.makeText(getContext(), "Bạn đang để trống thanh tìm kiếm", Toast.LENGTH_SHORT).show();
                }
                if (timkiem.length() != 3) {
                    Toast.makeText(getContext(), "Số phòng không được khác 3 chữ số", Toast.LENGTH_SHORT).show();
                }
                try {

                    tk = Integer.parseInt(timkiem);
                    if (tk < 0) {
                        Toast.makeText(getContext(), "Số phòng không âm", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Số phòng phải là số nguyên", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
        View view = inflater.inflate(R.layout.dialog_addphong, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        Button btnadd = view.findViewById(R.id.btn_add_phong);
        ed_soPhong = view.findViewById(R.id.edsophong);
        ed_moTa = view.findViewById(R.id.edmota);
        txtloi_mota = view.findViewById(R.id.loimota);
        txtloi_sophong = view.findViewById(R.id.loisophong);
        Button btnhuy = view.findViewById(R.id.btn_huy_phong);
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = true;
                String sop = ed_soPhong.getText().toString();
                String moTa = ed_moTa.getText().toString();
                if (moTa.length() == 0) {
                    txtloi_mota.setText("Bạn đang  để trống mô tả phòng");
                    check = false;
                }
                if (moTa.length() < 5 || moTa.length() > 1000) {
                    txtloi_mota.setText("Mô tả phải lớn hơn 5 kí tự và nhỏ hơn 1000 kí tự");
                    check = false;
                }

                int soPhong = 0;
                if (sop.length() == 0) {
                    txtloi_sophong.setText("Bạn đang để trống số phòng");
                    check = false;
                } else {
                    try {
                        soPhong = Integer.parseInt(sop);
                        if (soPhong < 0) {
                            txtloi_sophong.setText("Số phòng phải là số dương");

                            check = false;
                        }
                        if (sop.length() != 3) {
                            txtloi_sophong.setText("Số phòng phải là 3 chữ số");
                            check = false;
                        }
                    } catch (Exception e) {
                        txtloi_sophong.setText("Số phòng phải là số nguyên");
                        check = false;
                    }
                }
                try {
                    for (int i = 0; i < dao.getAll().size(); i++) {
                        Phong phong_ = dao.getAll().get(i);
                        if (soPhong == phong_.getSoPhong()) {
                            Toast.makeText(context, "Phòng " + soPhong + " đã có", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                } catch (Exception e) {
                }
                if (check == true) {

                    if (dao.insert(soPhong, moTa)) {
                        Toast.makeText(context, "thêm mới thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        list.clear();
                        list.addAll(dao.getAll());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(context, "thêm mới k thành công", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phong, container, false);
    }
}