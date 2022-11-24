package com.example.duan1.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duan1.DAO.PhongDAO;
import com.example.duan1.Model.Phong;
import com.example.duan1.R;
import com.example.duan1.adapter.PhongAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PhongFragment extends Fragment {
    FloatingActionButton fab;
    Button btnadd;
    TextView txtloi_sophong, txtloi_mota;
    int hinhanh;
    EditText ed_soPhong;
    EditText ed_moTa;
    PhongAdapter adapter;
    PhongDAO dao;
    ArrayList<Phong> list = new ArrayList<>();
    RecyclerView rec;
    Toolbar toolbar;
    private PhongAdapter adapterP;
    private SearchView searchView;

    public PhongFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phong, container, false);
        fab = view.findViewById(R.id.fab_addPhong);
        rec = view.findViewById(R.id.rec_phong);
        dao = new PhongDAO(getContext());
        // tao toobal;
        toolbar = view.findViewById(R.id.toolBar_p);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Nhập thông tin muốn tìm");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(layoutManager);
        list = dao.getAll();
        adapter = new PhongAdapter(getContext(), list);
        rec.setAdapter(adapter);
        // tao dữ liệu đổ vào RecyclerView
        list.add(new Phong(1,201,"dang sua chua",R.drawable.anhphongtro));
        list.add(new Phong(2,202,"dang sua chua",R.drawable.anhphongtro1));
        list.add(new Phong(3,203,"dang sua chua",R.drawable.anhphongtro2));
        list.add(new Phong(4,204,"dang sua chua",R.drawable.anhphongtro3));
        list.add(new Phong(5,205,"dang sua chua",R.drawable.anhphongtro4));
        list.add(new Phong(6,206,"dang sua chua",R.drawable.anhphongtro5));
        list.add(new Phong(7,207,"dang sua chua",R.drawable.anhphongtro6));
        list.add(new Phong(8,208,"dang sua chua",R.drawable.anhphongtro7));
        list.add(new Phong(9,209,"dang sua chua",R.drawable.anhphogntro8));
        list.add(new Phong(10,210,"dang sua chua",R.drawable.anhphongtro9));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getContext());
            }
        });
        return view;
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

                    if (dao.insert(soPhong, moTa, hinhanh)) {
                        Toast.makeText(context, "thêm mới thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        list.clear();
                        list.addAll(dao.getAll());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(context, "thêm mới không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.item_search, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_action).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return;
    }


}