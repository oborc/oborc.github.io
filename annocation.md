## 注解

​	注解：一个注解就是一个类。

​	注解可以加到类、方法、属性、包、参数以及局部变量上

​	

### @Retention注解类(元注解):	注解其他注解的注解类

```java
@Retention
让注解保留到哪个阶段,枚举类RetentionPolicy的三个取值
SOURCE\CLASS\RUNTIME
```

1. source		在源文件中有效
2. class               在class文件中保留
3. runtime          在运行时保留

```java
@Retention(RetentionPolicy.RUNTIME)
@Document
@Target(ElementType.ANNOTATION_TYPE)
public @interface Retention{
    RetentionPolicy value();
}
```

以上为@Retention 的定义。其中只有一个属性，即-RetentionPolicy的一个枚举类。

------

### @Target注解类(元注解):	注解其他类的注解

