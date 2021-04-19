package com.ashen.design.principle.singleresponsibility;


public class Method {
    private void updateUserInfo(String userName, String address) {
        userName = "lucifer";
        address = "beijing";
    }

    private void updateUserInfo(String userName, String... properties) {
        userName = "lucifer";
//        address = "beijing";
    }

    private void updateUsername(String userName) {
        userName = "lucifer";
    }

    private void updateUserAddress(String address) {
        address = "beijing";
    }

    private void updateUserInfo(String userName, String address, boolean bool) {
        if (bool) {
            //todo something1
        } else {
            //todo something2
        }

        userName = "lucifer";
        address = "beijing";
    }

}
