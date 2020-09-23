package com.animal.user.controller;

import feign.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/9/23 2:49 PM
 * @description: com.animal.user.controller
 * @version: v1.0
 */
@Slf4j
@RestController
@RequestMapping("/fegin")
@EnableFeignClients
public class FeginController {

    @Autowired
    Client client;

    @GetMapping("/test")
    public String test() {
        String result = client.hello("zzp");
        return "Return : " + result;
    }

    @FeignClient("discovery-server")
    interface Client {

        @GetMapping("/hello")
        String hello(@RequestParam(name = "name") String name);

    }
}
