package com.example.student.test.Lab05_Dialog;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.student.test.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Lab05_MyDialogFragment extends DialogFragment {

    private int count = 0;
    private StringBuilder Str;
    private EditText m_et_username;
    private Lab05_Dialog.MyDialogFragmentListener Listener;

    public Lab05_MyDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    //onCreateDialog方法負責產生對話框
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //從fragment.xml 取得自訂畫面
        //inflater( resource , viewGroup )
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_lab05_my_dialog,null);

        m_et_username = (EditText)view.findViewById(R.id.et_username);

        Str = new StringBuilder();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("Sign in", Listener)
                .setNegativeButton("Cancel", Listener);

        return builder.create();
    }

    public void setListener(Lab05_Dialog.MyDialogFragmentListener Listener){
        this.Listener = Listener;
    }

    public StringBuilder login(){
        Str.append("次數" + (++count) + "歡迎光臨" + m_et_username.getText());
        return Str;
    }
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lab05_my_dialog, container, false);
    }
    */

}
