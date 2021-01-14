package br.com.invillia.lyon.userevents.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Fail.fail;

@SpringBootTest
@ActiveProfiles("test")
public class WiremockTest {

    @Autowired
    private SwapiService swapiService;

    // criando uma instancia do wiremock
    private static WireMockServer wireMockServer = new WireMockServer(8090);

    private CloseableHttpClient httpClient = HttpClients.createDefault();

    @BeforeAll
    public static void setup() {
         wireMockServer.start();
         configureFor("localhost", 8090);
         SwapiService.BASE_URL = "http://localhost:8090/api/people/";
    }

    @Test
    public void shouldHandlePersonNotFoundAPIStatus() {
        stubFor(get(urlEqualTo("/api/people/-2/"))
                .willReturn(aResponse()
                        .withStatus(404)));
        try {
            swapiService.findPerson("-2");
        } catch(Exception e) {
            fail("Should have handled 404 exception");
        }
    }
}
