package com.ashen.authority.service;

import com.ashen.authority.domain.Product;

import java.util.List;

/**
 * 产品业务层接口
 */
public interface ProductService {

    /**
     * 查询所有产品
     * @return
     */
    List<Product> findAll();

    /**
     * 保存产品
     * @param product
     */
    void save(Product product);
}
