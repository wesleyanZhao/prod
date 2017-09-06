package com.lanou.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 27/6/17.
 */
@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("")
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();
		List<String> list = new ArrayList<>();
		list.add("zhangsan");
		list.add("lisi");
		list.add("zhouba");
		list.add("wangwu");
		list.add("zhaoliu");
		list.add("sunqi");

		mav.setViewName("index/test");
		mav.addObject("title","hello world");
		mav.addObject("values",list);
		return mav;
	}


}
