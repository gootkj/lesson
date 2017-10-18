package com.example.student.test.Lab04_PickerActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.student.test.R;

public class Lab04_Image extends AppCompatActivity {

    ImageButton m_team_a,m_team_b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab04_image);

        m_team_a = (ImageButton)findViewById(R.id.image_team_a);
        m_team_b = (ImageButton)findViewById(R.id.image_team_b);
    }

    public void imageSelected(View view) {
        int id = view.getId();
        switch (id){
            case R.id.image_team_a:
                Lab04_ColorPickerActive.image = R.drawable.team_a_logo;
                break;
            case R.id.image_team_b:
                Lab04_ColorPickerActive.image = R.drawable.team_b_logo;
                break;
        }
        finish();
    }
}
