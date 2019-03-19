#!/usr/bin/env bash

current=$(pwd)
BE=${current}/backend
FE=${current}/webclient


echo ">>> building frontend"
echo "skipping"
# TODO copy bundle into resources/public/static
# move index.html into templates/index.html


echo ">>> building backend"
./gradlew :backend:clean build
mv ${BE}/build/libs/backend-0.0.1.jar ${current}/app.jar


echo ">>> Build done!"