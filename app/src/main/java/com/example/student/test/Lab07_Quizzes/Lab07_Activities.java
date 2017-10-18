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

public class Lab07_Activities extends Lab07_AnimActivity {

      //Fragment用
//    private Lab07_ActivitiesFragment fragment_question;
//    private int[] question_id;
//    private int questionCount;
//    private String[] question;
//    public static String[] result;
//    private int index;
//    private Listener listener = new Listener();

    //include merge用
    public static final String Q1_ANSWER_KEY = "Q1";

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
        setContentView(R.layout.activity_lab07__activities);

        //Fragment用
//        initActivity();

        //include merge用
        init();
    }

    //include merge用
    private void init() {
        m_tv_no = (TextView)findViewById(R.id.tv_question_number);
        m_tv_question = (TextView)findViewById(R.id.tv_question);
        m_radio_group = (RadioGroup)findViewById(R.id.radio_group);
        m_radio_a = (RadioButton)findViewById(R.id.radio_a);
        m_radio_b = (RadioButton)findViewById(R.id.radio_b);
        m_radio_c = (RadioButton)findViewById(R.id.radio_c);

        m_tv_no.setText("1");
        //檢查系統版本
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            Spanned result;

            result = Html.fromHtml(getString(R.string.question_1),Html.FROM_HTML_MODE_LEGACY);
            m_tv_question.setText(result);
            result = Html.fromHtml(getString(R.string.question_1_radio_a ),Html.FROM_HTML_MODE_LEGACY);
            m_radio_a.setText(result);
            result = Html.fromHtml(getString(R.string.question_1_radio_b),Html.FROM_HTML_MODE_LEGACY);
            m_radio_b.setText(result);
            result = Html.fromHtml(getString(R.string.question_1_radio_c),Html.FROM_HTML_MODE_LEGACY);
            m_radio_c.setText(result);
        }
        else
        {
            m_tv_question.setText(Html.fromHtml(getString(R.string.question_1)));
            m_radio_a.setText(Html.fromHtml(getString(R.string.question_1_radio_a)));
            m_radio_b.setText(Html.fromHtml(getString(R.string.question_1_radio_b)));
            m_radio_c.setText(Html.fromHtml(getString(R.string.question_1_radio_c)));
        }
    }

    public void onClick(View view) {
        //先抓選取的答案
        int id = m_radio_group.getCheckedRadioButtonId();
        //沒選不動作
        if (id != -1) {
            m_radio_temp = (RadioButton) findViewById(id);
            m_answer = m_radio_temp.getTag().toString();
            System.out.println("你選擇的答案: " + m_answer);
            //傳至下個Activities
            Intent intent = new Intent(this, Lab07_Activities2.class);
            intent.putExtra(Q1_ANSWER_KEY, m_answer);
            //startActivity(intent);
            startActivityAnim(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Fragment用
//        initFragment();
//        index = 0;
//        startQuestion(index);
    }

    //Fragment用
//    private void initActivity() {
//        int[] i = {R.array.question_1,R.array.question_2,R.array.question_3};
//        question_id = i;
//        questionCount = question_id.length;
//        question = new String[questionCount];
//        result = new String[questionCount];
//    }
//
//    private void initFragment() {
//        fragment_question = (Lab07_ActivitiesFragment)getSupportFragmentManager().findFragmentById(fragment_question);
//        fragment_question.setListenter(listener);
//    }
//
//    //開始第 index 個問題
//    private void startQuestion(int index){
//        //取得R.array.question_1
//        int id = question_id[index];
//        //取得array問題陣列
//        question = getResources().getStringArray(id);
//        //把問題丟給Fragment
//        fragment_question.setQuestion(index,question);
//    }
//
//    public class Listener implements Button.OnClickListener{
//
//        @Override
//        public void onClick(View view) {
//            result[index] = fragment_question.getAnswer();
//            for(int i = 0 ; i < result.length ; i++)
//            {
//                if(result[i] == null)
//                {
//                    index = i;
//                    startQuestion(index);
//                    fragment_question.clearCheck();
//                    break;
//                }
//                else if( i == result.length -1)
//                {
//                    Intent in = new Intent();
//                    in.setClass(Lab07_Activities.this,Lab07_ResultActivity.class);
//                    startActivity(in);
//                }
//            }
//        }
//    }
}
