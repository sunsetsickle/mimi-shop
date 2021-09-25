package com.liang.service.impl;

import com.liang.mapper.AdminMapper;
import com.liang.pojo.Admin;
import com.liang.pojo.AdminExample;
import com.liang.service.AdminService;
import com.liang.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    AdminMapper adminMapper;

    @Override
    public Admin login(String name, String pwd) {

        //如果sql有条件（where）。则要创建AdminExample对象用于封装条件
        AdminExample example=new AdminExample();
        //添加用户名a_name条件
        example.createCriteria().andANameEqualTo(name);//先按用户名查找，之后进行密码的比对
        System.out.println("========33333=====");

        List<Admin> list=adminMapper.selectByExample(example);

        if (list.size()>0){
            Admin admin= list.get(0);//因为用户名不会相同，所以只会有一个数据


            String miPwd= MD5Util.getMD5(pwd);
            if (miPwd.equals(admin.getaPass())){
                return admin;
            }
        }
        return null;
    }
}
