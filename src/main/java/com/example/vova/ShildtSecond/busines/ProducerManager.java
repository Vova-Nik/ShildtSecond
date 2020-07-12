package com.example.vova.ShildtSecond.busines;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
        System.out.println(producersMaxQant + " Producers constructed (default constructor)");
    }

    @PostConstruct
    public void PostConstruct() {
        initiated = true;
        producers = new ArrayList<>();
        System.out.println(producers.size() + " Producers initiated in @postConstruct");
    }

    public void init(Dispatcher d) {
        dispatcher = d;
        q = dispatcher.getQ();
    }

    public boolean incProd() {
        if (producers.size()>producersMaxQant)
            return false;
        Producer pr = new Producer(producers.size(), q);
        producers.add(pr);
        pr.run();

        return true;
    }

    public boolean decProd() {
        if (pointer < 1)
            return false;
        int last = producers.size()-1;
        producers.get(last).stopYourself();
        producers.remove(last);
        pointer--;
        return true;
    }

    public void stop() {
        for (Producer pm : producers) {
            pm.stopYourself();
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

}
