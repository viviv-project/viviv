package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.viviv.model.entity.RoleStatus;
import ru.project.viviv.model.entity.User;
import ru.project.viviv.model.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        try {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException(
                        "No user found with username: " + email);
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getEnabled(),
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    getAuthorities(user.getRoleConnections().stream().map(roleConnection -> roleConnection.getRole().getStatus()).collect(Collectors.toList())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<GrantedAuthority> getAuthorities(List<RoleStatus> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleStatus role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
        }
        return authorities;
    }
}