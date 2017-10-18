package com.example.student.test;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by student on 2017/10/3.
 */

public class Lab08_MyListAdapter extends BaseAdapter {

    private Activity activity;
    private MyListAdapterHandler handler;

    public Lab08_MyListAdapter(Activity activity) {
        this.activity = activity;
        initHandler();
    }

    //為了解除耦合製作介面，必須實作以下功能
    public interface MyListAdapterHandler{
        //檢查價格是否為整數
        boolean priceCheck(String priceStr);
        //取得CoffeeList
        List<Lab08_Coffee> getmCoffeeList();
        //取得CoffeeList需要修改的Coffee index
        int getClickedCoffeeIndex();
        //資料異動後更新畫面
        void listAdapterUpdate(Lab08_Coffee coffee, String message);
    }

    //確認Activity有實作MyListAdapterHandler
    private void initHandler() {
        try {
            handler = (MyListAdapterHandler) activity;
        }catch (ClassCastException cause){
            String message = "搞什麼麻，Activity 沒有實作MyListAdapterHandler";
            throw  new Lab08_MyListAdapterException(message,cause);
        }
    }

    @Override
    public int getCount() {
        return handler.getmCoffeeList().size();
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

        View v = activity.getLayoutInflater().inflate(R.layout.lab08_listview_layout,null);

        TextView tvItemId = (TextView)v.findViewById(R.id.itemId);
        TextView tvItemTitle = (TextView)v.findViewById(R.id.itemTitle);
        TextView tvItemPrice = (TextView)v.findViewById(R.id.itemPrice);
        ImageView ivItemImage = (ImageView)v.findViewById(R.id.itemImage);

        Lab08_Coffee coffee = handler.getmCoffeeList().get(i);

//        tvItemId.setText("id");
//        tvItemTitle.setText("咖啡名");
//        tvItemPrice.setText("價格");
//        ivItemImage.setImageResource(R.drawable.coffee_cappuccino);
        tvItemId.setText("id");
        tvItemTitle.setText(coffee.getTitle());
        tvItemPrice.setText(String.valueOf(coffee.getPrice()));
        ivItemImage.setImageResource(coffee.getImg_resource_id());

        return v;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

}
