#!/bin/bash

MONGO_HOST="localhost:27020"
SCRIPT="data/orders.js"

mongosh --host "$MONGO_HOST" "$SCRIPT"
