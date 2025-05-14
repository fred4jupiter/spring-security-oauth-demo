#!/bin/bash

source .env

if [ -z "$CLIENT_ID" ]; then
    echo "CLIENT_ID is not set"
    exit 1
fi

if [ -z "$CLIENT_SECRET" ]; then
    echo "CLIENT_SECRET is not set"
    exit 1
fi

# fetch token

JSON=$(curl -k --location 'http://localhost:8380/realms/summer/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode client_id="$CLIENT_ID" \
--data-urlencode client_secret="$CLIENT_SECRET")

echo "JSON: $JSON"

if [ -z "$JSON" ]; then
    echo "Did not get any JSON response."
    exit 1
fi

TOKEN=$( jq -r ".access_token" <<<"$JSON" )

echo "--------------------------------------"

# echo "TOKEN: $TOKEN"

if [ -z "$TOKEN" ]; then
    echo "Could not parse token from JSON."
    exit 1
fi

# call ping endpoint

curl -k http://localhost:8280/ping -H "Authorization: Bearer $TOKEN"

echo "script finished."

