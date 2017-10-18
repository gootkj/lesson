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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Lab08_MyDialogFragment extends DialogFragment {

    private View mDialogView;
    EditText et_price;
    private AlertDialog mDialog;
    private Spinner mSpinner;

    private Activity activity;
    private Lab08_MySpinnerAdapter mSpinnerAdapter;
    private MyDialogListener listener = new MyDialogListener();
    private Lab08_MyListAdapter.MyListAdapterHandler handler;
    private List<Lab08_Coffee> mCoffeeList = new ArrayList<>();

    public Lab08_MyDialogFragment() {
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


    //為了解除耦合製作介面，改寫到Lab08_MyListAdapter
//    public interface MyDialogHandler{
//        void btn_nAdd_click(Lab08_Coffee coffee);
//        void btn_nCancel_click();
//    }
    
    //確認Activity有實作Lab08_MyListAdapter.MyListAdapterHandler
    private void initHandler() {
        try {
//            handler = (MyDialogHandler) activity;
            handler = (Lab08_MyListAdapter.MyListAdapterHandler) activity;
        }catch (ClassCastException cause){
//            String message = "搞什麼麻，Activity 沒有實作MyDialogHandle";
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

        et_price = (EditText)mDialogView.findViewById(R.id.coffee_price);
    }

    //spinner下拉選單部分
    private void initSpinner() {
        try {
            mSpinner = (Spinner)mDialogView.findViewById(R.id.coffee_spinner);

            mSpinnerAdapter = new Lab08_MySpinnerAdapter(activity);
            mSpinner.setAdapter(mSpinnerAdapter);
            mSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)activity);
            int position = 0;
            mSpinner.setSelection(position);
        }catch (ClassCastException cause){
            String message = "搞什麼麻，Activity 沒有實作AdapterView.OnItemSelectedListener";
            throw  new Lab08_MyDialogFragmentException(message,cause);
        }

    }

    //Dialog部分
    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("新增商品")
                .setIcon(android.R.drawable.ic_input_add)
                .setView(mDialogView)
                .setPositiveButton("確定", listener)
                .setNegativeButton("取消", listener);
        mDialog = builder.create();
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_lab08__my_dialog, container, false);
//    }

    //MyDialogFragment用
    public class MyDialogListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i){

                case DialogInterface.BUTTON_POSITIVE:

//                    handler.btn_nAdd_click(coffee);
                    if(handler.priceCheck(et_price.getText().toString())){
                        btn_Add_click();
                    }
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
//                    handler.btn_nCancel_click();
                    break;

            }
        }
    }

    private Lab08_Coffee getCoffee() {
        //咖啡名稱 = 下拉選單的選擇
        int position = mSpinner.getSelectedItemPosition();
        String title = mSpinnerAdapter.getCoffee_titles().getString(position);
        //咖啡圖檔 = 下拉選單的選擇
        int img_resource_id = mSpinnerAdapter.getImg_resource_id_array()[position];
        //咖啡價格 = 價格欄位輸入
        String priceStr = et_price.getText().toString();
        int price = Integer.parseInt(priceStr);

        return new Lab08_Coffee(title,price,img_resource_id);
    }

    //按下新增
    private void btn_Add_click() {
        Lab08_Coffee coffee = getCoffee();
        mCoffeeList = handler.getmCoffeeList();
        mCoffeeList.add(coffee);
        handler.listAdapterUpdate(coffee , "新增成功");
    }

}
