package com.ashen.paya.dao;

import com.ashen.paya.entity.CustomerAccount;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.Date;

public interface CustomerAccountMapper extends Mapper<CustomerAccount> {
    int updateBalance(@Param("accountId") String accountId, @Param("newBalance") BigDecimal newBalance,
                      @Param("version") int currentVersion, @Param("updateTime") Date currentTime);
}
