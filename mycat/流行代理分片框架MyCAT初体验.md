###### 安装MyCAT

mkdir -p /usr/local/mycat && cd /usr/local/mycat

wget  http://dl.mycat.io/1.6.6.1/Mycat-server-1.6.6.1-release-20181031195535-linux.tar.gz

tar -zxvf Mycat-server-1.6.6.1-release-20181031195535-linux.tar.gz -C ./

###### 目录结构
.  
└── mycat  
    ├── bin  
    │   ├── mycat           Mycat启动程序    
    │   ├── ...  
    ├── catlet  
    ├── conf  
    │   ├── log4j2.xml      日志的配置，可以根据自己的需要调整输出级别为debug    
    │   ├── rule.xml        分片规则的配置文件，分片规则的具体参数信息被单独存放为文件，也在当前目录下，       
                            对配置文件进行修改时需要重启Mycat     
    │   ├── schema.xml      逻辑库定义和表，以及分片定义的配置文件        
    │   ├── server.xml      Mycat 服务器参数调整和用户授权配置文件  
    │   ├── wrapper.conf    JVM内存配置文件        
    │   ├── zkconf          ZooKeeper 的配置文件  
    ├── lib                 Mycat 自身的Jar包或依赖的Jar包的存放目录  
    ├── logs                Mycat 日志的存放目录。日志被存在logs/log中，每天生成一个文件  
    └── version.txt         版本信息
    
    

