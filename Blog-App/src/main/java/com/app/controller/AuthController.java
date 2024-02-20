package com.app.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.JwtRequest;
import com.app.entity.JwtResponse;
import com.app.entity.User;
import com.app.exceptions.ApiException;
import com.app.payload.UserDto;
import com.app.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin("*")
@Tag(name = "Auth Controller", description = "Authentication Management API's")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private com.app.security.JwtHelper helper;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ModelMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);
      
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .user(this.mapper.map((User)userDetails, UserDto.class)).build();
        logger.info(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new ApiException(" Invalid Username or Password  !!");
        }
    }
    
    //register new user
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto){
    	
    	UserDto registereUser = userService.registereUser(userDto);
    	
    	return new ResponseEntity<UserDto>(registereUser,HttpStatus.CREATED);
    	
    }
}
