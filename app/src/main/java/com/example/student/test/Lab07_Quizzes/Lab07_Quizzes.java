package com.example.student.test.Lab07_Quizzes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.student.test.R;

public class Lab07_Quizzes extends Lab07_AnimActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab07__quizzes);
    }

    public void start(View view) {
        Intent in = new Intent();
        in.setClass(this,Lab07_Activities.class);
//        startActivity(in);
//        //overridePendingTransition(進場效果，出場效果);
//        overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
        startActivityAnim(in);
    }
}
