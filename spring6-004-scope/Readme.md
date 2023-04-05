# Spring bean的作用域

### Spring默认情况下管理Bean

1. 默认情况下单例
2. 在Spring上下文初始化的时候实例化对象（调用对象的无参构造方法）
3. 每一次调用都返回单例的对象

### 设置Spring Bean不单例

如果设置为prototype：

1. 加载时不会实例化对象
2. 每次getBean的时候才会初始化对象

```xml
<bean id="springBean" class="com.yty.spring6.bean.SpringBean" scope="prototype"/>
```

```txt
wucan gouzap
com.yty.spring6.bean.SpringBean@21de60b4
wucan gouzap
com.yty.spring6.bean.SpringBean@c267ef4
wucan gouzap
com.yty.spring6.bean.SpringBean@30ee2816
```

### Spring Bean作用域的其他选项

1. singleton：默认的，单例。

2. prototype：原型。每调用一次getBean()方法则获取一个新的Bean对象。或每次注入的时候都是新对象。

3. request：一个请求对应一个Bean。**仅限于在WEB应用中使用**。

4. session：一个会话对应一个Bean。**仅限于在WEB应用中使用**。

5. global session：**portlet应用中专用的**。如果在Servlet的WEB应用中使用global session的话，和session一个效果。（portlet和servlet都是规范。servlet运行在servlet容器中，例如Tomcat。portlet运行在portlet容器中。）

6. application：一个应用对应一个Bean。**仅限于在WEB应用中使用。**

7. websocket：一个websocket生命周期对应一个Bean。**仅限于在WEB应用中使用。**

8. **自定义scope**：很少使用。

   1. 自定义scope：实现Scope接口

   2. 注册进Spring配置文件

      ```xml
          <bean class="org.springframework.beans.factory.config.CustomScopeConfigurer">
              <property name="scopes">
                  <map>
                      <entry key="myThread">
                          <bean class="org.springframework.context.support.SimpleThreadScope"/>
                      </entry>
                  </map>
              </property>
      ```

   3. 调用

      ```xml
          <bean id="springBean" class="com.yty.spring6.bean.SpringBean" scope="myThread"/>
      
      ```

9. 