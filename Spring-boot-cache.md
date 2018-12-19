## Spring-boot-cache

由于公司业务需要，实现springp-cache来存储一个数据表中的所有数据，spring提供了默认的cache机制。采用concurrentHashMap 实现。由一个concurrentMap来作为存储机制。

**concurrentHashMap并不是作为一个实例变量存在，而是作为构造函数的参数传递给实例变量ConcurrentMap来实现。**

```java
public class ConcurrentMapCache extends AbstractValueAdaptingCache {

	private final String name;

    //cache中真正存储key-value的地方
	private final ConcurrentMap<Object, Object> store;

	private final SerializationDelegate serialization;
    
    //构造函数，指定cache的名字，默认stor 容量为256
    public ConcurrentMapCache(String name) {
		this(name, new ConcurrentHashMap<Object, Object>(256), true);
	}
    
	public ConcurrentMapCache(String name, boolean allowNullValues) {
		this(name, new ConcurrentHashMap<Object, Object>(256), allowNullValues);
	}

	public ConcurrentMapCache(String name, ConcurrentMap<Object, Object> store, boolean allowNullValues) {
		this(name, store, allowNullValues, null);
	}

    //构造函数，传入ConcurrentHashMap作为store
	protected ConcurrentMapCache(String name, ConcurrentMap<Object, Object> store,
			boolean allowNullValues, SerializationDelegate serialization) {

		super(allowNullValues);
		Assert.notNull(name, "Name must not be null");
		Assert.notNull(store, "Store must not be null");
		this.name = name;
		this.store = store;
		this.serialization = serialization;
	}

}
```

```java
//ConcurrentHashMap 的定义
public class ConcurrentHashMap<K,V> extends AbstractMap<K,V>
    implements ConcurrentMap<K,V>, Serializable {}

//ConcurrentMap 的定义
public interface ConcurrentMap<K, V> extends Map<K, V> {}
```

具体实例

```java
  @Cacheable(value = "userCache", key = "#age+':'+#name", cacheManager = "cacheManager")
    public List<Object> selectOneUser(Long userId) {
        ConcurrentMapCache testCache = (ConcurrentMapCache) myCacheManager.getCache("userCache");
        ConcurrentMap<String, User> cacheMap = (ConcurrentMap) testCache.getNativeCache();
        List<Object> value = new ArrayList<>();
        User user = new User();
        user.setId(userId);
        value.add(userMapper.selectOne(user));
        value.add(new Date());
        return value;
    }
```

**以上代码中，返回值是一个list<object>,在实际开发中，不建议这样使用。这里只是写了个测试**

###### 在方法上增加@Cacheable注解，需要注意的是：

- spring中的方法注解，如果要让其发挥作用，都要在类的层级上声明这是一个bean，一般不知道是哪一类的时候，采用component，明确知道的话，写上@service等。
- 在同类中，调用被@Cacheable标注的方法，该方法上的@Cacheable会失效，这里是因为，spring中的注解都是在程序启动的时候，由spring的AOP进行切面拦截。拦截在类上。无法对类内的调用进行bean 的注册拦截。所以会失效，此定理适用@Async。别的还没试过，应该是同理的。

###### @Cacheable的作用机制：

- @Cacheable注解的作用机制是：进入此方法前，查看缓存中是否有该key.如果没有的话，进入方法，将返回值放入cache.有的话，直接从缓存取值返回。

- @Cacheable中需要声明cache的名字，key 的生成策略。在需要用到多个缓存的时候，可以用一个cacheManager 来进行管理。cacheManager需要注册一个bean.

- Key 的生成策略如果不进行指定的话，默认采用参数拼接为一个String作为key。key 的定义也是一个string类型的字符串。

- cacheManager 在configure文件中进行指定。在spring-boot中，显示的申明一个bean.这里bean的名字就是函数的名字

  ```java
   	@Bean
      public CacheManager cacheManager() {
          SimpleCacheManager cacheManager = new SimpleCacheManager();
          cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("userCache"),
                  new ConcurrentMapCache("courtCache")));
  ```

###### 调用cache方法

```java
    @GetMapping(value = "/getUserById",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Object> getOneUser(@RequestParam("userId") Long userId)  {
        List<Object> users =  userService.selectOneUser(userId);
        return users;
    }
```

要让@Cacheable发挥作用，还必须要在主函数入口加上@EnableScheduling注解。



###### cache的stor获取

在某些场景中。@Cacheable的工作机制并不能满足实际需求中对cache 的操作。需要手动的对cache进行插入和删除操作。以下是对Cache 中实际存储的concurrentHashMap的获取。

```java 
//根据cache 的名字获取对应的cache   
ConcurrentMapCache testCache = (ConcurrentMapCache)myCacheManager.getCache("userCache");
//根据cache提供的nativeCache获取store
ConcurrentMap<String, User> cacheMap = (ConcurrentMap) testCache.getNativeCache();
```

- @CachePut 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用

- @CachEvict 主要针对方法配置，能够根据一定的条件对缓存进行清空