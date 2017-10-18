package com.example.student.test.Lab07_Quizzes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.student.test.R;

/**
 * Created by student on 2017/9/26.
 */

public class Lab07_AnimActivity extends AppCompatActivity {
    protected void startActivityAnim(Intent in){
        startActivity(in);
        //overridePendingTransition(進場效果，出場效果);
        overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
    }
}
