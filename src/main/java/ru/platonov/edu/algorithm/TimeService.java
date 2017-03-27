package ru.platonov.edu.algorithm;

import java.util.concurrent.*;

/**
 * Created by platonov on 25/03/17.
 */
public class TimeService {

    public static void main4(String... args) {
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(4);

        scheduled.schedule(() -> {
            System.out.println("something here");
        }, 5000, TimeUnit.MILLISECONDS);

        scheduled.shutdown();

    }

    public static void main1(String... args) {
        new Thread(() -> {
            try{
                Thread.sleep(5000);
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            //do something here
        }).start();

    }

    public static void main(String... args) throws InterruptedException {
         abstract class QueueTask implements Delayed {
            final private long delay;

            QueueTask(long delay) {
                this.delay = System.currentTimeMillis() + delay;
            }

            abstract void foo();

            @Override
            public long getDelay(TimeUnit unit) {
                return delay - System.currentTimeMillis();
            }

             @Override
             public int compareTo(Delayed o) {
                 return 0;
             }
         }

        DelayQueue<QueueTask> schedule = new DelayQueue<>();

        schedule.offer(new QueueTask(5000) {
            void foo() {
                System.out.println("Print with delay");
            }
        });

        do {
            QueueTask task = schedule.take();
            task.foo();
        } while (schedule.size() > 0);

    }
}
