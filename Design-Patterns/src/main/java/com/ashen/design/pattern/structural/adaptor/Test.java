package com.ashen.design.pattern.structural.adaptor;

/**
 * 通过PowerAdapter，调用接口DC5的方法，方法内调用了AC220的方法
 */
public class Test{
    public static void main(String[] args) {
        PowerAdapter powerAdapter = new PowerAdapter();
        powerAdapter.outputDC5V();
    }
}
