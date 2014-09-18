package com.heelenyc.research.misc.corperation;

/**
 * @author yicheng
 * @since 2014年2月13日
 * 
 */
public class TestMain {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        TestMain test = new TestMain();
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        MyThreadPrinter pa = test.new MyThreadPrinter("A", b, a);
        MyThreadPrinter pb = test.new MyThreadPrinter("B", c, b);
        MyThreadPrinter pc = test.new MyThreadPrinter("C", a, c);

        new Thread(pa).start();
        new Thread(pb).start();
        new Thread(pc).start();

    }

    public class MyThreadPrinter implements Runnable {

        private String name;
        private Object next;
        private Object self;

        private MyThreadPrinter(String name, Object prev, Object self) {
            this.name = name;
            this.next = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while (count > 0) {
                synchronized (next) {
                    synchronized (self){
                        System.out.print(name);
                        count--;

                        self.notify();
                    }
                    try {
                        next.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
            return;
        }
    }

}
