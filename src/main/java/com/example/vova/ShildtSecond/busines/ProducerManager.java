package com.example.vova.ShildtSecond.busines;

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
            pr = new Producer(807+i*171, i, q);
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

//    public boolean incProd() {
//        if (producers.size()>producersMaxQant)
//            return false;
//        Producer pr = new Producer(producers.size(), q);
//        producers.add(pr);
//        pr.run();
//
//        return true;
//    }


    public void stop() {
        for (Producer pm : producers) {
            pm.stopRunning();
        }
    }

    public int getNumberOfActiveProd(){
        return producers.size();
    }

//    public String getState() {
//        if(producers.size()<1) {
//            System.out.println("ProducerManager - there is no active producers");
//            return ("{\"producers\":0}");
//        }
//        StringBuilder state = new StringBuilder("{\"pproducers\":{");
//        for (Producer p : producers) {
//            state
//                  .append(p.toString())
//                    .append(',');
//        }
//        int length = state.length();
//        state.deleteCharAt(length-1);
//        state.append("}}");
//        return(state.toString());
//    }

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
