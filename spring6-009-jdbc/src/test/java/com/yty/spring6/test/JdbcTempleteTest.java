package com.yty.spring6.test;

import com.yty.spring6.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class JdbcTempleteTest {
    @Test
    public void testJdbc(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplete = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        System.out.println(jdbcTemplete);
    }

    @Test
    public void testJdbcInsert(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplete = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        String sql = "insert into t_user(real_name, age) values(?, ?)";
        int count = jdbcTemplete.update(sql, "wjs", 24);
        System.out.println(count);
    }
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

    @Test
    public void testJdbcUpdate(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplete = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        String sql = "update t_user set real_name=?, age=? where id=?";
        int count = jdbcTemplete.update(sql, "sbb", 24, 3);
        System.out.println(count);
    }

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

    @Test
    public void testJdbcDelete(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplete = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        String sql = "delete from t_user where id=?";
        int count = jdbcTemplete.update(sql,  3);
        System.out.println(count);
    }

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

    @Test
    public void testJdbcQuery(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplete = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        String sql = "select id, real_name, age from t_user where id=?";
        User user = jdbcTemplete.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), 2);
        System.out.println(user);
    }

    @Test
    public void testJdbcQueryAll(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplete = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        String sql = "select id, real_name, age from t_user";
        List<User> users = jdbcTemplete.query(sql, new BeanPropertyRowMapper<>(User.class));
        System.out.println(users);
    }

    @Test
    public void testJdbcQueryOneValue(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplete = applicationContext.getBean("jdbcTemplete", JdbcTemplate.class);
        String sql = "select count(*) from t_user";
        Integer total = jdbcTemplete.queryForObject(sql, Integer.class);
        System.out.println(total);
    }

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
