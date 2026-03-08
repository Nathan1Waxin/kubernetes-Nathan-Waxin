package com.example.myservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.*;

@RestController
public class MyWebService {
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String hello(){
        Message msg = new Message("World !");
        entityManager.persist(msg);
        
        return " World ! (saved in MySQL with ID: " + msg.getId() + ")";
    }
    
    @GetMapping("/messages")
    public String getMessages() {
        long count = (Long) entityManager.createQuery("SELECT COUNT(m) FROM Message m").getSingleResult();
        return "Total messages in database: " + count;
    }
}