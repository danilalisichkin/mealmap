#!/bin/bash

MONGO_HOST="localhost:27026"
DATABASE="notification_database"
COLLECTION_NAME="notifications"

mongosh --host "$MONGO_HOST" --eval "db.getSiblingDB('$DATABASE').$COLLECTION_NAME.deleteMany({})"
