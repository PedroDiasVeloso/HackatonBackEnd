package org.academiadecodigo.hackaton.services;

import org.academiadecodigo.hackaton.persistence.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagingServerImpl implements MessagingService{

    @Override
    public void sendMessage(Member member, String message) {
        member.addMessage(message);
    }

    @Override
    public List<String> listMessages(Member member) {
       return member.getInBox();
    }
}
