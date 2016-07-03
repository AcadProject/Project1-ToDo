package com.acadgild.jayadev.todoapp;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    MyDatabaseHelper db=new MyDatabaseHelper(this);
    List<ToDo> list;
    AdapterClass adaptor;
    ListView listView;
    ToDo selected;
    int selectedid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView=(ListView)findViewById(R.id.list);
        list=db.getAllToDos();
        //Collections.sort(list);
        adaptor=new AdapterClass(this,list);
        listView.setAdapter(adaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selected=list.get(position);
                selectedid=selected.getId();

                /*MyDatabaseHelper bd=new MyDatabaseHelper(MainActivity.this);

                bd.deleteItem(selectedid);
                bd.close();
                refrehsList();*/
                android.app.FragmentManager fmg=getFragmentManager();
                DialogSetup dialog=new DialogSetup();
                Bundle bund=new Bundle();
                bund.putInt("Ins/del",1);
                bund.putInt("selectid",selectedid);

                dialog.setArguments(bund);

                dialog.show(fmg,"test");

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selected=list.get(position);
                selectedid=selected.getId();
                MyDatabaseHelper bd=new MyDatabaseHelper(MainActivity.this);
                bd.taskDone(selectedid);
                refrehsList();
                return true;
            }
        });



    }

    public void refrehsList() {
        list=db.getAllToDos();
        adaptor.updatedList(list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater M=getMenuInflater();
        M.inflate(R.menu.menu_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.AddMe){
            android.app.FragmentManager fm=getFragmentManager();
            DialogSetup dlg=new DialogSetup();
            Bundle bun=new Bundle();
            bun.putInt("Ins/del", 0);
            dlg.setArguments(bun);
            dlg.show(fm,"test");

            return true;
        }
        else if(id==R.id.Done){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refrehsList();
    }


    /*@Override
    public void onDialogDismis() {
        refrehsList();
    }*/
}
