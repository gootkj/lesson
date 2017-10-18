package com.example.student.test.Lab03_Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.student.test.R;

public class Lab03_CourtCounterFragment extends Fragment implements View.OnClickListener{

    private CharSequence teamName;
    private int teamScore;
    private ImageView ivTeamLogo;
    private TextView tvTeamName;
    private TextView tvTeamScore;
    private Button bOnePoint;
    private Button bTwoPoint;
    private Button bThreePoint;

    public Lab03_CourtCounterFragment() {
        teamName = "";
        teamScore = 0;
    }

    @Override
    public void onStart() {
        super.onStart();
        ivTeamLogo = (ImageView)getView().findViewById(R.id.teamLogo);
        tvTeamName = (TextView)getView().findViewById(R.id.teamName);
        tvTeamScore = (TextView)getView().findViewById(R.id.teamScore);
        bOnePoint = (Button)getView().findViewById(R.id.btn_one_point);
        bOnePoint.setOnClickListener(this);
        bTwoPoint = (Button)getView().findViewById(R.id.btn_two_point);
        bTwoPoint.setOnClickListener(this);
        bThreePoint = (Button)getView().findViewById(R.id.btn_three_point);
        bThreePoint.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lab03_court_counter, container, false);
    }

    public void setIvTeamLogo(int resId){
        if(ivTeamLogo != null){
            ivTeamLogo.setImageResource(resId);
        }
    }
    public void setTeamName(int resId){
        this.teamName = getText(resId);
        tvTeamName.setText(resId);
    }
    public CharSequence getTeamName(){
        return teamName;
    }
    public void setTeamScore(int teamScore){
        this.teamScore = teamScore;
        display();
    }
    public int getTeamScore(){
        return teamScore;
    }
    public void reset(){setTeamScore(0);}
    public void display(){
        tvTeamScore.setText(String.valueOf(teamScore));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_one_point:
                setTeamScore((teamScore + 1));
                break;
            case R.id.btn_two_point:
                setTeamScore((teamScore + 2));
                break;
            case R.id.btn_three_point:
                setTeamScore((teamScore + 3));
                break;
        }
    }
}
