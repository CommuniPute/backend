package com.communipute.api.endUser;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "user")
public class EndUserController {

    private final EndUserService userService;

    @Autowired
    public EndUserController(EndUserService userService) {
        this.userService = userService;
    }

}
