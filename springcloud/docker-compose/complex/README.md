
#在每个项目根目录构建docker镜像
mvn clean package docker:build

#执行命令启动项目
docker-compose up #加-d后台启动


#微服务扩容，启动多个实例
docker-compose scale microservice-provider-user=3 microservice-consumer-movie-ribbon-hystrix=3
 microservice-gateway-zuul=3 microservice-hystrix-turbine=3