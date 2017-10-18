package com.example.student.test.Lab07_Quizzes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.student.test.R;

public class Lab07_ActivitiesFragment extends Fragment {

    private TextView m_tv_question_number;
    private TextView m_tv_question;
    private RadioGroup m_rg_radio_group;
    private RadioButton m_rb_radio_a;
    private RadioButton m_rb_radio_b;
    private RadioButton m_rb_radio_c;
    private Button m_btn_next;

    public Lab07_ActivitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lab07__activities, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        initFragment();
    }

    private void initFragment() {
        m_tv_question_number = (TextView)getView().findViewById(R.id.tv_question_number);
        m_tv_question = (TextView)getView().findViewById(R.id.tv_question);
        m_rg_radio_group = (RadioGroup)getView().findViewById(R.id.radio_group);
        m_rb_radio_a = (RadioButton)getView().findViewById(R.id.radio_a);
        m_rb_radio_b = (RadioButton)getView().findViewById(R.id.radio_b);
        m_rb_radio_c = (RadioButton)getView().findViewById(R.id.radio_c);
        m_btn_next = (Button)getView().findViewById(R.id.btn_next);

    }

    public void setQuestion(int index,String[] question) {
        int num = index + 1;
        m_tv_question_number.setText(String.valueOf(num));
        m_tv_question.setText(question[0]);
        m_rb_radio_a.setText(question[1]);
        m_rb_radio_b.setText(question[2]);
        m_rb_radio_c.setText(question[3]);
    }

    //fragment版本用
//    public void setListenter(Lab07_Activities.Listener listenter) {
//        m_btn_next.setOnClickListener(listenter);
//    }

    public String getAnswer(){
        String str = null;
        int id = m_rg_radio_group.getCheckedRadioButtonId();
        switch (id){
            case R.id.radio_a:
                str = "A";
                break;
            case R.id.radio_b:
                str = "B";
                break;
            case R.id.radio_c:
                str = "C";
                break;
        }
        return str;
    }

    public void clearCheck(){
        m_rg_radio_group.clearCheck();
    }
}
