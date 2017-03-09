package com.lp.controller;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lp.utils.EncryptUtils;
import com.lp.utils.Json;



/**
 * 公共转发Controller
 * 
 * @author 刘攀
 *
 */
@Controller
@RequestMapping("/mainController")
public class MainController {

	protected static final Logger LOG = Logger.getLogger(MainController.class);

	/**
	 * main转发方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/mainForward", method = RequestMethod.POST)
	public String MainForward(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String fields = request.getParameter("fields");
		request.removeAttribute("fields");
		try {
			fields = URLDecoder.decode(EncryptUtils.aesDecrypt(fields), "utf-8");
			return "forward:" + fields;
		} catch (Exception e) {
			e.printStackTrace();
			return "forward:/mainController/errorHandling?fields=" + fields;
		}
	}

	@RequestMapping(value = "/errorHandling", method = RequestMethod.POST)
	@ResponseBody
	public Json ErrorHandling(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String fields = request.getParameter("fields");
		LOG.info("进入" + StringUtils.substringBefore(fields, "?") + "失败");
		Json json = new Json();
		json.setReturnCode(Json.ERROR);
		json.setMessage("系统错误，请稍后再试");
		return json;
	}
}
