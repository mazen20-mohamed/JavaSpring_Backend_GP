package com.example.AuthoRasa.Model;
import jakarta.persistence.*;

import javax.annotation.Nonnegative;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {
    @Id
    @Nonnegative
    private Long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "height")
    private int height;

    @Column(name = "weight")
    private double weight;

    @Column(name = "age")
    private int age;

    @Column(name = "activationRate")
    private double activationRate;

    @Column(name = "calories")
    private int calories;

    @Column(name="firebaseToken")
    private String token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LoginUpdate> listLogin = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WeightUpdate> listWeight = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LunchTime> lunchTimeList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BreakfastTime> breakfastTimeList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DinnerTime> dinnerTimeList = new ArrayList<>();

    public List<BreakfastTime> getBreakfastTimeList() {
        return breakfastTimeList;
    }

    public List<DinnerTime> getDinnerTimeList() {
        return dinnerTimeList;
    }

    public List<LunchTime> getLunchTimeList() {
        return lunchTimeList;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void addInListLogin(LoginUpdate listLogin) {
        this.listLogin.add(listLogin);
    }
    public void addInListWeight(WeightUpdate listWeight) {
        this.listWeight.add(listWeight);
    }

    public List<WeightUpdate> getListWeight() {
        return listWeight;
    }

    public void setListWeight(List<WeightUpdate> listWeight) {
        this.listWeight = listWeight;
    }

    public List<LoginUpdate> getListLogin() {
        return listLogin;
    }

    public void setListLogin(List<LoginUpdate> listLogin) {
        this.listLogin = listLogin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getActivationRate() {
        return activationRate;
    }

    public void setActivationRate(double activationRate) {
        this.activationRate = activationRate;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
