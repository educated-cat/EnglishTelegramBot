gradle dockerBuildImage
eval $(minikube docker-env)
minikube image load api-gateway:latest
minikube image load bot-receiver:latest
minikube image load bot-sender:latest
minikube image load dictionary:latest
minikube image load discovery-server:latest
minikube image load user:latest
minikube image load user-statistics:latest