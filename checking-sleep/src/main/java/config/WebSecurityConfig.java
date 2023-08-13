package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import ru.dream.checkingsleep.repository.UserRepository;
import ru.dream.checkingsleep.service.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurer {
    //WebSecurityConfigurerAdapter был в примере

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;


}
