package com.example.incident_management_system.config;

import com.example.incident_management_system.entities.UserEntity;
import com.example.incident_management_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    /*@Autowired
    private UserRepository userRepository;*/

    @Bean
    public UserDetailsService userDetailsService(){
        /*List<UserEntity> userEntityList = userRepository.findAll();
        List<UserDetails> userDetailsList = new ArrayList<>();*/
        /*userEntityList.forEach(data->{
            UserDetails userDetails = User.builder().username(data.getName()).password(passwordEncoder().encode(data.getPassword())).roles("Admin").build();
            userDetailsList.add(userDetails);
        });
        return new InMemoryUserDetailsManager(userDetailsList);*/

       UserDetails userDetails = User.builder().username("ravita").password(passwordEncoder().encode("abc")).roles("Admin").build();
       return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
