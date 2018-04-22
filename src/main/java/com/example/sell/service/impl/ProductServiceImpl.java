package com.example.sell.service.impl;

import com.example.sell.dataobject.ProductInfo;
import com.example.sell.dto.CartDTO;
import com.example.sell.enums.ProductStatusEnum;
import com.example.sell.enums.ResultEnum;
import com.example.sell.exception.SellException;
import com.example.sell.repository.ProductInfoRespository;
import com.example.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.channels.Pipe;
import java.util.List;



@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductInfoRespository respository;

    @Override
    //@Cacheable(cacheNames = "product",key = "124")

    public ProductInfo findOne(String productId) {
        return respository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return respository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return respository.findAll(pageable);
    }

    @Override
    //@CachePut(cacheNames = "product",key = "123")
    // 使用此方法缓存，必须要返回的值是一样的（即 ProductInfo）
    public ProductInfo save(ProductInfo productInfo) {
        return respository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = respository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);

            respository.save(productInfo);
        }
        
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo=respository.findOne(cartDTO.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
           Integer result=productInfo.getProductStock()-cartDTO.getProductQuantity();
            if(result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);

            respository.save(productInfo);
        }


    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo=respository.findOne(productId);
        if(productInfo ==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);

        }
        if(productInfo.getProductStatusEnum() ==ProductStatusEnum.UP){
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return respository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo=respository.findOne(productId);
        if(productInfo ==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);

        }
        if(productInfo.getProductStatusEnum() ==ProductStatusEnum.DOWN){
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return respository.save(productInfo);
    }
}
