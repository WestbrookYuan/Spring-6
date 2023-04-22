# 代理模式

### 场景

拍电影：演员使用替身演员

原因：

1. 保护演员（保护目标对象）
2. 替身演员有新的能力（功能增强）



### Java程序中的代理模式的作用：

1. 当一个对象需要保护时，考虑使用代理对象去完成某个行为
2. 需要给某个对象进行功能增强时，可以考虑找一个代理进行增强

3. A对象无法和B对象直接交互时，也可以使用代理模式

### 代理模式的三大角色

1. 目标对象（演员）
2. 代理对象（替身）
3. 目标对象和代理对象的公共接口（演员和替身演员应该具有相同的行为和动作）
   1. 如果使用代理模式的话，对于客户端程序来说， 客户端是无法察觉到的，客户端在使用代理对象的时候就是在使用目标对象



### 例子：

```java
// 目标对象和代理对象的公共接口
public interface act {
  void fight();
}

// 目标对象（演员）
public class actor implements act（）{
	public void fight(){
	...
	}
}

// 代理对象（替身）
public class subActor implements act（）{
	public void fight(){
	...
	}
}
```

### 具体例子：

假如对系统（已上线）进行运行时间的测试

1. 硬编码 所有方法前后添加计时器

   1. 不符合OCP原则
   2. 代码复用

2. 继承实现类

   1. 解决OCP原则，但代码耦合度提高
   2. 代码复用

3. 代理模式

   1. 三个角色：目标对象、代理对象、目标对象与代理对象的公共接口
   2. 代理对象中实现公共接口的方法，使其具有与目标对象相同的属性和行为
   3. 将目标对象作为代理对象的一个属性，成为关联对象，比继承关系的代码耦合度低
      1. **类和类之间的关系**（六类）：
         1. 泛化关系（继承）：cat is an animal
         2. 关联关系：i have a pen
         3. 耦合度：关联关系 > 泛化关系
   4. 解决OCP、降低耦合
      1. 静态代理：
         1. 缺点：类爆炸，代理类数量太多
      2. 可以使用动态代理解决这个问题：
         1. 添加了字节码生成技术，可以在内存中为我们动态的生成一个class字节码，这个字节码就是代理类
         2. 在内存中动态生成代理类的技术，叫做动态代理
      3. 动态代理：解决类爆炸，解决代码复用

   ### 动态代理

   1. JDK动态代理技术：只能代理接口

      1. Proxy.newProxyInstance(类加载器，要实现的接口，调用处理器)，返回Proxy Object

         ```java
         public static Object newProxyInstance(ClassLoader loader,
                                                  Class<?>[] interfaces,
                                                  InvocationHandler h)
         ```

         1. 在内存中动态生成了代理类

         2. new了个对象

         3. 参数列表：

            1. **类加载器**：Classloader loader，在内存当中生成的字节码，需要加载到JVM中。JDK要求，目标类的类加载器和代理类的类加载器必须是同一个

            2. **要实现的接口**:代理类和目标类必须实现同一个接口，需要告知JDK

               1. 代理对象和目标对象实现的接口一样，可以向下转型

            3. **调用处理器**：是一个接口，插入增强代码，既然是接口，就要实现接口的实现类，**只需要实现一次**。

               1. 不会导致类爆炸

               2. 可能需要传入目标对象

               3. 实现**invoke**方法

                  1. JDK**在底层调用**invoke方法生成动态代理对象

                  2. 当代理对象调用代理方法时，注册在InvocationHandler中的方法会被JDK调用

                  3. 写增强代码

                  4. 如果增强的话，目标对象的目标方法得保证执行

                  5. 参数：（jdk调用方法时会传参，可以在invoke中直接调用），调用目标参数的目标方法，增强代码

                     ```java
                     @Override
                         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                             System.out.println("invoke...");
                             return null;
                         }
                     ```

                     1. proxy：代理对象的引用，使用较少
                     2. method：目标对象上的目标方法
                     3. args：目标方法上的实参

                  6. 需要返回结果的话，将invoke方法的返回值继续返回

         4. 

   2. CGLIB动态代理技术：CGLIB(Code Generation Library)是一个开源项目。是一个强大的，高性能，高质量的Code生成类库，它可以在运行期扩展Java类与实现Java接口。它既可以代理接口，又可以代理类，**底层是通过继承的方式实现的**。性能比JDK动态代理要好。**（底层有一个小而快的字节码处理框架ASM。）**

      ```java
      package com.yty.proxy.service;
      
      /**
       * @author yty
       * @version 1.0
       * @since 1.0
       **/
      public class UserService {
          public boolean login(String username, String password){
              System.out.println("verifying");
              return "admin".equals(username) && "1234".equals(password);
          }
      
          public void logout(){
              System.out.println("quiting");
          }
      }
      
      ```

      ```java
      package com.yty.proxy.client;
      
      import com.yty.proxy.interceptor.TimerMethodInterceptor;
      import com.yty.proxy.service.UserService;
      import net.sf.cglib.proxy.Enhancer;
      
      /**
       * @author yty
       * @version 1.0
       * @since 1.0
       **/
      public class Client {
          public static void main(String[] args) {
              // 创建字节码增强器对象
              Enhancer enhancer = new Enhancer();
              // 告诉cglib目标类是谁
              enhancer.setSuperclass(UserService.class);
              // 设置回调：等同于JDK中的InvocationHandler
              // 方法拦截器接口
              enhancer.setCallback(new TimerMethodInterceptor());
              // 创建代理的对象：生成子类，创建对象
              UserService proxyObj = (UserService) enhancer.create();
              System.out.println(proxyObj);
              System.out.println(proxyObj.login("admin", "1234") ? "success" : "failed");
              proxyObj.logout();
          }
      
      
      }
      
      ```

      ```java
      package com.yty.proxy.interceptor;
      
      import net.sf.cglib.proxy.MethodInterceptor;
      import net.sf.cglib.proxy.MethodProxy;
      
      import java.lang.reflect.Method;
      
      /**
       * @author yty
       * @version 1.0
       * @since 1.0
       **/
      public class TimerMethodInterceptor implements MethodInterceptor {
          @Override
          public Object intercept(Object target, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
              // 前面增强
              long begin = System.currentTimeMillis();
              // 目标方法
              Object retValue = methodProxy.invokeSuper(target, objects);
              //后面增强
              long end = System.currentTimeMillis();
              System.out.println("time consume: " + (end - begin));
              return retValue;
          }
      }
      
      ```

      

   3. Javassist动态代理技术：Javassist是一个开源的分析、编辑和创建Java字节码的类库。是由东京工业大学的数学和计算机科学系的 Shigeru Chiba （千叶 滋）所创建的。它已加入了开放源代码JBoss 应用服务器项目，通过使用Javassist对字节码操作为JBoss实现动态"AOP"框架。



