package com.example.vova.ShildtSecond.busines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
    private int consumerLength = 16;
    @Autowired
    private Q q;

//    @Autowired
//    ConsumerManager(Dispatcher d){
//        dispatcher = d;
//        System.out.println(consumerLength + " ConsumerManager constructed (default constructor)");
//    }

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

    private boolean incCons() {
        if (pointer == consumerLength)
            return false;
        consumers[pointer] = new Consumer(q, pointer);
        consumers[pointer].run();
        return true;
    }
    public int getNumberOfActiveCons(){
        return consumerLength;
    }


}
