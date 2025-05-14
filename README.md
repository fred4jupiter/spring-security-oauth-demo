# spring-security-oauth-demo

Demo project showing spring security with OAuth and Keycloak.

## Important Endpoints

* Issuer URL:  
  * http://localhost:8380/realms/summer
* Open ID configuration URL:
  * http://localhost:8380/realms/summer/.well-known/openid-configuration
* jwk-set-uri
  * http://localhost:8380/realms/summer/protocol/openid-connect/certs
  * This endpoint has the be enabled and setup in the Keycloak Admin Console!
* Token URL
  * http://localhost:8380/realms/summer/protocol/openid-connect/token
* Authorization URL
  * http://localhost:8380/realms/summer/protocol/openid-connect/auth