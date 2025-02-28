#!/bin/bash

MONGO_HOST="localhost:27022"
SCRIPT="data/promo_stats.js"

mongosh --host "$MONGO_HOST" "$SCRIPT"
