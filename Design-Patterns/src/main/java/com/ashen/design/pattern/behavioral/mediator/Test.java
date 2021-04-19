package com.ashen.design.pattern.behavioral.mediator;

public class Test {
    public static void main(String[] args) {
        User alice = new User("Alice");
        User tom = new User("Tom");

        alice.sendMessage("Hey! Tom! Let's learn Java.");
        tom.sendMessage("OK! Alice.");
    }
}
