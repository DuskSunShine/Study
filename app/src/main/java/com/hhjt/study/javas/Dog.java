package com.hhjt.study.javas;


/**
 * @author: SCY
 * @date: 2020/3/12   15:50
 * @version:
 * @desc:
 */
@Property(value = "公",name = "狗dog",age = 10)
public class Dog extends Animal{

    @Property(color = "红色")
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void setAge(int age) {
        super.setAge(age);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "color='" + color + '\'' +
                ", age=" + super.getAge() +
                ", sex='" + super.getSex() + '\'' +
                ", name='" + super.getName() + '\'' +
                '}';
    }
}
