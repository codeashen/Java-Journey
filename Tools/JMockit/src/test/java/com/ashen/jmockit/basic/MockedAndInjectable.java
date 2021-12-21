package com.ashen.jmockit.basic;

import mockit.Injectable;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * <h1>@Mocked与@Injectable的不同</h1>
 * 
 * - @Injectable 也表示一个Mocked对象，相比@Mocked，只不过只影响类的一个实例。而@Mocked默认是影响类的所有实例。
 */
public class MockedAndInjectable {

    @Test
    public void testMocked(@Mocked Locale locale) {
        // 静态方法不起作用了,返回了null
        Assert.assertNull(Locale.getDefault());
        // 非静态方法（返回类型为String）也不起作用了，返回了null
        Assert.assertNull(locale.getCountry());
        // 自已new一个，也同样如此，方法都被mock了
        Locale chinaLocale = new Locale("zh", "CN");
        Assert.assertNull(chinaLocale.getCountry());
    }

    @Test
    public void testInjectable(@Injectable Locale locale) {
        // 静态方法不mock
        Assert.assertNotNull(Locale.getDefault());
        // 非静态方法（返回类型为String）也不起作用了，返回了null,但仅仅限于locale这个对象
        Assert.assertNull(locale.getCountry());
        // 自已new一个，并不受影响
        Locale chinaLocale = new Locale("zh", "CN");
        Assert.assertEquals("CN", chinaLocale.getCountry());
    }
}
