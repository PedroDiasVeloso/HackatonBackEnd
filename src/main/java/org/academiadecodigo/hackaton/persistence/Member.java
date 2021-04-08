package org.academiadecodigo.hackaton.persistence;

import java.util.List;

public interface Member {

    Integer getId();
    void addMessage(String message);
    List<String> getInBox();
    String getFirstName();
    String getPassword();

    Integer getAge();
    void setAge(Integer age);
    String getUsername();
    void setUsername(String username);
    void setFirstName(String firstName);
    String getLastName();
    void setLastName(String lastName);
    Integer getRank();
    void setRank(Integer rank);
    void setPassword(String password);


}
