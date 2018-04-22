package com.example.sell.service.impl;

import com.example.sell.dataobject.OrderDetail;
import com.example.sell.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private  OrderServiceImpl orderService;

    private  final  String BUYER_OPENID="110110";

    private  final  String ORDER_ID="1510842905695264901";

    @Test
    public void create() throws Exception {

       OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("小冰");
        orderDTO.setBuyerAddress("beijing");
        orderDTO.setBuyerPhone("2222");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList=new ArrayList<>();
        OrderDetail o1=new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(2);

        OrderDetail o2=new OrderDetail();
        o2.setProductId("12333");
        o2.setProductQuantity(1);

        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailsList(orderDetailList);
        OrderDTO result=orderService.create(orderDTO);
        log.info("[创建订单] result={}",result);
        Assert.assertNotNull(result);

    }

    @Test
    public void findOne() throws Exception {

        OrderDTO result=orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】 result={}",result );
        Assert.assertEquals(ORDER_ID,result.getOrderId());


    }

    @Test
    public void findList() throws Exception {
        PageRequest request= new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage=orderService.findList(BUYER_OPENID,request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void paid() throws Exception {
    }

    @Test
    public void findList1() throws Exception {
    }

}