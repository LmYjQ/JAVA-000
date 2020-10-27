学习笔记

## 1.NIO存在的意义
单线程效率最低

多线程，频繁建立销毁线程浪费资源，效率稍高，提升有限

线程池，相对多线程效率进一步提升

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

### select
selector负责检查哪个channel的活干完了。channel需要*注册*到selector上，然后selector轮训channel看谁好了。

### gather/scatter
gather (write)把多个buffer塞到一个channel,scatter (read)把一个channel分给多个buffer

反向过程（多个channel一个buffer）不存在

Ref：
http://tutorials.jenkov.com/java-nio/overview.html

