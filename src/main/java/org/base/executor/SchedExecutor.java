package org.base.executor;

import com.google.inject.Singleton;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


@Singleton
public class SchedExecutor {

    private final ScheduledExecutorService scheduler;
    final ScheduledFuture<?> beeperHandle;

    public SchedExecutor() {
        scheduler = Executors.newScheduledThreadPool(1);

        beeperHandle = scheduler.scheduleAtFixedRate(beepForAnHour(), 1, 1, TimeUnit.SECONDS);

        scheduler.schedule(stopBeeping(),  6, TimeUnit.SECONDS);

    }


    public Runnable stopBeeping() {
        Runnable stopBeeps = new Runnable() {
            @Override
            public void run() {
                System.err.println("Stopping the beeping now!!!!");
                beeperHandle.cancel(true);
            }
        };

        return stopBeeps;
    }

    public Runnable beepForAnHour() {
        final Runnable beeper = new Runnable() {
            public void run() {
                System.out.println("beep");
            }
        };
        return beeper;
    }

}
