package com.example.sell.repository;


import com.example.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "110110";

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("12333");
        orderMaster.setBuyerName("小轩");
        orderMaster.setBuyerPhone("1123");
        orderMaster.setBuyerAddress("上海");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        repository.save(orderMaster);
    }
    @Test
    public void findByBuyerOpenid() throws Exception {

        PageRequest request=new PageRequest(0,3);
        Page<OrderMaster> result=repository.findByBuyerOpenid(OPENID,request);
      //  System.out.println(result.getTotalElements());
      Assert.assertNotEquals(0,result.getTotalElements());

    }

}