package com.ttn.goals.service;

import com.ttn.goals.dao.UserRepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@Transactional
public class CustomUserServiceDetail  implements UserDetailsService {

    @Autowired
    UserRepositoryService userRepositoryService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.ttn.goals.model.User> user = userRepositoryService.findByUserIdAndActive(username,true);
        if (Objects.isNull(user.get()))
            throw new UsernameNotFoundException("Username is invalid: " + username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>() {{
           add(new SimpleGrantedAuthority("ROLE_USER"));
        }};
        return new org.springframework.security.core.userdetails.User(user.get().getUserId(), user.get().getPassword(), grantedAuthorities);
    }
}
