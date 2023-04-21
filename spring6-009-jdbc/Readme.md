# Spring6 Jdbc templete

### 依赖配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.yty</groupId>
    <artifactId>spring6-009-jdbc</artifactId>
    <version>1.0-SNAPSHOT</version>

    <packaging>jar</packaging>
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    <repositories>
        <repository>
            <id>repository.spring.milestone</id>
            <realName>Spring Milestone Repository</realName>
            <url>https://repo.spring.io/milestone</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>6.0.0-M2</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.30</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>6.0.0-M2</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

### spring配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context  https://www.springframework.org/schema/context/spring-context.xsd">
    <context:property-placeholder location="jdbc.properties"/>
<!--data source bean    -->
    <bean id="ds" class="com.yty.spring6.bean.MyDataSource">
        <property name="driver" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>

    </bean>
<!--jdbc templete-->
    <bean id="jdbcTemplete" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="ds"/>
    </bean>

</beans>
```

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/spring6
jdbc.username=root
jdbc.password=12345678
```

### 数据源对象

```java
package com.yty.spring6.bean;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 * 提供Connection对象
 **/

public class MyDataSource implements DataSource {
    private String driver;
    private String url;
    private String username;
    private String password;

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}

```

### Test

```java
package com.yty.spring6.test;

import com.yty.spring6.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class JdbcTempleteTest {
  //测试jdbc
    @Test
    public void testJdbc(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplete = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        System.out.println(jdbcTemplete);
    }
	// 测试插入一条
    @Test
    public void testJdbcInsert(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplete = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        String sql = "insert into t_user(real_name, age) values(?, ?)";
        int count = jdbcTemplete.update(sql, "wjs", 24);
        System.out.println(count);
    }
  // 测试批量插入 使用对象数组分别存每一条，组合成list
      @Test
    public void testJdbcBatchInsert(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        String sql = "insert into t_user(real_name, age) values(?, ?)";
        Object[] user1 = {"xcj", 25};
        Object[] user2 = {"fls", 25};
        List<Object[]> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        int[] count = jdbcTemplate.batchUpdate(sql, users);
        System.out.println(Arrays.toString(count));
    }
	//测试更新一条
    @Test
    public void testJdbcUpdate(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplete = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        String sql = "update t_user set real_name=?, age=? where id=?";
        int count = jdbcTemplete.update(sql, "sbb", 24, 3);
        System.out.println(count);
    }
  
  // 测试批量更新 使用对象数组分别存每一条，组合成list
    @Test
    public void testJdbcBatchUpdate(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        String sql = "update t_user set real_name=?, age=? where id=?";
        Object[] user1 = {"syb", 25, 4};
        Object[] user2 = {"ly", 25, 5};
        List<Object[]> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        int[] count = jdbcTemplate.batchUpdate(sql, users);
        System.out.println(Arrays.toString(count));
    }
	//测试删除一条
    @Test
    public void testJdbcDelete(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplete = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        String sql = "delete from t_user where id=?";
        int count = jdbcTemplete.update(sql,  3);
        System.out.println(count);
    }
	// 测试批量删除 使用对象数组分别存每一条（一个值也得这么做），组合成list
      @Test
    public void testJdbcBatchDelete(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplete = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        String sql = "delete from t_user where id=?";

        Object[] user1 = {4};
        Object[] user2 = {5};
        List<Object[]> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        int[] counts = jdbcTemplete.batchUpdate(sql, users);
        System.out.println(Arrays.toString(counts));
    }
  //测试选择一条，使用jdbcTemplete.queryForObject方法，形参中需要绑定映射方法，并指定映射的Bean的class
    @Test
    public void testJdbcQuery(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplete = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        String sql = "select id, real_name, age from t_user where id=?";
        User user = jdbcTemplete.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), 2);
        System.out.println(user);
    }
  //测试选择全部，使用jdbcTemplete.query方法，形参中需要绑定映射方法，并指定映射的Bean的class
    @Test
    public void testJdbcQueryAll(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplete = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        String sql = "select id, real_name, age from t_user";
        List<User> users = jdbcTemplete.query(sql, new BeanPropertyRowMapper<>(User.class));
        System.out.println(users);
    }
  //测试选择一个值，使用jdbcTemplete.query方法，形参中需要指定映射的值的class
    @Test
    public void testJdbcQueryOneValue(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplete = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        String sql = "select count(*) from t_user";
        Integer total = jdbcTemplete.queryForObject(sql, Integer.class);
        System.out.println(total);
    }
  	// 调用callback函数，使用jdbc代码，形参中加入new PreparedStatementCallback<User>，给定返回值类型和jdbcTemplete要执行的doInPreparedStatement方法
      @Test
    public void testJdbcCallback(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplete = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        String sql = "select id, real_name, age from t_user where id=?";
        User user = jdbcTemplete.execute(sql, new PreparedStatementCallback<User>() {
            @Override
            public User doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                User user = new User();
                ps.setInt(1, 2);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String realName = resultSet.getString("real_name");
                    int age = resultSet.getInt("age");
                    user.setId(id);
                    user.setRealName(realName);
                    user.setAge(age);
                }
                return user;
            }
        });
        System.out.println(user);
    }
}

```

