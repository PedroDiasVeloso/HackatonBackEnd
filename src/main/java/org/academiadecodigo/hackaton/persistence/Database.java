package org.academiadecodigo.hackaton.persistence;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class Database {


    private  Map<Integer, Member> members = new HashMap<>();
    private Map<String, String> passwords = new HashMap<>();



    public Member addMember(Member member){
        members.put(member.getId(),member);
        return member;
    }

    public void addToPasswordsMap(Member member){
        passwords.put(member.getFirstName(),member.getPassword());
    }

    public void removeMember(Member member){
        members.remove(member.getId());
    }

    public Member getMember(Integer id){
       return members.get(id);
    }

    public Collection<Member> getMembers(){


        return members.values();
    }

    public boolean validateLogin(String username, String password){

        for(Member member : members.values()){
            if(member.getUsername().equals(username) && member.getPassword().equals(password)){
                return true;
            }
        }

        return false;
    }

    public boolean checkUsername(String username){

        for(Member member : members.values()){
            if(member.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }


}
