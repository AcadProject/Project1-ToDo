package com.acadgild.jayadev.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jayad on 31-05-2016.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    // database version
    private static final int database_VERSION = 3;
    // database name
    private static final String database_NAME = "ToDoDB";

    private static final String table_TODO = "ToDoList";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_DATE = "date";
    private static final String KEY_STATUS = "status";



    public MyDatabaseHelper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TODO_TABLE = "CREATE TABLE ToDoList ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "title TEXT, " + "description TEXT, " + "date TEXT, " + "status INTEGER )";
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP  TABLE IF EXISTS ToDoList");
        this.onCreate(db);
    }

    public void createToDo(ToDo todo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, todo.getTitle());
        values.put(KEY_DESCRIPTION, todo.getDescription());
        values.put(KEY_DATE,todo.getDate());
        values.put(KEY_STATUS,0);
        db.insert(table_TODO,null,values);
        db.close();

    }

    public List<ToDo> getAllToDos() {
        List<ToDo> todos = new LinkedList<ToDo>();

        // select book query
        String query = "SELECT  * FROM " + table_TODO;

        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        ToDo todo = null;
        if (cursor.moveToFirst()) {
            do {
                todo = new ToDo();
                todo.setId(Integer.parseInt(cursor.getString(0)));
                todo.setTitle(cursor.getString(1));
                todo.setDescription(cursor.getString(2));
                todo.setDate(cursor.getString(3));
                todo.setStatus(cursor.getInt(4));
                // Add book to books
                todos.add(todo);
            } while (cursor.moveToNext());
        }
        return todos;
    }

    public void deleteItem(int selid){
        SQLiteDatabase db=this.getWritableDatabase();
        //Log.i("delete pos: ", String.valueOf(position));
        db.delete(table_TODO,KEY_ID + " = ? ",new String[]{String.valueOf(selid)});
        db.close();

    }

    public void updateToDo(ToDo updItem){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues valuess = new ContentValues();
        valuess.put(KEY_TITLE, updItem.getTitle());
        valuess.put(KEY_DESCRIPTION, updItem.getDescription());
        valuess.put(KEY_DATE,updItem.getDate());
        db.update(table_TODO, valuess,KEY_ID + " = ? ", new String[]{String.valueOf(updItem.getId())});
        db.close();
    }

    public Cursor getToDo(int updid){
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor c=db.rawQuery("Select * from ToDoList where " +KEY_ID+ " = "+updid, null);

        return c;
    }

    public void taskDone(int idDone){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("UPDATE ToDoList SET status=1 WHERE id="+idDone+"");
        db.close();
    }
}
