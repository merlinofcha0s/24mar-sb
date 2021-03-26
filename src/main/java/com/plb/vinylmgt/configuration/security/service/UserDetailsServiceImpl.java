package com.plb.vinylmgt.configuration.security.service;

import com.plb.vinylmgt.model.User;
import com.plb.vinylmgt.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findOneByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        // ROLE_ADMIN,ROLE_USER
        List<SimpleGrantedAuthority> authorities = Arrays
                .stream(user.get().getAuthorities().split(","))
                .map(auth -> new SimpleGrantedAuthority("ROLE_" + auth.toUpperCase()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),
                authorities);
    }
}
