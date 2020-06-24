package com.example.vova.ShildtSecond.busines;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Q {
    int maxItemsInStore = 12;
    int store;

    public void Q() {
        store = 0;
        System.out.println("Q created");
    }

    public synchronized  void  putProd(Producer prod) {

        while(store >= maxItemsInStore)
        {
            try{
                wait(100);
            }catch(Exception e){
                System.out.println("ReceiveProd waiting exception");
            }
        }
        store++;
    }

    public boolean giveOutProd() {
        if (store < 1)
            return false;
        store--;
        return true;
    }


}
