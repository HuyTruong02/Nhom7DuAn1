package com.example.duan1.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.Activity.interfaceDeleteClickdistioner;
import com.example.duan1.DAO.HoaDonDAO;
import com.example.duan1.DAO.PhongDAO;
import com.example.duan1.Model.HoaDon;
import com.example.duan1.Model.Phong;
import com.example.duan1.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class HoaDonAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HoaDon> list = new ArrayList<>();
    private com.example.duan1.Activity.interfaceDeleteClickdistioner interfaceDeleteClickdistioner;
    HoaDonDAO dao;
    List<HoaDon> listhd;


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

    public static int sophong;

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
        sophong = hoaDon.getSoPhong();
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


                        Button btn_sua_HD = view.findViewById(R.id.btn_them_HDons);
                        Button btn_huy_HD = view.findViewById(R.id.btn_huy_HDons);

                        ImageView image_ngaybatdau = view.findViewById(R.id.image_ngaybatdaus);
                        ImageView image_ngayketthuc = view.findViewById(R.id.image_ngayketthucs);
                        Button buttontongtien = view.findViewById(R.id.tongtiens);
                        EditText edsophong = view.findViewById(R.id.ed_updatesophong);
                        EditText edt_tienDien_HDon = view.findViewById(R.id.ed_TienDien_HDons);
                        EditText edt_tienNuoc_HDon = view.findViewById(R.id.ed_TienNuoc_HDons);
                        EditText edt_tienPhong_HDon = view.findViewById(R.id.ed_TienPhong_HDons);
                        EditText edt_tongTien_HDon = view.findViewById(R.id.ed_TongTien_HDons);
                        EditText edt_chiPhiKhac_HDon = view.findViewById(R.id.ed_ChiPhiKhac_HDons);
                        EditText edt_batdau_hd = view.findViewById(R.id.ed_batDau_HDons);
                        EditText edt_hethan_hd = view.findViewById(R.id.ed_ketThuc_HDons);
                        Phong phong = new Phong();
                        PhongDAO phongDAO = new PhongDAO(context);
                        phongDAO.open();
                        ArrayList<Phong> listt= phongDAO.getAll();
                        Log.d("zzz", String.valueOf(listt));
                        edsophong.setText(hoaDon.getSoPhong()+"");

                        HoaDon hoaDon1 =list.get(i);
                        edt_batdau_hd.setText(hoaDon1.getNgayBatDau());
                        edt_hethan_hd.setText(hoaDon1.getNgayHetHan());
                        edt_tienDien_HDon.setText(hoaDon1.getTienDien()+"");


                      edt_tienNuoc_HDon.setText(hoaDon1.getTienNuoc()+"");
                      edt_tienPhong_HDon.setText(hoaDon1.getTienPhong()+"");
                      edt_chiPhiKhac_HDon.setText(hoaDon1.getChiPhiKhac()+"");
                      edt_tongTien_HDon.setText(hoaDon1.getTongTien()+"");







                        buttontongtien.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String dien =edt_tienDien_HDon.getText().toString();
                                int dien1 = Integer.parseInt(dien);
                                String nuoc =edt_tienNuoc_HDon.getText().toString();
                                int nuoc1 = Integer.parseInt(nuoc);
                                String phong =edt_tienPhong_HDon.getText().toString();
                                int phong1 = Integer.parseInt(phong);
                                String khac =edt_chiPhiKhac_HDon.getText().toString();
                                int khac1 = Integer.parseInt(khac);
                                int tong = (dien1*4000)+(nuoc1*3000)+phong1+khac1;
                                edt_tongTien_HDon.setText(String.valueOf(tong));

                            }
                        });
                        btn_sua_HD.setOnClickListener(new View.OnClickListener() {


                            @Override
                            public void onClick(View v) {
                                hoaDon.setSoPhong(Integer.parseInt((edsophong.getText().toString())));
                                hoaDon.setNgayBatDau(edt_batdau_hd.getText().toString());
                                hoaDon.setNgayHetHan(edt_hethan_hd.getText().toString());
                                hoaDon.setTienDien(Integer.parseInt(edt_tienDien_HDon.getText().toString()));
                                hoaDon.setTienNuoc(Integer.parseInt(edt_tienNuoc_HDon.getText().toString()));
                                hoaDon.setTienPhong(Integer.parseInt(edt_tienPhong_HDon.getText().toString()));
                                hoaDon.setChiPhiKhac(Integer.parseInt(edt_chiPhiKhac_HDon.getText().toString()));
                                hoaDon.setTongTien(Integer.parseInt(edt_tongTien_HDon.getText().toString()));

                                if(dao.updateHoaDon(hoaDon)){
                                    Toast.makeText(context,"cập nhật thành công",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    listhd.clear();
                                    listhd.addAll(dao.getAll());
                                    notifyDataSetChanged();
                                }else {
                                    Toast.makeText(context,"cập nhật k thành công",Toast.LENGTH_SHORT).show();

                                }
                                dialog.dismiss();
                            }
                        });




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
                                        GregorianCalendar gregorianCalendar =new GregorianCalendar(y,m,d);
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
//                        Intent intent = new Intent(context, Detail_HoaDon.class);
//                        context.startActivity(intent);
                        opendetail(hoaDon);
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

        return view;
    }
    private  void  opendetail(HoaDon hoadon){
        Dialog dialog=new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_hoadon);

        TextView edsophong = dialog.findViewById(R.id.sophongchitiet);
        TextView edt_tienDien_HDon = dialog.findViewById(R.id.tiendienchitiet);
        TextView edt_tienNuoc_HDon = dialog.findViewById(R.id.tiennuocchitiet);
        TextView edt_tienPhong_HDon = dialog.findViewById(R.id.tienphongchitiet);
        TextView edt_tongTien_HDon = dialog.findViewById(R.id.tongtienchitiet);
        TextView edt_chiPhiKhac_HDon = dialog.findViewById(R.id.chiphikhacchitiet);
        TextView edt_batdau_hd = dialog.findViewById(R.id.ngaybatdauchitiet);
        TextView edt_hethan_hd = dialog.findViewById(R.id.ngayhethanchitiet);

        edsophong.setText("Số phòng :" +"  "+ hoadon.getSoPhong());
        edt_batdau_hd.setText("Ngày bắt đầu :"  +"  "+hoadon.getNgayBatDau());
        edt_hethan_hd.setText("Ngày hết hạn :"  +"  "+hoadon.getNgayHetHan());
        edt_tienDien_HDon.setText("Tiền điện :"  +"  "+hoadon.getTienDien());
        edt_tienNuoc_HDon.setText("Tiền nước :"  +"  "+hoadon.getTienNuoc());
        edt_tienPhong_HDon.setText("Tiền phòng :"  +"  "+hoadon.getTienPhong());
        edt_chiPhiKhac_HDon.setText("Chi Phí Khác :"  +"  "+hoadon.getChiPhiKhac());
        edt_tongTien_HDon.setText("Tổng tiền :"  +"  "+hoadon.getTongTien());



        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

}
