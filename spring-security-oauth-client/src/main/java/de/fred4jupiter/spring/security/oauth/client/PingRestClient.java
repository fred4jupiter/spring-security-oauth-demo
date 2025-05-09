package de.fred4jupiter.spring.security.oauth.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PingRestClient {

    private static final Logger LOG = LoggerFactory.getLogger(PingRestClient.class);

    private final RestClient restClient;

    public PingRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public String callPingOnServer() {
        ResponseEntity<String> responseEntity = restClient.get().uri("http://localhost:8280/ping").retrieve().toEntity(String.class);

        final HttpStatusCode statusCode = responseEntity.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            LOG.info("Ping returned status code: {}", statusCode);
            return responseEntity.getBody();
        }
        return null;
    }
}
