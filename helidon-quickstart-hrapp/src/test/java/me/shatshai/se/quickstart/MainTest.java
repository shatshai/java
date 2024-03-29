
package me.shatshai.se.quickstart;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

import io.helidon.media.jackson.JacksonSupport;
import io.helidon.common.http.Http;
import io.helidon.webclient.WebClient;
import io.helidon.webclient.WebClientResponse;
import io.helidon.webserver.WebServer;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class MainTest {

    private static WebServer webServer;
    private static WebClient webClient;

    @BeforeAll
    static void startTheServer() {
        webServer = Main.startServer().await(Duration.ofSeconds(10));

        webClient = WebClient.builder()
                .baseUri("http://localhost:" + webServer.port())
                .addMediaSupport(JacksonSupport.create())
                .build();
    }

    @AfterAll
    static void stopServer() {
        if (webServer != null) {
            webServer.shutdown().await(10, TimeUnit.SECONDS);
        }
    }


    @Test
    void testMetrics() {
        WebClientResponse response = webClient.get()
                .path("/metrics")
                .request()
                .await(Duration.ofSeconds(5));
        assertThat(response.status().code(), is(200));
    }

    @Test
    void testHealth() {
        WebClientResponse response = webClient.get()
                .path("health")
                .request()
                .await(Duration.ofSeconds(5));
        assertThat(response.status().code(), is(200));
    }

    @Test
    void testEmployees() {
        Employee[] employees;
        WebClientResponse response;

        employees = webClient.get()
                .path("/employees")
                .request(Employee[].class)
                .await(Duration.ofSeconds(5));

        assertThat(employees.length, is(3));
        assertThat(employees[0].getId(), is(101));
        assertThat(employees[1].getName(), is("Bob"));
        assertThat(employees[2].getSalary(), is(1122.33));
    }

}
