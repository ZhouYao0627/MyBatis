package com.atguigu.mybatis.controller;

import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 @author：ZhouYao
 @create：2021-12-17 16:12
 */

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @RequestMapping("/getemps")
    public String emps(Map<String, Object> map) {
        List<Employee> emps = employeeService.getEmps();
        map.put("allEmps", emps);
        return "list";
    }


}










