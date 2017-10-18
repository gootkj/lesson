package com.example.student.test.Lab04_PickerActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.student.test.R;

public class Lab04_Color extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab04_color);
    }

    public void ok(View view) {
        RadioGroup rg = (RadioGroup)findViewById(R.id.rg);
        int id = rg.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton) findViewById(id);
        Lab04_ColorPickerActive.color = rb.getCurrentTextColor();
        Lab04_ColorPickerActive.text = rb.getText();

        finish();
    }

    public void clickColor(View view) {
    }

    public void cancel(View view) {
        finish();
    }
}
