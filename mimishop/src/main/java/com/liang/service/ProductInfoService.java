package com.liang.service;

import com.github.pagehelper.PageInfo;
import com.liang.pojo.ProductInfo;
import com.liang.pojo.ProductInfoExample;

import java.util.List;

public interface ProductInfoService {
    //显示全部商品（不分页）
    List<ProductInfo> getAll();


    PageInfo sqlitPage(int pageNum,int pageSize);

    //增加商品
    int save(ProductInfo info);

    //根据主键id查询商品
    ProductInfo getById(int pid);

    //更新商品
    int update(ProductInfo info);
}
