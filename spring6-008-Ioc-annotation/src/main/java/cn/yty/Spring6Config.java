package cn.yty;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/** 编写一个类代替xml配置文件
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Configuration
@ComponentScan({"cn.yty", "com.yty"})
public class Spring6Config {
}
