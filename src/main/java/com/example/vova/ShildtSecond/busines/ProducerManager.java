package com.example.vova.ShildtSecond.busines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("singleton")
public class ProducerManager {
    @Autowired
    private ApplicationContext context;
    Dispatcher dispatcher;

    boolean initiated = false;
    Producer[] producers;
    private int pointer = 0;
    private int producersLength = 8;
    private Q q;

     ProducerManager(){
        System.out.println(8 + " Producers constructed (default constructor)");
    }

    @PostConstruct
    public void init() {
        initiated = true;
        dispatcher = context.getBean(Dispatcher.class);
        q =  dispatcher.getQ();
        producers = new Producer[8];
       // incProd();
        System.out.println(producers.length + " Producers initiated in @postConstruct");
    }

    public boolean incProd(){
        if(pointer == (producersLength-1))
            return false;
        pointer ++;
        if(producers[pointer] == null)
            producers[pointer] = new Producer(pointer, q);
        producers[pointer].run();
        return true;
    }

    public boolean decProd(){
        if(pointer < 1)
            return false;
        producers[pointer].stopYourself();
        pointer--;
        return true;
    }

    public void stop(){
        for (Producer pm:producers) {
            pm.stopYourself();
        }
    }
}
