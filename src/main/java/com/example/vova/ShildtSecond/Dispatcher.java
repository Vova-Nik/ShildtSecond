package com.example.vova.ShildtSecond;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton") // change to prototype
//@PropertySource("classpath:my.properties")
@PropertySource("application.properties")
public class Dispatcher {
    @Value("${dispatcher.max_producers}")
    private String max_producers;

    @Value("${dispatcher.max_consumers}")
    private String max_consumers;

    Dispatcher(){
       System.out.print("max_producers " + max_producers + " max_consumers " + max_consumers + " ");
    }



    public String getMessage() {
        return String.format("max_producers %d max_consumers %d ", Integer.parseInt(max_producers), Integer.parseInt(max_consumers));
    }
}
