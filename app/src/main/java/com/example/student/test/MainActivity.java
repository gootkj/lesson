package com.example.student.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.student.test.Lab01_View.Lab01_View;
import com.example.student.test.Lab02_Order.Lab02_Order;
import com.example.student.test.Lab03_Fragment.Lab03_CourtCounter;
import com.example.student.test.Lab04_PickerActivity.Lab04_ColorPickerActive;
import com.example.student.test.Lab05_Dialog.Lab05_Dialog;
import com.example.student.test.Lab06_Animation.Lab06_AnimationDrawable;
import com.example.student.test.Lab07_Quizzes.Lab07_Quizzes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void myClick(View view) {
        Intent in = new Intent();
        int id = view.getId();
        switch (id){
            case R.id.Lab01:
                in.setClass(this,Lab01_View.class);
                break;
            case R.id.Lab02:
                in.setClass(this,Lab02_Order.class);
                break;
            case R.id.Lab03:
                in.setClass(this,Lab03_CourtCounter.class);
                break;
            case R.id.Lab04:
                in.setClass(this,Lab04_ColorPickerActive.class);
                break;
            case R.id.Lab05:
                in.setClass(this,Lab05_Dialog.class);
                break;
            case R.id.Lab06:
                in.setClass(this,Lab06_AnimationDrawable.class);
                break;
            case R.id.Lab07:
                in.setClass(this,Lab07_Quizzes.class);
                break;
            case R.id.Lab08:
                in.setClass(this,Lab08_Spinner.class);
                break;
        }
        startActivity(in);
    }
}
