package com.example.studentmanager.Object;

import javafx.collections.ObservableList;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Student implements Serializable {
    private String id, name, sdt;
    private Float avgPoint;
    private ArrayList<Subject> subjectList;

    public Student(String id, String name, String sdt, Float avgPoint) {
        this.id = id;
        this.name = name;
        this.sdt = sdt;
        this.avgPoint = avgPoint;
        subjectList = new ArrayList<>();
    }

    public Student() {
        subjectList = new ArrayList<>();
    }

    public Student(String id, String name, String sdt) {
        this.id = id;
        this.name = name;
        this.sdt = sdt;
        subjectList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Float getAvgPoint() {
        return avgPoint;
    }

    public void setAvgPoint() {
        if(!subjectList.isEmpty()) {
            float sum = 0;
            for(Subject s : subjectList){
                sum += s.getPoint();
            }
            avgPoint = sum / subjectList.size();
        }
    }

    public ArrayList<Subject> getSubjectList() {
        if(!subjectList.isEmpty()) {
            return subjectList;
        }
        return null;
    }

    public void setSubjectList(ArrayList<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public boolean addSub(Subject s){
        if(!subjectList.isEmpty()) {
            for (Subject subject : subjectList) {
                if(Objects.equals(subject.getSubId(), s.getSubId())) {
                    return true;
                }
            }
        }
            subjectList.add(s);
            setAvgPoint();
            return false;
    }
}
