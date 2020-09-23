package com.animal.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/9/23 1:45 PM
 * @description: com.animal.user.controller
 * @version: v1.0
 */

@Slf4j
@RestController("/rest")
public class RestTemplateController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/test")
    public String test() {
        String result = restTemplate.getForObject("http://discovery-server/hello?name=didi", String.class);
        return "Return : " + result;
    }
}
