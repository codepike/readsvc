package org.readingplanets.svc.security;

import org.readingplanets.svc.mapper.UserMapper;
import org.readingplanets.svc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        if (!userMapper.existsByUsername(username)) {
            throw new UsernameNotFoundException("Username not found " + username);
        }

        User user = userMapper.getUserByUsername(username);
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(long id) throws UsernameNotFoundException{

        if (!userMapper.existsById(id)) {
            throw new UsernameNotFoundException("Id not found " + id);
        }

        User user = userMapper.getUserById(id);
        return UserPrincipal.create(user);
    }
}
