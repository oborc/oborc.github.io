shoppe一面复盘

## 从页面输入一个url发生了什么

（how browers works）

这个问题被问到很多次，每次都说个大概，其实并没有真正理解面试官想问什么。昨天忽然醒悟，明白了每一个步骤都有想要询问的知识点：

协议+(域名+端口号)+文件地址

- ##### 协议相关

  1. http协议和https协议的区别
     1. https就是在安全的传输层上发送的http请求。在http报文发送给tcp之前先发送到安全层。3次握手，3个随机数，最后用3个随机数生成会话密钥，每次请求带上会话密钥，服务器用公钥解密会话密钥进行验证
  2. http1.1和http2.0的区别 

- ##### 连接请求相关

  1. 解析域名

  2. 建立TCP连接

     - 三次握手

       客户端向服务端发起小的带有特殊标记的**连接请求**。

       服务端接受接受请求，返回确认标记（ACK）。

       客户端向服务端发送连接建立的确认信息。

     - TCP和UDP的区别

       - TCP建立连接需要三次握手。

  3. 发送http请求

     - 请求方法

       get/post/put/delete/head

     - get/post区别

       get通常用来向服务器获取数据，post通常用来向服务器发送数据。在浏览器中，浏览器发出的get只能由url出触发，所以get请求总是有在url中携带参数。而浏览器实现的post可以由提交按钮触发，所以post参数可以携带在请求体的body中。也可以放在url中。

     - restFul API

       restFulAPI中，get被建议用来获取资源，需要实现幂等性，无论多少次调用，结果都是一样的。

       post被建议用来创建一个资源，每次表单提交都会有新的数据或者资源产生。

       具体实现由服务端和客户端约定而成。

       推荐请求体使用json格式。

  4. 读取响应（渲染页面）

     - 渲染过程

  5. 关闭连接

     - 四次挥手

       由于TCP连接是全双工(连接的两端都可以读写数据)的，因此每个方向都必须单独进行关闭。

       客户端向服务端发起关闭发送数据请求。客户端关闭到服务端的连接，不再发送数据。进入等待关闭确认状态。

       服务端响应关闭请求，发送确认码。

       服务端关闭服务端到客户端的连接，并返回FIN给客户端。

       客户端发送ACK给服务端，服务端close

## 浏览器缓存：

### cache-control 、e-tag lastModify状态码区别为什么是强制缓存和协商缓存

Cache-control，状态码是200

e-tag状态码是304

### 渲染具体原理，如何生成dom和css树

### 重绘和回流具体概念



## jacascript标签的异步请求

### css标签被引入的作用和作用机制

### 标准盒模型、position\display;

## 1.mvvm是什么：vue是如何实现m v vm的

Model（模型） view（视图） viewModel（视图模型）

模型：后端数据模型

视图：前端界面

视图模型：模型和视图的通信方式： dom变化通知模型，模型变化通知视图（称之为双向绑定）

## 2.Object.defineProprty()实现拦截（vue-实现数据驱动的原理--拦截）手动实现

## 3.proxy的实现原理。如何用proxy实现拦截

Object.definePrototy和proxy的区别，优缺点

## 4.引用类型和普通类型的区别-- 在definePrototy中的表现区别

## 5.普通类型的转换，当用 == 时。数据会做什么样的变化？

```javascript
分别输出什么
[] == false
![] == false
'' == false
```

## 6.js事件机制里的微任务和宏任务有什么区别

执行栈为空时，先轮巡微任务队列，再轮巡宏任务队列

## 7.promise 和setTimeout 有什么区别

setTimeout 宏任务

Promise 微任务

## 8.读程序

```javascript
//执行栈 ===》 直接输出，不需要回调的。
//执行栈对应的任务队列 ==》 回调都放在这里执行。
//执行栈对应的微任务队列
 
console.log(1);//task1
setTimeout(() => {
	console.log(2); //task2
  Promise.resolve().then(() => {
    console.log(3) // 微任务队列1   //task3
  });
});

new Promise((resolve, reject) => { 
	console.log(4) //task4 
  resolve(5)
	}).then((data) => {
 	console.log(data); //微任务队列2  //task5 
})

setTimeout(() => {
  console.log(6); //task6
})
console.log(7); //task7


```

```java
输出：1
task1出栈
执行setTimeOut：将setTimeOut的回调放入任务队列
执行new Promise. 其中console.log(4) task4 压入执行栈并执行 
输出：4
task4出栈
promise.then 放入微任务队列 （task5）
setTimeOut ，将task6放入任务队列
task7压入执行栈并执行
输出：7
task7出栈
执行栈轮空，遍历微任务队列： task5
输出：5
微任务队列轮空，遍历任务队列
task2 执行。task3进入微任务队列
输出：2
遍历微任务队列
输出：3
微任务为空，遍历任务队列
输出： 6
// 1475236
```

## 9.vue.nextTick是什么，原理是什么，如何实现的

vue渲染dom是异步的，将需要修改的dom进队，在下一个事件循环中进行处理队列中的dom操作。如果有对数据变化后的dom的操作，则数据变化之后，dom不会发生改变（因为异步。此时还没有进行到下一个事件循环，dom操作不会执行，）可以用vue.nextTick将"对变化后的dom的操作"放入回调函数中，则会在dom变化之后执行。相当于延迟“对变化后的dom的操作”。

## 10.js中await和async理解原理

await 是Generator函数的语法糖，用来在异步函数中实现同步

## 11.promise如何实现并发请求

Promise.all?

## 12.伪数组

es6新增特性：

程序题：

Regx；正则替换。使用方式,去除头尾的空格

去重数组：set,reduce

数组的全排列组合

# 防抖和节流

## 防抖

触发某个在一段时间内只能执行一次的事件，频繁触发导致重新计算时间导致事件实际不能被触发（相当于中断了）

## 节流

优化连续触发事件，在频繁触发的事件，在某段时间内减少实际触发次数，只执行一次。

# vue2生命周期

beforeCreate:

在数据观测和初始化事件还未开始

Created:

完成数据观测，属性和方法的运算，初始化事件，$el属性还没显示

Beformounted:

在挂在开始之前被调用，相关的render函数被首次调用，实例已经完成：编译模版，把data里的数据和模版生成html，但html还没挂载到页面上

Mounted:

在el被新创建的el替换。并挂载到市里上之后调用，完成：用编译好的html内容替换el属性只想的dom对象，完成模版中的html渲染到html页面中，此过程中进行ajax交互

beforeupdate:

数据更新之前调用，发生在虚拟dom重新渲染和打补丁之前，可以在钩子中进一步更改状态，不会出发附加的重渲染过程

update:

由于数据更新导致的虚拟dom重新渲染和打补丁以后的调用，调用时，dom已经更新。所以可以执行依赖dom的操作。

beforeDestory:

在实例销毁之前嗲用

destroyed:

实例销毁之后调用，所有的事件监听器会被移除，所有的子实例也会被销毁。该钩子在服务器端渲染期间不被调用

# Vue key的作用

文档：`key` 的特殊 attribute 主要用在 Vue 的虚拟 DOM 算法，在新旧 nodes 对比时辨识 VNodes。如果不使用 key，Vue 会使用一种最大限度减少动态元素并且尽可能的尝试就地修改/复用相同类型元素的算法。而使用 key 时，它会基于 key 的变化重新排列元素顺序，并且会移除 key 不存在的元素。

自己的话：vue的虚拟dom在执行diff算法时，会基于key的变化重新排列元素，移除key不存在的元素。如果没有key，则vue会选择一种能最大限度减少动态元素且尽可能就地修改相同元素的算法，简而言之就是最大可能使用开销小的算法。

**相同父元素的字元素必须有不一样的key,否则会造成渲染错误！**

# promise、promiseAll

所谓`Promise`，简单说就是一个容器，里面保存着某个未来才会结束的事件（通常是一个异步操作）的结果。从语法上说，Promise 是一个对象，从它可以获取异步操作的消息。Promise 提供统一的 API，各种异步操作都可以用同样的方法进行处理

promise是一个提供统一API的可以获取异步操作消息的对象（容器）

promiseAll可以将多个promise实例包装成多个实例，接收一个实现了iterator接口的对象作为参数，且列表中的每个对象都得是promise 实例 。

promiseAll中，所有的对象都是fulfilled的时候，返回结果才是fulfilled,有一个reject就是结果就是reject.



