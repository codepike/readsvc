package org.readingplanets.svc.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://www.readingplanets.org", maxAge = 3600)
public class UserController {

    @RequestMapping("/user")
    public String home() {
        return "Home run";
    }
}
