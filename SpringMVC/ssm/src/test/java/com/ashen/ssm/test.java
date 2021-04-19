package com.ashen.ssm;

import com.ashen.ssm.dao.AccountDao;
import com.ashen.ssm.domain.Account;
import com.ashen.ssm.service.AccountService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;
import java.util.List;

public class test {

    /**
     * 测试Spring框架
     */
    @Test
    public void testService() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService service = context.getBean("accountService", AccountService.class);
        service.findAll();
    }

    /**
     * 测试Mybatis框架
     * @throws Exception
     */
    @Test
    public void testDao() throws Exception {
        // 加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 获取代理对象
        AccountDao dao = sqlSession.getMapper(AccountDao.class);

        // 执行方法
        List<Account> accounts = dao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
        // 关闭资源
        sqlSession.close();
        in.close();
    }
}
