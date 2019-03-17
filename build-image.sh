#!/usr/bin/env bash

current=$(pwd)
BE=${current}/backend
FE=${current}/webclient

LOCAL_IMAGE_NAME=dua-lupa


echo ">>> building frontend"
echo "skipping"


echo ">>> building backend"
./gradlew :backend:clean build
mv ${BE}/build/libs/backend-0.0.1.jar ${current}/app.jar


echo ">>> building image"
docker build -t ${LOCAL_IMAGE_NAME} .

