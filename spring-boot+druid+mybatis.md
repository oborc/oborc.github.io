# spring-boot+druid+mybatis 动态创建数据源

很多blog中写的都是动态切换数据源，在配置文件中已经定义好很多个数据源之后，用AOP进行切换或者配置好多个sqlSessionTemplate来进行数据源的切换。

但在实际应用场景中，可能会存在需要动态的创建数据源，而不是动态的切换数据源的需求，在读取配置文件之前并不知道数据源的url或者别的参数。url 都是在程序中动态生成。并且，在配置好的数据源都是由spring+mybatis动态注入，sqlSession是由spring自动生成的sqlSession，只有一个实例，在并发的情况下，需要多个sqlSession的时候，就不能通过配置文件中申明数据源，在Java代码中设置url来实现。

首先，动态创建数据源要分清mybatis中sqlSession、sqlSessionFactory、sqlSessionFactortBean、sqlSessionTemplate 中的联系和区别。

## sqlSessionFactory



## sqlSession



```java

package org.apache.ibatis.session;
import java.io.Closeable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;

/**
执行commend、获取mapper和管理事务。实现了closeable接口
*/
public interface SqlSession extends Closeable {

    //取回一个记录，通过申明的。
  <T> T selectOne(String statement);

    //取回一个由statement 和 parameter映射到的记录
  <T> T selectOne(String statement, Object parameter);

  <E> List<E> selectList(String statement);
    
 ...

  <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds);

  <T> Cursor<T> selectCursor(String statement);
    
...

  void select(String statement, Object parameter, ResultHandler handler);

  ...
 
  int insert(String statement, Object parameter);
    
...
    
  int delete(String statement);

...
  void commit();

  void rollback();


  List<BatchResult> flushStatements();

  @Override
  void close();

    //获取和mapper的映射
  <T> T getMapper(Class<T> type);

  Connection getConnection();
}

```

sqlSession是一个mybatis实现的接口，定义了对于数据库操作的select、update、insert等操作，同时还有对与数据库的连接、获取mapper 和mapper映射的接口。

在mybatis中有三个对于sqlSession 的实现：

#### sqlSessionTemplate

​	sqlSessionTemplate 是mybatis后来增加的对于spring-boot支持的、线程安全的、由spring来管理事务的一个sqlSession实现。用spring 来管理事务，保证实际用到的sqlSession是和当前spring事务关联的sqlSession。sqlSessionTemplate还管理着session的生命周期(closing、commiting、rollingback)都是被spring所管理。不用显示进行sqlSession 的commit.close 等操作。

#### DefaultSqlSession

​	spring 中默认实现的sqlSession.每个线程都有自己的SqlSession.执行语句结束后会自动commit 和关闭事物。

#### sqlSession工作。

在mybatis 中，sqlSession用来执行sql语句，可以用getMapper方法去和mapper.xml关联映射，来方便代码的编写，否则，直接用sqlSession 会出现代码编写困难，难以记住mapper.xml中sql语句id的问题。用mapper.interface和mapper.xml关联映射，在Java中的代码编写会简单很多。 在spring和mybatis 没有整合之前，直接使用mybatis就需要现实的用sqlSessionFactory open sqlSession后执行SQL，然后close Sqlsession.



# 动态数据源

mybatis 实现动态多数据源：

可以从以下角度思考：

实现多个sqlSessionTemplate.



