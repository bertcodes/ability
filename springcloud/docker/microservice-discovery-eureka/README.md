
#构建镜像文件
docker build -t damon/microservice-discovery-eureka:0.0.1 .

#查看镜像文件
docker images


#运行
docker run -d 8761:8761 damon/microservice-discovery-eureka:0.0.1