package com.example.vova.ShildtSecond.busines;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("singleton")
public class Q {
    int maxItemsInStore = 12;
    int store;
    int recieved = 0;
    int emmited = 0;

    public void Q() {
        store = 0;
        System.out.println("Q created");
    }

    public synchronized  void  putProd() {
        while(store >= maxItemsInStore)
        {
            try{
                wait(100);
            }catch(Exception e){
                System.out.println("ReceiveProd waiting exception");
            }
        }
        store++;
        recieved++;
       // System.out.println("Q Received Prod recieved: " + recieved);
       // store--;
    }

    public boolean giveOutProd() {
//         System.out.println("Q gave Prod : " + emmited);
        if (store < 1)
            return false;
        store--;
        emmited ++;
        return true;
    }

    public Map<String, String> getState(){
        Map<String, String> me = new HashMap<>();
        me.put("class", "Q");
        me.put("inWork", Boolean.toString(true));
        me.put("store", Integer.toString(store));
        me.put("recieved", Integer.toString(recieved));
        me.put("emmited", Integer.toString(emmited));
        return me;
    }

}
