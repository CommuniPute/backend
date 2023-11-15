package com.communipute.api.endUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "user")
public class EndUserController {

    private final EndUserService userService;

    @Autowired
    public EndUserController(EndUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<EndUser> getEndUsers() {
        return userService.getEndUsers();
    }

    @PostMapping
    public void registerNewEndUser(@RequestBody EndUser endUser) {
        userService.addNewEndUser(endUser);
    }

}
