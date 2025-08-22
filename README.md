# Java Spring Microservices

This project is a collection of microservices built with Java and Spring Boot.

## About

This project is a demonstration of a microservices architecture. It includes services for authentication, employee management, and notifications. It also includes an API gateway to route requests to the appropriate service.

## Projects

The following microservices are included in this project:

*   **api-gateway:** A Spring Cloud Gateway project that acts as an API gateway for the microservices.
*   **auth-service:** A Spring Boot project that provides authentication and authorization services.
*   **employee-service:** A Spring Boot project that manages employee data. It also uses gRPC and Kafka.
*   **notification-service:** A Spring Boot project that sends notifications. It uses Kafka and protobuf.
*   **infrastructure:** This project contains the AWS CDK code for deploying the infrastructure.
*   **integration-tests:** This project contains integration tests for the microservices.

## How to run

To run the projects, you will need to have the following installed:

*   Java 21
*   Maven
*   Docker

To run the projects, you can use the following command in each project's directory:

```bash
./mvnw spring-boot:run
```

You can also use the provided Dockerfiles to build and run the projects in Docker containers.

## Infrastructure as Code (IaC) with Terraform and EKS

This project uses Terraform to provision the infrastructure on AWS and deploy the microservices to an EKS cluster.

### Prerequisites

*   [Terraform](https://learn.hashicorp.com/tutorials/terraform/install-cli)
*   [AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-install.html)
*   [kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/)

### Setup

1.  Configure your AWS credentials.
2.  Navigate to the `terraform` directory.
3.  Initialize Terraform:
    ```bash
    terraform init
    ```
4.  Apply the Terraform configuration:
    ```bash
    terraform apply
    ```

This will create the EKS cluster and all the necessary resources.

## Kubernetes

The `kubernetes` directory contains the Kubernetes manifest files for the microservices.

*   `api-gateway-deployment.yml`
*   `auth-service-deployment.yml`
*   `employee-service-deployment.yml`
*   `notification-service-deployment.yml`
*   `services.yml`

## Deployment

To deploy the microservices to the EKS cluster, you can use the `deploy.sh` script.

1.  Make sure you have built the Docker images for the microservices.
2.  Update the `deploy.sh` script with your Docker Hub username.
3.  Update the Kubernetes deployment files with your Docker registry.
4.  Run the script:
    ```bash
    ./deploy.sh
    ```