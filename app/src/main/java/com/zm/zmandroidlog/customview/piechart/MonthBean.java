package com.zm.zmandroidlog.customview.piechart;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by 张明_ on 2017/9/9.
 * Email 741183142@qq.com
 */

public class MonthBean implements Parcelable {

    private String date;
    private ArrayList<PieBean> obj;

    protected MonthBean(Parcel in) {
        date = in.readString();
    }

    public static final Creator<MonthBean> CREATOR = new Creator<MonthBean>() {
        @Override
        public MonthBean createFromParcel(Parcel in) {
            return new MonthBean(in);
        }

        @Override
        public MonthBean[] newArray(int size) {
            return new MonthBean[size];
        }
    };

    @Override
    public String toString() {
        return "MonthBean{" +
                "date='" + date + '\'' +
                ", obj=" + obj.toString() +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<PieBean> getObj() {
        return obj;
    }

    public void setObj(ArrayList<PieBean> obj) {
        this.obj = obj;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
    }

    public float getSum(int index) {
        float sum = 0;
        for (int i = 0; i < index; i++) {
            sum += obj.get(i).getValue();
        }
        return sum;
    }

    public float getSum() {
        float sum = 0;
        for (PieBean pieBean : obj) {
            sum += pieBean.value;
        }
        return sum;
    }



    class PieBean {
        private String title;
        private int value;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "PieBean{" +
                    "title='" + title + '\'' +
                    ", value=" + value +
                    '}';
        }
    }
}
