package com.liang.listener;

import com.liang.pojo.ProductType;
import com.liang.service.ProductTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;


@WebListener
public class ProductTypeListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //手工从spring容器中取出ProductTypeServiceImpl对象
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext-*.xml");
        ProductTypeService productTypeService= (ProductTypeService) context.getBean("productTypeServiceImpl");
        List<ProductType> typeList=productTypeService.getAll();
        //放入全局应用作用域中，供新增页面，查询，提供全部商品类别集合
        servletContextEvent.getServletContext().setAttribute("typeList",typeList);


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
