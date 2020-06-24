package com.example.vova.ShildtSecond.busines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("singleton")
public class ConsumerManager {
    @Autowired
    private ApplicationContext context;
    Dispatcher dispatcher;

    boolean initiated = false;
    Consumer[] consumers;
    private int pointer = 0;
    private int consumerLength = 32;
    private Q q;

    ConsumerManager(){
        System.out.println(8 + " ConsumerManager constructed (default constructor)");
    }

    @PostConstruct
    public void init() {
        initiated = true;
        dispatcher = context.getBean(Dispatcher.class);

        q =  dispatcher.getQ();
        consumers = new Consumer[consumerLength];
        System.out.println(consumerLength + " Consumers initiated in @postConstruct");
    }

    private boolean incCons(){
        if(pointer == consumerLength)
            return false;
        consumers[pointer] = new Consumer(q, pointer);
        consumers[pointer].run();
        return true;
    }


}
