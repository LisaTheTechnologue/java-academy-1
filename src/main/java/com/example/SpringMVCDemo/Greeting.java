package com.example.SpringMVCDemo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Greeting implements Serializable {
    private Integer id;
    private String greetingMessage;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateGreeting;

    public Greeting() {
    }

    public Greeting(Integer id, String greetingMessage, Date dateGreeting) {
        this.id = id;
        this.greetingMessage = greetingMessage;
        this.dateGreeting = dateGreeting;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGreetingMessage() {
        return greetingMessage;
    }

    public void setGreetingMessage(String greetingMessage) {
        this.greetingMessage = greetingMessage;
    }

    public Date getDateGreeting() {
        return dateGreeting;
    }

    public void setDateGreeting(Date dateGreeting) {
        this.dateGreeting = dateGreeting;
    }
    public static ArrayList<Greeting> greetings = new ArrayList<>();
    public static int generateId() {
        return greetings.size() + 1;
    }
    public static ArrayList<Greeting> readGreetings(){
//        var lst = new ArrayList<Greeting>(3);
//        greetings.add(new Greeting(generateId(),"hello1",new Date()));
//        greetings.add(new Greeting(generateId(),"hello2",new Date()));
//        greetings.add(new Greeting(generateId(),"hello3",new Date()));
        return greetings;
    }

    // Create
    public static ArrayList<Greeting> createGreeting(Greeting greeting) {
        if (greeting.getId() < 0) {
            return greetings;
        }
        greetings.add(greeting);
        return greetings;
    }

    // Read by ID
    public static Greeting getGreetingById(int id) {
        for (Greeting greeting : greetings) {
            if (greeting.getId() == id) {
                return greeting;
            }
        }
        return null;
    }

    // Update
    public static boolean updateGreeting(int id,Greeting updated) {
        Greeting existed = getGreetingById(id);
        if (existed != null) {
            existed.setGreetingMessage(updated.getGreetingMessage());
            existed.setDateGreeting(updated.getDateGreeting());
            return true;
        }
        return false;
    }

    // Delete 
    public static boolean deleteGreeting(int id) {
        Greeting greeting = getGreetingById(id);
        if (greeting != null) {
            return greetings.remove(greeting);
        }
        return false;
    }
}
