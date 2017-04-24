
#生产环境为了解耦，建议使用hostname：  
eureka.instance.hostname   

本例中需要在host文件中添加主机名：  
127.0.0.1 svr1  svr2

host文件，windows10中的位置  
C:\Windows\System32\drivers\etc\hosts

application.properties文件是默认配置  
application-svr1.properties  
和
application-svr2.properties  
是高可用场景，作replicas容灾用的，需要单独分别部署  
java -jar target/eureka-server-0.0.1-SNAPSHOT.jar  --spring.profiles.active=svr1  
java -jar target/eureka-server-0.0.1-SNAPSHOT.jar  --spring.profiles.active=svr2


