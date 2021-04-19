package com.ashen.param.domain;

/**
 * 身份证
 */
public class IDCard {
    private String idNumber;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return "IDCard{" +
                "idNumber='" + idNumber + '\'' +
                '}';
    }
}
