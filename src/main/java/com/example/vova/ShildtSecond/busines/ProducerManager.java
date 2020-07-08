package com.example.vova.ShildtSecond.busines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("singleton")
public class ProducerManager {

    boolean initiated = false;
    private Producer[] producers;
    //    private ProducerState[] producerStates;
    private int pointer = 0;
    private int producersLength = 8;
    private Q q;
    private Dispatcher dispatcher;
//    ProducerState producersState;

    ProducerManager() {
        System.out.println(producersLength + " Producers constructed (default constructor)");
    }

    @PostConstruct
    public void PostConstruct() {
        initiated = true;
        producers = new Producer[producersLength];
//        producerStates = new ProducerState[producersLength];
        System.out.println(producers.length + " Producers initiated in @postConstruct");
    }

    public void init(Dispatcher d) {
        dispatcher = d;
        q = dispatcher.getQ();
    }

    public boolean incProd() {
        if (pointer == (producersLength - 1))
            return false;
        pointer++;
        if (producers[pointer] == null)
            producers[pointer] = new Producer(pointer, q);
        producers[pointer].run();
        return true;
    }

    public boolean decProd() {
        if (pointer < 1)
            return false;
        producers[pointer].stopYourself();
        pointer--;
        return true;
    }

    public void stop() {
        for (Producer pm : producers) {
            pm.stopYourself();
        }
    }

    public String getState() {
        StringBuilder state = new StringBuilder();
   //     for (Producer p : producers
   //          ) {

            state.append("{" + p.toString() +"}");
    //    }

        System.out.println("ProducerManager" + state.toString());
        return "{producers:" + state.toString() + "},";
    }

}
