package com.ashen.design.pattern.structural.adaptor;

/**
 * 适配器
 */
public class PowerAdapter implements DC5 {

    private AC220 ac220 = new AC220();

    @Override
    public int outputDC5V() {
        int adapterInput = ac220.outputAC220V();
        int adapterOutput = adapterInput / 44;  // 模拟变压器
        System.out.println("使用适配器PowerAdapter，输入AC" + adapterInput + "V，输出" + adapterInput + "V");
        return adapterOutput;
    }
}
