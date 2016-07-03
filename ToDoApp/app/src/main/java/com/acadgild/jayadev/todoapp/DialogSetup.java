package com.acadgild.jayadev.todoapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.List;

/**
 * Created by jayad on 31-05-2016.
 */
public class DialogSetup extends DialogFragment {
    Context context;
    MyDatabaseHelper db;
    MainActivity act=new MainActivity();
    int updateid;
    AdapterClass ad=new AdapterClass();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog, container);
        Button savebtn=(Button)view.findViewById(R.id.Sav);
        Button cancelbtn=(Button)view.findViewById(R.id.Cnl);
        final EditText title=(EditText)view.findViewById(R.id.title);
        final EditText description=(EditText)view.findViewById(R.id.desc);
        final DatePicker datedata=(DatePicker)view.findViewById(R.id.datepick);
        db=new MyDatabaseHelper(getActivity());
        final ToDo todoobj=new ToDo();

        //mlisten=(listUpdate)getActivity();

        final int i=getArguments().getInt("Ins/del");
        if(i==1){
            updateid=getArguments().getInt("selectid");
            Cursor cr=db.getToDo(updateid);
            cr.moveToFirst();

            title.setText(cr.getString(cr.getColumnIndex("title")));
            description.setText(cr.getString(cr.getColumnIndex("description")));
            String[] datedat=cr.getString(cr.getColumnIndex("date")).split("/");
            Log.i("date ", datedat[0]+"/"+datedat[1]+"/"+datedat[2]);
            datedata.updateDate(Integer.parseInt(datedat[2]),(Integer.parseInt(datedat[1])-1),Integer.parseInt(datedat[0]));

        }

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {

                    todoobj.setTitle(title.getText().toString());
                    todoobj.setDescription(description.getText().toString());
                    todoobj.setDate(datedata.getDayOfMonth() + "/" + (datedata.getMonth() + 1) + "/" + datedata.getYear());
                    db.createToDo(todoobj);

                    getDialog().dismiss();
                    ((MainActivity)getActivity()).refrehsList();
                }
                if (i == 1) {
                    todoobj.setTitle(title.getText().toString());
                    todoobj.setDescription(description.getText().toString());
                    todoobj.setDate(datedata.getDayOfMonth() + "/" + (datedata.getMonth() + 1) + "/" + datedata.getYear());
                    todoobj.setId(updateid);
                    db.updateToDo(todoobj);
                    getDialog().dismiss();
                    ((MainActivity)getActivity()).refrehsList();
                }
            }
        });

        return view;
    }

  /*  public interface listUpdate{
        public void onDialogDismis();
    }*/
}