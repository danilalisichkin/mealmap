#!/bin/bash

MONGO_HOST="localhost:27022"
SCRIPT="data/promo_codes.js"

mongosh --host "$MONGO_HOST" "$SCRIPT"
