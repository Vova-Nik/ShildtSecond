package com.example.vova.ShildtSecond.busines;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Q {
    int store;

    public void Q() {
        store = 0;
        System.out.println("Q created");
    }

    public void receiveProd(Producer prod) {
        store++;
    }

    public boolean giveOutProd() {
        if (store < 1)
            return false;
        store--;
        return true;
    }
}
