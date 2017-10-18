package com.example.student.test.Lab07_Quizzes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.student.test.R;

public class Lab07_Activities3 extends Lab07_AnimActivity {

    public static final String Q3_ANSWER_KEY = "Q3";

    private TextView m_tv_no;
    private TextView m_tv_question;
    private RadioGroup m_radio_group;
    private RadioButton m_radio_a;
    private RadioButton m_radio_b;
    private RadioButton m_radio_c;
    private RadioButton m_radio_temp;
    private CharSequence m_answer;

    CharSequence q1Answer;
    CharSequence q2Answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab07__activities3);

        //初始化
        init();
        //確認收到的答案
        Intent q2Intent = getIntent();
        q1Answer = q2Intent.getStringExtra(Lab07_Activities.Q1_ANSWER_KEY);
        q2Answer = q2Intent.getStringExtra(Lab07_Activities2.Q2_ANSWER_KEY);
        System.out.println("q1Answer = " + q1Answer );
        System.out.println("q2Answer = " + q2Answer );
    }

    private void init() {
        m_tv_no = (TextView)findViewById(R.id.tv_question_number);
        m_tv_question = (TextView)findViewById(R.id.tv_question);
        m_radio_group = (RadioGroup)findViewById(R.id.radio_group);
        m_radio_a = (RadioButton)findViewById(R.id.radio_a);
        m_radio_b = (RadioButton)findViewById(R.id.radio_b);
        m_radio_c = (RadioButton)findViewById(R.id.radio_c);

        m_tv_no.setText("3");
        //檢查系統版本
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            Spanned result;

            result = Html.fromHtml(getString(R.string.question_3),Html.FROM_HTML_MODE_LEGACY);
            m_tv_question.setText(result);
            result = Html.fromHtml(getString(R.string.question_3_radio_a ),Html.FROM_HTML_MODE_LEGACY);
            m_radio_a.setText(result);
            result = Html.fromHtml(getString(R.string.question_3_radio_b),Html.FROM_HTML_MODE_LEGACY);
            m_radio_b.setText(result);
            result = Html.fromHtml(getString(R.string.question_3_radio_c),Html.FROM_HTML_MODE_LEGACY);
            m_radio_c.setText(result);
        }
        else
        {
            m_tv_question.setText(Html.fromHtml(getString(R.string.question_3)));
            m_radio_a.setText(Html.fromHtml(getString(R.string.question_3_radio_a)));
            m_radio_b.setText(Html.fromHtml(getString(R.string.question_3_radio_b)));
            m_radio_c.setText(Html.fromHtml(getString(R.string.question_3_radio_c)));
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_back3:
                finish();
                break;
            case R.id.btn_done:
                done();
                break;
            case R.id.btn_dialog:
                dialog();
                break;
        }
    }

    public void done(){
        int id = m_radio_group.getCheckedRadioButtonId();
        //沒選不動作
        if (id != -1) {
            //抓選取的答案
            getCheckedAnswer(id);

            //去下個Activities
            goResultPage();

        }
    }

    //m_answer先抓選取的答案
    public void getCheckedAnswer(int id){
        m_radio_temp = (RadioButton) findViewById(id);
        m_answer = m_radio_temp.getTag().toString();
        System.out.println("你選擇的答案: " + m_answer);
    }

    //把值傳到下個Activities
    private void goResultPage() {
        Intent intent = new Intent(this, Lab07_ResultActivity.class);

        intent.putExtra(Lab07_Activities.Q1_ANSWER_KEY, q1Answer);
        intent.putExtra(Lab07_Activities2.Q2_ANSWER_KEY, q2Answer);
        intent.putExtra(Lab07_Activities3.Q3_ANSWER_KEY, m_answer);
        //startActivity(intent);
        startActivityAnim(intent);
    }

    private void dialog() {
        int id = m_radio_group.getCheckedRadioButtonId();
        //沒選不動作
        if (id != -1) {
            //抓選取的答案
            getCheckedAnswer(id);

            AlertDialogYesNoListener Listener = new AlertDialogYesNoListener();
            StringBuilder sb = new StringBuilder();
            sb.append("您的作答如下\n");
            sb.append(q1Answer + "\n");
            sb.append(q2Answer + "\n");
            sb.append(m_answer + "\n");
            sb.append("確定要結束?");
            new AlertDialog.Builder(this)
                    .setMessage(sb)
                    .setPositiveButton("YES", Listener)
                    .setNegativeButton("NO", Listener)
                    .show();
        }
    }

    private class AlertDialogYesNoListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i){
                case DialogInterface.BUTTON_POSITIVE:
                    goResultPage();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    //goResultPage();
                    break;
            }
        }
    }
}
