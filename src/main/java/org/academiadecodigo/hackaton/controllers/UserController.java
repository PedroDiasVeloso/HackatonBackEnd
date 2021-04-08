package org.academiadecodigo.hackaton.controllers;

import org.academiadecodigo.hackaton.converters.UserDtoToUser;
import org.academiadecodigo.hackaton.converters.UserToUserDto;
import org.academiadecodigo.hackaton.dto.LoginDto;
import org.academiadecodigo.hackaton.dto.MessageDto;
import org.academiadecodigo.hackaton.dto.UserDto;
import org.academiadecodigo.hackaton.persistence.Database;
import org.academiadecodigo.hackaton.persistence.FormResponse;
import org.academiadecodigo.hackaton.persistence.Member;
import org.academiadecodigo.hackaton.services.FormServiceImpl;
import org.academiadecodigo.hackaton.services.MessagingServerImpl;
import org.academiadecodigo.hackaton.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

    private MessagingServerImpl messagingServer;
    private UserServiceImpl userService;
    private FormServiceImpl formService;
    private UserDtoToUser userDtoToUser;
    private UserToUserDto userToUserDto;
    private Database database;


    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMessagingServer(MessagingServerImpl messagingServer) {
        this.messagingServer = messagingServer;
    }

    @Autowired
    public void setUserDtoToUser(UserDtoToUser userDtoToUser) {
        this.userDtoToUser = userDtoToUser;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

    @Autowired
    public void setUserToUserDto(UserToUserDto userToUserDto) {
        this.userToUserDto = userToUserDto;
    }

    @Autowired
    public void setFormService(FormServiceImpl formService) {
        this.formService = formService;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/{id}", })
    public ResponseEntity<UserDto> showUser(@PathVariable Integer id) {

        Member user = userService.getMember(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDto userDto = userToUserDto.convert(user);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/login", })
    public ResponseEntity<Member> validateUser(@RequestBody LoginDto loginDto) {

        if(!database.validateLogin(loginDto.getUsername(), loginDto.getPassword())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Member member = userService.getMemberByUsername(loginDto.getUsername());

        return new ResponseEntity<>(member,HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public ResponseEntity<?> addCustomer(@Valid @RequestBody UserDto userDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || userDto.getId() != null || !database.checkUsername(userDto.getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Member savedUser = userService.createMember(userDtoToUser.convert(userDto));

        // get help from the framework building the path for the newly created resource
        UriComponents uriComponents = uriComponentsBuilder.path("/api/" + savedUser.getId()).build();

        // set headers with the created path
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/message", })
    public ResponseEntity<?> sendMessage(@Valid @RequestBody MessageDto messageDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Member member = userService.getMemberByUsername(messageDto.getUsername());

        messagingServer.sendMessage(member,messageDto.getMessage());

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/delete/{id}", })
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {

        if(userService.getMember(id) == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        userService.removeMember(userService.getMember(id));

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, path = {"/users", })
    public ResponseEntity<Collection<Member>> listUsers() {

        Collection<Member> list = database.getMembers();

        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, path = {"/formResponse/{id}"})
    public ResponseEntity<Integer> getAnswers(@RequestBody FormResponse formResponse, @PathVariable Integer id){

        Integer result = formService.calculateRank(formResponse.getFirstQuestion(),formResponse.getSecondQuestion(),
                formResponse.getThirdQuestion(),formResponse.getFourthQuestion());

        userService.getMember(id).setRank(result);


        return new ResponseEntity<>(id, HttpStatus.OK);
    }



}
