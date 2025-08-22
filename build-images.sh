#!/bin/bash

# Build the Docker image for the api-gateway
(cd api-gateway && docker build -t api-gateway:latest .)

# Build the Docker image for the auth-service
(cd auth-service && docker build -t auth-service:latest .)

# Build the Docker image for the employee-service
(cd employee-service && docker build -t employee-service:latest .)

# Build the Docker image for the notification-service
(cd notification-service && docker build -t notification-service:latest .)
