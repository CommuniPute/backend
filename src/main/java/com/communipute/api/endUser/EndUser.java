package com.communipute.api.endUser;

import com.communipute.api.computeResource.ComputeResource;
import com.communipute.api.token.Token;
import com.communipute.api.transaction.Transaction;
import com.communipute.api.utils.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// Renamed data model to EndUser as User is a reserved keyword in PostgreSQL
@Entity
public class EndUser implements UserDetails {

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
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonManagedReference
    @OneToMany(mappedBy = "requestingUser")
    private List<Transaction> transactions;

    @JsonManagedReference
    @OneToMany(mappedBy = "offeringUser")
    private List<ComputeResource> computeResources;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    public EndUser() {

    }

    public EndUser(String username, String first_name, String last_name, String password, String email) {
        this.username = username;
        this.firstName = first_name;
        this.lastName = last_name;
        this.password = password;
        this.email = email;
        this.role = Role.USER;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public void setUsername(String username) {
//        this.username = username;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<ComputeResource> getComputeResources() {
        return computeResources;
    }

    public void setComputeResources(List<ComputeResource> computeResources) {
        this.computeResources = computeResources;
    }

    // This method implicitly defines which field (username vs email) is used to produce the subject field
    // in the JWT token
    @Override
    public String getUsername() {return this.username;}

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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
