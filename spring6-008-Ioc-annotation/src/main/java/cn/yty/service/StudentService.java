package cn.yty.service;

import cn.yty.dao.StudentDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Service
public class StudentService {
    @Resource
    StudentDao studentDao;

//    @Resource(name = "studentDaoImplForMySQL")
//    public void setStudentDao(StudentDao studentDao) {
//        this.studentDao = studentDao;
//    }

//    //@Resource(name = "studentDaoImplForMySQL")
//    public StudentService(StudentDao studentDao){
//        this.studentDao = studentDao;
//    }
    public void deleteStudent(){
        studentDao.deleteById();

    }
}
