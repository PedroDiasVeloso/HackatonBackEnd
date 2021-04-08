package org.academiadecodigo.hackaton.services;

import org.academiadecodigo.hackaton.persistence.Member;

import java.util.List;

public interface MessagingService {

    void sendMessage(Member member, String message);
    List<String> listMessages(Member member);



}
