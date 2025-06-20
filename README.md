# spring-security-oauth-demo

Demo project showing spring security configuration with OAuth and Keycloak using `client_credentials`-flow.

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

## Start Keycloak locally

Use the provided `docker-compose.yml` file and issue the following command:

    docker compose up -d

Then login with `admin/admin` at http://localhost:8380/admin/master/console/

## Initial Keycloak Setup

After starting keycloak with the provided `docker-compose.yml` file you have to setup some things to run the demo:

1. Create a realm `summer`
2. Create a client called `demo-client`. As credentials choose `Client Id and Secret` and copy the secret to use into the clients project `application.yaml` file at key `client-secret`.

## Settings Overview

| Key           | Value                      | alternative Keycloak Variable |
|---------------|----------------------------|-------------------------------|
| client id     | demo-client                |                               |
| client secret | _generate a secret value_   |                               |
| Root URL      | http://localhost:8380/auth | ${authBaseUrl}                |
| Home URL      | /realms/summer/demo-client/ |                               |

## App URLs

| App        | Port                  | Username | Password
|------------|-----------------------|-|-
| Keycloak   | http://localhost:8380 | `admin` | `admin`
| Prometheus | http://localhost:9090 | |
| Grafana    | http://localhost:3000 | `admin` | `admin`
| MailPit    | http://localhost:8025 |  | 

### demo-client

![demo-client](keycloak/screenshots/demo-client.jpg?raw=true "demo-client")

