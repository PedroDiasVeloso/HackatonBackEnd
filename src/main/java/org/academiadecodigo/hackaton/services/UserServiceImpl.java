package org.academiadecodigo.hackaton.services;

import org.academiadecodigo.hackaton.persistence.Database;
import org.academiadecodigo.hackaton.persistence.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private Database database;


    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

    @Override
    public Member createMember(Member member) {
        database.addToPasswordsMap(member);
        return database.addMember(member);
    }

    @Override
    public void removeMember(Member member) {
        database.removeMember(member);
    }

    @Override
    public Member getMember(Integer id) {
        return database.getMember(id);
    }

    @Override
    public Member getMemberByUsername(String username) {
       Collection<Member> list = database.getMembers();

       for(Member member : list){
           if (member.getUsername().equals(username)){
               return member;
           }
       }
       return null;
    }
}
