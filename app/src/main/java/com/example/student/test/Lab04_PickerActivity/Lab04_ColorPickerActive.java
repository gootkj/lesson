package com.example.student.test.Lab04_PickerActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.student.test.R;

public class Lab04_ColorPickerActive extends AppCompatActivity {

    static int color ;
    static CharSequence text = "顏色未設定";
    static int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab04__color_picker_active);

    }

    @Override
    protected void onStart() {
        super.onStart();
        set();
    }
    /*
    public void selectColor(View view) {
        Intent in = new Intent();
        in.setClass(this,Lab04_Color.class);
        startActivity(in);
    }
    */
    public void set(){
        TextView tv = (TextView)findViewById(R.id.tv_color);
        tv.setBackgroundColor(color);
        tv.setText(text);
        ImageView iv = (ImageView)findViewById(R.id.iv_image);
        iv.setImageResource(image);
    }

    public void select(View view) {
        Intent in = new Intent();
        int id = view.getId();
        switch (id){
            case R.id.selectColor:
                in.setClass(this,Lab04_Color.class);
                break;
            case R.id.selectImage:
                in.setClass(this,Lab04_Image.class);
                break;
        }
        startActivity(in);
    }
}
