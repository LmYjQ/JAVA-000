学习笔记

## 1.NIO存在的意义
单线程效率最低

多线程，频繁建立销毁线程浪费资源，效率稍高，提升有限

线程池，相对多线程效率进一步提升,但是切换线程还是有开销

NIO nonblockIO，通过IO复用的方式继续优化性能

还有什么缺点吗？IO进行过程中CPU一直在等待，浪费了资源

## 2.什么是NIO
### channel
多*路*（类比多个通道）IO同时在干活，channel的类型包含
- FileChannel
- DatagramChannel
- SocketChannel
- ServerSocketChannel
channel包含了UDP+TCP网络IO，也包含了文件IO

### buffer
缓冲区和channel可以互相读写,buffer的类型包含
- ByteBuffer
- CharBuffer
- DoubleBuffer
- FloatBuffer
- IntBuffer
- LongBuffer
- ShortBuffer

buffer分配的容量capacity，每次最多写这么多。实际每次写的数据量limit（可多可少，不能超过capacity），目前读到的位置position。

#### put 
向buffer中填充数据，可以有各种方式
```
// 填充一个 byte 值
public abstract ByteBuffer put(byte b);
// 在指定位置填充一个 int 值
public abstract ByteBuffer put(int index, byte b);
// 将一个数组中的值填充进去
public final ByteBuffer put(byte[] src) {...}
public ByteBuffer put(byte[] src, int offset, int length) {...}
```
#### get
从buffer中拿数据，也有各种方式
```
// 根据 position 来获取数据
public abstract byte get();
// 获取指定位置的数据
public abstract byte get(int index);
// 将 Buffer 中的数据写入到数组中
public ByteBuffer get(byte[] dst)
```

#### flip
切换get/put状态

### channel和buffer交互
channel.read(buffer)，从文件或者网络把数据读到内存中

channel.write(buffer)，如通过FileChannel写文件，SocketChannel发送数据

### selector
selector负责检查哪个channel的活干完了。channel需要*注册*到selector上，然后selector轮训channel看谁好了。用二进制位标示监听什么事件，如果多个位都置为1可以监听多个。

NIO 中 Selector 是对底层操作系统实现的一个抽象，管理通道状态其实都是底层系统实现的，这里简单介绍下在不同系统下的实现。

select：上世纪 80 年代就实现了，它支持注册 FD_SETSIZE(1024) 个 socket，在那个年代肯定是够用的，不过现在嘛，肯定是不行了。

poll：1997 年，出现了 poll 作为 select 的替代者，最大的区别就是，poll 不再限制 socket 数量。

select 和 poll 都有一个共同的问题，那就是它们都只会告诉你有几个通道准备好了，但是不会告诉你具体是哪几个通道。所以，一旦知道有通道准备好以后，自己还是需要进行一次扫描，显然这个不太好，通道少的时候还行，一旦通道的数量是几十万个以上的时候，扫描一次的时间都很可观了，时间复杂度 O(n)。所以，后来才催生了以下实现。

epoll：2002 年随 Linux 内核 2.5.44 发布，epoll 能直接返回具体的准备好的通道，时间复杂度 O(1)。

除了 Linux 中的 epoll，2000 年 FreeBSD 出现了 Kqueue，还有就是，Solaris 中有 /dev/poll。

Windows非阻塞比较low只有select，不过有异步的IOCP

### gather/scatter
gather (write)把多个buffer塞到一个channel,scatter (read)把一个channel分给多个buffer

反向过程（多个channel一个buffer）不存在

## 3.异步
Java的两种异步IO方式：Future/回调函数


## 4.netty
BossGroup:领导拍需求,通过channel把任务分别worker
WorkerGroup：小组长分任务,通过channel把任务给到handler
eventloop：码农搬砖
taskQueue：任务队列
pipeline：执行任务的步骤
(server)bootstrap:启动（服务器）客户端
handler: 包含encode/decode/handle
ChannelHandlerContext:负责维护多个handler之间的关系

run一个server/client，代码差别不大

server/client的in/outBound相反,做一些打开/关闭连接，读/写数据等事情

### 粘包与拆包
http协议需要有结束的约定，比如按长度/结束字符
TCP本身没有粘包/拆包的问题，因为是长连接流式

TCP/HTTP头各20个字节
MTU：1500Byte
MSS：1460Byte

Nagle算法主要是避免发送小的数据包，要求TCP连接上最多只能有一个未被确认的小分组，在该分组的确认到达之前不能发送其他的小分组。相反，TCP收集这些少量的小分组，并在确认到来时以一个分组的方式发出去。

## Reactor模型
单线程速度慢->work线程池处理->主从（两组reactor）模型，都多线程。
boss配置线程数少，worker多

为什么叫eventloop：轮询监听IO事件(一个组长监听老板有什么需求，分给手下）,单线程防止被阻塞

## DSL
语言/规范体系


## 作业
1. client组件化成outbound，组合
2. 改造成netty版
3. 实现filter/router，组合：filter加一个kv
4. router：负载均衡 Random/RoundRibbon/Weight

加上inputStream的read和close，curl不报错connection reset by peer
```
BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
inputStream.read();
printWriter.write("hello,nio");
printWriter.close();
socket.close();
inputStream.close();
```


Ref：
http://tutorials.jenkov.com/java-nio/overview.html

