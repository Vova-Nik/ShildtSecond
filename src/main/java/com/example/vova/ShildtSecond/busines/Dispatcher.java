package com.example.vova.ShildtSecond.busines;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

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

    public String getState() {
        return producerManager.getState();
    }

    public String processReq(int key) {
        String resp = "";
        switch (key) {

            case 10:
                resp = "{\"Producer_increased\" : " + producerManager.incProd() + "}";
                break;
            case 12:
                resp = "{\"Producer_decreased\" : " + producerManager.decProd() + '}';
                break;
            case 14:
                resp = producerManager.getState();
                break;

            default:
                resp = "{\"Unnknown_buttonn_request\" : " + key + '}';
                break;
        }
//        System.out.println("{\"Dispatcher_resp\":" + resp + "}");
        return resp;
    }
}
