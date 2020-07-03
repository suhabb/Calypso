package com.course.calypso.concurrency.semaphore;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;


/*
The program uses a semaphore to control access to the count variable, which is a static variable within the Shared class.
".count" is incremented five times by thread A and decremented five times by thread B.To prevent these two threads
from accessing Shared.count at the same time, access is allowed only after a permit is acquired from the controlling
semaphore. After access is complete, the permit is released.
In this way, only one thread at a time will access Shared.count, as the output shows.

 */
class Shared
{
    static int count = 0;
}

@Slf4j
class SemaphoreDemo extends Thread
{
    Semaphore semaphore;
    String threadName;

    public SemaphoreDemo(Semaphore semaphore, String threadName)
    {
        super(threadName);
        this.semaphore = semaphore;
        this.threadName = threadName;
    }

    @Override
    public void run() {

        // run by thread A 
        if(this.getName().equals("A"))
        {
            log.info("Starting " + threadName);
            try
            {
                // First, get a permit. 
                log.info(threadName + " is waiting for a permit.");

                // acquiring the lock 
                semaphore.acquire();

                log.info(threadName + " gets a permit.");

                // Now, accessing the shared resource. 
                // other waiting threads will wait, until this  
                // thread release the lock 
                for(int i=0; i < 5; i++)
                {
                    Shared.count++;
                    log.info(threadName + ": " + Shared.count);

                    // Now, allowing a context switch -- if possible. 
                    // for thread B to execute 
                    Thread.sleep(10);
                }
            } catch (InterruptedException exc) {
                log.error("Exception: ",exc);
            }

            // Release the permit. 
            log.info(threadName + " releases the permit.");
            semaphore.release();
        }

        // run by thread B 
        else
        {
            log.info("Starting " + threadName);
            try
            {
                // First, get a permit. 
                log.info(threadName + " is waiting for a permit.");

                // acquiring the lock 
                semaphore.acquire();

                log.info(threadName + " gets a permit.");

                // Now, accessing the shared resource. 
                // other waiting threads will wait, until this  
                // thread release the lock 
                for(int i=0; i < 5; i++)
                {
                    Shared.count--;
                    log.info(threadName + ": " + Shared.count);

                    // Now, allowing a context switch -- if possible. 
                    // for thread A to execute 
                    Thread.sleep(10);
                }
            } catch (InterruptedException exc) {
                log.info("Exception : ",exc);
            }
            // Release the permit. 
            log.info(threadName + " releases the permit.");
            semaphore.release();
        }
    }
}

