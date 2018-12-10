#编写pom文件添加docker-maven插件 ,构建Docker镜像
mvn clean package docker:build
#编写docker-compose配置文件，运行应用
docker-compose up