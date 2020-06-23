package com.example.vova.ShildtSecond.busines;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;


@Component
@Scope("prototype") // change to prototype
public class Producer {
    public Producer producer() {
        return new Producer();
    }
}
