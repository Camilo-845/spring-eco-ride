{
  "realm": "ecoride",
  "enabled": true,

  "roles": {
    "realm": [
      { "name": "ROLE_DRIVER" },
      { "name": "ROLE_PASSENGER" },
      { "name": "ROLE_ADMIN" }
    ]
  },

  "clients": [
    {
      "clientId": "eco-gateway",
      "protocol": "openid-connect",
      "publicClient": true,
      "standardFlowEnabled": true,
      "directAccessGrantsEnabled": true,
      "redirectUris": ["${GATEWAY_REDIRECT_URL_1}/*", "${GATEWAY_REDIRECT_URL_2}/*"],
      "webOrigins": ["*"]
    },
    {
      "clientId": "eco-internal",
      "protocol": "openid-connect",
      "publicClient": false,
      "secret": "${ECO_INTERNAL_SECRET}",
      "serviceAccountsEnabled": true,
      "standardFlowEnabled": true,
      "redirectUris": ["${INTERNAL_REDIRECT_URL}/*"],
      "webOrigins": ["*"]
    }
  ]
}
