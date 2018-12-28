package com.kafka.producer.kafka.producer.resource;


import com.kafka.producer.kafka.producer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class UserResource {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "kafka_example";

    @GetMapping("/publish/{name}")
    public String post(@PathVariable("name") final String name) {

        kafkaTemplate.send(TOPIC, new User(name, "Technology", 12000));
        System.out.println("Published successfully");
        return "Published successfully";
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String postMessage(@RequestBody User user) {
        kafkaTemplate.send(TOPIC, user);
        System.out.println("Published Post Message for User: "+user.getName());
        return "Published Post Message";
    }
}