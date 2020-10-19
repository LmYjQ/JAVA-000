学习笔记

## 1.编译并查看字节码
```
cd  src/com/geektime/
javac Hello.java
javap -c Hello
```

```
Compiled from "Hello.java"
public class com.geektime.Hello {
  public com.geektime.Hello();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: iconst_0
       1: istore_1
       2: iconst_1
       3: istore_2
       4: iload_2
       5: bipush        100
       7: if_icmpgt     26
      10: iload_2
      11: iconst_2
      12: irem
      13: ifne          20
      16: iload_1
      17: iload_2
      18: iadd
      19: istore_1
      20: iinc          2, 1
      23: goto          4
      26: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
      29: new           #3                  // class java/lang/StringBuilder
      32: dup
      33: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
      36: ldc           #5                  // String sum=
      38: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      41: iload_1
      42: invokevirtual #7                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
      45: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      48: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      51: return
}
```

## 2.自定义classloader
代码见src/com/geektime/HelloClassLoader.java
### print结果理解
一个字节=8位，2^8=256，补码最高位表示正负，所以-128-127
8=2*4,4位最多表示0-15，16进制0-F

## 3.参数理解
[!image](https://github.com/LmYjQ/JAVA-000/tree/main/Week_01/jvm_cheatseet.jpg)
Xms、XmX为堆内存的最小-最大值，这里存了所有的对象。对象被所有线程共享。
Xmn为新生代内存占用量，G1GC不需要（G1设置每个小块的内存和块的数量）。
Meta
DirectMemory
Xss为每个线程栈的内存占用，这里存了每个方法的局部变量。


参考资料
https://dzone.com/articles/java-memory-architecture-model-garbage-collection
