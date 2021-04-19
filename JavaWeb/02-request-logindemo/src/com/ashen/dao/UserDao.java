package com.ashen.dao;

import com.ashen.domain.User;
import com.ashen.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 操作数据库中User表的类
 */
public class UserDao {

    // 声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登录方法
     * @param loginUser 只有用户名和密码
     * @return 数据库查到的，包含用户所有信息，没查到返回Null
     */
    public User login(User loginUser) {
        try {
            // 1.编写sql
            String sql = "select * from user where username = ? and password = ?";
            // 2.调用query方法，查询数据库
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());

            return user;
        } catch (DataAccessException e) {
            // e.printStackTrace();
            return null;
        }
    }
}
