package com.communipute.api.endUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EndUserRepository extends JpaRepository<EndUser, Integer> {
    @Query("SELECT s FROM EndUser s WHERE s.email = ?1")
    Optional<EndUser> findEndUserByEmail(String email);
}
