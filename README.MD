参考资料
====  

### Eureka Server 服务注册中心   
* Eureka 集群：[Spring Cloud Eureka 集群搭建 - 以及发现一个 “直觉BUG”](https://www.cnblogs.com/hfultrastrong/p/8549590.html)
* Eureka Server 集群同步：[深入理解Eureka Server集群同步](https://www.jianshu.com/p/b8c614c442e0)
* Eureka 参数配置项详解：[Eureka参数配置项详解](https://blog.csdn.net/qq_33594101/article/details/80430947)
* Eureka 集群节点均出现在 unavailable-replicas:[Eureka高可用，节点均出现在unavailable-replicas下](https://www.jianshu.com/p/59c54ccc6ba6)
* Eureka 集群节点均出现在不可用片区：[Eureka高可用，节点均出现在unavailable-replicas下](https://www.jianshu.com/p/59c54ccc6ba6)

### Eureka Config 配置中心
* 远程配置文件属性值加密：[Spring Cloud Config服务端配置细节(二)之加密解密](https://segmentfault.com/a/1190000011680775)
* 配置中心自动刷新获取最url新配置：[pringCloud Config(配置中心)实现配置自动刷新](https://blog.csdn.net/wtdm_160604/article/details/83720391)

### Eureka Feign 调用服务
* Eureka + Zuul + Feign + Hystrix：[手把手带你整合eureka&zuul&feign&hystrix](https://www.jianshu.com/p/cab8f83b0f0e)

### Eureka Zuul 路由
* 负载均衡示例：[Zuul和Eureka的负载均衡示例](https://www.jianshu.com/p/c93b285ce32e)

### RabbitMQ 
* CentOS 安装 RabbitMQ：[linux CentOS 7 安装 RabbitMQ Erlang 21.0](https://www.cnblogs.com/caozengling/p/9963476.html)
* Linux 安装 RabbitMQ：[Linux下安装erlang及rabbitmq](https://www.cnblogs.com/dreasky/p/9146494.html)
### 日志
* 分布式日志采集：[spring-cloud 分布式日志采集](https://blog.csdn.net/guduyishuai/article/details/79228306)


### 其他
* MD 文档编写：[创建README.md文件](https://blog.csdn.net/zhao_jing_bo/article/details/68063070)




细节笔记
====
* config 读取方式解释

        /{application}/{profile}[/{label}]/{application}-{profile}.yml
        /{label}/{application}-{profile}.yml
        /{application}-{profile}.properties
        /{label}/{application}-{profile}.properties
        
        访问 http://localhost:1201/config-client/dev/master，上面的 url 会映射 {application}-{profile}.properties 对应的配置文件，其中 {label} 对应 Github 上不同的分支，默认为 master。
        所以我们最终访问的配置文件是： application --》 dev 配置文件--》 master 分支的属性文件
        如果 config 客户端里，在 controller 添加 @RefreshScope 注解，可以访问 localhost:xxxx/refresh 更新刷新配置文件的更新
        