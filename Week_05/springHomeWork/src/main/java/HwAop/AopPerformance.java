package HwAop;

import entity.Musician;
import entity.MusicianImp;

import java.lang.reflect.Proxy;

/**
 * @author Dongfanger
 * @date 2020/11/16
 */

public class AopPerformance {

        public static  void main (String[] args) {
            MusicianImp hello = new MusicianImp();
            MyInvocationHandler handler = new MyInvocationHandler(hello);
            // 构造代码实例
            Musician proxyHello = (Musician) Proxy.newProxyInstance(MusicianImp.class.getClassLoader(), MusicianImp.class.getInterfaces(), handler);
            // 调用代理方法
            proxyHello.play();
        }



}
