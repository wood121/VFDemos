package com.example.wood121.viewdemos.util;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wood121 on 2018/7/17.
 */

public class ThreadPoolManager {

    /**
     * *********************静态内部类的单例模式*********************
     */
    private ThreadPoolManager mThreadPoolManager;

    public static ThreadPoolManager getInstance() {
        return SingletoHolder.sInstance;
    }

    private static class SingletoHolder {
        private static final ThreadPoolManager sInstance = new ThreadPoolManager();
    }

    /**
     * *********************线程池的创建、执行任务、shutDown操作*********************
     */
    private static ThreadPoolExecutor mExecutor = null;
    //根据cpu的数量动态的配置核心线程数和最大线程数
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    //核心线程数 = CPU核心数 + 1
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    //线程池最大线程数 = CPU核心数 * 2 + 1
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    //非核心线程闲置时超时1s
    private static final int KEEP_ALIVE = 1;

    /**
     * 初始化ThreadPoolExecutor
     * 双重检查加锁,只有在第一次实例化的时候才启用同步机制,提高了性能
     */
    private void initThreadPoolExecutor() {
        if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()) {
            synchronized (ThreadPoolManager.class) {
                if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()) {
                    TimeUnit unit = TimeUnit.MILLISECONDS;
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

                    mExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE, unit, workQueue,
                            threadFactory, handler);
                }
            }
        }
    }

    /**
     执行任务和提交任务的区别?
     1.有无返回值
     execute->没有返回值
     submit-->有返回值
     2.Future的具体作用?
     1.有方法可以接收一个任务执行完成之后的结果,其实就是get方法,get方法是一个阻塞方法
     2.get方法的签名抛出了异常===>可以处理任务执行过程中可能遇到的异常
     */
    /**
     * 执行任务
     */
    public void execute(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.execute(task);
    }

    /**
     * 提交任务
     */
    public Future<?> submit(Runnable task) {
        initThreadPoolExecutor();
        return mExecutor.submit(task);
    }

    /**
     * 移除任务
     */
    public void remove(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.remove(task);
    }

    /**
     * 立马关闭线程池
     */
    public static void shutDown() {
        if (mExecutor != null) {
            Log.e("wood121", "shutDown::::::" + mExecutor);
            mExecutor.shutdown();
            Log.e("wood121", "shutDown::::::" + mExecutor);
        }
    }

    public static void shutDownNow() {
        if (mExecutor != null) {
            Log.e("wood121", "shutDownNow::::::" + mExecutor);
            mExecutor.shutdownNow();
            Log.e("wood121", "shutDownNow::::::" + mExecutor);
        }
    }
}
