package com.communipute.api.endUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EndUserService {

    private final EndUserRepository endUserRepository;

    @Autowired
    public EndUserService(EndUserRepository endUserRepository) {
        this.endUserRepository = endUserRepository;
    }

    /**
     * This method returns a list of users
     *
     * @return
     */
    public List<EndUser> getEndUsers() {
        return endUserRepository.findAll();
//        return List.of(
//            new EndUser(1, "username", "password", "email@gmail.com"),
//            new EndUser(2, "username2", "password2", "email2@gmail.com"),
//            new EndUser(3, "username3", "password3", "email3@gmail.com")
//            );
    }

    public void addNewEndUser(EndUser endUser) {
        Optional<EndUser> endUserOptional = endUserRepository
                .findEndUserByEmail(endUser.getEmail());
        if (endUserOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        endUserRepository.save(endUser);
    }

}
