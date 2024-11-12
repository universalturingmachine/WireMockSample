package org.example;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockSample {

    public static void main(String[] args) {
        // Start WireMock server and load JSON-based stubs from mappings folder
        WireMockServer wireMockServer = new WireMockServer(
                WireMockConfiguration.wireMockConfig()
                        .port(8080)
                        .withRootDirectory("src/main/resources")  // root directory where mappings folder exists
        );

        wireMockServer.start();

        // Define additional stubs programmatically
        stubFor(get(urlEqualTo("/api/dynamic"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\": \"This is a dynamically configured response\"}")));

        stubFor(get(urlEqualTo("/api/error"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\": \"Server error\"}")));

        stubFor(get(urlEqualTo("/api/shutdown"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("Server is shutting down")));

        Runtime.getRuntime().addShutdownHook(new Thread(wireMockServer::stop));

        // Additional application logic
        System.out.println("WireMock server started with mixed configurations!");

        // When done
        // wireMockServer.stop();
    }
}
