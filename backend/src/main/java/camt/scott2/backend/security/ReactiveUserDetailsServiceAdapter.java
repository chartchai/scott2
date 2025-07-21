package camt.scott2.backend.security;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ReactiveUserDetailsServiceAdapter implements ReactiveUserDetailsService {
    
    private final UserDetailsService userDetailsService;
    
    public ReactiveUserDetailsServiceAdapter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.fromCallable(() -> userDetailsService.loadUserByUsername(username))
                .subscribeOn(Schedulers.boundedElastic())
                .cast(UserDetails.class);
    }
}