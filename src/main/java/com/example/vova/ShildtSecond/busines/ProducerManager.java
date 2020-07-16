package com.example.vova.ShildtSecond.busines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Map;

@Component
@Scope("singleton")
public class ProducerManager {

    boolean initiated = false;
    private ArrayList<Producer> producers;
    private int pointer = 0;
    private int producersMaxQant = 8;
    @Autowired
    private Q q;
    private Dispatcher dispatcher;

    ProducerManager() {
    }

    @PostConstruct
    public void PostConstruct() {
        initiated = true;
        producers = new ArrayList<>();
        Producer pr;
        for(int i=0; i<producersMaxQant; i++) {
            pr = new Producer(773+i*171, i, q);
            producers.add(i,pr);
            pr.startRunning();
        }
        System.out.println(producers.size() + " Producers initiated in @postConstruct");
    }

    @PreDestroy
    void PreDestroy() {
        for (Producer pr: producers
             ) {
            pr.stopRunning();
        }
        System.out.print("Dispatcher  PreDestroy performed \n");
    }

    public void init(Dispatcher d) {
        dispatcher = d;
        q = dispatcher.getQ();
    }

    public void stop() {
        for (Producer pm : producers) {
            pm.stopRunning();
        }
    }

    public int getNumberOfActiveProd(){
        return producers.size();
    }

    public ArrayList<Map<String, String>> getProducers(){
        ArrayList<Map<String, String>> producersList = new ArrayList<>();
        for (Producer p : producers) {
            producersList.add(p.getProducer());
        }
        return producersList;
    }

    public boolean switchProducer(int prodNum){
        System.out.println("Producer manager - switchProducer:" + Integer.toString(prodNum));
        return producers.get(prodNum).switchWorkState();
    }
}
