# springboot-features
practice springboot features





### 2.配置

1.如果同时存在properties和yaml文件，properties的优先级更高。

2.使用java -jar启动项目的时候，可以使用--属性 来覆盖配置文件中的配置。

3.命令行参数会被加入到Environment中，可以通过注入Environment接口获取配置信息。如果不想命令行参数被加入到Environment中，可以设置setAddCommandLineProperties(false)。

```java
// 自定义的SpringApplication
SpringApplication springApplication new SpringApplication(SpringbootFeaturesApplication.class);
// 不将命令行的参数加入到Environment中
springApplication.setAddCommandLineProperties(false);
springApplication.run(args);
```

4.springboot项目启动时可以加载JSON的配置，可以通过System property/命令行参数/环境变量来指定参数值：

```java
// 通过命令行参数指定
--spring.application.json="{\"my\": {\"name\": \"Kim\"}}"
// 通过环境变量指定
SPRING_APPLICATION_JSON={"my":{"name":"test"}}
```

5.默认的配置文件名称为application.xxx,可以通过命令行参数/环境变量/System Property来指定配置文件的名称。如果存在同名不同后缀的配置文件，properties的优先级高于yaml。

```java
// 通过命令行指定配置文件的名称,记得不能加后缀
--spring.config.name=test
```

也可以通过location来指定配置文件的路径,多个路径使用逗号分割：

如果location包含目录，必须以\结尾。springboot会自动拼接上spring.config.name的值。

```java
// 通过classpath指定配置文件的目录
--spring.config.location=classpath:/test.yml

// 通过绝对路径指定配置文件的目录
--spring.config.location=C:\Users\kim.zhang\IdeaProjects\springboot-features\src\main\resources\ --spring.config.name=test
```

如果在配置文件中指定了spring.profiles.active=xxx,那么spring.config.location指定的路径下如果也有spring.appliction.name-xxx的文件，也会被加载到Environment中。

6.如果通过spring.config.location指定的目录不一定存在时，会抛出ConfigDataLocationNotFoundExceptions的异常。springboot项目会启动不起来。如果想忽略此异常，可以加上optional:前缀，或者设置忽略这个异常。

```java
// optional前缀
--spring.config.location=optional:C:\Users\kim.zhang\IdeaProjects\springboot-features\src\main\resources\ --spring.config.name=test

// 设置忽略ConfigDataLocationNotFoundExceptions
public static void main(String[] args) {
    // 自定义的SpringApplication
    SpringApplication springApplication = new SpringApplication(SpringbootFeaturesApplication.class);
    Map<String,Object> properties = new HashMap<>();
    properties.put("spring.config.on-not-found","ignore");
    springApplication.setDefaultProperties(properties);
    springApplication.run(args);
}
```

7.@ConfigurationProperties和@Value的区别：

|            | @ConfigurationProperties                                     | @Value |
| ---------- | ------------------------------------------------------------ | ------ |
| 宽松绑定   | 支持。比如配置文件中是item-pirce,在实体类中的属性可以是itemPrice. 但是@Value是不支持宽松绑定的。 | 不支持 |
| SPEL表达式 | 不支持                                                       | 支持   |

8.