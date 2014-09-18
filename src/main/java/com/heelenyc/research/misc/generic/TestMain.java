package com.heelenyc.research.misc.generic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Fruit {
    private final String name = "Fruit";

    public void show() {
        System.out.println(this.name);
    }
}

class Apple extends Fruit {
    private final String name = "Apple";
}

class Orange extends Fruit {
    private final String name = "Orange";
}

class Fushi extends Apple {
    private final String name = "Fushi";
}

public class TestMain {

    public static void main(String[] args) {
        // 泛型的运行时类型
        // Class c1 = new ArrayList<Integer>().getClass();
        // Class c2 = new ArrayList<String>().getClass();
        //
        // Class<ArrayList> c3 = ArrayList.class;
        //
        // System.out.println(c1);
        // System.out.println(c1 == c2);
        // System.out.println(c3);

        // 数组的协变
        // Fruit[] fruits = new Apple[20];
        // fruits[0] = new Apple();// ok
        // fruits[1] = new Fruit(); // java.lang.ArrayStoreException

        // ArrayList<Fruit> fl = new ArrayList<Apple>(); // compilor error
        // ,类型不匹配，泛型不支持协变，编译器看到的是两个不同的类型，虽然运行时的类型都是list（因为擦除机制，java的期望是把这种问题放在编译期处理）
        
        // 限定通配符
        // List<? extends Fruit> fl = new ArrayList<Apple>(); //
        // 泛型不像数组有内建的协变，所有要通配符，子类型限定通配符读取是安全的
        // // fl.add(new Apple());//compiler error ,因为编译期不知道fl指向的到底是什么类型的泛型实例。
        // Fruit f = fl.get(0); // ? extends fruit 转为 fruit 是可以的
        //
        // List<? super Apple> al = new ArrayList<Fruit>();//
        // 超类型限定符，读不合法，因为不知道到底是什么类型，但是写是可以的；
        // al.add(new Apple());
        // // Apple a = al.get(0);// compilor error , 类型不合
        
        // 超类限定符的一个应用
        fun(new MyNumber(1));
    }
    public static <T extends Comparable<? super MyNumber>> void fun(T number) {
        System.out.println(number);
    }
}

class BashNumber{
    protected Integer value;
    
    /**
     * 
     */
    public BashNumber(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BashNumber [value=" + value + "]";
    }
}

class MyNumber extends BashNumber implements Comparable<BashNumber>{

    /**
     * @param value
     */
    public MyNumber(int value) {
        super(value);
    }

    @Override
    public int compareTo(BashNumber o) {
        return value.compareTo(o.value);
    }
    
}
