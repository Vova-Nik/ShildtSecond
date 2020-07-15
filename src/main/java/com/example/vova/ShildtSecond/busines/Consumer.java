package com.example.vova.ShildtSecond.busines;

public class Consumer implements Runnable{
    private boolean isRunning = false;
    private int myNumber;
    private Q q;

    Consumer(){
        isRunning = false;
    }

    Consumer(Q q, int numberInArr){
        isRunning = false;
        myNumber = numberInArr;
        this.q = q;
    }

    @Override
    public void run(){
        consumeOne();
        try{Thread.sleep(200);}catch(Exception e){System.out.println("Consumer sleep exception");}
    }


    public boolean consumeOne(){
        q.giveOutProd();
        return true;
    }

}
