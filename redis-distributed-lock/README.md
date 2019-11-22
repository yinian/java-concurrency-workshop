# redis-distributed-lock
> redis分布式锁工具包，提供纯Java方式调用，支持传统Spring工程，
> 为spring boot应用提供了starter，更方便快捷的调用。
## 项目结构
        
[redis-distributed-lock-core](http://wuwenliang.net/2018/07/08/%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E7%9A%84%E5%A4%9A%E7%A7%8D%E5%AE%9E%E7%8E%B0/) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;             原生redis分布式锁实现，支持注解，不推荐项目中使用，仅供学习使用

redis-distributed-lock-demo-spring &nbsp;      redis-distributed-lock-core 调用实例，仅供学习

[redis-distributed-lock-starter](./redis-distributed-lock-starter/readme.md) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;          基于Redisson的分布式锁spring starter实现，可用于实际项目中

[redis-distributed-lock-starter-demo](./redis-distributed-lock-starter-demo/readme.md) &nbsp;     redis-distributed-lock-starter调用实例
 
## 文档
### springboot应用（直接编程方式）
0. 配置文件application.properties中引入

        redisson.lock.server.address=127.0.0.1:6379
        redisson.lock.server.type=standalone

1. 针对springboot应用只需要引入依赖

        <!--分布式锁redisson版本-->
        <dependency>
        	<groupId>com.snowalker</groupId>
        	<artifactId>redis-distributed-lock-starter</artifactId>
        	<version>1.0.0</version>
        </dependency>
2. 直接编程方式调用如下，在需要加锁的定时任务中，注入 **RedissonLock** 实体，即可进行加锁、解锁等操作。
<br/>锁自动释放时间默认为10秒，这个时间需要你根据自己的业务执行时间自行指定。

        @Autowired
        RedissonLock redissonLock;
        
        @Scheduled(cron = "${redis.lock.cron}")
        public void execute() throws InterruptedException {
            if (redissonLock.lock("redisson", 10)) {
                LOGGER.info("[ExecutorRedisson]--执行定时任务开始，休眠三秒");
                Thread.sleep(3000);
                System.out.println("=======================业务逻辑=============================");
                LOGGER.info("[ExecutorRedisson]--执行定时任务结束，休眠三秒");
                redissonLock.release("redisson");
            } else {
                LOGGER.info("[ExecutorRedisson]获取锁失败");
            }
        }
       
3. 你可以改变测试demo的端口，起多个查看日志，能够看到同一时刻只有一个实例获取锁成功并执行业务逻辑

        2018-07-10 23:00:12.810 |-INFO  [main] org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer [198] -| Tomcat started on port(s): 8888 (http)
        2018-07-10 23:00:12.814 |-INFO  [main] com.snowalker.RedisDistributedLockStarterDemoApplication [57] -| Started RedisDistributedLockStarterDemoApplication in 2.684 seconds (JVM running for 3.038)
        2018-07-10 23:00:15.033 |-INFO  [pool-3-thread-1] com.snowalker.lock.redisson.RedissonLock [35] -| 获取Redisson分布式锁[成功],lockName=redisson
        2018-07-10 23:00:15.034 |-INFO  [pool-3-thread-1] com.snowalker.executor.ExecutorRedissonNormal [27] -| [ExecutorRedisson]--执行定时任务开始，休眠三秒
        =======================业务逻辑=============================
        2018-07-10 23:00:18.035 |-INFO  [pool-3-thread-1] com.snowalker.executor.ExecutorRedissonNormal [30] -| [ExecutorRedisson]--执行定时任务结束，休眠三秒
        2018-07-10 23:00:20.005 |-INFO  [pool-3-thread-1] com.snowalker.lock.redisson.RedissonLock [35] -| 获取Redisson分布式锁[成功],lockName=redisson
        2018-07-10 23:00:20.006 |-INFO  [pool-3-thread-1] com.snowalker.executor.ExecutorRedissonNormal [27] -| [ExecutorRedisson]--执行定时任务开始，休眠三秒
        =======================业务逻辑============================= 
### springboot应用（注解方式）
1. 前提条件同样是要引入依赖

        <!--分布式锁redisson版本-->
        <dependency>
        	<groupId>com.snowalker</groupId>
        	<artifactId>redis-distributed-lock-starter</artifactId>
        	<version>1.0.0</version>
        </dependency>
2. 注解方式调用如下，在需要加锁的定时任务的执行方法头部，添加 **@DistributedLock(value = "redis-lock", expireSeconds = 11)**
即可进行加锁、解锁等操作。<br/>锁自动释放时间默认为10秒，这个时间需要你根据自己的业务执行时间自行指定。
<br/>我这里以spring schedule定时任务为例，用其他的定时任务同理，只需要添加注解。

        @Scheduled(cron = "${redis.lock.cron}")
        @DistributedLock(value = "redis-lock", expireSeconds = 11)
        public void execute() throws InterruptedException {
            LOGGER.info("[ExecutorRedisson]--执行定时任务开始，休眠三秒");
            Thread.sleep(3000);
            System.out.println("=======================业务逻辑=============================");
            LOGGER.info("[ExecutorRedisson]--执行定时任务结束，休眠三秒");
        }
       
3. 你可以改变测试demo的端口，起多个查看日志，能够看到同一时刻只有一个实例获取锁成功并执行业务逻辑

        2018-07-11 09:48:06.330 |-INFO  [main] com.snowalker.RedisDistributedLockStarterDemoApplication [57] -| Started RedisDistributedLockStarterDemoApplication in 3.901 seconds (JVM running for 4.356)
        2018-07-11 09:48:10.006 |-INFO  [pool-3-thread-1] com.snowalker.lock.redisson.annotation.DistributedLockHandler [32] -| [开始]执行RedisLock环绕通知,获取Redis分布式锁开始
        2018-07-11 09:48:10.622 |-INFO  [pool-3-thread-1] com.snowalker.lock.redisson.RedissonLock [35] -| 获取Redisson分布式锁[成功],lockName=redis-lock
        2018-07-11 09:48:10.622 |-INFO  [pool-3-thread-1] com.snowalker.lock.redisson.annotation.DistributedLockHandler [39] -| 获取Redis分布式锁[成功]，加锁完成，开始执行业务逻辑...
        2018-07-11 09:48:10.625 |-INFO  [pool-3-thread-1] com.snowalker.executor.ExecutorRedissonAnnotation [22] -| [ExecutorRedisson]--执行定时任务开始，休眠三秒
        =======================业务逻辑=============================
        2018-07-11 09:48:13.625 |-INFO  [pool-3-thread-1] com.snowalker.executor.ExecutorRedissonAnnotation [25] -| [ExecutorRedisson]--执行定时任务结束，休眠三秒
        2018-07-11 09:48:13.627 |-INFO  [pool-3-thread-1] com.snowalker.lock.redisson.annotation.DistributedLockHandler [46] -| 释放Redis分布式锁[成功]，解锁完成，结束业务逻辑...
        2018-07-11 09:48:13.628 |-INFO  [pool-3-thread-1] com.snowalker.lock.redisson.annotation.DistributedLockHandler [50] -| [结束]执行RedisLock环绕通知

## application.properties配置方式(yaml同理)
### redisson分布式锁配置--单机

        redisson.lock.server.address=127.0.0.1:6379
        redisson.lock.server.type=standalone
        redisson.lock.server.password=
        redisson.lock.server.database=1

### redisson分布式锁配置--哨兵
**redisson.lock.server.address** 格式为: sentinel.conf配置里的sentinel别名,sentinel1节点的服务IP和端口，sentinel2节点的服务IP和端口，sentinel3节点的服务IP和端口
<br/>比如sentinel.conf里配置为sentinel monitor my-sentinel-name 127.0.0.1 6379 2,那么这里就配置my-sentinel-name

        redisson.server.address=my-sentinel-name,127.0.0.1:26379,127.0.0.1:26389,127.0.0.1:26399
        redisson.server.type=sentinel
        redisson.lock.server.password=
        redisson.lock.server.database=1

### redisson分布式锁配置--集群方式
cluster方式至少6个节点(3主3从，3主做sharding，3从用来保证主宕机后可以高可用)
<br/>地址格式为: 127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381,127.0.0.1:6382,127.0.0.1:6383,127.0.0.1:6384

        redisson.server.address=127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381,127.0.0.1:6382,127.0.0.1:6383,127.0.0.1:6384
        redisson.server.type=cluster
        redisson.lock.server.password=
        redisson.lock.server.database=1

### redisson分布式锁配置--主从
地址格式为**主节点,子节点,子节点**
<br/>比如:127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381
<br/>代表主节点:127.0.0.1:6379，从节点127.0.0.1:6380，127.0.0.1:6381

        redisson.server.address=127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381
        redisson.server.type=masterslave
        redisson.lock.server.password=
        redisson.lock.server.database=1

## 参考资料

[自己写分布式锁-基于redission](http://wuwenliang.net/2018/12/07/%E8%87%AA%E5%B7%B1%E5%86%99%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81-%E5%9F%BA%E4%BA%8Eredission/)