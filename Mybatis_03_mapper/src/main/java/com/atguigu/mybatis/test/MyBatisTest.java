package com.atguigu.mybatis.test;

import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * 1.接口式编程
 *      原生：      Dao      --->   DaoImpl
 *      MyBatis：   Mapper   --->   xxxMapper.xml
 * 2.SqlSession代表和数据库的一次会话；用完必须关闭
 * 3.SqlSession和connection一样都是非线程安全的，每次使用都应该去获取新的对象
 * 4.mapper接口没有实现类，但是MyBatis会为这个接口生成一个代理对象
 *      (将接口和xml绑定)
 *      EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
 * 5.两个重要的配置文件:
 *      MyBatis的全局配置文件：包含数据库连接池对象，事务管理器信息等...系统运行环境信息
 *      sql映射文件：保存了每一个sql语句的映射信息：将sql抽取出来
 *
 @author：ZhouYao
 @create：2021-12-09 18:09
 */
public class MyBatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 1.根据xml配置文件(全局配置文件)创建一个sqlSessionFactory对象
     *    有数据源一些运行环境信息
     * 2.sql映射文件：配置了每一个sql以及sql的封装规则等
     * 3.将sql映射文件注册在全局配置文件中
     * 4.写代码
     *      1）根据全局配置文件得到SqlSessionFactory
     *      2）使用SqlSession工厂，获取根据全局配置文件得到sqlSession对象使用它来执行增删改查
     *         一个SqlSession就是代表和数据库的一次会话，用完关闭
     *      3）使用sql唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 1.获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2.获取SqlSession实例，能直接执行已经映射的sql语句
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            Employee employee = openSession.selectOne("com.atguigu.mybatis.dao.EmployeeMapper.getEmpById2", 1);
            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test01() throws IOException {
        // 1.获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 弹幕：使用反射，解析sql，动态生成接口的实现类

        // 2.获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();

        // 3.获取接口实现类对象
        // 只要将接口与xml文件动态绑定，MyBatis就会为接口自动的创建一个代理对象，代理对象去执行增删改查
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }

    /**
     * 测试增删改
     * 1.mybatis允许增删改直接定义以下类型返回值
     *      Integer，Long，Boolean，void
     * 2.需要手动提交数据
     *      sqlSessionFactory.openSession();  --> 手动提交
     *      sqlSessionFactory.openSession(true); --> 自动提交
     */
    @Test
    public void test03() throws IOException {
        // 1.获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2.获取sqlSession对象 --> (此次选择)不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            // 3.获取接口实现类对象

            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            // 3.1 测试添加
            mapper.addEmp(new Employee(null, "Jerry", "Jerry@qq.com", "1"));

            // 3.2 测试修改
//            boolean updateEmp = mapper.updateEmp(new Employee(1, "Tom", "JoJo@qq.com", "1"));
//            System.out.println(updateEmp);

            // 3.3 测试删除
//            mapper.deleteEmpById(2);

            // 4.手动提交数据
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test04() throws IOException {
        // 1.获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2.获取sqlSession对象 --> (此次选择)不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession(true);

        try {
            // 3.获取接口实现类对象
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            // 测试添加
//            Employee employee = mapper.getEmpByIdAndLastName(1, "Tom");
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", 1);
            map.put("lastname", "Tom");
            Employee employee = mapper.getEmpByMap(map);
            System.out.println(employee);

        } finally {
            openSession.close();
        }
    }


}













