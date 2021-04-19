package com.ashen.authority.dao;

import com.ashen.authority.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品持久层接口，Mybatis扫描该包下的类，生产代理对象，无需编写实现类
 */
@Repository
public interface ProductDao {

    /**
     * 查询所有产品
     * @return
     */
    @Select("select * from authority_product")
    List<Product> findAll();

    /**
     * 保存产品
     * @param product
     */
    @Insert("insert into authority_product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) " +
            "values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    /**
     * 根据id查询产品
     * @param id
     * @return
     */
    @Select("select * from authority_product where id = #{id}")
    Product findById(String id);
}
