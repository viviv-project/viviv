package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.viviv.model.entity.Profile;
import ru.project.viviv.model.entity.RoleStatus;
import ru.project.viviv.model.repository.ProfileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Profile profile = profileRepository.findByEmail(email);
        if (profile == null) {
            throw new UsernameNotFoundException(
                    "Пользователя с таким email не найдено: " + email);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return new org.springframework.security.core.userdetails.User
                (profile.getEmail(),
                        profile.getPassword(), enabled, accountNonExpired,
                        credentialsNonExpired, accountNonLocked,
                        getAuthorities(profile.getRoleConnections().stream().map(roleConnection -> roleConnection.getRole().getStatus()).collect(Collectors.toList())));
    }

    private static List<GrantedAuthority> getAuthorities(List<RoleStatus> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleStatus role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }
        return authorities;
    }
}