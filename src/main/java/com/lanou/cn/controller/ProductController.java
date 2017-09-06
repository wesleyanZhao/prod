package com.lanou.cn.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.cn.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by landfash on 2017/7/17.
 */
@Controller
@RequestMapping("/prd")
public class ProductController {

    @Resource
    private ProductService productService;

    @RequestMapping("addProduct")
    public ModelAndView addProductPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/addProduct");
        Map<String,List<Map<String,Object>>> supAndType = productService.getTypeAndSup();
        modelAndView.addObject("supAndType",supAndType);
        return modelAndView;
    }

    @RequestMapping("addProductForm")
    @ResponseBody
    public Map<String,Object> addProduct(@RequestParam Map<String,Object> params, HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        try {
            String username = (String) request.getSession().getAttribute("loginInfo");
            params.put("username",username);
            System.out.println("params>>>>>>>>"+params);
            productService.addProduct(params);
            result.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result","error");
        }
        return result;
    }

    @RequestMapping("getProduct")
    public ModelAndView getProduct(@RequestParam Map<String,Object> params){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/managerProduct");
        PageInfo<Map<String,Object>> pageInfo = productService.getAllProductPageList(params);
        Map<String,List<Map<String,Object>>> supAndType = productService.getTypeAndSup();
        modelAndView.addObject("supAndType",supAndType);
        modelAndView.addObject("page",pageInfo);
        modelAndView.addObject("list",pageInfo.getList());
        modelAndView.addObject("cond",params);
        return modelAndView;
    }

    @RequestMapping("modifyProduct")
    public ModelAndView modifyProduct(@RequestParam Map<String,Object> params){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/modifyProduct");
        Map<String,List<Map<String,Object>>> supAndType = productService.getTypeAndSup();
        modelAndView.addObject("supAndType",supAndType);
        modelAndView.addObject("product",productService.getModifyProduct(params));
        return modelAndView;
    }

    @RequestMapping("addDetailedProduct")
    public ModelAndView addDetailedProduct(@RequestParam Map<String,Object> params){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/addDetailedProduct");
        List<Map<String,Object>> list = productService.getWare(params);
        modelAndView.addObject("ware",list);
        modelAndView.addObject("length",list.size());
        modelAndView.addObject("params",params);
        return modelAndView;
    }

    @RequestMapping("addDetailedProductForm")
    @ResponseBody
    public Map<String,Object> addDetail(@RequestParam("wNo") String[] wNo,@RequestParam Map<String,Object> params,HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        String username = (String)request.getSession().getAttribute("loginInfo");
        params.put("username",username);
        try {
            productService.addDetailAndWare(params,wNo);
            result.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result","error");
        }
        return result;
    }

    @RequestMapping("getDetailedProduct")
    public ModelAndView getDetailed(@RequestParam Map<String,Object> params){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/managerDetailedProduct");
        PageInfo<Map<String,Object>> pageInfo = productService.getAllDetailedPageList(params);
        modelAndView.addObject("page",pageInfo);
        modelAndView.addObject("list",pageInfo.getList());
        modelAndView.addObject("params",params);
        return modelAndView;
    }

    @RequestMapping("modifyDetailedShow")
    public ModelAndView getModifyDetailed(@RequestParam Map<String,Object> params){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/modifyDetailedProduct");
        List<Map<String,Object>> list = productService.getModifyDetailed(params);
//        Map<String,Object> map = new HashMap<>();
//        map.put("prdDtlNo",list.get(0).get("prdDtlNo"));
//        map.put("prdDtlName",list.get(0).get("prdDtlName"));
//        map.put("imgUrl",list.get(0).get("imgUrl"));
//        map.put("salePrice",list.get(0).get("salePrice"));
//        map.put("isUsed",list.get(0).get("isUsed"));
//        modelAndView.addObject("list",list);
//        modelAndView.addObject("length",list.size());
        modelAndView.addObject("map",list.get(0));
        return modelAndView;
    }

    @RequestMapping("saveModifyDetailed")
    @ResponseBody
    public Map<String,Object> saveModifyDetailed(@RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        try {
            productService.saveModifyDetailed(params);
            result.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result","error");
        }
        return result;
    }
}
