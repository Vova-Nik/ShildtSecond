package com.example.vova.ShildtSecond.busines;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
@Scope("singleton") // change to prototype

//@PropertySource("classpath:my.properties")

public class Dispatcher {
  //  @Value("${dispatcher.max_producers}")
    private String max_producers;
    private int callCounter = 0;

   // @Value("${dispatcher.max_consumers}")
    private String max_consumers;

    Dispatcher(){
       System.out.print("Dispatcher constructor performed \n");
    }
    @PostConstruct
    void init(){
        System.out.print("Dispatcher  PostConstruct performed \n");
    }
//    @Autowired
    public void sayHi(){
        callCounter ++;
        System.out.println("Dispatcher  sayHi performed. Counter = " + callCounter);
    }


    public String getMessage() {
        return String.format("max_producers %d max_consumers %d ", Integer.parseInt(max_producers), Integer.parseInt(max_consumers));
    }
}
