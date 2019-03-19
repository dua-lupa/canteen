#!/usr/bin/env bash

# TODO increment image version here
version=3
LOCAL_IMAGE_NAME=dua-lupa
REMOTE_IMAGE_NAME=yattbot/dua-lupa:${version}
DOCKERHUB_LOGIN=yattbot


echo ">>> Make sure you have rebuilt jar!"
echo ">>> building docker image"
docker build -t ${LOCAL_IMAGE_NAME} .


echo ">>> Tagging image with name: ${REMOTE_IMAGE_NAME}"
docker tag ${LOCAL_IMAGE_NAME} ${REMOTE_IMAGE_NAME}


echo ">>> Logging in to Docker Hub"
cat pwd.txt | docker login --username ${DOCKERHUB_LOGIN} --password-stdin


echo ">>> Pushing image to Docker Hub"
docker push ${REMOTE_IMAGE_NAME}


echo ">>> Image pushed successfully! ${REMOTE_IMAGE_NAME}"
