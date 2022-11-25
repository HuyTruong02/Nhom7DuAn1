package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.duan1.DAO.PhongDAO;
import com.example.duan1.Model.Phong;
import com.example.duan1.adapter.PhongAdapter;

import java.util.ArrayList;
import java.util.List;

public class Spinnerr extends AppCompatActivity {
Spinner spinner;
    private List<Phong> listPhong;
    PhongDAO dao;
    PhongAdapter adapter;

    public ArrayList<Phong> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        //Tạo một mảng dữ liệu giả
        anhxa();
        TextView selection;


            //Lấy đối tượng Spinner ra

            //Gán Data source (arr) vào Adapter
            ArrayAdapter<Phong> adapter=new ArrayAdapter<Phong>
                    (
                            this,
                            android.R.layout.simple_spinner_item,
                            list
                    );
            //phải gọi lệnh này để hiển thị danh sách cho Spinner
            adapter.setDropDownViewResource
                    (android.R.layout.simple_list_item_single_choice);
            //Thiết lập adapter cho Spinner
            spinner.setAdapter(adapter);
            //thiết lập sự kiện chọn phần tử cho Spinner
          //  spinner.setOnItemSelectedListener(new MyProcessEvent());
        }
        //Class tạo sự kiện
//        private class MyProcessEvent implements AdapterView.OnItemSelectedListener
//
//        {
//            //Khi có chọn lựa thì vào hàm này
//            public void onItemSelected(AdapterView<Phong>
//                                      listPhongAdapterAdapterView
//
//                                       ) {
//                //arg2 là phần tử được chọn trong data source
//                selection.setText(arr[arg2]);
//            }
//            //Nếu không chọn gì cả
//            public void onNothingSelected(AdapterView<?> arg0) {
//                selection.setText("");
//            }




    public void anhxa(){
        spinner = findViewById(R.id.spinner1);
    }
    }

