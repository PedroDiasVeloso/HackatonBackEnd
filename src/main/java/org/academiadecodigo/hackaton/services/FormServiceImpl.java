package org.academiadecodigo.hackaton.services;

import org.springframework.stereotype.Service;

@Service
public class FormServiceImpl {


    public Integer calculateRank(String one, String two, String three, String four, String five, String six,
                                 String seven, String eight){

        return Integer.parseInt(one) + Integer.parseInt(two) + Integer.parseInt(three) + Integer.parseInt(four)
                + Integer.parseInt(four) + Integer.parseInt(five) + Integer.parseInt(six) + Integer.parseInt(seven)
                + Integer.parseInt(eight);

    }
}
