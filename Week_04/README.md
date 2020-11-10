学习笔记

## 锁思想
尽量加小锁，锁尽量小的范围

## 锁最佳实践
1. 更新对象成员变量加锁
2. 访问可变成员变量加锁
3. 调用其他对象的方法不加锁

## atomic

CAS(CompareAndSet)记录试图修改之前的值，如果变了说明有别人改过

乐观锁悲观锁

volatile保证能看见修改

## 加锁vsCAS
并发很低时没区别
并发稍高时无锁好，效率高
并发非常高加锁好，一直自旋还不如排队加锁

## LongAdder改进
分段思想：每个线程自己加自己，需要get的时候把每个线程的结果合并

## 控制并发量
semaphore:总共的个数有限，一个人可以拿多个
countDownLatch；几个人干完活了聚合。一次性
cyclicBarrir：每个子线程await，聚合点在子线程。可以加回调函数。可以重用

## 集合
copyOnWriteArray:用副本
concurrentHashMap:JDK7分段锁，8 CAS

尾递归（最后一行return是递归）一定可以优化为循环

Asynchronous Programming in Java 
https://www.baeldung.com/java-asynchronous-programming
