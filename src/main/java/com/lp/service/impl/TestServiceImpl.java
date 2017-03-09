package com.lp.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lp.dao.TestMapper;
import com.lp.po.Test;
import com.lp.service.TestService;

@Service(value = "testService")
@Transactional
public class TestServiceImpl implements TestService {

	@Resource(name = "testMapper")
	TestMapper testMapper;

	@Override
	public int insert(Integer id) throws Exception {
		this.testMapper.insert(new Test());
		if (id == 2) {
			throw new RuntimeException("服务器错误，请联系管理员");
		}
		return 0;
	}

}
