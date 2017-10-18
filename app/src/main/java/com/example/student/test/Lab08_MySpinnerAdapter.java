package com.example.student.test;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by student on 2017/9/26.
 */

public class Lab08_MySpinnerAdapter extends BaseAdapter {

    private Activity activity;
    private TypedArray m_coffee_titles;
    private TypedArray m_coffee_drawables;
    private int[] img_resource_id_array;
    //ArrayList<Lab08_Coffee> coffees = new ArrayList();

    public Lab08_MySpinnerAdapter(Activity activity) {
        this.activity = activity;
        m_coffee_titles = activity.getResources().obtainTypedArray(R.array.lab08_coffee_title);
        m_coffee_drawables = activity.getResources().obtainTypedArray(R.array.lab08_coffee_image);
        img_resource_id_array = new int[m_coffee_drawables.length()];
        for (int i = 0 ; i < img_resource_id_array.length ; i++)
        {
            img_resource_id_array[i] = m_coffee_drawables.getResourceId(i,-1);
        }
    }

    @Override
    public int getCount() {return m_coffee_titles.length();}

    @Override
    public Object getItem(int i) {
        return null;//coffees.get(i);
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

        tv_coffee_title.setText(m_coffee_titles.getText(i));
        iv_coffee_drawable.setImageDrawable(m_coffee_drawables.getDrawable(i));

        //Lab08_Coffee c = new Lab08_Coffee(m_coffee_name[i],m_coffee_price[i],img_resource_id_array[i]);
        //coffees.add(c);

        return v;
    }

    public TypedArray getCoffee_titles() {
        return m_coffee_titles;
    }

    public int[] getImg_resource_id_array() {
        return img_resource_id_array;
    }

}
