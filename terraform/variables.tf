variable "aws_region" {
  description = "The AWS region to deploy the resources to."
  default     = "us-east-1"
}

variable "cluster_name" {
  description = "The name of the EKS cluster."
  default     = "java-microservices-cluster"
}
