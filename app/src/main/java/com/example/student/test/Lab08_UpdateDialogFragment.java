package com.example.student.test;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Lab08_UpdateDialogFragment extends DialogFragment {

    View mDialogView;
    AlertDialog mDialog;
    TextView tv_coffee_price;

    private Activity activity;
    private List<Lab08_Coffee> coffees;
    private int index = 0;
    private UpdateDialogListener listener = new UpdateDialogListener();
    private Lab08_MyListAdapter.MyListAdapterHandler handler;
    private List<Lab08_Coffee> mCoffeeList = new ArrayList<>();

    public Lab08_UpdateDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        //初始化
        activity = getActivity();
        initHandler();
        initDialogView();
        initSpinner();
        initDialog();

        return mDialog;
    }

    //確認Activity有實作Lab08_MyListAdapter.MyListAdapterHandler
    private void initHandler() {
        try {
            handler = (Lab08_MyListAdapter.MyListAdapterHandler) activity;
        }catch (ClassCastException cause){
            String message = "搞什麼麻，Activity 沒有實作Lab08_MyListAdapter.MyListAdapterHandler";
            throw  new Lab08_MyDialogFragmentException(message,cause);
        }
    }

    //對話框從layout fragment.xml取得畫面
    private void initDialogView() {
        //inflater( resource , viewGroup )
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //spinner外觀部分
        mDialogView = inflater.inflate(R.layout.fragment_lab08__my_dialog,null);
    }

    //spinner下拉選單部分
    private void initSpinner() {

        Spinner mSpinner = (Spinner)mDialogView.findViewById(R.id.coffee_spinner);
        coffees = handler.getmCoffeeList();
        index = handler.getClickedCoffeeIndex();
        Lab08_UpdateSpinnerAdapter mSpinnerAdapter = new Lab08_UpdateSpinnerAdapter(activity,coffees.get(index));
        mSpinner.setAdapter(mSpinnerAdapter);
        //mSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)activity);
        int position = 0;
        mSpinner.setSelection(position);

        tv_coffee_price = (TextView) mDialogView.findViewById(R.id.coffee_price);
        tv_coffee_price.setText(String.valueOf(coffees.get(index).getPrice()));

    }

    //Dialog部分
    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("修改商品")
                .setIcon(android.R.drawable.ic_input_add)
                .setView(mDialogView)
                .setPositiveButton("修改", listener)
                .setNegativeButton("取消", listener)
                .setNeutralButton("刪除",listener);

        mDialog = builder.create();
    }

    //MyDialogFragment用
    public class UpdateDialogListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i){

                case DialogInterface.BUTTON_POSITIVE:
                    if(handler.priceCheck(tv_coffee_price.getText().toString())){
                        btn_update_click();
                    }
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    btn_cancel_click();
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    btn_delete_click();
                    break;

            }
        }
    }

    //按下修改
    void btn_update_click(){
        int price = Integer.parseInt(tv_coffee_price.getText().toString());
        coffees.get(index).setPrice(price);
        handler.listAdapterUpdate(coffees.get(index), "修改成功");
    }

    void btn_cancel_click(){

    }

    //按下刪除
    void btn_delete_click(){
        handler.listAdapterUpdate(coffees.get(index), "刪除成功");
        coffees.remove(index);
    }
}
