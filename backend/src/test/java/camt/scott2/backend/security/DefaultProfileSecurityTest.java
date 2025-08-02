package camt.scott2.backend.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Import(SecurityTestConfiguration.class)
class DefaultProfileSecurityTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldEnforceAuthenticationForProtectedEndpoints() {
        // GraphQL should require authentication
        webTestClient.post()
                .uri("/graphql")
                .bodyValue("{\"query\": \"{ hello }\"}")
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void shouldAllowAccessToPublicHealthEndpoint() {
        // Health endpoint should remain public
        webTestClient.get()
                .uri("/api/health")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.status").isEqualTo("UP");
    }

    @Test
    void shouldAllowAccessToAuthEndpoints() {
        // Auth endpoints should be public
        webTestClient.get()
                .uri("/api/auth/login")
                .exchange()
                .expectStatus().is4xxClientError(); // Method not allowed, not unauthorized
    }
}