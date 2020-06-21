package com.example.vova.ShildtSecond.Controllers;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.HashMap;

@Controller

public class MainController {
    String myName = "Vovchik";

    @RequestMapping(value = "/")

    public String printHello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        model.addAttribute("title", "Sh2 Index");
        System.out.println("multiThreads core catched!!!");
        return "index";
    }

    @RequestMapping(value = "/multiThreads")
    public String multiThreads(ModelMap model) {

        System.out.println("multiThreads simply mapping");
        model.addAttribute("message", "multiThreads");
        model.addAttribute("title", "Sh2 multiThreads");

        return "multiThreads";
    }


    @RequestMapping(value = "/multiThreads1", method = RequestMethod.POST)
    public ResponseEntity<String> doubleNumber(@RequestHeader("btnClicked") int btnClicked) {

        int reqCounter;

        System.out.println("multiThreads button mapping " + btnClicked);

//        @Autowired
//                Dispatcher.getMessage();


        //HashMap<String, String> respBody = new HashMap<>();
        HashMap<String, Object> respBody = new HashMap<>();
        respBody.put("param1", "Hi");
        respBody.put("param2", "There");
        respBody.put("param3", 12);

        Gson gson = new Gson();
        String jsonString = gson.toJson(respBody);


        return ResponseEntity.ok()
                .header("ctr", "foo")
                .body(jsonString);
    }
}



