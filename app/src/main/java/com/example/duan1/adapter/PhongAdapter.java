package com.example.duan1.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.DAO.PhongDAO;
import com.example.duan1.Model.Phong;
import com.example.duan1.R;

import java.util.ArrayList;
import java.util.List;

public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.PhongViewHolder> implements Filterable{
    Context context;
    ArrayList<Phong> list;
    private List<Phong> listPhong;
    List<Phong> liSearch;
    PhongDAO dao;

    public PhongAdapter(Context context, ArrayList<Phong> list) {
        this.context = context;
        this.list = list;
        dao = new PhongDAO(context);
        this.listPhong=list;
        this.liSearch=list;
    }

    @NonNull
    @Override
    public PhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater l = ((Activity) context).getLayoutInflater();
        View view = l.inflate(R.layout.viewitem_phong, parent, false);
        PhongViewHolder v = new PhongViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull PhongViewHolder holder, int position) {
        Phong p = list.get(position);
        holder.tvsoPhong.setText( "Phòng"+" :  " +p.getSoPhong()+"");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bt_sheet_phong);
                LinearLayout edLayout=dialog.findViewById(R.id.layoutedit);
                LinearLayout deleteLayout=dialog.findViewById(R.id.layoutdelete);
                LinearLayout detaillayout=dialog.findViewById(R.id.layoutdetail);
                deleteLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                        alertDialog.setTitle("Thông báo!");
                        alertDialog.setMessage("Bạn có muốn xóa Phòng này không?");
                        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(dao.delete(p.getMaPhong())){
                                    Toast.makeText(context,"xóa thành công",Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll(dao.getAll());
                                    notifyDataSetChanged();
                                }else {
                                    Toast.makeText(context,"xóa k thành công",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "Hủy xóa", Toast.LENGTH_SHORT).show();
                            }
                        });

                        alertDialog.show();
                        dialog.dismiss();
                    }
                });
                edLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openupdate(p);
                        dialog.dismiss();
                    }
                });
                detaillayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        opendetail(p);
                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        });
    }


    public  void openupdate(Phong p){
        Dialog dialog=new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_updatephong);
        EditText edMota=dialog.findViewById(R.id.edmota);
        edMota.setText(p.getMoTa());
        TextView txtloimoTa=dialog.findViewById(R.id.loimotaup);
        TextView txtsop=dialog.findViewById(R.id.tv_updatesophong);
        txtsop.setText(p.getSoPhong()+"");
        Button btnupdate=dialog.findViewById(R.id.btn_update_phong);
        Button btnhuy=dialog.findViewById(R.id.btn_huyupd_phong);
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check=true;
                p.setMoTa(edMota.getText().toString());
                String moTa=edMota.getText().toString();
                if(moTa.length()==0){
                    txtloimoTa.setText("Bạn đang  để trống mô tả phòng");
                    check=false;
                }
                if(moTa.length()<5||moTa.length()>1000){
                    txtloimoTa.setText("Mô tả phải lớn hơn 5 kí tự và nhỏ hơn 1000 kí tự");
                    check=false;
                }
                if(check==true){
                    if(dao.update(p)){
                        Toast.makeText(context,"cập nhật thành công",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        list.clear();
                        list.addAll(dao.getAll());
                        notifyDataSetChanged();
                    }else {
                        Toast.makeText(context,"cập nhật k thành công",Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
        dialog.show();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    private  void  opendetail(Phong p){
        Dialog dialog=new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_detailphong);
        TextView txtsop=dialog.findViewById(R.id.tvdetailsophong);
        TextView txtmota=dialog.findViewById(R.id.txtdetail_mota);
        ImageView hinhanh=dialog.findViewById(R.id.anhphongchitiet);
        txtsop.setText("Số phòng :" +"  "+ p.getSoPhong()+"");
        txtmota.setText("Mô tả :"  +"  "+p.getMoTa());
        hinhanh.setImageResource(p.getHinhanh());

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.CENTER);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


 //cash tìm kiếm searchView
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch=charSequence.toString();
                if (strSearch.isEmpty()){
                    liSearch=listPhong;
                }else {
                    List<Phong> list= new ArrayList<>();
                    for (Phong phong:listPhong) {
                        //nếu số phòng ta tìm bằng số phòng nhập vào thì hiển thị
                        if (phong.getMoTa().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(phong);
                        }
                    }
                    liSearch=list;
                }
                FilterResults filterResults= new FilterResults();
                filterResults.values=liSearch;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                liSearch= (List<Phong>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class PhongViewHolder extends RecyclerView.ViewHolder {
        TextView tvsoPhong;
        CardView cardView;
        LinearLayout liedit, lidelete, lidetail;

        public PhongViewHolder(@NonNull View view) {
            super(view);
            tvsoPhong = view.findViewById(R.id.tv_sophong);
            cardView = view.findViewById(R.id.cardview);
            liedit = view.findViewById(R.id.layoutedit);
            lidelete = view.findViewById(R.id.layoutdelete);
            lidetail = view.findViewById(R.id.layoutdetail);
        }
    }


}
