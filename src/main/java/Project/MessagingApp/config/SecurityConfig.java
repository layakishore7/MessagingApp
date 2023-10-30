package Project.MessagingApp.config;

import Project.MessagingApp.filter.JwtAuthFilter;
import Project.MessagingApp.serviceClass.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withUsername("Laya")
//                .password(passwordEncoder().encode("Laya123"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder().encode("user123"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
        return new UserService();
    }



    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf ->csrf.disable())
                .authorizeRequests()
                .requestMatchers("user/authenticate").permitAll()
                .requestMatchers("user/**","message/**","conversation/**","conversation-member/**,/**").permitAll()
                .and()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
