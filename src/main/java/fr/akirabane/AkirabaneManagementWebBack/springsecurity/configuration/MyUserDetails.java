package fr.akirabane.AkirabaneManagementWebBack.springsecurity.configuration;

import fr.akirabane.AkirabaneManagementWebBack.compute.entity.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private PlayerEntity user;
    public MyUserDetails(PlayerEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(user.getStaff() == true){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return  user.getPassword_player();
    }

    @Override
    public String getUsername() {
        return user.getPseudo_player();
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
