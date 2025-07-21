package camt.scott2.backend.controller;

import camt.scott2.backend.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private ReactiveAuthenticationManager authenticationManager;

    @Autowired
    private ReactiveUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Mono<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()))
                .onErrorMap(BadCredentialsException.class, ex -> new RuntimeException("Invalid credentials"))
                .then(userDetailsService.findByUsername(loginRequest.getUsername()))
                .map(userDetails -> {
                    String jwt = jwtUtil.generateToken(userDetails);
                    Map<String, String> response = new HashMap<>();
                    response.put("token", jwt);
                    response.put("username", userDetails.getUsername());
                    return response;
                });
    }

    @PostMapping("/register")
    public Mono<Map<String, String>> register(@RequestBody RegisterRequest registerRequest) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registration functionality to be implemented");
        response.put("username", registerRequest.getUsername());
        
        return Mono.just(response);
    }

    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class RegisterRequest {
        private String username;
        private String password;
        private String email;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}