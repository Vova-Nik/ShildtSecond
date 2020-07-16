package com.example.vova.ShildtSecond.busines;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

    @Autowired
    Dispatcher(ProducerManager pm, ConsumerManager cm, Q qq) {
        producerManager = pm;
        consumerManager = cm;
        q = qq;
        System.out.print("Dispatcher constructor performed \n");
    }

    @PostConstruct
    void PostConstruct() {
        System.out.print("Dispatcher  PostConstruct performed \n");
    }

    public Q getQ() {
        return q;
    }

    public boolean processBtn(Map<String, String> message) {
        boolean ret = false;
        String elName = message.get("elementName");
        System.out.println("Dispatcher.processBtn elementName:" + elName);
        switch (elName) {
            case "Producer":
                int pnum = Integer.parseInt(message.get("elementNumber"));
                ret = producerManager.switchProducer(pnum);
                break;
            case "consumerInformer":
                ret = false;
                break;
            case "qInformer":
                ret = true;
                break;
            default:
                ret = false;
                break;
        }
        return ret;
    }

//    public ArrayList<Map<String, String>> processReq() {
//        ArrayList<Map<String, String>> dispatcherState = new ArrayList<>();
//        producerManager.getProducers();
//        System.out.println("{\"Dispatcher_resp\":" + resp + "}");
//        return producerManager.getProducers();
//    }

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
        info.put("Q", "1");
        ArrayList<Map<String, String>> commonInfo = new ArrayList<>();
        commonInfo.add(info);

        ArrayList<Map<String, String>> outer = producerManager.getProducers();
        mapa.put("CommonInfo", commonInfo);
        mapa.put("Producers", outer);
        mapa.put("Consumers", outer);

        ArrayList<Map<String, String>> qInfo = new ArrayList<>();
        qInfo.add(q.getState());
        mapa.put("Q", qInfo);

        return mapa;
    }
}
