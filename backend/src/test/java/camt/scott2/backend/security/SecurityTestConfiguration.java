package camt.scott2.backend.security;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

@TestConfiguration
public class SecurityTestConfiguration {

    @Bean
    @Primary
    public ReactiveUserDetailsService testUserDetailsService() {
        return username -> {
            if ("testuser".equals(username)) {
                UserDetails user = User.builder()
                        .username("testuser")
                        .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW") // "password"
                        .roles("USER")
                        .build();
                return Mono.just(user);
            }
            return Mono.empty();
        };
    }
}