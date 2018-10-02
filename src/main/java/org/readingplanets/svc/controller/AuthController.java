package org.readingplanets.svc.controller;

import io.swagger.annotations.ApiOperation;
import org.readingplanets.svc.mapper.UserMapper;
import org.readingplanets.svc.model.*;
import org.readingplanets.svc.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://www.readingplanets.org","http://localhost:3000"}, maxAge = 3600)
public class AuthController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Sign up")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        if(userMapper.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if(userMapper.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }

        User user = new User(0,
                signUpRequest.getUsername(),
                signUpRequest.getPassword(),
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                signUpRequest.getRole());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userMapper.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/user/{username}")
                .buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        String usernameOrEmail = loginRequest.getUsernameOrEmail();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @ApiOperation(value = "Get a user by user name")
    @RequestMapping("/user/{username}")
    public User getUserByName(@PathVariable String username) {
        return userMapper.getUserByUsername(username);
    }

    @ApiOperation(value = "Get all users")
    @RequestMapping("/user")
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

}
