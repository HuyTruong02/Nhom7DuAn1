package com.example.duan1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Model.Phong;
import com.example.duan1.R;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    List<Phong> list;
    Context context;
    public SpinnerAdapter(List<Phong> list) {
        this.list = list;

    }

    public SpinnerAdapter(List<Phong> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {


        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        TextView txtphong;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder= new ViewHolder();
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemspinnerhoadon,parent,false);
            viewHolder.txtphong = convertView.findViewById(R.id.sophong1);
            convertView.setTag(viewHolder);
        }else
            viewHolder = (ViewHolder) convertView.getTag();

        Phong phong = list.get(position);

        viewHolder.txtphong.setText(String.valueOf(phong.getSoPhong()));


        return convertView;
    }
}
