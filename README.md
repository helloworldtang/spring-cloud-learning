

Eureka server  注册中心，对外提供服务注册以及发现功能    
下面两个是loadbalance用的。只用启动一个即可。实现了lb和circuit breaker功能   
ribbon   
feign  
实际处理业务的服务，启两个，容灾：   
compute-server-1   
compute-server-2

HystrixDashboard使用流程：
（1）启动eureka-server
（2）启动eureka-ribbon
（3）http://localhost:3333/add?a=1&b=2 //此步骤不能省略
如果lbs没有接口被调用，则http://localhost:3333/hystrix.stream没有数据，dashboard一直处于连接状态
（4）http://localhost:3333/hystrix.stream 页面会不停的刷新监控数据
（5）http://localhost:3333/hystrix
在这个页面Hystrix Dashboard下面的text输入URL http://localhost:3333/hystrix.stream （Delay和Title是选填项）
然后点击按钮“Monitor Stream”即可