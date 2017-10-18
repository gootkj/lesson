package com.example.student.test;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by student on 2017/10/3.
 */

public class Lab08_UpdateSpinnerAdapter extends BaseAdapter {

    private Activity activity;
    private String m_coffee_title;
    private int img_resource_id;

    public Lab08_UpdateSpinnerAdapter(Activity activity , Lab08_Coffee coffee) {
        this.activity = activity;
        m_coffee_title = coffee.getTitle();
        img_resource_id = coffee.getImg_resource_id();
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //讀取layout產生view
        View v = activity.getLayoutInflater().inflate(R.layout.lab08_spinner_layout,null);

        TextView tv_coffee_title = (TextView) v.findViewById(R.id.coffee_title);
        ImageView iv_coffee_drawable = (ImageView) v.findViewById(R.id.coffee_drawable);

        tv_coffee_title.setText(m_coffee_title);
        iv_coffee_drawable.setImageResource(img_resource_id);

        return v;
    }
}
