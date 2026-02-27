# sparrow-spring-boot-starter

Sparrow Spring Book Edition

[中文](./README_zh_CN.md)

Support Function:

- [x] SendMessage
- [x] ConsumerMessage

# SendMessage:

Send Message, Method Runs Successfully,Ensure The Normal Operation
Of [SparrowServer](https://github.com/ThierrySquirrel/sparrow-server-spring-boot-starter) And The Message Must Be Sent
Successfully                                                                                                                                                  
Method Failed To Run, Message Not Sent.

# ConsumerMessage:

Cluster Consumption Messages  

# Message queues and distributed transactions

Ensure that the message can be sent to the server and that the message can be consumed  
Messages can be sent repeatedly for verification, and messages can be consumed repeatedly  
Final consistency  

## Quick Start

```xml
<!--Adding dependencies to pom. XML-->
<dependency>
    <artifactId>sparrow-spring-boot-starter</artifactId>
    <groupId>io.github.thierrysquirrel</groupId>
    <version>2.5.0.2-RELEASE</version>
</dependency>
``` 

 ```properties
 ## application.properties
sparrow.sparrow-server-url=127.0.0.1:6060 #If You Need A Cluster 127.0.0.1:6060,127.0.0.1:6061,127.0.0.1:6062
 ```  

# Start Sparrow

 ```java

@SpringBootApplication
public class SparrowApplication {
	public static void main(String[] args) {
		SpringApplication.run(SparrowApplication.class, args);
	}

}
 ```

# SendMessage

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

# ConsumerMessage

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
 
 
