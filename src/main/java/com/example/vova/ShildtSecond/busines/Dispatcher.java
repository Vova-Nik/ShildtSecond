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
    private int callCounter = 0;
    Q q;

    ProducerManager producerManager;
    @Autowired
    private ApplicationContext context;

   // @Value("${dispatcher.max_consumers}")
    private String max_consumers;

    Dispatcher(){
        System.out.print("Dispatcher constructor performed \n");
    }

    @PostConstruct
    void init(){

       // producerManager = context.getBean(ProducerManager.class);
        q = context.getBean(Q.class);
       // createProducerManager();
        System.out.print("Dispatcher  PostConstruct performed \n");
    }

    public void sayHi(){
        callCounter ++;
        System.out.println("Dispatcher  sayHi performed. Counter = " + callCounter);
    }


    public String getMessage() {
        return String.format("max_producers %d max_consumers %d ", Integer.parseInt(max_producers), Integer.parseInt(max_consumers));
    }

    public Q getQ() {
        return q;
    }
}
