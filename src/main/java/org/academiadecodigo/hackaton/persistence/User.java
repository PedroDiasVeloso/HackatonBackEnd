package org.academiadecodigo.hackaton.persistence;

import java.util.LinkedList;
import java.util.List;

public class User implements Member{


    private static Integer idCounter = 0;
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private Integer age;
    private String password;
    private Integer rank;
    private List<String> inBox = new LinkedList<>();

    public User(){
        this.id = idCounter;
        idCounter++;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getRank() {
        return rank;
    }

    public void addMessage(String string){
        inBox.add(string);
    }

    @Override
    public List<String> getInBox() {
        return inBox;
    }

    public static Integer getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(Integer idCounter) {
        User.idCounter = idCounter;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
