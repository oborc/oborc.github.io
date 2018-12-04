//package com.example.demo;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.junit.Test;
//import org.mybatis.spring.SqlSessionFactoryBean;
//
//public class SqlSessionFactoryTest {
//
//    @Test
//    public void testCreate() throws Exception {
//
////        DruidDataSource myDatasource = new DruidDataSource();
////        myDatasource.setUrl("jdbc:mysql://localhost:3306/demo?useSSL=false");
////        myDatasource.setPassword("root");
////        myDatasource.setUsername("root");
////        myDatasource.setMaxActive(15);
////        myDatasource.setInitialSize(5);
////
////        //factoryBean
////        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
////        factory.setDataSource(myDatasource);
////        //sqlFactory sqlSessionFactory is an interface
////        //use bean to get a factory
////        SqlSessionFactory sqlSessionFactorybyBean = factory.getObject();
////        //set factory's dataSource.
////
////        SqlSession sqlSession = sqlSessiononFactorybyBean.openSession(myDatasource.getConnection());
//
//
//    }
//}
