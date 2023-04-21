package com.yty.spring6.bean2;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Controller
public class A {
    public A(){
        System.out.println("A");
    }
}
@Service
class B{
    public B(){
        System.out.println("B");
    }
}
@Repository
class C{
    public C(){
        System.out.println("C");
    }
}
@Service
class D{
    public D(){
        System.out.println("D");
    }
}
@Component
class E{
    public E(){
        System.out.println("E");
    }
}