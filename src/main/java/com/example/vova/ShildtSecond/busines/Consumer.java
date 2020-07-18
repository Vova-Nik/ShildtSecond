package com.example.vova.ShildtSecond.busines;

import java.util.HashMap;
import java.util.Map;

public class Consumer extends Thread {
    private boolean isRunning;
    private boolean inWork;
    private int myNumber;
    private Q q;
    private int consumed;
    private int timeInterval;

    Consumer() {
        isRunning = true;
        inWork = true;
        consumed = 0;

    }

    Consumer(Q q, int numberInArr) {
        this();
        this.q = q;
        myNumber = numberInArr;
        timeInterval = 1011;
    }

    Consumer(Q q, int numberInArr, int ti) {
        this();
        this.q = q;
        myNumber = numberInArr;
        timeInterval = ti;
    }


    @Override
    public void run() {
        do {
            if (inWork) {
                consumeOne();
            }
            try {
                Thread.sleep(timeInterval);
            } catch (Exception e) {
                System.out.println("Consumer sleep exception");
            }
        }while(isRunning);
        System.out.println("  - ==> Consumer Thread stopped");
    }

    public boolean stopRunning(){
        isRunning = false;
        System.out.println("Consumer stopRunning");
        return true;
    }

    public boolean consumeOne() {
        boolean cons = q.giveOutProd();
        if(cons) {
            consumed++;
            return true;
        }
        //System.out.println("Consumer consumed: " + consumed);
        return false;
    }

    public Map<String, String> getConsumer(){
        Map<String, String> me = new HashMap<>();
        me.put("class", "Consumer");
        me.put("myNumber", Integer.toString(myNumber));
        me.put("timeInterval", Integer.toString(timeInterval));
        me.put("inWork", Boolean.toString(inWork));
        me.put("consumed", Integer.toString(consumed));
        return me;
    }

    public boolean switchWorkState(){
        inWork = !inWork;
        return true;
    }
}
