package com.course.calypso.concurrency.thread_local;

import com.course.calypso.dto.User;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/* Thread local
  - One object per thread
  - Per Thread Context
  - Per Thread instances for memory efficiency and safety
  - No Synchronization required
  - Return each copy of instance for each thread
  - The Java ThreadLocal class enables you to create variables that can only be read and written by the same thread.
   Thus, even if two threads are executing the same code, and the code has a reference to the same ThreadLocal variable, the two threads cannot see each other's ThreadLocal variables.
*/
@Slf4j
public class ThreadLocalDemo {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public void execute() throws InterruptedException {

        log.info("--------------------Demo One-----------------------------------------");

        for (int i = 0; i < 10; i++) {
            threadPool.submit(() -> {
                String birthDate = new ThreadLocalDemo().birthDate(new Date());
                log.info("Thread Name :{},Birth Date : {}", Thread.currentThread().getName(),birthDate);

            });
            Thread.sleep(1000);
        }
        Thread.sleep(1000);

        log.info("--------------------Demo Two-----------------------------------------");

        for (int i = 0; i < 10; i++) {
          final int id= i;
            threadPool.submit(() -> {
                new ThreadLocalDemo().setId(String.valueOf(id));
                log.info("Thread Name :{},Get Id : {}", Thread.currentThread().getName()
                ,   new ThreadLocalDemo().get().getId());

            });
            Thread.sleep(1000);
        }

    }

    public String birthDate(Date localDate) {
        SimpleDateFormat simpleDate = ThreadLocalFormatter.simpleDateFormat.get();
        return simpleDate.format(localDate);
    }

     public void setId(String id) {
        User user = User.builder().id(id).build();
        ThreadLocalUser.threadLocalUser.set(user);
    }
    public User  get() {
        return ThreadLocalUser.threadLocalUser.get();
    }
}


