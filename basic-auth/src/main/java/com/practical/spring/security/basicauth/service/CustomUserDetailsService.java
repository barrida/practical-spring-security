package com.practical.spring.security.basicauth.service;

import com.practical.spring.security.basicauth.entity.User;
import com.practical.spring.security.basicauth.model.CustomUserDetails;
import com.practical.spring.security.basicauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

/**
 * @author Suleyman Yildirim
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Supplier<UsernameNotFoundException> error = () -> new UsernameNotFoundException("Problem with authentication");
        User user = userRepository.findByUsername(username)
                .orElseThrow(error);

        return new CustomUserDetails(user);
    }
}
