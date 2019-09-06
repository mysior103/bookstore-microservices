#!/usr/bin/env bash
echo 'Initializing mongodb'
MONGO_HOST=localhost
MONGO_PORT=27017

DB_NAME=rating-service-db
DB_USERNAME=rafal
DB_PASSWORD=rafal

mongo $DB_NAME --host $MONGO_HOST --port $MONGO_PORT -u $MONGO_INITDB_ROOT_USERNAME -p $MONGO_INITDB_ROOT_PASSWORD --authenticationDatabase admin --eval "db.createUser({user: '$DB_USERNAME', pwd: '$DB_PASSWORD', roles:[{role:'readWrite', db:'$DB_NAME'}]});"

