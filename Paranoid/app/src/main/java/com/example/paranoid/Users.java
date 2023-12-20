package com.example.paranoid;

public class Users {
    String Name;
    int Height, Age, Body_Fats, Muscle, Calories_per_day, chestOverload,backOverload,forearmsOverload,absOverload,legsOverload;
    float Weight;


    public Users() {
    }

    public Users(String name, int height, int weight, int age, int body_Fats, int muscle, int calories_per_day, int chestOverload, int backOverload, int forearmsOverload, int absOverload, int legsOverload) {
        Name = name;
        Height = height;
        Weight = weight;
        Age = age;
        Body_Fats = body_Fats;
        Muscle = muscle;
        Calories_per_day = calories_per_day;
        this.chestOverload = chestOverload;
        this.backOverload = backOverload;
        this.forearmsOverload = forearmsOverload;
        this.absOverload = absOverload;
        this.legsOverload = legsOverload;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public float getWeight() {
        return Weight;
    }

    public void setWeight(float weight) {
        Weight = weight;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getBody_Fats() {
        return Body_Fats;
    }

    public void setBody_Fats(int body_Fats) {
        Body_Fats = body_Fats;
    }

    public int getMuscle() {
        return Muscle;
    }

    public void setMuscle(int muscle) {
        Muscle = muscle;
    }

    public int getCalories_per_day() {
        return Calories_per_day;
    }

    public void setCalories_per_day(int calories_per_day) {
        Calories_per_day = calories_per_day;
    }

    public int getChestOverload() {
        return chestOverload;
    }

    public void setChestOverload(int chestOverload) {
        this.chestOverload = chestOverload;
    }

    public int getBackOverload() {
        return backOverload;
    }

    public void setBackOverload(int backOverload) {
        this.backOverload = backOverload;
    }

    public int getForearmsOverload() {
        return forearmsOverload;
    }

    public void setForearmsOverload(int forearmsOverload) {
        this.forearmsOverload = forearmsOverload;
    }

    public int getAbsOverload() {
        return absOverload;
    }

    public void setAbsOverload(int absOverload) {
        this.absOverload = absOverload;
    }

    public int getLegsOverload() {
        return legsOverload;
    }

    public void setLegsOverload(int legsOverload) {
        this.legsOverload = legsOverload;
    }
}


