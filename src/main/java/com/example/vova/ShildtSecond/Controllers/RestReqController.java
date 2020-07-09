package com.example.vova.ShildtSecond.Controllers;

import com.example.vova.ShildtSecond.busines.Dispatcher;
import com.example.vova.ShildtSecond.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First message"); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second message"); }});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Third message"); }});
    }};
    @Autowired
    Dispatcher dispatcher; // = context.getBean(Dispatcher.class);

    @CrossOrigin
    @GetMapping
   // public List<Map<String, String>> list() {
    public String list() {

        System.out.println("Get request! " + getCounter);
        System.out.println(dispatcher.getState());
        getCounter++;
        return "messages";
    }

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
        putCounter ++;
        if(!message.containsKey("btnId")) {
            System.out.println("Put request! Bad request data" );
            return ("{\"processed\":false}");
        }

        System.out.println(dispatcher.processReq(Integer.parseInt(message.get("btnId"))));
        return dispatcher.processReq(Integer.parseInt(message.get("btnId")));
//        return ("{\"processed\":true}");
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
}
