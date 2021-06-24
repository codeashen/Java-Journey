package com.ashen.store.service.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreServiceProviderTest {

    @Autowired
    private StoreServiceProvider storeServiceProvider;

    @Test
    public void test() {
        int version = storeServiceProvider.selectVersion("1", "1");
        System.out.println(version);
    }

}