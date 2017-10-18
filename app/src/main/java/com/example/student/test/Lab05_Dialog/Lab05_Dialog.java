package com.example.student.test.Lab05_Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.student.test.R;

public class Lab05_Dialog extends AppCompatActivity implements DialogInterface.OnClickListener{

    TextView m_tv_message;
    Lab05_MyDialogFragment dialog = null;
    MyDialogFragmentListener dialogListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab05__dialog);

        init();
    }

    private void init() {
        m_tv_message = (TextView)findViewById(R.id.tv_message);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        m_tv_message.setText("我知道");
    }

    //委託給Lab05_Dialog(this)類別處理
    public void clickAlertDialog(View view) {
        new AlertDialog.Builder(this)
                .setMessage("你好帥喔")
                .setPositiveButton("我知道",this)
                .show();

    }

    //委託給內部類別AlertDialogYesNoListener(Listener)處理
    public void clickAlertDialogYesNo(View view) {
        AlertDialogYesNoListener Listener = new AlertDialogYesNoListener();
        new AlertDialog.Builder(this)
                .setMessage("你好帥喔")
                .setPositiveButton("我知道",Listener)
                .setNegativeButton("狗腿",Listener)
                .show();
    }

    private class AlertDialogYesNoListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i){
                case DialogInterface.BUTTON_POSITIVE:
                    m_tv_message.setText("我知道");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    m_tv_message.setText("狗腿");
                    break;
            }
        }
    }

    //給內部匿名類別處理
    public void clickAlertDialogYesNoCancel(View view) {
        new AlertDialog.Builder(this)
                .setMessage("你好帥喔")
                .setPositiveButton("我知道", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        m_tv_message.setText("我知道");
                    }
                })
                .setNegativeButton("狗腿", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        m_tv_message.setText("狗腿");
                    }
                })
                .setNeutralButton("不知該說什麼", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        m_tv_message.setText("不知該說什麼");
                    }
                })
                .show();
    }

    //AlertDialogItems
    public void clickAlertDialogItems(View view) {
        //資源檔res->values->arrays
        final String[] response = getResources().getStringArray(R.array.response);
        new AlertDialog.Builder(this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar)
                .setTitle("你好帥")
                .setItems(response, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        m_tv_message.setText(response[i]);
                    }
                })
                .show();
    }

    //AlertDialogMultiChoice
    public void clickAlertDialogMultiChoice(View view) {
        final String[] response = getResources().getStringArray(R.array.response);
        final boolean[] selected = new boolean[response.length];


        new AlertDialog.Builder(this)
                .setTitle("你好帥")
                .setMultiChoiceItems(response, selected, new DialogInterface.OnMultiChoiceClickListener() {
                    //每次 勾選 或 取消勾選 時執行
                    //i 這次點選了哪個項目
                    //b 這次點選後的狀態
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder result = new StringBuilder();
                        for (int j = 0 ;j < selected.length ; j++)
                        {
                            if (selected[j]) {
                                result.append(response[j]).append("\n");
                            }
                        }
                        m_tv_message.setText(result);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        m_tv_message.setText("無語");
                    }
                })
                .show();
    }

    //AlertDialogSingleChoice
    private int mChoice;
    public void clickAlertDialogSingleChoice(View view) {
        final String[] response = getResources().getStringArray(R.array.response);
        mChoice = 0;//預設選第1項
        new AlertDialog.Builder(this)
                .setTitle("你好帥")
                .setSingleChoiceItems(response, mChoice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mChoice = i;//將選擇項目記錄下來，不然好像抓不到
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        m_tv_message.setText(response[mChoice]);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        m_tv_message.setText("無語");
                    }
                })
                .show();
    }

    public void clickMyDialogFragment(View view) {
        if(dialog == null) {
            dialog = new Lab05_MyDialogFragment();
            dialogListener = new MyDialogFragmentListener();
            dialog.setListener(this.dialogListener);
        }
        dialog.show(getSupportFragmentManager(),"Lab05_MyDialogFragment");
    }

    //MyDialogFragment用
    public class MyDialogFragmentListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i){

                case DialogInterface.BUTTON_POSITIVE:
                    m_tv_message.setText(dialog.login());
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    m_tv_message.setText("登入取消");
                    break;

            }
        }
    }

    public MyDialogFragmentListener getListener(){
        return dialogListener;
    }
}
