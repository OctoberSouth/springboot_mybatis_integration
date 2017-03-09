package com.lp.dao;

import org.apache.ibatis.annotations.Mapper;

import com.lp.po.Test;

@Mapper
public interface TestMapper {
    int deleteByPrimaryKey(Integer id);
    
    int insert(Test record);

    int insertSelective(Test record);
}