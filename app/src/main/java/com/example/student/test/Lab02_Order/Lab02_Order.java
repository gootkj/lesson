package com.example.student.test.Lab02_Order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.student.test.R;

public class Lab02_Order extends AppCompatActivity {
    private int mQuantitiy = 0;
    private int mPrice = 5;
    private StringBuilder mTotalPriceMessage = new StringBuilder(100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab02_order);
    }

    public void sumitOrder(View view) {
        displayTotalPrice();
    }

    private void display(){
        TextView quantityTextViewe = (TextView)findViewById(R.id.quantity_text_View);
        quantityTextViewe.setText(String.valueOf(mQuantitiy));

    }

    public void decrement(View view) {
        if(mQuantitiy > 0){
            --mQuantitiy;
            display();
            resetTotalPrice();
        }
    }

    public void increment(View view) {
        ++mQuantitiy;
        display();
        resetTotalPrice();
    }


    public void displayTotalPrice() {
        CheckBox checkbox = (CheckBox)findViewById(R.id.toppings_checkbox);
        boolean bTopping = checkbox.isChecked();


        int lan = mTotalPriceMessage.length();
        mTotalPriceMessage.delete(0,lan);//清空
        mTotalPriceMessage
                .append("客戶:")
                .append("孫悟空")
                .append("\n")
                .append("商品:")
                .append("臭豆腐")
                .append("\n")
                .append("是否要加泡菜")
                .append(bTopping)
                .append("\n");

        tryOrPay();

        TextView priceTextViewe = (TextView)findViewById(R.id.price_text_View);
        priceTextViewe.setText(mTotalPriceMessage);
    }

    private void resetTotalPrice(){
        TextView priceTextViewe = (TextView)findViewById(R.id.price_text_View);
        priceTextViewe.setText("");
    }

    public void tryOrPay(){
        if(mQuantitiy == 0){
            mTotalPriceMessage.append("免費試吃");
        }else{
            int total =  mPrice * mQuantitiy;
            mTotalPriceMessage
                    .append("數量:")
                    .append(mQuantitiy)
                    .append("\n")
                    .append("單價:")
                    .append(mPrice)
                    .append("\n")
                    .append("總金額:")
                    .append("NT$")
                    .append(total);
        }
    }

    public void clickToppings(View view) {
        CheckBox checkbox = (CheckBox)findViewById(R.id.toppings_checkbox);
        if(checkbox.isChecked() == true)
        {
            System.out.println("有勾");

        }
        else
        {
            System.out.println("沒有勾");

        }
    }

    public void myClick(View view) {
        System.out.println("click ...");
        int id = view.getId();
        switch (id){
            case R.id.toppings_checkbox:
                clickToppings(view);
                break;
            case R.id.btn_inc:
                increment(view);
                break;
            case R.id.btn_dec:
                decrement(view);
                break;
            case R.id.btn_order:
                sumitOrder(view);
                break;
        }

    }
}
