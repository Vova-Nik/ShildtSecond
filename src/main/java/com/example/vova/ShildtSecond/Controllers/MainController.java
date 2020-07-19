package com.example.vova.ShildtSecond.Controllers;

import com.example.vova.ShildtSecond.busines.Dispatcher;
//import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.ApplicationContext;

@Controller

public class MainController {
    String myName = "Vovchikkkk";

    @Autowired private ApplicationContext context;
    @Value("${vova.itest}")
    private String itest;
    @RequestMapping(value = "/")
    public String printHello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        model.addAttribute("title", "Sh2 Index");
//        model.addAttribute("myName", itest);
//        System.out.println("multiThreads core catched!!!");
        return "index";
    }

    @RequestMapping(value = "/multiThreads")
    public String multiThreads(ModelMap model) {
        System.out.println("multiThreads simply mapping");
        model.addAttribute("message", "multiThreads");
        model.addAttribute("title", "Sh2 multiThreads");

        return "multiThreads";
    }

    @CrossOrigin
    @RequestMapping(value = "/vue")
    public String vue(ModelMap model) {
        System.out.println("multiThreads simply mapping");
        return "vue";
    }

    @CrossOrigin
    @RequestMapping(value = "/multiThreads1", method = RequestMethod.POST)
    public ResponseEntity<String> doubleNumber(@RequestHeader("btnClicked") int btnClicked) {

        int reqCounter;
        System.out.println("multiThreads button mapping " + btnClicked);
        //HashMap<String, String> respBody = new HashMap<>();
//        HashMap<String, Object> respBody = new HashMap<>();
//        respBody.put("param1", "Hi");
//        respBody.put("param2", "There");
//        respBody.put("param3", 12);
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(respBody);

        Dispatcher dispatcher = context.getBean(Dispatcher.class);

        return ResponseEntity.ok()
                .header( "ctr", "foo")
                .body(dispatcher.toString());
    }
}



