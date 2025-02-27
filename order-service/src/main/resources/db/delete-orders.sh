#!/bin/bash

MONGO_HOST="localhost:27020"
DATABASE="order_database"
COLLECTION_NAME="orders"

mongosh --host "$MONGO_HOST" --eval "db.getSiblingDB('$DATABASE').$COLLECTION_NAME.deleteMany({})"
