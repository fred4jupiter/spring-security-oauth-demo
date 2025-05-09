package de.fred4jupiter.spring.security.oauth.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {

    @Bean
    public RestClient restClient(OAuth2AuthorizedClientManager authorizedClientManager) {
        OAuth2ClientHttpRequestInterceptor requestInterceptor = new OAuth2ClientHttpRequestInterceptor(authorizedClientManager);
        return RestClient.builder().requestInterceptor(requestInterceptor).build();
    }

//    /**
//     * This sample uses profiles to demonstrate additional strategies for resolving the
//     * {@code clientRegistrationId}. See {@link ClientRegistrationIdResolverConfiguration}
//     * for alternate implementations.
//     *
//     * @return the default
//     * {@link OAuth2ClientHttpRequestInterceptor.ClientRegistrationIdResolver}
//     * @see ClientRegistrationIdResolverConfiguration
//     */
//    @Bean
//    @ConditionalOnMissingBean
//    public OAuth2ClientHttpRequestInterceptor.ClientRegistrationIdResolver clientRegistrationIdResolver() {
//        return new RequestAttributeClientRegistrationIdResolver();
//    }
//

//    /**
//     * This sample uses profiles to demonstrate additional strategies for resolving the
//     * {@code principal}. See {@link PrincipalResolverConfiguration} for alternate
//     * implementations.
//     *
//     * @return the default {@link OAuth2ClientHttpRequestInterceptor.PrincipalResolver}
//     * @see PrincipalResolverConfiguration
//     */
//    @Bean
//    @ConditionalOnMissingBean
//    public OAuth2ClientHttpRequestInterceptor.PrincipalResolver principalResolver() {
//        return new SecurityContextHolderPrincipalResolver();
//    }

//    @Bean
//    public OAuth2ClientHttpRequestInterceptor.PrincipalResolver principalResolver() {
//        return new RequestAttributePrincipalResolver();
//    }

}
