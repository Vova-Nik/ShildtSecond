package com.example.vova.ShildtSecond.busines;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Producer extends Thread {

    int myNumber;
    private int timeInterval;
    boolean isRunning = false;
    Q q;
    private int produced;
    Producer selfRef;

    public Producer(int n, Q q) {
        timeInterval = 1000;
        isRunning = false;
        myNumber = n;
        this.q = q;
        selfRef = this;
        produced = 0;
    }

    public boolean setTimeInterval(int num) {
        timeInterval = num;
        System.out.println("Producer initiated " + num);
        return true;
    }

    public int getTimeInterval() {
        return timeInterval;
    }
    public int getProduced() {
        return produced;
    }

    public void stopYourself() {
        isRunning = false;
    }

    @Override
    public void run() {
        System.out.println("###########################");
        if (isRunning)
            return;
        isRunning = true;
        while (isRunning) {
            produceOne();
            //System.out.println("###########################");
            try {
                 Thread.sleep(timeInterval);
            } catch (InterruptedException e) {
                System.out.println("Exception in Producer # " + myNumber);
            }
        }
        System.out.println("  - ==> Hello Thread stopped");
    }

    private void produceOne()
    {
        //q.putProd(selfRef);
        //System.out.println("Producer # " + myNumber + " put 1 more item");
        produced++;
    }

    @Override
    public String toString(){
        return  "\"producer" + myNumber + "\":" +
                "{" +
                "\"keys\":[class myNumber timeInterval isRunning produced]," +
                "\"class\":\"Producer\"," +
                "\"myNumber\":" + myNumber + "," +
                "\"timeInterval\":" + timeInterval + "," +
                "\"isRunning\":" + isRunning + "," +
                "\"produced\":" + produced +
                "}";
    }

    public Map<String, String> getProducer(){
            Map<String, String> me = new HashMap<>();
            me.put("class", "Producer");
            me.put("myNumber", Integer.toString(myNumber));
            me.put("timeInterval", Integer.toString(timeInterval));
            me.put("isRunning", Boolean.toString(isRunning));
            me.put("produced", Integer.toString(produced));
            return me;
    }

}

