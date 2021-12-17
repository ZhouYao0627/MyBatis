package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Employee;

import java.util.List;

/**
 @author：ZhouYao
 @create：2021-12-09 19:25
 */
public interface EmployeeMapper {

    Employee getEmpById(Integer id);

    List<Employee> getEmpls();

}
