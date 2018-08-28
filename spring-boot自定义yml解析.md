#### spring-boot自定义yml解析

刚开始不知道怎么解析yml配置文件，上网找了很多资料，很多都只是写了要先用

```
@Component
@ConfigurationProperties(prefix = "yml文件名")
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

然后在使用yml 的地方用@value 注解即可。

```
 @Value("${text.template}")
    private String template;
```

application-text.yml 如图：

![image-20180828210538188](/var/folders/0s/c9bws2n97dg02w8d5lh96vrh0000gn/T/abnerworks.Typora/image-20180828210538188.png)

输出结果如下：

![image-20180828210454785](/var/folders/0s/c9bws2n97dg02w8d5lh96vrh0000gn/T/abnerworks.Typora/image-20180828210454785.png)

并不需要创建实体类去进行注入。

但是，在测试read yml 的时候，需要添加

![image-20180828210714204](/var/folders/0s/c9bws2n97dg02w8d5lh96vrh0000gn/T/abnerworks.Typora/image-20180828210714204.png)

这里，是说明mybatis、文件目录。还有运行环境的注解。

