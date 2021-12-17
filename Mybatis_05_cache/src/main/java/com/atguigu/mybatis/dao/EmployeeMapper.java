package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 @author：ZhouYao
 @create：2021-12-09 19:25
 */
public interface EmployeeMapper {
    //多条记录封装一个map：Map<Integer,Employee>：键是这条记录的主键，值是记录封装后的javaBean
    //@MapKey：告诉mybatis封装这个map的时候使用哪个属性作为map的key
    @MapKey("lastname")
    Map<String, Employee> getEmpByLastNameLikeReturnMap(String lastName);

    //返回一条记录的map；key就是列名，值就是对应的值
    Map<String, Object> getEmpByIdReturnMap(Integer id);

    List<Employee> getEmpsByLastNameLike(String lastName);

    Employee getEmpByMap(Map<String, Object> map);

    Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastname") String lastname);

    Employee getEmpById(Integer id);

    long addEmp(Employee employee);

    boolean updateEmp(Employee employee);

    void deleteEmpById(Integer id);

}
