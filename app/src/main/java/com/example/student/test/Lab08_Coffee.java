package com.example.student.test;

import java.io.Serializable;

public class Lab08_Coffee implements Serializable{
    private int id;
    private static int count = 0;
    private String title;
    private int price;
    private int img_resource_id;

    public Lab08_Coffee(String title,int price,int img_resource_id) {
        this.id = ++count;
        this.title = title;
        this.price = price;
        this.img_resource_id = img_resource_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImg_resource_id() {
        return img_resource_id;
    }

    public void setImg_resource_id(int img_resource_id) {
        this.img_resource_id = img_resource_id;
    }

    @Override
    public String toString() {
        return "Lab08_Coffee{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", img_resource_id=" + img_resource_id +
                '}';
    }
}
