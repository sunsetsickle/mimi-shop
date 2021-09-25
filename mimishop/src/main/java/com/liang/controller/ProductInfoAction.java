package com.liang.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageInfo;
import com.liang.pojo.ProductInfo;
import com.liang.service.ProductInfoService;
import com.liang.utils.FileNameUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    //分页：每页条数
    public static final int PAGE_SIZE = 5;

    //图片名称
    String saveFileName="";

    @Autowired
    private ProductInfoService productInfoService;

    //显示全部商品（不分页）
    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest request) {
        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list", list);
        return "product";
    }

    //显示第一条的5条记录
    @RequestMapping("/split")
    public String split(HttpServletRequest request) {
        PageInfo info = productInfoService.sqlitPage(1, PAGE_SIZE);
        request.setAttribute("info", info);
        return "product";
    }


    @RequestMapping("/ajaxsplit")
    @ResponseBody
    public void ajaxSplit(int page, HttpSession session) {
        //取得当前page参数的页面的数据
        PageInfo info = productInfoService.sqlitPage(page, PAGE_SIZE);

        session.setAttribute("info", info);
    }

    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage, HttpServletRequest request) {//pimage:和前端file组件id和name一致
        //提取生成文件名UUID+上传图片的后缀
        saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());

        //得到项目中图片存储的路径
        String path = request.getServletContext().getRealPath("/image_big");

        System.out.println("path的路径=========》》" + path);
        //File.separator:目录分隔符，确保在不同操作系统都能适用
        //转存
        try {
            pimage.transferTo(new File(path + File.separator + saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回客户端JSON对象，封转图片的路径，为了在页面实现回显
        JSONObject object = new JSONObject();
        object.put("imgurl", saveFileName);
        return object.toString();
    }

    @RequestMapping("/save")
    public String save(ProductInfo info,HttpServletRequest request){
        System.out.println("=====save===");
        info.setpImage(saveFileName);
        info.setpDate(new Date());

        int num=1;
        try {
            num=productInfoService.save(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num>0){
            request.setAttribute("msg","添加成功！");
        }else {
            request.setAttribute("msg","添加失败！");
        }

        //然后重新访问数据库，所以跳转到分页显示的action上
        return "forward:/prod/split.action";

    }
}