package com.ashen.design.pattern.creational.prototype.clone;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Pig implements Cloneable {
    private String name;
    private Date birthday;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Pig[" + super.toString() + "]{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
