package com.geektime;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class<?> helloClass = new HelloClassLoader().findClass("Hello");
            Object instance = helloClass.newInstance();
            Method func = helloClass.getMethod("hello");
            func.invoke(instance);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = "/Users/boss/Documents/code/JAVA-000/Week_01/src/com/geektime/Hello.xlass";
        File file = new File(fileName);
        InputStream in = null;
        byte[] bytes;
        try {
            in = new FileInputStream(file);
            bytes = new byte[in.available()];

            int i = 0;
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                bytes[i] = (byte) (255 - tempbyte);
                i += 1;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println(Arrays.toString(bytes));
        return defineClass(name, bytes, 0, bytes.length);
    }
}
