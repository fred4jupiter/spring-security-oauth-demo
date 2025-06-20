ARG KEYCLOAK_BASE_IMAGE=quay.io/keycloak/keycloak:26.2.4
FROM ${KEYCLOAK_BASE_IMAGE} AS builder

# enable health and metrics support
env KC_HEALTH_ENABLED=true
env KC_METRICS_ENABLED=true
env KC_TRACING_ENABLED=true
env KC_EVENT_METRICS_USER_ENABLED=true

# configure a database vendor
env kc_db=postgres

workdir /opt/keycloak
run /opt/keycloak/bin/kc.sh build --features="user-event-metrics" --metrics-enabled=true --event-metrics-user-enabled=true --spi-events-listener-micrometer-user-event-metrics-enabled=true

# Create the actual image
FROM ${KEYCLOAK_BASE_IMAGE}
COPY --from=builder /opt/keycloak/ /opt/keycloak/

# change these values to point to a running postgres instance
entrypoint ["/opt/keycloak/bin/kc.sh"]