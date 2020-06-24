package com.example.vova.ShildtSecond.busines;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

import javax.annotation.PreDestroy;
import java.util.ArrayList;


public class Producer extends Thread {
    //static ArrayList<Producer> producers = new ArrayList();

    int myNumber;
    private int timeInterval;
    boolean isRunning = false;
    Q q;
    Producer selfRef;

    public Producer(int n, Q q) {
        timeInterval = 1000;
        isRunning = false;
        myNumber = n;
        this.q = q;
        selfRef = this;
    }


    public boolean setTimeInterval(int num) {
        timeInterval = num;
        System.out.println("Producer initiated " + num);
        return true;
    }

    public void stopYourself() {
        isRunning = false;
    }

    @Override
    public void run() {
        if (isRunning)
            return;
        isRunning = true;
        while (isRunning) {
            System.out.println(" Producer # " + myNumber + "running");
            produceOne();
            try {
                 Thread.sleep(timeInterval);
            } catch (InterruptedException e) {
                System.out.println("Exception in Producer # " + myNumber);
            }
        }
        System.out.println("  - ==> HelloThread stopped");
    }

    private void produceOne()
    {
        q.putProd(selfRef);
        System.out.println("Producer # " + myNumber + " put 1more item");

    }
}

