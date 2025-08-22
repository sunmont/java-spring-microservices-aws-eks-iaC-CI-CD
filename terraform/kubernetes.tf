resource "kubernetes_manifest" "api_gateway_deployment" {
  manifest = yamldecode(file("${path.module}/../kubernetes/api-gateway-deployment.yml"))
}

resource "kubernetes_manifest" "auth_service_deployment" {
  manifest = yamldecode(file("${path.module}/../kubernetes/auth-service-deployment.yml"))
}

resource "kubernetes_manifest" "employee_service_deployment" {
  manifest = yamldecode(file("${path.module}/../kubernetes/employee-service-deployment.yml"))
}

resource "kubernetes_manifest" "notification_service_deployment" {
  manifest = yamldecode(file("${path.module}/../kubernetes/notification-service-deployment.yml"))
}

resource "kubernetes_manifest" "services" {
  manifest = yamldecode(file("${path.module}/../kubernetes/services.yml"))
}
