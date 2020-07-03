package com.course.calypso.concurrency.thread_local;

import com.course.calypso.dto.User;

public class ThreadLocalUser {

    public static ThreadLocal<User> threadLocalUser = new ThreadLocal<>();

    public void set(User user){
        ThreadLocalUser.threadLocalUser.set(user);
    }

    public User get(){
        return  ThreadLocalUser.threadLocalUser.get();
    }
}
