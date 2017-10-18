package com.example.student.test;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Lab08_Spinner extends AppCompatActivity
        implements Lab08_MyListAdapter.MyListAdapterHandler,
        AdapterView.OnItemSelectedListener,
        AdapterView.OnItemClickListener{
//    Lab08_MyDialogFragment.MyDialogHandler
    FloatingActionButton fab ;
    private static final String TAG = "Lab08_Spinner";
    private ListView mListView;
    private Lab08_MyListAdapter myListAdapter;
    private List<Lab08_Coffee> mCoffeeList = new ArrayList<>();
    private int ClickedCoffeeIndex = 0;
    private static final String FILENAME = "coffeelist.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab08__spinner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initFab();
        initListView();
    }

    @Override
    protected void onStart() {
        super.onStart();


        restore();
        myListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();

        save();
    }

    private void initListView() {
        mListView = (ListView)findViewById(R.id.listview);
        mListView.setEmptyView(findViewById(R.id.empty));
        mListView.setAdapter(new Lab08_MyListAdapter(this));
        mListView.setOnItemClickListener(this);

        myListAdapter = (Lab08_MyListAdapter) mListView.getAdapter();
    }

    //右下角按鈕
    private void initFab() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialog = new Lab08_MyDialogFragment();
                dialog.show(getSupportFragmentManager(),"MyDialogFragment");
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void btn_nAdd_click(Lab08_Coffee coffee) {
//        Snackbar.make(fab, "收到確定 coffee = " + coffee, Snackbar.LENGTH_SHORT)
//                        .setAction("Action", null).show();
//        //Log.d(TAG,"收到確定 coffee = " + coffee);
//        mCoffeeList.add(coffee);
//
//        listAdapterUpdate();
//    }
//
//    @Override
//    public void btn_nCancel_click() {
//        Snackbar.make(fab, "收到取消", Snackbar.LENGTH_SHORT)
//                        .setAction("Action", null).show();
//    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d(TAG,"onItemSelected , position = " + i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Snackbar.make(fab, "點選了第" + i + "項", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
        Lab08_Coffee coffee = mCoffeeList.get(i);

        DialogFragment dialog = new Lab08_UpdateDialogFragment();
        ClickedCoffeeIndex = i;
        //((Lab08_UpdateDialogFragment)dialog).updateCoffee();
        dialog.show(getSupportFragmentManager(),"UpdateDialogFragment");

    }

    //確認價格為整數
    @Override
    public boolean priceCheck(String priceStr) {
        try {
            int price = Integer.parseInt(priceStr);
            return true;
        }catch (NumberFormatException e) {
            Snackbar.make(fab, "價格需為整數", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
            return false;
        }
    }

    @Override
    public List<Lab08_Coffee> getmCoffeeList() {
        return mCoffeeList;
    }

    @Override
    public int getClickedCoffeeIndex() {
        return ClickedCoffeeIndex;
    }

    @Override
    public void listAdapterUpdate(Lab08_Coffee coffee , String message) {
//        Snackbar.make(fab, "收到確定 coffee = " + coffee, Snackbar.LENGTH_SHORT)
//                .setAction("Action", null).show();
        Snackbar.make(fab, message + " coffee = " + coffee, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
        myListAdapter.notifyDataSetChanged();
    }

    //存檔
    private void save(){
        ObjectOutputStream oos = null;
        try{
            try {
                FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);//開啟寫入檔案
                oos = new ObjectOutputStream(fos);
                oos.writeObject(mCoffeeList);
                Log.d(TAG, "存檔成功");
            }finally {
                if(oos != null){
                    oos.close();
                }
            }
        }catch (IOException e) {
            Log.d(TAG, e.toString());
        }
    }

    //讀檔
    private void restore(){
        FileInputStream fis = null;
        try{
            try {
                fis = openFileInput(FILENAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
                mCoffeeList = (List)ois.readObject();
                Log.d(TAG, "讀檔成功");
            }finally {
                if(fis != null){
                    fis.close();
                }
            }
        }catch (IOException | ClassNotFoundException e) {
            Log.d(TAG, e.toString());
        }
    }

}
