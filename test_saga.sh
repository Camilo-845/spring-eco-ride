#!/bin/bash

# --- Test Script for Reservation Saga ---

# Please replace this placeholder with your actual Keycloak JWT token
AUTH_TOKEN=""

# The script will exit immediately if a command fails
set -e

# API Gateway base URL
GATEWAY_URL="http://localhost:8080"

echo "--- Starting Saga Test ---"

if [ "$AUTH_TOKEN" == "PASTE_YOUR_JWT_TOKEN_HERE" ]; then
  echo "ERROR: Please edit this script and replace 'PASTE_YOUR_JWT_TOKEN_HERE' with a valid JWT token."
  exit 1
fi

# 1. Create an origin location
echo "Creating origin location..."
ORIGIN_ID=$(xh post "localhost:8085/locations/" \
  name='Casa' \
  latitude:=4.60971 \
  longitude:=-74.08175 \
  "Authorization: Bearer $AUTH_TOKEN" | jq -r '.id')
echo "  => Origin Location ID: $ORIGIN_ID"

# 2. Create a destination location
echo "Creating destination location..."
DESTINATION_ID=$(xh post "localhost:8085/locations/" \
  name='Oficina' \
  latitude:=4.67868 \
  longitude:=-74.04354 \
  "Authorization: Bearer $AUTH_TOKEN" | jq -r '.id')
echo "  => Destination Location ID: $DESTINATION_ID"

# 3. Create a trip
# Note: The startTime needs to be a future timestamp in ISO 8601 format
FUTURE_TIMESTAMP=$(date -u -d "+1 day" +"%Y-%m-%dT%H:%M:%SZ")
echo "Creating trip for tomorrow at $FUTURE_TIMESTAMP..."
TRIP_ID=$(xh post "localhost:8085/trips/" \
  origin:="$ORIGIN_ID" \
  destination:="$DESTINATION_ID" \
  startTime="$FUTURE_TIMESTAMP" \
  seatsTotal:=2 \
  pricePerSeat:=15.50 \
  "Authorization: Bearer $AUTH_TOKEN" | jq -r '.id')
echo "  => Trip ID: $TRIP_ID"

# 4. Create a reservation for the trip (This triggers the Saga)
echo "Creating reservation for trip '$TRIP_ID'..."
RESERVATION_RESPONSE=$(xh post "localhost:8085/trips/$TRIP_ID/reservations" "Authorization: Bearer $AUTH_TOKEN")
RESERVATION_ID=$(echo "$RESERVATION_RESPONSE" | jq -r '.id')
echo "  => Reservation request sent. Reservation ID: $RESERVATION_ID"

echo ""
echo "--- Saga Triggered ---"
echo "Check the logs for 'trip-service' and 'payment-service' to observe the event flow."
echo "The reservation should be PENDING, then move to CONFIRMED (on payment success) or CANCELLED (on payment failure)."
