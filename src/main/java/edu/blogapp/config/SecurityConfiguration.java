package edu.blogapp.config;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.blogapp.service.impl.SecurityCustomUserDeatilsService;

@Configuration
public class SecurityConfiguration {

    @Autowired
    SecurityCustomUserDeatilsService customUserDeatilsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authorizeHttpRequests(authorize ->{
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        httpSecurity.formLogin(login->{
            login.loginPage("/login");
            login.loginProcessingUrl("/authenticate");
            login.successForwardUrl("/user/profile");
            login.usernameParameter("email");
            login.passwordParameter("password");

        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logout->{
            logout.logoutUrl("/do-logout");
            logout.logoutSuccessUrl("/login?logout=true");
        });

        return httpSecurity.build();



    } 

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(customUserDeatilsService);
        authenticationProvider.setPasswordEncoder(encoder());
        return authenticationProvider;
        
    }


    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
