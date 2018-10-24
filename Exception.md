## Exception

它发生在程序运行期间，干扰了正常的指令流程。

### Try-catch-finally

#### Test-finally-exception

```java 
		int i=10;
        try
        {
            System.out.println("before 10/0:");
            i=i/0;
            System.out.println("try:"+1);
            return 1;
        }catch(Exception e)
        {
            System.out.println("catch:"+2);
            return 2;
        }
        finally
        {
            System.out.println("finally:"+3);
            return 3;
        }
```

Result:

```java
before 10/0:
catch:2
finally:3
3
```

#### Test-without finally-exception

```java
 		int i=10;
        try
        {
            System.out.println("before 10/0:");
            i=i/0;
            System.out.println("try:"+1);
            return 1;
        }catch(Exception e)
        {
            System.out.println("catch:"+2);
            return 2;
        }
```

Result:

```java
before 10/0:
catch:2
2
```

#### Test-finally-without-exception

```java
        int i=10;
        try
        {
            System.out.println("before 10/10:");
            i=i/10;
            System.out.println("try:"+1);
            return 1;
        }catch(Exception e)
        {
            System.out.println("catch:"+2);
            return 2;
        }
        finally
        {
            System.out.println("finally:"+3);
            return 3;
        }
```

Result:

```java
before 10/10:
try:1
finally:3
3
```



### summary

1. 在有finally的情况下，无论有没有有没有异常，都会执行finally中的语句。如果finally中有返回值，则返回finally中的值。try-catch中的返回值会被覆盖。
2. try-catch中的执行。如果有异常，则try中在抛出异常前的语句会正常执行，然后进入到catch块，执行catch中的所有语句。没有finally，则try-catch结束，否则执行finally中的语句。
3. try-catch中的执行，try中没有异常，则catch 中的语句不执行。

### 特殊情况

```java
System.exit(0);
```

System.exit(0)是强制退出当前执行进程。无论在哪个块。如果有System.exit(0)被执行，则进程会被强制在该语句被执行的地点退出。如果在try-catch中，finally中的语句就不会被执行到。

### 检查异常

runtimeException及其子类和error都是检查异常，其他异常都是非检查异常。