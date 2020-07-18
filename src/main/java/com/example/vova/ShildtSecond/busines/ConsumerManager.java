package com.example.vova.ShildtSecond.busines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Map;

@Component
@Scope("singleton")
public class ConsumerManager {
//    @Autowired
//    private ApplicationContext context;

    //@Autowired
    private Dispatcher dispatcher;
    boolean initiated = false;
    Consumer[] consumers;
    private int pointer = 0;
    private int consumerLength = 8;
    @Autowired
    private Q q;

    ConsumerManager() {
        System.out.println(consumerLength + " ConsumerManager constructed (default constructor)");
    }

    @PostConstruct
    public void PostConstruct() {
        initiated = true;
        consumers = new Consumer[consumerLength];
        System.out.println(consumerLength + " Consumers initiated in @postConstruct");
        for (int i=0; i<consumerLength; i++) {
           // Consumer consumer = new Consumer(q, i);
            consumers[i] = new Consumer(q, i);
            consumers[i].start();
        }
    }

    void stopRunning() {
        for (Consumer cm: consumers
        ) {
            cm.stopRunning();
        }
        System.out.print("ConsumerManager  PreDestroy performed \n");
    }

    public int getNumberOfActiveCons(){
        return consumerLength;
    }

    public ArrayList<Map<String, String>> getConsumers(){
        ArrayList<Map<String, String>> consumersList = new ArrayList<>();
        for (Consumer c : consumers) {
            consumersList.add(c.getConsumer());
        }
        return consumersList;
    }

    public boolean switchConsumer(int consNum){
        return consumers[consNum].switchWorkState();
    }

//    public boolean switchConsumer(int consNum){
//        System.out.println("Consumer manager - switchConsumer:" + Integer.toString(consNum));
//        return consumers.get(consNum).switchWorkState();
//    }
}
