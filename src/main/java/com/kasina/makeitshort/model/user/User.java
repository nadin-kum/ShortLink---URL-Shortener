package com.kasina.makeitshort.model.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kasina.makeitshort.model.MakeItShort;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    private String id;
    @Column(unique = true)
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @DBRef
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
    /*@DBRef
    private List<MakeItShort> urls = new ArrayList<>();*/


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*return roles;*/
        /*return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());*/
        return this.roles;
    }

    @Override
    public String getUsername() {
        return email;
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
}
