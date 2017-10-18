package com.example.student.test.Lab07_Quizzes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.student.test.R;

public class Lab07_ResultActivity extends Lab07_AnimActivity {

    TextView m_tv_result;
    CharSequence q1Answer;
    CharSequence q2Answer;
    CharSequence q3Answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab07__result);

        m_tv_result = (TextView)findViewById(R.id.tv_result);
        //確認收到的答案
        q1Answer = getIntent().getStringExtra(Lab07_Activities.Q1_ANSWER_KEY);
        q2Answer = getIntent().getStringExtra(Lab07_Activities2.Q2_ANSWER_KEY);
        q3Answer = getIntent().getStringExtra(Lab07_Activities3.Q3_ANSWER_KEY);
//        System.out.println("q1Answer = " + q1Answer );
//        System.out.println("q2Answer = " + q2Answer );
//        System.out.println("q3Answer = " + q3Answer );
        setResult();
    }

    //Fragment用
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        setResult();
//    }
//
//    private void setResult() {
//
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0 ; i < Lab07_Activities.result.length ; i++)
//        {
//            int num = i + 1 ;
//            sb.append(String.valueOf(num) + "." + Lab07_Activities.result[i] + "\n");
//        }
//        m_tv_result.setText(sb);
//    }

    //include merge用
    private void setResult() {

        StringBuilder sb = new StringBuilder();
        sb.append("1." + q1Answer + "\n");
        sb.append("2." + q2Answer + "\n");
        sb.append("3." + q3Answer + "\n");
        m_tv_result.setText(sb);
    }

    public void back(View view) {
        Intent in = new Intent();
        in.setClass(this,Lab07_Quizzes.class);
        //startActivity(in);
        startActivityAnim(in);
    }
}
