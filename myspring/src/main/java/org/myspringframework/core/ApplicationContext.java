package org.myspringframework.core;

/** mySpring框架应用上下文
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public interface ApplicationContext {
    /**
     * 根据bean id获取对象
     * @param BeanName
     * @return 返回对应的单例对象
     */
    Object getBean(String BeanName);
}
