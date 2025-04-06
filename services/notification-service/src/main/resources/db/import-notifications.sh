#!/bin/bash

MONGO_HOST="localhost:27026"
SCRIPT="data/notifications.js"

mongosh --host "$MONGO_HOST" "$SCRIPT"
