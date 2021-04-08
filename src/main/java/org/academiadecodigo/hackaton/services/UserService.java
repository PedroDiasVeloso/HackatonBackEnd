package org.academiadecodigo.hackaton.services;

import org.academiadecodigo.hackaton.persistence.Member;

public interface UserService {


    Member createMember(Member member);
    void removeMember(Member member);
    Member getMember(Integer id);
    Member getMemberByUsername(String username);



}
