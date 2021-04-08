package org.academiadecodigo.hackaton.services;

import org.springframework.stereotype.Service;

@Service
public class FormServiceImpl {


    public Integer calculateRank(Integer one, Integer two, Integer three, Integer four){

        return one + two + three + four;
    }
}
