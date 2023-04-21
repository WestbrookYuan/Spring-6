package cn.yty.dao.impl;

import cn.yty.dao.StudentDao;
import org.springframework.stereotype.Repository;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Repository
public class StudentDaoImplForMySQL implements StudentDao {
    @Override
    public void deleteById() {
        System.out.println("mysql deleting");
    }
}
