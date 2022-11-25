package com.example.duan1.adapter;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.Activity.interfaceDeleteClickdistioner;
import com.example.duan1.DAO.DichVuDAO;
import com.example.duan1.Model.DichVu;
import com.example.duan1.R;


import java.util.ArrayList;

public class DichVuAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DichVu> list;
    private com.example.duan1.Activity.interfaceDeleteClickdistioner interfaceDeleteClickdistioner;


    public DichVuAdapter(Context context, interfaceDeleteClickdistioner interfaceDeleteClickdistioner) {
        this.context = context;
        this.interfaceDeleteClickdistioner = interfaceDeleteClickdistioner;
    }

    public void setData(ArrayList<DichVu> arrayList) {
        this.list = arrayList;
        notifyDataSetChanged();// có tác dụng refresh lại data
    }

    public final class MyViewHolder {
        //khai báo các thành phần view có trong layoutitem
        private ImageView img_update_dv;
        private TextView tvten_dv;
        private TextView tvgia_dv;
        private TextView tvmota_dv;
    }

    public int getCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder = null;
        if (view == null) {
            myViewHolder = new MyViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_dichvu_layout, null);
            myViewHolder.tvten_dv = view.findViewById(R.id.tvten_dv);
            myViewHolder.tvgia_dv = view.findViewById(R.id.tvgia_dv);
            myViewHolder.tvmota_dv = view.findViewById(R.id.tvmota_dv);
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        LinearLayout ln_item_dv = view.findViewById(R.id.ln_item_dv);
        myViewHolder.tvten_dv.setText(list.get(i).getTenDV());
        myViewHolder.tvgia_dv.setText(String.valueOf(list.get(i).getGiaDV()));
        myViewHolder.tvmota_dv.setText(list.get(i).getMota());

        System.out.println(list.get(i));

        ln_item_dv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_bottomhdon_layout);

                LinearLayout editLayout = dialog.findViewById(R.id.edt_update_dv);
                LinearLayout delete_layout = dialog.findViewById(R.id.edt_delete_dv);

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
                        View view = inflater.inflate(R.layout.dialog_update_dichvu, null);
                        EditText edtsuaten = view.findViewById(R.id.ed_update_TenDichVu_DV);
                        EditText edtsuagia = view.findViewById(R.id.ed_update_GiaDichVu_DV);
                        EditText edtsuamota = view.findViewById(R.id.ed_update_MoTa_DV);
                        Button btnluu = view.findViewById(R.id.btn_update_luu_DV);
                        Button btnhuy = view.findViewById(R.id.btn_update_huy_DV);
                        edtsuaten.setText(list.get(i).getTenDV());
                        edtsuagia.setText(String.valueOf(list.get(i).getGiaDV()));
                        edtsuamota.setText(list.get(i).getMota());

                        builder.setView(view);
                        Dialog dialog = builder.create();
                        dialog.show();

                        btnluu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DichVuDAO dichVuDAO = new DichVuDAO(context);
                                DichVu dichVu = new DichVu();
                                dichVu = list.get(i);
                                dichVu.setTenDV(edtsuaten.getText().toString());
                                dichVu.setGiaDV(Integer.parseInt(edtsuagia.getText().toString()));
                                dichVu.setMota (edtsuamota.getText().toString());
                                if (dichVuDAO.updateDichVu(dichVu) > 0) {
                                    Toast.makeText(context, "sửa thành công", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                    list.clear();
                                    list.addAll(dichVuDAO.getAll());
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(context, "sửa không thành công", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                        btnhuy.setOnClickListener(new View.OnClickListener() {
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
            }
        });

        return view;
    }


}
