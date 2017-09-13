package com.zm.zmandroidlog.customview.cardviewpager;

import java.io.Serializable;


public class ImageBean implements Serializable {
    private int id;
    private int img;

    public ImageBean(int img, int id) {
        this.img = img;
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "id=" + id +
                ", img='" + img + '\'' +
                '}';
    }
}
