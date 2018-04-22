//package com.example.sell.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
///**
// * Created by 廖师兄
// * 2017-07-03 00:50
// */
//@RestController
//@RequestMapping("/weixin")
//@Slf4j
//public class WeixinController {
//
//    @GetMapping("/auth")
//    public void auth(@RequestParam("code") String code) {
//        log.info("进入auth方法。。。");
//        log.info("code={}", code);
//
//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx40a4f62582d578e8&secret=63319836a7ff3c0119ddf51abe091fd6&code="+code+"&grant_type=authorization_code";
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject(url, String.class);
//        log.info("response={}", response);
//    }
//}
