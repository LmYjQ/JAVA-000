学习笔记
## 网关
业务：关注功能 vs 流量：关注性能

根据vip等级限制接口调用次数

## netty
作为server需要绑定boss和worker
作为client只需要绑定worker

可以控制用堆内还是堆外内存

## 老师代码逻辑梳理
1. 网关服务入口，启动HttpInboundServer。两个参数，网关接口port（host是自己），代理后端服务地址host:port
2. bootstrap启动boss，worker组，绑定childHandler
3. Inbound初始化，增加InboundHandler处理逻辑。这一步也可以做编码解码
4. InboundHandler中有一个OutboundHandler成员，handle方法负责处理request
5. OutboundHandler的handle方法是异步的（submit），这步也可以换成同步




## SMP vs NUMA
对称内存结构

## 进程vs 线程
多个进程可以绑定一个端口，父子进程

## 多线程
如果只有一个线程，还设置为守护线程，那么里面的逻辑不会执行

## Thread方法
Thread.sleep()静态方法，属于类
thread.join()属于实例的方法

violate并不能保证一致性：多线程sum的例子会比不带violate好一些，但是还是会少
