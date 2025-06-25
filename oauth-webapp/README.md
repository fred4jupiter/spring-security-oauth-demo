# oauth-webapp

This is an example for using the `authorization code flow`.

## Logout

In the keycloak client you can configure the backchannel logout URL. This is

    http://localhost:8580/logout/connect/back-channel/oauth-webapp

But be aware that the keycloak is running inside a container and cannot access the webb application running on the host.

To test this you can containerize the `oauth-webbapp` and start it as a container.