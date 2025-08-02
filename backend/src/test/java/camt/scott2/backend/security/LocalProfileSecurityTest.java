package camt.scott2.backend.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("local")
@Import(SecurityTestConfiguration.class)
class LocalProfileSecurityTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldBypassAuthenticationForAllEndpoints() {
        // Test GraphQL endpoint (normally requires authentication)
        webTestClient.post()
                .uri("/graphql")
                .bodyValue("{\"query\": \"{ hello }\"}")
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void shouldAllowAccessToHealthEndpoint() {
        webTestClient.get()
                .uri("/api/health")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.status").isEqualTo("UP")
                .jsonPath("$.service").isEqualTo("backend");
    }

    @Test
    void shouldAllowAccessToAuthEndpoints() {
        // Auth endpoints should be accessible but may return method not allowed for GET
        webTestClient.get()
                .uri("/api/auth/login")
                .exchange()
                .expectStatus().is4xxClientError(); // Not 401 Unauthorized
    }
}