package com.jam.java.guide;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: SpringCloudStudy
 * @description: 并发
 * @author: Mr.Pu
 * @create: 2022-04-10 21:39
 **/

public class JUC {

    /**
     * <p style="font-size:20px;color:red">并发编程的三个重要特性</p>
     * <span style="font-size:15px">原子性 :</span> 一次操作或者多次操作，要么所有的操作全部都得到执行并且不会受到任何因素的干扰而中断，要么都不执行。
     * synchronized 可以保证代码片段的原子性。<br>
     * <span style="font-size:15px">可见性 ：</span>当一个线程对共享变量进行了修改，那么另外的线程都是立即可以看到修改后的最新值。
     * volatile 关键字可以保证共享变量的可见性。<br>
     * <span style="font-size:15px">有序性 ：</span>代码在执行的过程中的先后顺序，Java 在编译器以及运行期间的优化，
     * 代码的执行顺序未必就是编写代码时候的顺序。volatile 关键字可以禁止指令进行重排序优化。
     * <br/>
     * <p style="font-size:20px;color:red">两个关键字的联系和区别</p>
     * synchronized 关键字和 volatile 关键字是两个互补的存在，而不是对立的存在！<br>
     * <p>
     * volatile 关键字是线程同步的轻量级实现，所以 volatile 性能肯定比synchronized关键字要好 。
     * 但是 volatile 关键字只能用于变量而 synchronized 关键字可以修饰方法以及代码块 。<br>
     * volatile 关键字能保证数据的可见性，但不能保证数据的原子性。synchronized 关键字两者都能保证。<br>
     * volatile关键字主要用于解决变量在多个线程之间的可见性，
     * 而 synchronized 关键字解决的是多个线程之间访问资源的同步性。<br>
     *
     * <p style="font-size:20px;color:red">ThreadLocal 简介</p>
     * 通常情况下，我们创建的变量是可以被任何一个线程访问并修改的。
     * 如果想实现每一个线程都有自己的专属本地变量该如何解决呢？
     * JDK 中提供的ThreadLocal类正是为了解决这样的问题。
     * ThreadLocal类主要解决的就是让每个线程绑定自己的值，可以将ThreadLocal类形象的比喻成存放数据的盒子，
     * 盒子中可以存储每个线程的私有数据。
     * <p>如果你创建了一个ThreadLocal变量，那么访问这个变量的每个线程都会有这个变量的本地副本，
     * 这也是ThreadLocal变量名的由来。他们可以使用 get（） 和 set（） 方法来获取默认值或将其值更改为当前线程所存的副本的值，
     * 从而避免了线程安全问题。</p>
     *
     * <p style="font-size:20px;color:red">使用线程池的好处：</p>
     *
     * <p><span style="font-size:15px">降低资源消耗。</span>通过重复利用已创建的线程降低线程创建和销毁造成的消耗。</p>
     * <p><span style="font-size:15px">提高响应速度。</span>当任务到达时，任务可以不需要等到线程创建就能立即执行。</p>
     * <p><span style="font-size:15px">提高线程的可管理性。</span>线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控。</p>
     */


    public static void main(String[] args) {
        javaThread();
    }

    /**
     * 获取Java的线程信息
     */
    public static void javaThread() {
        //获取java线程管理的MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //仅获取线程和线程堆栈信息，不获取同步的monitor和synchronizer信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        //遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
        }
    }

    //死锁
    static class DeadLock {
        private static final Object resource1 = new Object();
        private static final Object resource2 = new Object();

        public static void main(String[] args) {
            new Thread(() -> {
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "get resource1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get resource2");
                    synchronized (resource2) {
                        System.out.println(Thread.currentThread() + "get resource2");
                    }
                }
            }, "线程1").start();
            new Thread(() -> {
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get resource1");
                    synchronized (resource1) {
                        System.out.println(Thread.currentThread() + "get resource1");
                    }
                }
            }, "线程2").start();
            //更改为以下代码就不会死锁
//            new Thread(() -> {
//                synchronized (resource1) {
//                    System.out.println(Thread.currentThread() + "get resource1");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(Thread.currentThread() + "waiting get resource2");
//                    synchronized (resource2) {
//                        System.out.println(Thread.currentThread() + "get resource2");
//                    }
//                }
//            }, "线程 2").start();
        }
    }

    //单例模式 双重检验锁方式实现单例模式
    public static class Singleton {
        //volatile 关键字 除了防止 JVM 的指令重排 ，还有一个重要的作用就是保证变量的可见性。
        private volatile static Singleton uniqueInstance;

        private Singleton() {
        }

        public static Singleton getUniqueInstance() {
            //先判断对象是否已经实例过，没有实例化过才进入加锁代码
            if (uniqueInstance == null) {
                //类对象加锁
                synchronized (Singleton.class) {
                    if (uniqueInstance == null) {
                        uniqueInstance = new Singleton();
                    }
                }
            }
            return uniqueInstance;
        }

        public static synchronized void method() {
            synchronized (Singleton.class) {
                System.out.println("synchronized 代码块");
            }
        }

        public static void main(String[] args) {
            method();
        }
    }

    //ThreadLocal实现
    public static class ThreadLocalExample implements Runnable {

        // SimpleDateFormat 不是线程安全的，所以每个线程都要有自己独立的副本
        private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

        public static void main(String[] args) throws InterruptedException {
            ThreadLocalExample obj = new ThreadLocalExample();
            for (int i = 0; i < 10; i++) {
                Thread t = new Thread(obj, "" + i);
                Thread.sleep(new Random().nextInt(1000));
                t.start();
            }
        }

        @Override
        public void run() {
            System.out.println("Thread Name= " + Thread.currentThread().getName() + " default Formatter = " + formatter.get().toPattern());
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //formatter pattern is changed here by thread, but it won't reflect to other threads
            formatter.set(new SimpleDateFormat());

            System.out.println("Thread Name= " + Thread.currentThread().getName() + " formatter = " + formatter.get().toPattern());
        }

    }

    public static class MyRunnable implements Runnable {

        private final String command;

        public MyRunnable(String s) {
            this.command = s;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
            processCommand();
            System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
        }

        private void processCommand() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return this.command;
        }
    }

    public static class ThreadPoolExecutorDemo {

        private static final int CORE_POOL_SIZE = 5;
        private static final int MAX_POOL_SIZE = 10;
        private static final int QUEUE_CAPACITY = 100;
        private static final Long KEEP_ALIVE_TIME = 1L;

        public static void main(String[] args) {

            //使用阿里巴巴推荐的创建线程池的方式
            //通过ThreadPoolExecutor构造函数自定义参数创建
            ThreadPoolExecutor executor = new ThreadPoolExecutor(
                    CORE_POOL_SIZE,
                    MAX_POOL_SIZE,
                    KEEP_ALIVE_TIME,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                    new ThreadPoolExecutor.CallerRunsPolicy());

            for (int i = 0; i < 10; i++) {
                //创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
                Runnable worker = new MyRunnable("" + i);
                //执行Runnable
                executor.execute(worker);
            }
            //终止线程池
            executor.shutdown();
            while (!executor.isTerminated()) {
            }
            System.out.println("Finished all threads");
        }
    }

    //原子类

    /**
     * <p style="font-size:20px;color:red">基本类型</p>
     * <p>
     * 使用原子的方式更新基本类型
     * <p>
     * AtomicInteger：整型原子类
     * AtomicLong：长整型原子类
     * AtomicBoolean：布尔型原子类
     * <p style="font-size:20px;color:red">数组类型</p>
     * <p>
     * 使用原子的方式更新数组里的某个元素
     * <p>
     * AtomicIntegerArray：整型数组原子类
     * AtomicLongArray：长整型数组原子类
     * AtomicReferenceArray：引用类型数组原子类
     * <p style="font-size:20px;color:red">引用类型</p>
     * <p>
     * AtomicReference：引用类型原子类
     * AtomicStampedReference：原子更新带有版本号的引用类型。该类将整数值与引用关联起来，可用于解决原子的更新数据和数据的版本号，可以解决使用 CAS 进行原子更新时可能出现的 ABA 问题。
     * AtomicMarkableReference ：原子更新带有标记位的引用类型
     * <p style="font-size:20px;color:red">对象的属性修改类型</p>
     * <p>
     * AtomicIntegerFieldUpdater：原子更新整型字段的更新器
     * AtomicLongFieldUpdater：原子更新长整型字段的更新器
     * AtomicReferenceFieldUpdater：原子更新引用类型字段的更新器
     */
    public static class Atomic {
        private final AtomicInteger count = new AtomicInteger();

        //使用AtomicInteger之后，不需要对该方法加锁，也可以实现线程安全。
        public void increment() {
            count.incrementAndGet();
        }

        public int getCount() {
            return count.get();
        }
    }

}
