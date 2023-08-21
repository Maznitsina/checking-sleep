package ru.dream.checkingsleep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.dream.checkingsleep.model.User;
import ru.dream.checkingsleep.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

 /*   @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userRepository
                .findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found With Email: " + mail));
        return UserDetailImpl.build(user);
    }*/
}
