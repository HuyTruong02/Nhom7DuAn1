package com.example.duan1.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1.DAO.HoaDonDAO;
import com.example.duan1.Model.HoaDon;
import com.example.duan1.R;

import java.util.Calendar;


public class DoanhThuFragment extends Fragment {
    ImageView img_doanh_thu_nambd,img_doanh_thu_namkt;
    EditText ed_doanh_thu_nambd,ed_doanh_thu_namkt;
    Button btn_xemDTN;
    TextView doanhthu;
    public DoanhThuFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        img_doanh_thu_nambd = view.findViewById(R.id.img_doanh_thu_namdb);
        img_doanh_thu_namkt=view.findViewById(R.id.img_doanh_thu_namKT);
        ed_doanh_thu_nambd = view.findViewById(R.id.ed_doanh_thu_namBd);
        ed_doanh_thu_namkt=view.findViewById(R.id.ed_doanh_thu_namKT);
        btn_xemDTN = view.findViewById(R.id.btn_xemDTN);
        doanhthu=view.findViewById(R.id.txtDoanhthu);
        //----------------
        Calendar calendar = Calendar.getInstance();//Lay time
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);
        //-----------------------------
        //datePicker
        img_doanh_thu_nambd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        ed_doanh_thu_nambd.setText(d + "/" + (m + 1) + "/" + y);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        img_doanh_thu_namkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        ed_doanh_thu_namkt.setText(d + "/" + (m + 1) + "/" + y);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        btn_xemDTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay=ed_doanh_thu_nambd.getText().toString();
                String denNgay=ed_doanh_thu_namkt.getText().toString();
                HoaDonDAO HDDAO= new HoaDonDAO(getActivity());

            }
        });
    return view;
    }
}