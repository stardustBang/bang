/*
package com.example.sell.controller;


import com.example.sell.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class SecKillController {

    @Autowired
    private SecKillService secKillService;


    */
/*查询秒杀活动特价商品的信息*//*

    @GetMapping("/query/{productId}")
    public  String query(@PathVariable String productId) throws  Exception{

        return  secKillService.querySecKillProductInfo(productId);
    }

   */
/*秒杀，没有抢到获得“哎呦喂”，抢到了会返回剩余的库存量*//*

    @GetMapping("/order/{productId}")
    public  String skill(@PathVariable String productId) throws  Exception{
        log.info("@skill request, productId:"+productId);
        secKillService.orderProductMockDiffUser(productId);
        return secKillService.querySecKillProductInfo(productId);
    }

}
*/
