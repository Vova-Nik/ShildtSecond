package com.example.vova.ShildtSecond.busines;

public class Consumer extends Thread {
    private boolean isRunning;
    private boolean inWork;
    private int myNumber;
    private Q q;
    private int consumed;

    Consumer() {
        isRunning = true;
        inWork = true;
        consumed = 0;
    }

    Consumer(Q q, int numberInArr) {
        this();
        this.q = q;
        myNumber = numberInArr;
    }

    @Override
    public void run() {
        do {
            if (inWork) {
                consumeOne();
            }
            try {
                Thread.sleep(200);
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
        consumed++;
//        System.out.println("Consumer consumed: " + consumed);
        return q.giveOutProd();
    }

}
