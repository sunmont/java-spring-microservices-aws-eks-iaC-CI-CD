#!/bin/bash

# Set your Docker Hub username
DOCKER_HUB_USERNAME="<your-docker-hub-username>"

# Build the Docker images
./build-images.sh

# Push the Docker images to Docker Hub
docker push $DOCKER_HUB_USERNAME/api-gateway:latest
docker push $DOCKER_HUB_USERNAME/auth-service:latest
docker push $DOCKER_HUB_USERNAME/employee-service:latest
docker push $DOCKER_HUB_USERNAME/notification-service:latest

# Apply the Kubernetes manifests
kubectl apply -f ./kubernetes/api-gateway-deployment.yml
kubectl apply -f ./kubernetes/auth-service-deployment.yml
kubectl apply -f ./kubernetes/employee-service-deployment.yml
kubectl apply -f ./kubernetes/notification-service-deployment.yml
kubectl apply -f ./kubernetes/services.yml
