package com.communipute.api.endUser;

import com.communipute.api.computeResource.ComputeResource;
import com.communipute.api.transaction.Transaction;
import jakarta.persistence.*;

import java.util.List;

// Renamed data model to EndUser as User is a reserved keyword in PostgreSQL
@Entity
public class EndUser {

    @Id
    @SequenceGenerator(
            name = "end_user_id_sequence",
            sequenceName = "end_user_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "end_user_id_sequence"
    )
    private Integer id;
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "requestingUser")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "offeringUser")
    private List<ComputeResource> computeResources;


    public EndUser() {

    }

    public EndUser(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public EndUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
