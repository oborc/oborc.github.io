## Thread

### 线程生命周期

​	Java中的线程状态：有	`new`、`runnable`、`blocked`、`waiting`、`time_waiting`、`terminated`

### 线程创建、启动、终止

​	调用`start`方法进行启动，随着run执行完毕，线程终止。

- #### `start`&`run`

​	线程对象在初始化完成之后，调用start方法可以启动这个线程：当前线程同步告知Java虚拟机，只要线程规划器空闲，应立即启动调用start方法的线程。

​	run 是一个实例方法，可以直接调用。

- #### `interrupt`&`suspend`

  - `suspend`将线程变为占有资源的睡眠状态，线程不会释放已经获得的锁。需要用`resume`唤醒
  - `interrupt`. Java提供了`isInterrupted` 方法来判断线程是否被中断。在线程中调用`interrupt`方法进行线程中断。**设置线程的中断状态，线程退出后，中断状态会清零**


### 线程池



### `ThreadLocal`

​	线程中的一个变量，一个以`ThreadLocal`对象为键，任意对象为值的存储结构，这个存储结构被附带在线程上，一个线程可以根据一个`ThreadLocal`对象查询绑定在这个线程上的一个值。

### 并发中的三个问题：

​	原子性：操作的原子性，不可打断性。eg: x=1;

​	可见性：一个线程的对于某个数据的操作，另一个行程可马上看到（volatile）

​	有序性：有顺序的执行代码

### wait-sleep

​	从代码实现上看。`wait`和`sleep`并没有什么区别，都是`threw`一个`InterruptedException`。

​	但是`sleep`是`Thread`的一个static 方法。可以直接用`Thread.sleep( timeout )`实现sleep，也可以用子类实例调用实现sleep。

​	`**wait` 是object里的方法。任何object类都可以调用。`wait`调用后，线程进入waiting状态。waiting状态的线程需要`notify`等方法的调用才能返回运行态。**

## tips

线程被`synchronized`阻塞后，是`blocked`状态，但是如果是用`concurrent`包中的`lock`，是`wait`状态。



