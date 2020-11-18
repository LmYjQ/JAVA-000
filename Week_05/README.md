学习笔记

什么类型的循环依赖Spring无法处理？构造函数的循环依赖
A有属性B，B有属性A：可以解决。先new后赋值(定义引用关系)
依赖接口，不依赖实例。

循环依赖/死锁唯二解决方案：打断/超时

1、严格分层，同层直接不相互调用，比如Service里不能调用Service，这样就是一个从Controller往下的树结构，依赖不会有环，
2、如果改动太大，试着找到依赖的几个service，试试lazy=true或者required（false），spring会自己解决依赖问题

还有问题的话排查：
1.在idea里看，是不是重复引入了一些不同版本的包，比如netty4.1.42和45等，exclude掉不需要的
2.打包时，加上-U，或者情况.m2，解决缓存不同版本的snapshot


字节码增强：改变基因
反射：拍CT照B超


AOP
emit
instrument可以给不同的项目用同一个agnet:APM
ByteBuddy字节码增强:API更友好

FactoryBean
复杂的创建过程封装到方法中

disposable和destroy区别？

xsd文件，规定了对应命名空间下bean的规范
handler负责构造对象

componentScan：自动扫描包，不需要写xml

https://github.com/dromara/athena

## Springboot
限定性框架，约定大于配置，牺牲灵活性换取便利性

Spring框架：很多工具，和业务无关
Springboot脚手架：整合好的框架
Springcloud解决方案：springcloud

未来Springcloud dataflow

fat jar:所有依赖打到一个大包里
production-ready：偏向于运维，指标度量，健康检查，外部配置

## 核心原理
自动化配置：configuration.EnableXX,Condition
脚手架：各种starter


### 配置
application.yaml转换成configuration，再产生bean
配置可按前缀分组，形成一组配置，打包成一个starter组件

spring.profiles.active指定环境dev/prod,或者再yml里加也可
EnableAutoConfiguration自动装载

### 项目实例
spring.factories定义自动化配置的类
privides：表示这个包的starter的名字

additional-spring-configuration-metadata.json：定义了可配置的参数,相当于xsd，区别是有默认值

## JDBC
通过实现DataSource接口对功能做增强，aop思想

## ORM
### Hibernate
实体类+hbm。操作方式：HQL(自动把对象名映射成表字段)，criteria(类似mongo/java stream)，nativeSQL

### Mybatis
需要写原生sql
因为没有HQL，mapper.xml
继承mapper，不要覆盖:区分变与不变

大公司都用Mybatis why？对DBA友好;sql易读,方便优化

## JPA
JAVA原生的ORM规范
嵌套事务坑（传播机制+上下文）：需要都在spring上下文中创建才能生效（坑在内部事务不生效）。类似调runnable的run方法并不会新创建线程

不要嵌套service：1.用DAO 2.加一层biz用来聚合订单和userDAO

JPA项目结构：entity+repository+service

## 使用经验
多数据源：指定名字。shardingshpere自动识别表名确定数据源。写之后马上读，读主库不要读从库（框架解决）
连接池：连接数配几十就够，配心跳/重连
ORM辅助工具有性能坑：比如分页会先count limit，很慢。可以把子查询优化掉

