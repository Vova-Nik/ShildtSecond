package com.example.vova.ShildtSecond.Controllers;

import com.example.vova.ShildtSecond.busines.Dispatcher;
import com.example.vova.ShildtSecond.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("message")
public class RestReqController {
    private int getCounter = 0;
    private int putCounter = 0;

    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {
        {
            add(new HashMap<String, String>() {{
                put("id", "1");
                put("text", "First message");
                put("vvv", "my message");
            }});
            add(new HashMap<String, String>() {{
                put("id", "2");
                put("text", "Second message");
            }});
            add(new HashMap<String, String>() {{
                put("id", "3");
                put("text", "Third message");
            }});
        }
    };
    @Autowired
    Dispatcher dispatcher; // = context.getBean(Dispatcher.class);

    @CrossOrigin
    @GetMapping
    public Map<String, ArrayList<Map<String, String>>> getRequest() {
        Map<String, ArrayList<Map<String, String>>> ans;
        ans = dispatcher.giveState();
//        System.out.println("Get request! ");
        return ans;
    }

//    public  ArrayList<ArrayList<Map<String, String>>>getRequest(){
//        ArrayList<ArrayList<Map<String, String>>> ans = new ArrayList<ArrayList<Map<String, String>>>();
//        ans = dispatcher.giveState();
//        return ans;
//    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getMessage(id);
    }

    private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @CrossOrigin
    @PostMapping
    public String buttons(@RequestBody Map<String, String> message) {
        putCounter++;
        if (!message.containsKey("elementName") && !message.containsKey("elementNumber")){
            System.out.println("Put request! Bad request data");
            return ("{\"processed\":false}");
        }

//        System.out.println(dispatcher.processBtn(message.get("btnId")));
//        System.out.println(dispatcher.processBtn(message.get("btnId")));
        boolean res = dispatcher.processBtn(message);
        System.out.println("POST req message. ElementName:" + message.get("elementName") + " message.elementNumber:" + message.get("elementNumber"));
        if (res)
            return ("{\"processed\":true}");
        else
            return ("{\"processed\":false}");
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> messageFromDb = getMessage(id);
        messageFromDb.putAll(message);
        messageFromDb.put("id", id);
        return messageFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> message = getMessage(id);

        messages.remove(message);
    }

//    @Endpoint
//    @WebEndpoint
//    //@EndpointWebExtension
//    public void shutDovn(){
//        System.out.println("Shutdown !!!!!!!!");
//    }


}
