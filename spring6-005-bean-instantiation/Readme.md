# Bean的实例化

Spring为Bean提供了多种实例化方式，通常包括4种方式。（也就是说在Spring中为Bean对象的创建准备了多种方案，目的是：更加灵活）

- 第一种：通过构造方法实例化

  - 将bean的全类路径直接写入Spring配置文件，通过无参构造方法实例化Bean

    ```xml
        <bean id="sb" class="com.yty.spring6.bean.SpringBean"/>
    ```

- 第二种：通过简单工厂模式实例化

  - 创建一个简单工厂，其中有一个静态方法，最终还是我们负责new的对象

  - 在Spring配置文件中注册，通过调用哪个类的哪个方法获取bean

    ```xml
        <bean id="starBean" class="com.yty.spring6.bean.StarFactory" factory-method="get"/>
    ```

    

- 第三种：通过factory-bean实例化

  - 通过factory-bean属性和factory-method属性

    ```xml
        <bean id="gunFactory" class="com.yty.spring6.bean.GunFactory"/>
        <bean id="gun" factory-bean="gunFactory" factory-method="get"/>
    ```

- 第四种：通过FactoryBean接口实例化

  ```xml
      <bean id="personBean" class="com.yty.spring6.bean.PersonFactoryBean"/>
  ```

  

个人理解：

1. 构造方法实际上是直接暴露出一个对象，没有升价改造的空间
2. 简单工厂模式利用工厂的静态方法，实际上没有Factory Bean对象的实例化，直接利用静态方法产出一个对象
3. factory bean 方法则需要实例化factory Bean对象，再利用一个普通方法产出对象，因此需要在对象附近指定factory bean的实例化和factory method
4. 对factory bean方法的简化，spring识别出该对象是一个实现了factory bean接口的对象，可以省略fatory bean的实例化和方法的指定

### BeanFactory和FactoryBean的区别（个人理解）

1. BeanFactory实际上是制造、实例化Bean的工厂
2. FactoryBean是一个被实例化的Factory 对象，可以在spring中被用来辅助制造对象



## 7.5 BeanFactory和FactoryBean的区别

### 7.5.1 BeanFactory

Spring IoC容器的顶级对象，BeanFactory被翻译为“Bean工厂”，在Spring的IoC容器中，“Bean工厂”负责创建Bean对象。

BeanFactory是工厂。

### 7.5.2 FactoryBean

FactoryBean：它是一个Bean，是一个能够**辅助Spring**实例化其它Bean对象的一个Bean。

在Spring中，Bean可以分为两类：

- 第一类：普通Bean
- 第二类：工厂Bean（记住：工厂Bean也是一种Bean，只不过这种Bean比较特殊，它可以辅助Spring实例化其它Bean对象。）