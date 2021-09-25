package com.liang.service;

import com.github.pagehelper.PageInfo;
import com.liang.pojo.ProductInfo;

import java.util.List;

public interface ProductInfoService {
    //显示全部商品（不分页）
    List<ProductInfo> getAll();


    PageInfo sqlitPage(int pageNum,int pageSize);


    int save(ProductInfo info);
}
