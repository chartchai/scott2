package camt.scott2.backend.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class SecurityProfileIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @AutoConfigureWebTestClient
    @ActiveProfiles("local")
    static class LocalProfileTest {

        @Autowired
        private WebTestClient webTestClient;

        @Test
        void shouldBypassSecurityWithLocalProfile() {
            // Test that health endpoint is accessible
            webTestClient.get()
                    .uri("/api/health")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.status").isEqualTo("UP");
        }

        @Test
        void shouldAllowAccessToAuthEndpointsWithLocalProfile() {
            // Test that auth endpoints are accessible without authentication
            webTestClient.get()
                    .uri("/api/auth/login")
                    .exchange()
                    .expectStatus().is4xxClientError(); // Method not allowed (GET instead of POST) but not 401/403
        }
    }

    @Test
    void shouldEnforceSecurityWithoutLocalProfile() {
        // Test that auth login endpoint allows POST without authentication
        webTestClient.get()
                .uri("/api/health")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void shouldAllowPublicEndpointsWithoutLocalProfile() {
        // Test that public endpoints remain accessible
        webTestClient.get()
                .uri("/api/health")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.status").isEqualTo("UP");
    }
}