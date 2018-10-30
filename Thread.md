## 线程（并发）

### 线程生命周期

### 线程创建

### 线程池

### ThreadLocal

### 并发中的三个问题：

​	原子性：操作的原子性，不可打断性。eg: x=1;

​	可见性：一个线程的对于某个数据的操作，另一个行程可马上看到（volatile）

​	有序性：有顺序的执行代码

### volatile

​	volatile关键字是相当于对临界资源的一种保护访问。强制线程从内存中读取变量，而不是自己的线程栈中。避免出现并发过程中的读取数据后，操作数据没有改变或改变与预期不符的状态。

### wait-sleep

​	从代码实现上看。wait和sleep并没有什么区别，都是threw一个InterruptedException。

​	但是sleep是Thread的一个static 方法。可以直接用Thread.sleep( timeout )实现sleep，也可以用子类实例调用实现sleep。

​	wait 是object里的方法。任何object类都可以调用。



