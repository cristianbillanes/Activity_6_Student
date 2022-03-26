package com.example.student;

public class Student {
    private String name;
    private String age;
    private String course;
    private String year;
    public Student(){}

    public Student(String name, String age, String course, String year) {
        this.name = name;
        this.age = age;
        this.course = course;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
