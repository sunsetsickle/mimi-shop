package com.liang.service.impl;

import com.liang.mapper.ProductTypeMapper;
import com.liang.pojo.ProductInfo;
import com.liang.pojo.ProductType;
import com.liang.pojo.ProductTypeExample;
import com.liang.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {


    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getAll() {
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
