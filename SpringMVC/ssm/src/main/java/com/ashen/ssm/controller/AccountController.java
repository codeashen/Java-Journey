package com.ashen.ssm.controller;

import com.ashen.ssm.domain.Account;
import com.ashen.ssm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 账户Controller
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 查询所有账户
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(Model model) {
        System.out.println("表现层：查询所有账户...");
        List<Account> accounts = accountService.findAll();
        model.addAttribute("list", accounts);
        return "list";
    }

    /**
     * 保存账户
     * @param account
     * @return
     */
    @RequestMapping("/save")
    public String saveAccount(Account account) {
        System.out.println("表现层：保存账户...");
        accountService.saveAccount(account);
        // 保存完成后重定向到查询所有
        return "redirect:/account/findAll";
    }
}
