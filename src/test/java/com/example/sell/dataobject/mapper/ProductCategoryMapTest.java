package com.example.sell.dataobject.mapper;

import com.example.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapTest {

    @Autowired
    private ProductCategoryMap mapper;

    @Test
    public void insertByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "我最帅");
        map.put("category_type", 110);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertByObject() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("你最爱");
        productCategory.setCategoryType(101);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType() throws Exception {
        ProductCategory productCategory = mapper.findByCategoryType(110);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void updateCategoryType() throws Exception {
        int result = mapper.updateCategoryType("你最丑", 110);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateCategoryObject() throws Exception {
    ProductCategory productCategory = new ProductCategory();
    productCategory.setCategoryType(110);
    productCategory.setCategoryName("我最帅1");
    int result=mapper.insertByObject(productCategory);
    Assert.assertEquals(1,result);
    }

    @Test
    public void deleteCategoryObject() throws  Exception{
        int result=mapper.deleteCategoryObject(110);
        Assert.assertEquals(1,result);
    }

}