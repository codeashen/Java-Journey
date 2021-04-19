package com.ashen.contentcenter.feignclient;

import com.ashen.contentcenter.domain.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient(name = "user-center", configuration = UserCenterFeignConfig.class)
@FeignClient(name = "user-center")
public interface UserCenterFeignClient {
    
    @GetMapping("/users/{id}")  // 对应远程接口endpoint
    UserDTO findById(@PathVariable Integer id);
}
