#!/bin/bash

MONGO_HOST="localhost:27026"
SCRIPT="data/user_contacts.js"

mongosh --host "$MONGO_HOST" "$SCRIPT"
