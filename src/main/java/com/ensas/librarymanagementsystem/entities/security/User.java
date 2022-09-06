package com.ensas.librarymanagementsystem.entities.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Data
@NoArgsConstructor
@Table(name = "APP_USER")
public class User extends BaseEntity implements UserDetails {

    private  String firstName;
    private  String lastName;
    private  String username;
    private  String password;
    private  String email;
    private  String phone;
    private  String address;

    @ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = {@JoinColumn(name = "USER_ID",referencedColumnName = "ID")},inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID",referencedColumnName = "ID")
    })
    private Set<Role> roles ;

    public Set<GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> role.getAuthorities())
                .flatMap(authorities -> authorities.stream())
                .map(authority->{
                    return  new SimpleGrantedAuthority(authority.getPermission());
                })
                .collect(Collectors.toSet());
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