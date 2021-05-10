package com.example.orm;
import javax.persistence.Entity;
import javax.persistence.Id;

//Ich nehme an, diese Klasse muss von mir nicht weiter kommentiert werden.
@Entity
public class Student {

    private String name;
    private int age=0;
    private String phoneNumber;
    private String emailAddress;
    private boolean sex;
    private double studentNumber;
    private long id;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public double getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(double studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    public long getId() {
        return id;
    }



}