package org.academiadecodigo.hackaton.converters;

import org.academiadecodigo.hackaton.dto.UserDto;
import org.academiadecodigo.hackaton.persistence.Member;
import org.academiadecodigo.hackaton.persistence.User;
import org.academiadecodigo.hackaton.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUser {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;

    }

    public Member convert(UserDto userDto){

        Member user = (userDto.getId() != null ? userService.getMember(userDto.getId()): new User());

        user.setAge(userDto.getAge());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRank(userDto.getRank());
        user.setPassword(userDto.getPassword());


        return user;

    }

}
