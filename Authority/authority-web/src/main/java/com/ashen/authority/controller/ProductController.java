package com.ashen.authority.controller;

import com.ashen.authority.domain.Product;
import com.ashen.authority.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 产品Web层
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询所有产品
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView view = new ModelAndView("product-list");
        List<Product> productList = productService.findAll();
        view.addObject("productList", productList);
        return view;
    }

    /**
     * 保存产品
     * @param product
     * @return
     */
    @RequestMapping("/save")
    public String save(Product product) {
        productService.save(product);
        return "redirect:findAll";
    }
}
