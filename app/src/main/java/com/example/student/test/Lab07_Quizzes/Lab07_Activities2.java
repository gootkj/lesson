package com.example.student.test.Lab07_Quizzes;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.student.test.R;

public class Lab07_Activities2 extends Lab07_AnimActivity {

    public static final String Q2_ANSWER_KEY = "Q2";

    private TextView m_tv_no;
    private TextView m_tv_question;
    private RadioGroup m_radio_group;
    private RadioButton m_radio_a;
    private RadioButton m_radio_b;
    private RadioButton m_radio_c;
    private RadioButton m_radio_temp;
    private CharSequence m_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab07__activities2);

        //初始化
        init();
        //確認收到的答案
        CharSequence q1Answer = getIntent().getStringExtra(Lab07_Activities.Q1_ANSWER_KEY);
        System.out.println("q1Answer = " + q1Answer );
    }

    private void init() {
        m_tv_no = (TextView)findViewById(R.id.tv_question_number);
        m_tv_question = (TextView)findViewById(R.id.tv_question);
        m_radio_group = (RadioGroup)findViewById(R.id.radio_group);
        m_radio_a = (RadioButton)findViewById(R.id.radio_a);
        m_radio_b = (RadioButton)findViewById(R.id.radio_b);
        m_radio_c = (RadioButton)findViewById(R.id.radio_c);

        m_tv_no.setText("2");
        //檢查系統版本
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            Spanned result;

            result = Html.fromHtml(getString(R.string.question_2),Html.FROM_HTML_MODE_LEGACY);
            m_tv_question.setText(result);
            result = Html.fromHtml(getString(R.string.question_2_radio_a ),Html.FROM_HTML_MODE_LEGACY);
            m_radio_a.setText(result);
            result = Html.fromHtml(getString(R.string.question_2_radio_b),Html.FROM_HTML_MODE_LEGACY);
            m_radio_b.setText(result);
            result = Html.fromHtml(getString(R.string.question_2_radio_c),Html.FROM_HTML_MODE_LEGACY);
            m_radio_c.setText(result);
        }
        else
        {
            m_tv_question.setText(Html.fromHtml(getString(R.string.question_2)));
            m_radio_a.setText(Html.fromHtml(getString(R.string.question_2_radio_a)));
            m_radio_b.setText(Html.fromHtml(getString(R.string.question_2_radio_b)));
            m_radio_c.setText(Html.fromHtml(getString(R.string.question_2_radio_c)));
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_back2:
                finish();
                break;
            case R.id.btn_next2:
                next();
                break;
        }
    }

    private void next() {
        int id = m_radio_group.getCheckedRadioButtonId();
        //沒選不動作
        if (id != -1) {
            //先抓選取的答案
            m_radio_temp = (RadioButton) findViewById(id);
            m_answer = m_radio_temp.getTag().toString();
            System.out.println("你選擇的答案: " + m_answer);
            //傳至下個Activities
            Intent intent = new Intent(this, Lab07_Activities3.class);
            Intent q1Intent = getIntent();
            CharSequence q1Answer = q1Intent.getStringExtra(Lab07_Activities.Q1_ANSWER_KEY);
            CharSequence q2Answer = m_answer;
            intent.putExtra(Lab07_Activities.Q1_ANSWER_KEY, q1Answer);
            intent.putExtra(Lab07_Activities2.Q2_ANSWER_KEY, q2Answer);
            //startActivity(intent);
            startActivityAnim(intent);
        }
    }
}
