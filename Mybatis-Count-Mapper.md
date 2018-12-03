## Mybatis count（*）注入

mybatis 的mapper实现select时。如果直接用count(*) 的值，会得到0或者null

```java
//第一种直接注入会得到entity 中count为null.(count 为integer类型时，count 为0，当count为int 类型)
@select("select column,count(*) from table group by column")
List<entity> getCounts();

@Select（"select column,count(*) `count` from table group by column")
List<entity> getCounts();
//以上方法的到的count 为实际上数到的count 值。

mybatis 不支持直接用count（*）作为结果注入到实体类中，需要给结果重命名才能解析道结果。

class entity{
    private String column;
    private Integer count;
    
    public int gettCount(){
        return this.count;
    }
    public String getColumn(){
        return this.column;
    }
    
    public void setColunn(String column)
    {
        this.column = column;
    }
    
    public void setCount(Integer count){
        this.count = count;
    }
}
```



