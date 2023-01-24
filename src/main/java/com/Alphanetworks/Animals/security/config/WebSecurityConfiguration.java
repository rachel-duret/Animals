package com.Alphanetworks.Animals.security.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {


    private final UserSecurityService userSecurityService;

    @Autowired
    public WebSecurityConfiguration(UserSecurityService userSecurityService) {
        this.userSecurityService = userSecurityService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()// disable pour development
                .authorizeHttpRequests((requests) -> requests
//  Except login register pages , all rest pages have to be authenticated to visit the pages.
                        .requestMatchers("/login", "/register").permitAll()
                        .anyRequest()
                        .authenticated()
                )
//  Default application page will be login page, authentication success will redirect to animals page
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/animals")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error=true")
                        .permitAll()
                ).logout(
                        logout ->logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                )
                .headers().frameOptions().disable(); // to Show H2 database panel

        return httpSecurity.build();
    }


    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }




}

