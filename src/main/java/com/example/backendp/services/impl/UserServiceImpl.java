package com.example.backendp.services.impl;

import com.example.backendp.repository.UserRepo;
import com.example.backendp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {

    private final UserRepo userRepo;
    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
           public UserDetails loadUserByUsername (String username){
                return userRepo.findByEmail(username)
                        .orElseThrow(()-> new UsernameNotFoundException("user not found.."));
            }

        };
    }
}
