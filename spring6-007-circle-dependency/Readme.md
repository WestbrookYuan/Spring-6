# Spring的循环依赖问题

### setter+singleton情况下的循环依赖

1. 默认没有任何问题
2. 这一类的对象只有一个
3. 理由：
   1. 在这种模式下，spring对Bean的管理主要分为以下清晰的**两个阶段**：
      1. 在Spring容器加载时，实例化Bean，只要其中任意一个Bean实例化之后，马上进行**曝光**，不等属性赋值就**曝光**
      2. **曝光**之后，再进行属性的赋值（调用set方法）
   2. 核心解决方案：实例化对象和给对象赋值在两个阶段中进行
   3. 只有在scope是**singleton**的情况下，**Spring才会进行提前曝光**

### setter+prototype模式下的循环依赖

存在问题：会出现异常：当前的Bean正在处于创建中异常，**每次都会创建新的对象，相互创建**

Caused by: org.springframework.beans.factory.BeanCurrentlyInCreationException: 

Error creating bean with name 'husband': 

Requested bean is currently in creation: Is there an unresolvable circular reference?

如果其中一方是Singleton，获取非单例的一方每次都会匹配到一个新的对象



### constructor+singleton模式下的循环依赖

存在问题：会出现异常：当前的Bean正在处于创建中异常，**实例化对象的过程中无法曝光**

Caused by: org.springframework.beans.factory.BeanCurrentlyInCreationException: 

Error creating bean with name 'husband': 

Requested bean is currently in creation: Is there an unresolvable circular reference?



### Spring解决循环依赖的机理

只能在**setter+singleton**的情况下解决循环依赖

根本原因在于可以将实例化Bean和给Bean赋值分开去完成

1. 实例化Bean的时候，调用无参数方法执行，**此时可以先不给Bean赋值，可以提前将Bean赋值给外界**
2. 给Bean赋值的时候：调用setter方法来执行

两个步骤可以分开去执行，而且不要求在同一个时间点完成，也就是说Bean都是单例的，我们完全可以先把Bean都实例化出来，并放入集合（缓存）中，以后再慢慢地调用set方法给Bean赋值。这样就解决了循环依赖问题。

#### 源码分析：

DefaultSingletonBeanRegistry类中有三个比较重要的缓存：

1. private final Map<String, Object> singletonObjects **一级缓存**
2. private final Map<String, Object> earlySingletonObjects **二级缓存**
3. private final Map<String, ObjectFactory<?>> singletonFactories **三级缓存**

这三个缓存都是Map集合，map集合的name都是Bean的name（bean id）

一级缓存：存储的是完整的单例Bean对象，这个缓存中的所有单例Bean对象的所有属性都赋值了。每一个Bean都是完整的Bean对象

二级缓存：存储的是早期的单例Bean对象，这个缓存中的所有单例Bean对象并没有赋值，只是一个早期的实例对象

三级缓存：存储的是单例工厂对象，存储了大量的工厂对象，每一个单例Bean对象都对应一个单例工厂对象，这个集合存储的是，创建该单例对象时对应的那个单例工厂对象



```java
this.singletonFactories.put(beanName, singletonFactory); // 提前曝光三级缓存中的工厂


protected Object getSingleton(String beanName, boolean allowEarlyReference) {
        Object singletonObject = this.singletonObjects.get(beanName);
        if (singletonObject == null && this.isSingletonCurrentlyInCreation(beanName)) {
            singletonObject = this.earlySingletonObjects.get(beanName);
            if (singletonObject == null && allowEarlyReference) {
                synchronized(this.singletonObjects) {
                    singletonObject = this.singletonObjects.get(beanName);
                    if (singletonObject == null) {
                        singletonObject = this.earlySingletonObjects.get(beanName);
                        if (singletonObject == null) {
                            ObjectFactory<?> singletonFactory = (ObjectFactory)this.singletonFactories.get(beanName);
                            if (singletonFactory != null) {
                                singletonObject = singletonFactory.getObject();
                                this.earlySingletonObjects.put(beanName, singletonObject);
                                this.singletonFactories.remove(beanName);
                            }
                        }
                    }
                }
            }
        }

        return singletonObject;
   }

//先从一级缓存取对象，取不到就去二级缓存取对象，再取不到就从三级缓存中取出工厂，实例化一个对象，并去除三级缓存，加入二级缓存
```

