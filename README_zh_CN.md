# sparrow-server-spring-boot-starter

Sparrow Spring Book Edition

[English](./README.md)

支持功能:

- [x] 发送消息
- [x] 消费消息

# 发送消息:

发送消息,方法运行成功,保障[SparrowServer](https://github.com/ThierrySquirrel/sparrow-server-spring-boot-starter) 运行正常,消息一定发送成功  
方法执行失败,消息不发送

# 消费消息:

集群消费消息  

# 消息队列与分布式事务

保证消息一定能够发送到服务端,保证消息一定能够被消费  
消息可重复发送验证,消息可重复消费  
最终一致性  

## Quick Start

```xml
<!--在pom.xml中添加依赖-->
<dependency>
    <artifactId>sparrow-spring-boot-starter</artifactId>
    <groupId>io.github.thierrysquirrel</groupId>
    <version>2.4.0.0-RELEASE</version>
</dependency>
``` 

 ```properties
 ## application.properties
sparrow.sparrow-server-url=127.0.0.1:6060 #如果您需要集群 127.0.0.1:6060,127.0.0.1:6061,127.0.0.1:6062
 ```  

# 启动Sparrow

 ```java

@SpringBootApplication
public class SparrowApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
 ```

# 发送消息

 ```java

@RestController
public class SendMessage {

	@GetMapping("/hello")
	@Producer("hello")
	public User sendMessageHello() {
		User user = new User();
		user.setId(123);
		user.setName("hello");
		return user;
	}

	@GetMapping("/world")
	@Producer("world")
	public User sendMessageWorld() {
		User user = new User();
		user.setId(123);
		user.setName("hello");
		return user;
	}
}

```

# 消费消息

 ```java

@SparrowListener
public class ConsumerMessage {

	@ConsumerListener("hello")
	public void hello(User user) {
		System.out.println(user);
	}

	@ConsumerListener("world")
	public void world(User user) {
		System.out.println(user);
	}

}
 ```

