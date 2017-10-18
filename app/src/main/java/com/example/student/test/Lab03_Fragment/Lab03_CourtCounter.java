package com.example.student.test.Lab03_Fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.student.test.R;

public class Lab03_CourtCounter extends AppCompatActivity {

    private Lab03_CourtCounterFragment fragment_team_a;
    private Lab03_CourtCounterFragment fragment_team_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab03_court_counter);
    }
    public void onStart(){
        super.onStart();

        setFragmentName();
    }

    public void setFragmentName(){
        fragment_team_a = (Lab03_CourtCounterFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_team_a);
        fragment_team_a.setIvTeamLogo(R.drawable.team_a_logo);
        fragment_team_a.setTeamName(R.string.team_a_name);
        fragment_team_b = (Lab03_CourtCounterFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_team_b);
        fragment_team_b.setIvTeamLogo(R.drawable.team_b_logo);
        fragment_team_b.setTeamName(R.string.team_b_name);
    }

    public void myClick(View view) {
        fragment_team_a.reset();
        fragment_team_b.reset();
    }
}