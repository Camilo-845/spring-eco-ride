#!/bin/bash

# This script prepares the Keycloak realm file by substituting environment variables
# and then starts the Keycloak server.

set -e

REALM_TEMPLATE_FILE="/opt/keycloak/conf/realm-ecoride.json.tpl"
REALM_OUTPUT_FILE="/opt/keycloak/data/import/realm-ecoride.json"

echo "Ensuring realm import directory exists..."
mkdir -p /opt/keycloak/data/import

echo "Substituting environment variables in Keycloak realm template..."

# Substitute variables using sed and create the final realm file.
# We use '#' as a delimiter for sed because the URLs contain slashes.
sed \
  -e "s#\${GATEWAY_REDIRECT_URL_1}#${GATEWAY_REDIRECT_URL_1}#g" \
  -e "s#\${GATEWAY_REDIRECT_URL_2}#${GATEWAY_REDIRECT_URL_2}#g" \
  -e "s#\${INTERNAL_REDIRECT_URL}#${INTERNAL_REDIRECT_URL}#g" \
  -e "s#\${ECO_INTERNAL_SECRET}#${ECO_INTERNAL_SECRET}#g" \
  < "${REALM_TEMPLATE_FILE}" > "${REALM_OUTPUT_FILE}"

echo "Successfully created realm file at ${REALM_OUTPUT_FILE}."
echo "---"
cat "${REALM_OUTPUT_FILE}"
echo "---"

echo "Starting Keycloak..."
exec /opt/keycloak/bin/kc.sh start-dev --import-realm
