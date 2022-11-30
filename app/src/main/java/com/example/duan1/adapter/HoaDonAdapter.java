package com.example.duan1.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.duan1.Activity.Detail_HoaDon;
import com.example.duan1.Activity.interfaceDeleteClickdistioner;
import com.example.duan1.DAO.PhongDAO;
import com.example.duan1.Model.HoaDon;
import com.example.duan1.Model.Phong;
import com.example.duan1.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HoaDonAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HoaDon> list = new ArrayList<>();
    private com.example.duan1.Activity.interfaceDeleteClickdistioner interfaceDeleteClickdistioner;

    public HoaDonAdapter(Context context, ArrayList<HoaDon> list, com.example.duan1.Activity.interfaceDeleteClickdistioner interfaceDeleteClickdistioner) {
        this.context = context;
        this.list = list;
        this.interfaceDeleteClickdistioner = interfaceDeleteClickdistioner;
    }

    public HoaDonAdapter(Context context, interfaceDeleteClickdistioner interfaceDeleteClickdistioner) {
        this.context = context;
        this.interfaceDeleteClickdistioner = interfaceDeleteClickdistioner;
    }

    public void setData(ArrayList<HoaDon> arrayList){
        this.list= arrayList;
        notifyDataSetChanged();// có tác dụng refresh lại data
    }

    public final class MyViewHolder {
        //khai báo các thành phần view có trong layoutitem
        TextView tv_item_tenphong_dv;

    }
    public int getCount() {
        if(list!=null)
            return list.size();
        return 0;
    }
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    public View getView(int i, View view, ViewGroup viewGroup) {

        MyViewHolder myViewHolder = null;
        if (view == null) {
            myViewHolder = new MyViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_hoadon_layout, null);
            myViewHolder.tv_item_tenphong_dv = view.findViewById(R.id.tv_item_tenphong_hd);

            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        HoaDon hoaDon = list.get(i);
        myViewHolder.tv_item_tenphong_dv.setText(String.valueOf(hoaDon.getSoPhong()));







        LinearLayout ln_item_dv = view.findViewById(R.id.ln_item_hdon);

        ln_item_dv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_bottomhdon_layout);

                LinearLayout editLayout = dialog.findViewById(R.id.edt_update_dv);
                LinearLayout delete_layout = dialog.findViewById(R.id.edt_delete_dv);
                LinearLayout detail_layout = dialog.findViewById(R.id.edt_detail_hdon);

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);

                editLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                        View view = inflater.inflate(R.layout.dialog_update_hoadon, null);

                        Button btn_them_HD = view.findViewById(R.id.btn_them_HDons);
                        Button btn_huy_HD = view.findViewById(R.id.btn_huy_HDons);

                        ImageView image_ngaybatdau = view.findViewById(R.id.image_ngaybatdaus);
                        ImageView image_ngayketthuc = view.findViewById(R.id.image_ngayketthucs);
                        EditText ed_soPhong_HDon = view.findViewById(R.id.ed_soPhong_HDons);
                        EditText ed_tienDien_HDon = view.findViewById(R.id.ed_TienDien_HDons);
                        EditText ed_tienNuoc_HDon = view.findViewById(R.id.ed_TienNuoc_HDons);
                        EditText ed_tienPhong_HDon = view.findViewById(R.id.ed_TienPhong_HDons);
                        EditText ed_tongTien_HDon = view.findViewById(R.id.ed_TongTien_HDons);
                        EditText ed_chiPhiKhac_HDon = view.findViewById(R.id.ed_ChiPhiKhac_HDons);
                        EditText edt_batdau_hd = view.findViewById(R.id.ed_batDau_HDons);
                        EditText edt_hethan_hd = view.findViewById(R.id.ed_ketThuc_HDons);


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


                        builder.setView(view);
                        Dialog dialog = builder.create();
                        dialog.show();

                        btn_them_HD.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

                        btn_huy_HD.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                    }
                });

                delete_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        interfaceDeleteClickdistioner.OnClickDelete(i);
                    }
                });
                detail_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, Detail_HoaDon.class);
                        context.startActivity(intent);
                    }

                });
            }
        });
        return view;
    }
}
