package com.atguigu.mybatis.bean;

import org.apache.ibatis.type.Alias;

/**
 @author：ZhouYao
 @create：2021-12-09 17:15
 */

@Alias("emp")
public class Employee {

    private Integer id;
    private String lastname;
    private String email;
    private String gender;
    private Department dept;


    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Employee() {
    }

    public Employee(Integer id, String lastname, String email, String gender, Department dept) {
        this.id = id;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.dept = dept;
    }

    public Employee(Integer id, String lastname, String email, String gender) {
        this.id = id;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
