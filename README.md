

Eureka server  注册中心，对外提供服务注册以及发现功能
下面两个是loadbalance用的。只用启动一个即可。实现了lb和circuit breaker功能
ribbon
feign
实际处理业务的服务，启两个，容灾：
compute-server-1
compute-server-2
