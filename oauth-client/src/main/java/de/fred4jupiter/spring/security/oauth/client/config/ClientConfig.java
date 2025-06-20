package de.fred4jupiter.spring.security.oauth.client.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizationFailureHandler;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver;
import org.springframework.security.oauth2.client.web.client.SecurityContextHolderPrincipalResolver;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {

    @Bean
    public RestClient restClient(OAuth2AuthorizedClientManager authorizedClientManager,
                                 OAuth2AuthorizedClientRepository authorizedClientRepository,
                                 OAuth2ClientHttpRequestInterceptor.ClientRegistrationIdResolver clientRegistrationIdResolver,
                                 OAuth2ClientHttpRequestInterceptor.PrincipalResolver principalResolver,
                                 RestClient.Builder builder) {
        OAuth2ClientHttpRequestInterceptor requestInterceptor = new OAuth2ClientHttpRequestInterceptor(authorizedClientManager);
        requestInterceptor.setClientRegistrationIdResolver(clientRegistrationIdResolver);
        requestInterceptor.setPrincipalResolver(principalResolver);

        OAuth2AuthorizationFailureHandler authorizationFailureHandler = OAuth2ClientHttpRequestInterceptor
                .authorizationFailureHandler(authorizedClientRepository);
        requestInterceptor.setAuthorizationFailureHandler(authorizationFailureHandler);

        return builder
                .requestInterceptor(requestInterceptor)
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2ClientHttpRequestInterceptor.ClientRegistrationIdResolver clientRegistrationIdResolver() {
        return new RequestAttributeClientRegistrationIdResolver();
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2ClientHttpRequestInterceptor.PrincipalResolver principalResolver() {
        return new SecurityContextHolderPrincipalResolver();
    }

}
