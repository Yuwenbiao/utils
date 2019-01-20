package com.example.util.多线程.ThreadFactory的使用;

import java.util.concurrent.*;

public class ThreadFactoryTest {
    private ThreadFactory singleThreadFactory = r -> new Thread(r, "t_pl_pool_" + r.hashCode());

    private void test() {
        ExecutorService executorService = Executors.newSingleThreadExecutor(r -> new Thread(r, "t_pl_pool_" + r.hashCode()));
        Executor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), singleThreadFactory);
        executor.execute(() -> System.out.println("hello"));
        executorService.submit(() -> System.out.println("hello"));
    }
}
