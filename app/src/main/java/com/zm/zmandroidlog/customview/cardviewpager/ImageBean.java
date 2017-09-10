package com.zm.zmandroidlog.customview.cardviewpager;

import java.io.Serializable;


public class ImageBean implements Serializable {
    private long id;
    private int img;

    public ImageBean(int img) {
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "id=" + id +
                ", img='" + img + '\'' +
                '}';
    }
}
