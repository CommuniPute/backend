package com.example.communipute.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    /**
     * This method returns a list of users. For now, it's just dummy data.
     * @return
     */
    public List<User> getUsers() {
        return List.of(
            new User(1L, "username", "password", "email@gmail.com"),
            new User(2L, "username2", "password2", "email2@gmail.com"),
            new User(3L, "username3", "password3", "email3@gmail.com")
            );
    }

}
