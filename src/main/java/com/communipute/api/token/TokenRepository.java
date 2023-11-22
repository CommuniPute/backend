package com.communipute.api.token;

import com.communipute.api.endUser.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("SELECT t FROM Token t inner join EndUser u on t.user.id = u.id " +
            "where u.id = :id and (t.expired = false and t.revoked = false)")
    List<Token> findAllValidTokensByUser(Integer id);

    Optional<Token> findByToken(String token);

}
