package com.acadgild.jayadev.todoapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jayad on 30-05-2016.
 */
public class AdapterClass extends BaseAdapter {
    private Context context;
    List<ToDo> toDoList;
    public  AdapterClass(){

    }
    public AdapterClass(Context context, List<ToDo> toDoList){
        this.context=context;
        this.toDoList=toDoList;
    }
    @Override
    public int getCount() {
        return toDoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.listitem,parent,false);
        }

        TextView text1=(TextView)convertView.findViewById(R.id.tx1);
        TextView text2=(TextView)convertView.findViewById(R.id.tx2);
        TextView text3=(TextView)convertView.findViewById(R.id.tx3);
        TextView text4=(TextView)convertView.findViewById(R.id.tx4);
        ImageView img=(ImageView)convertView.findViewById(R.id.imgsts);
        int status;

        text1.setText(toDoList.get(position).getDate());
        text2.setText(toDoList.get(position).getTitle());
        text3.setText(toDoList.get(position).getDescription());
        text4.setText(toDoList.get(position).getDate());
        switch (toDoList.get(position).getStatus()){
            case 0: img.setImageResource(R.drawable.ntdoneimg);
                break;
            case 1: img.setImageResource(R.drawable.doneimg);
                break;
            default: img.setImageResource(R.mipmap.ic_launcher);
                break;
        }
        return convertView;
    }
    public void updatedList(List<ToDo> list){
        this.toDoList=list;
        this.notifyDataSetChanged();
    }
}
