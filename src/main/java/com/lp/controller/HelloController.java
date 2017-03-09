package com.lp.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lp.service.TestService;
import com.lp.utils.Json;

@Controller
@RequestMapping("/helloController")
public class HelloController {

	@Resource(name = "testService")
	TestService testService;

	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	@ResponseBody
	public Json hello(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		System.err.println("id===" + id);
		Json json = new Json();
		try {
			this.testService.insert(Integer.valueOf(id));
			json.setData("哈哈");
			json.setReturnCode(Json.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMessage(e.getMessage());
			json.setReturnCode(Json.ERROR);
		}
		return json;
	}
}
