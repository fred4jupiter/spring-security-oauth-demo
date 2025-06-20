package de.fred4jupiter.spring.security.oauth.client;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PingRestClientMT {

    private static final Logger LOG = LoggerFactory.getLogger(PingRestClientMT.class);

    @Autowired
    private PingRestClient pingRestClient;

    @Test
    void callPingViaRestClient() {
        PingResult result = pingRestClient.callPingOnServer();
        LOG.info("result: {}", result);
        assertThat(result).isNotNull();
        assertThat(result.responseText()).isNotBlank();
    }

}
