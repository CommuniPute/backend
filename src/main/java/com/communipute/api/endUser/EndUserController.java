package com.communipute.api.endUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    /**
     * This method is used to get all the users in the database.
     * TODO: Remove this method after testing
     * @return
     */
    @GetMapping
    public ResponseEntity<List<EndUser>> getEndUsers() {
        return ResponseEntity.ok(userService.getEndUsers());
    }

}
