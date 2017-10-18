package com.example.student.test.Lab06_Animation;

import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.student.test.R;

public class Lab06_AnimationDrawable extends AppCompatActivity {

    private ImageView m_iv_duke;
    private AnimationDrawable m_animationDrawable;
    private TextView m_tv_message;
    private Handler m_handler = new Handler();

    private View m_view_logo;
    private TextView m_logo_name;
    private TextView m_view_message;

    private TypedArray mNbaLogos;
    private String[] mNbaNames;
    private int mNbaLogosCount;

    private Button m_btn_go;
    private Runnable mStartRandomTask = new StartRandomTask();
    private Runnable mStopRandomTask = new StopRandomTask();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab06__animation_drawable);
        
        initView();
        initFrameAnimation();
        initNbaLogos();
    }

    private void initView() {
        m_iv_duke = (ImageView)findViewById(R.id.img_duke);
        m_tv_message = (TextView)findViewById(R.id.tv_Lab06_message);

        m_view_logo = findViewById(R.id.view_logo);
        m_logo_name = (TextView)findViewById(R.id.tv_logo_name);
        m_view_message = (TextView)findViewById(R.id.view_message);
        m_btn_go = (Button)findViewById(R.id.btn_go);
    }

    private void initFrameAnimation() {
        m_iv_duke.setBackgroundResource(R.drawable.frame_animation);//設定動畫資源
        m_animationDrawable = (AnimationDrawable)m_iv_duke.getBackground();//取得控制
    }

    private void initNbaLogos() {
        mNbaLogos = getResources().obtainTypedArray(R.array.nba_logos);
        mNbaNames = getResources().getStringArray(R.array.nba_logo_name);
        mNbaLogosCount = mNbaLogos.length();
        setNbaTeam(0);
    }

    private void setNbaTeam(int i){
        m_view_logo.setBackground(mNbaLogos.getDrawable(i));
        m_logo_name.setText(mNbaNames[i]);
    }

    public void click(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_start:
                m_animationDrawable.start();
                break;
            case R.id.btn_stop:
                m_animationDrawable.stop();
                break;
            case R.id.btn_5_secs:
                animation5secs();
                break;
            case R.id.btn_go:
                go();
                break;
        }
    }

    //撥5秒動畫
    private void animation5secs() {
        int delayMillis = 5 * 1000;

        Runnable task = new Task();
        boolean result = m_handler.postDelayed(task,delayMillis);

        m_tv_message.setText(result ? "交付成功" : "交付失敗");
        m_animationDrawable.start();
    }

    private class Task implements Runnable {
        @Override
        public void run() {
            m_animationDrawable.stop();
            m_tv_message.setText("時間到");
        }
    }

    private void go() {
        boolean result = m_handler.post(mStartRandomTask);
        m_handler.postDelayed(mStopRandomTask,3000);
        m_btn_go.setEnabled(false);
        m_view_message.setText(result ? "交付成功" : "交付失敗");
    }

    //每隔換圖
    private class StartRandomTask implements Runnable {
        @Override
        public void run() {
            int index = (int)(Math.random() * mNbaLogosCount);
            setNbaTeam(index);
            //m_view_logo.setBackground(mNbaLogos.getDrawable(index));
            m_handler.postDelayed(this,100);
        }
    }

    private class StopRandomTask implements Runnable {
        @Override
        public void run() {
            m_handler.removeCallbacks(mStartRandomTask);
            m_btn_go.setEnabled(true);
            m_view_message.setText("時間到");
        }
    }
}
