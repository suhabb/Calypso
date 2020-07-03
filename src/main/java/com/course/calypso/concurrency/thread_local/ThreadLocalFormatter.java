package com.course.calypso.concurrency.thread_local;

import java.text.SimpleDateFormat;


public class ThreadLocalFormatter {

    public static ThreadLocal<SimpleDateFormat> simpleDateFormat = ThreadLocal
            .withInitial(()->new SimpleDateFormat("yyyy-MM-dd"));//
}
