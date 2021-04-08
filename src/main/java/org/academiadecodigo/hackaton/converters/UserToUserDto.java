package org.academiadecodigo.hackaton.converters;

import org.academiadecodigo.hackaton.dto.UserDto;
import org.academiadecodigo.hackaton.persistence.Member;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDto {

    public UserDto convert(Member user){

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setAge(user.getAge());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        userDto.setRank(user.getRank());
        userDto.setInBox(user.getInBox());
        userDto.setAge(user.getAge());

        return userDto;


    }

}
