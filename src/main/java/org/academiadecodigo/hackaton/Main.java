package org.academiadecodigo.hackaton;
import org.academiadecodigo.hackaton.persistence.Database;
import org.academiadecodigo.hackaton.persistence.User;


import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        User user = new User();
        User user1 = new User();

        Database database = new Database();

        Map<String, String> testMap = new HashMap<>();

        testMap.put("Veloso", "A3ooooo@");

        System.out.println(testMap.get("Veloso"));
        System.out.println(testMap.containsKey("Veloso"));

        System.out.println(user.getId());
        System.out.println(user1.getId());
    }


}
