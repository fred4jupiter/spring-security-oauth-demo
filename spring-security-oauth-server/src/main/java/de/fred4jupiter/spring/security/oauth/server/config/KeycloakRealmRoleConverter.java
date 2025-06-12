package de.fred4jupiter.spring.security.oauth.server.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
class KeycloakRealmRoleConverter implements Converter<Jwt, JwtAuthenticationToken> {

    @Override
    public JwtAuthenticationToken convert(Jwt jwt) {
        return new JwtAuthenticationToken(jwt, mapAuthorities(jwt), mapName(jwt));
    }

    private String mapName(Jwt jwt) {
        return jwt.getClaimAsString("preferred_username");
    }

    private Collection<? extends GrantedAuthority> mapAuthorities(Jwt jwt) {
        final Collection<SimpleGrantedAuthority> allRoles = new ArrayList<>();
        allRoles.addAll(extractRealmRoles(jwt));
        allRoles.addAll(extractClientRoles(jwt));
        return allRoles;
    }

    private List<SimpleGrantedAuthority> extractRealmRoles(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        if (realmAccess != null) {
            List<String> roles = (List<String>) realmAccess.get("roles");
            if (CollectionUtils.isEmpty(roles)) {
                return Collections.emptyList();
            }
            return roles.stream().map(SimpleGrantedAuthority::new).toList();
        }
        return Collections.emptyList();
    }

    private Set<SimpleGrantedAuthority> extractClientRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaimAsMap("resource_access");
        if (resourceAccess != null) {
            Map<String, Object> bsmukFrontendMap = (Map<String, Object>) resourceAccess.get("demo-client");
            if (bsmukFrontendMap != null) {
                Collection<String> roles = (Collection<String>) bsmukFrontendMap.get("roles");
                if (roles != null) {
                    return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
                }
            }
        }
        return Collections.emptySet();
    }

}
