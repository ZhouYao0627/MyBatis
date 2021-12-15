package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Department;

/**
 @author：ZhouYao
 @create：2021-12-15 15:44
 */
public interface DepartmentMapper {

    Department getDeptById(Integer id);

    Department getDeptByIdPlus(Integer id);

    Department getDeptByIdStep(Integer id);
}
