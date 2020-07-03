package com.course.calypso.concurrency.locks;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWithConditionDemo {

    Stack<String> stack = new Stack<>();
    int CAPACITY = 5;

    ReentrantLock lock = new ReentrantLock();
    Condition stackEmptyCondition = lock.newCondition();
    Condition stackFullCondition = lock.newCondition();

    public void pushToStack(String item) throws InterruptedException {
        try {
            lock.lock();
            while(stack.size() == CAPACITY) {
                //The current thread suspends its execution until it is signalled or interrupted.
                stackFullCondition.await();
            }
            stack.push(item);

            //This method wakes all threads waiting on this condition.
            stackEmptyCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String popFromStack() throws InterruptedException {
        try {
            lock.lock();
            while(stack.size() == 0) {

                //The current thread suspends its execution until it is signalled or interrupted.
                stackEmptyCondition.await();
            }
            return stack.pop();
        } finally {

            //This method wakes all threads waiting on this condition.
            stackFullCondition.signalAll();
            lock.unlock();
        }
    }
}