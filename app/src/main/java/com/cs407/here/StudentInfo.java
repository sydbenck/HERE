package com.cs407.here;

public class StudentInfo {

    String name;
    String status;
    String points;
    String class1;
    String class2;
    String class3;

    public StudentInfo(String name, String status, String points, String class1, String class2, String class3) {
        this.name = name;
        this.status = status;
        this.points = points;
        this.class1 = class1;
        this.class2 = class2;
        this.class3 = class3;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getPoints() {
        return points;
    }

    public String getClass1() {
        return class1;
    }

    public String getClass2() {
        return class2;
    }

    public String getClass3() {
        return class3;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
