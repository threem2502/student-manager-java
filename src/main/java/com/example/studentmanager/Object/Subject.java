package com.example.studentmanager.Object;

import java.io.Serializable;
import java.util.Objects;

public class Subject implements Serializable {
    private String subId, subName;
    private int soTc;
    private float point;

    public Subject(String subId, String subName, float point) {
        this.subId = subId;
        this.subName = subName;
        this.point = point;
    }

    public Subject() {
    }

    public Subject(String subId, String subName, int soTc) {
        this.subId = subId;
        this.subName = subName;
        this.soTc = soTc;
    }

    public int getSoTc() {
        return soTc;
    }

    public void setSoTc(int soTc) {
        this.soTc = soTc;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

}
