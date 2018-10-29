# Java 集合

![image-20180930120605476](/Users/haha/Documents/注解/collection.png)

​										java-collection 

## List

1. ### ArryList

   1. 动态数组
   2. 允许null存在
   3. 默认大小为10
   4. 增加新元素的时候检查是否需要扩容。
      扩容机制：每次增减数组用ensureCapacity函数检查大小，每次增量为原来的1.5倍.
   5. 没有用synchronize关键字保证同步
   6. 顺序存储序列

2. ### LinkList

   1. linklist就是java里面的链表

   2. ```java
      class entry<E>
      {
      	E element;
      	entry<E> next;
      	entry<E> previous;
          
          entry(E element,entry next,entry previous)
          {
          	this.element = element;
          	this.next = next;
          	this.previous = previous;
          }
      }
      ```

3. ### vector

   1. 与传统数组类似，可以直接通过索引访问。

   2. vector也是用List 实现的动态数组每次增加元素的时候会检查容量

   3. 每个函数的实现和Array list几乎一样，但是加了synchronize关键字保证同步。

   4. Vector 初始化

      ```java
      vector初始化：vector v = new vector();//默认大小10的vector
      vector v = new vector(1,2);//指定大小为1的向量，每次增加2个向量
      vector v = new vector(4);//指定size为4的vector
      vector v = new vector(Collecton c)//包含集合元素c 的向量
      ```

   5. #### Stack

      ```java
      empty();//判空
      peek() // 查看栈顶
      pop();//取栈顶
      ```


## Set（原理）

1. ### hashSet

   hashSet 中有hashMap的实现。

   所有hashSet的很多特性和hashMap是一样的。

   hashSet（）

## Map

1. ### hashMap（原理、负载因子、安全）

   1. key-value存取数值
   2. 允许使用null值和null键
   3. 不保证映射顺序。不同步（加synchronized 保证同步）

2. currentHashMap（分段锁原理）

