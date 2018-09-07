#### spring-boot自定义yml解析

刚开始不知道怎么解析yml配置文件，上网找了很多资料，很多都只是写了要先用

```
@Component（表示注入容器）
@ConfigurationProperties(prefix = "yml文件中组件名")
```

这两个注解在实体类上标注，并用set和get方法设置和返回数据。

然后在用到实体类的地方直接用get 方法即可。

实际上，这样子做是不会读到自定义的yml 文件的。读取到的值为null 

因为yml 配置文件默认读取前缀为application 的文件，如果自定义文件没有application前缀，则读取不到。

因此需要在application.yml中增加一个属性

```
spring.profiles.include: text
```

text 是自定义文件后缀。例如：application-text.yml

```java
@CongigurationProfiles()注解是将配置文件中的所有属性都和当前类的属性匹配并注入。
@Value("${text.template}")
    private String template;
是单个注入，只选取某个值的时候用到。
二者不能同事使用。
```

在自定义的配置文件的时候，可以在代码中加上

```java
@ProperitySource(value={"文件路径"})
```

来表明配置文件的路径，表示要在某个路径下去读取配置文件。一般直接放在resource文件夹下，是默认路径。可不加该注解。即resource就是classpath。