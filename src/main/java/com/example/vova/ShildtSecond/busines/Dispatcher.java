package com.example.vova.ShildtSecond.busines;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope("singleton") // change to prototype

//@PropertySource("classpath:my.properties")

public class Dispatcher {
    //  @Value("${dispatcher.max_producers}")
    private String max_producers;
    private String max_consumers;
    private int callCounter = 0;
    Q q;
    private final ProducerManager producerManager;
    private final ConsumerManager consumerManager;

//    StateObject stateObject;

    @Autowired
    Dispatcher(ProducerManager pm, ConsumerManager cm, Q qq) {
        producerManager = pm;
        consumerManager = cm;
        q = qq;
//        stateObject = new StateObject();
        System.out.print("Dispatcher constructor performed \n");
    }

    @PostConstruct
    void PostConstruct() {
        System.out.print("Dispatcher  PostConstruct performed \n");
    }

    public void sayHi() {
        callCounter++;
        System.out.println("Dispatcher  sayHi performed. Counter = " + callCounter);
    }

    public String getMessage() {
        return String.format("max_producers %d max_consumers %d ", Integer.parseInt(max_producers), Integer.parseInt(max_consumers));
    }

    public Q getQ() {
        return q;
    }

//    public String getState() {
//        return producerManager.getState();
//    }

    public boolean addProducer() {
        return producerManager.incProd();
    }

    public boolean decProducer() {
        return producerManager.decProd();
    }

    public boolean processBtn(String btn) {
        boolean ret = false;
        switch (btn) {
            case "10":
                ret = addProducer();
                break;
            case "12":
                ret = decProducer();
                break;
            default:
                ret = false;
                break;
        }
        return ret;
    }

    public ArrayList<Map<String, String>> processReq() {
//        ArrayList<Map<String, String>> dispatcherState = new ArrayList<>();
//        producerManager.getProducers();
//        System.out.println("{\"Dispatcher_resp\":" + resp + "}");
        return producerManager.getProducers();
    }

    public ArrayList<ArrayList<Map<String, String>>> give_State() {
        ArrayList<ArrayList<Map<String, String>>> state = new ArrayList<>();
        state.add(producerManager.getProducers());
//        producerManager.getProducers();
//        System.out.println("{\"Dispatcher_resp\":" + resp + "}");
        return state;
    }

    public Map<String, ArrayList<Map<String, String>>> giveState() {
        Map<String, ArrayList<Map<String, String>>> mapa = new HashMap<>();

        Map<String, String> info = new HashMap<>();

        info.put("Producers", Integer.toString(producerManager.getNumberOfActiveProd()));
        info.put("Consumers", Integer.toString(consumerManager.getNumberOfActiveCons()));
        info.put("Q", "Not implemented yet");
        ArrayList<Map<String, String>> commonInfo = new ArrayList<>();
        commonInfo.add(info);


        ArrayList<Map<String, String>> outer = producerManager.getProducers();
        mapa.put("CommonInfo", commonInfo);
        mapa.put("Producers", outer);
        mapa.put("Consumers", outer);
        return mapa;
    }

}
