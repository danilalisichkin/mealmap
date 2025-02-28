#!/bin/bash

MONGO_HOST="localhost:27022"
DATABASE="promo_database"
COLLECTION_NAME="promo_stats"

mongosh --host "$MONGO_HOST" --eval "db.getSiblingDB('$DATABASE').$COLLECTION_NAME.deleteMany({})"
