# 网络相关

### http各版本的区别

​	0.9 只有一个GET命令

​	1.0  一个TCP连接只能发送一次请求，请求结束就关闭连接。服务器按顺序响应。

​	1.1 TCP连接可复用，

​			持久化：默认不关闭，可以复用多次，

​			管道：一个TCP连接可以同时发送多个请求  但服务器的响应是按顺序响应的。（content-length or 分块传输数据以判断数据属于哪一个回应，即对应哪一个请求）

2.0

### http报文

http报文分为三部分

```javascript
//请求行							//	响应行
GET /index.html 	（status:200）
//请求（响应）头
Cache-control:max-age=100000
Cookie:afakjsdf
Accept: text/html;application/json
//请求（响应）数据
data
```



### https加密过程及握手过程

​	3次握手，3个随机数，最后用3个随机数生成会话密钥，每次请求带上会话密钥，服务器用公钥解密会话密钥进行验证

### TCP\UDP 区别

### 前后端鉴权

Authorization 向web站点传送用户的相关信息。

### Web socket 通信（手动编程nodes 实现以理解webSocket）

### cookie session 前端理解的区别，**Token**

cookie是识别当前用户，**实现持久会话的一种方式**。cookie由服务器生成， 在客户端第一次访问服务器后，由服务器设置响应头中的Set-cookie为客户端设置一个cookie，用来标示客户端。分为会话cookie和长久cookie两种。

会话cookie：

​	记录了用户访问站点时的设置和偏好，退出浏览器会被删除（Set-cookie:无Discard参数，Expires或者Max-age说明过期时间，则cookie是临时cookie）

持久cookie:

​	存储在硬盘上，重启仍然存在。直到过期为止

session->服务端机制，是服务器生成一个session对象，每次会话这个session对象存储在服务器中，每一个session对象对应一个sessionId,一般情况下，服务器会将sessionId放入cookie，会话结束，删除cookie.sessionId.下次访问，如果是会话cookie则sessionId被删除。找不到之前生成的session对象，需要给session设置过期时间以删除，防止内存溢出。

如果是持久cookie，则可以继续使用原来的session。

session只有在服务器端，调用删除session的代码的时候才会被删除，创建同理。（服务被调用，自动创建-默认true，除非显示关闭）

# 浏览器工作原理	

### 回流和重绘的区别

解析DOM dom树

​	解析CSS css树

​	根据css+dom的可见节点（display:none不可见）生成的render树进行render

​	如果有大小，位置改变的要回流--》（渲染树发生变化）

​	颜色改变的要重绘--》css样式改变

### 浏览器存储（localstorage sessionStorage ）

​	localStorage: 长久保存，不删除就存在。setItem.getItem，localstorage只能存储字符串

​	sessionStorage:短期保存，标签页或者窗口关闭即消失

### 浏览器（应用程序）缓存

通过在html标签中启用manifest=mainfest文件所在位置属性来启用应用程序缓存

manifest文件可以告知浏览器什么可以缓存，什么不可以缓存。分为三个部分：

​		CACHE-MANIFEST :首次下载即缓存

​		NETWORK：需要与服务器连接才可访问，不缓存

​		FALLBACK：当某个页面无法访问时显示的页面

当用户清除浏览器缓存、manifest的文件发生改变、应用程序更新缓存的时候，浏览器缓存才会被清除

### WEB缓存-响应（请求）头中的Cache

通过响应头中的date或者age检测响应是从缓存还是服务器返回的。

Cache-Control：max-age定义文档最大使用时间

​							 no-cache

​							 no-store:不会被缓存，不会被保存到临时文件

​						     only-if-cached: 获取缓存而不向服务器获取

​							 no-transform: 缓存时不对响应体中的数据做任何处理/获取没有被转化过的资源

Expires 绝对过期时间

私有缓存：（浏览器缓存）

缓存代理服务器-服务器缓存

### 怎么处理跨域

1:http头中添加origin信息，包含请求页面的协议、域名、端口，让服务器决定是否响应。cros

​	简单请求：

​	get.且

2.xmlhttpReuest支持跨域

### v8垃圾回收的原理、机制

# vue原理

### vueRouter 原理

### vueX原理

### diff算法

### Vue数据驱动原理（双向绑定）

Object.defPrototy

和指令v-model

### Vue组件交互（父子组件、兄弟组件）

# webpack

### 打包

#### 构建依赖

### 性能优化

# javaScript基础

#### 手动实现js原生函数

##### 实现call

```javascript
Function.prototype.myCall = function(target,arg){
  let innerArguments [];
  for(let i = 1;i<arguments.length;i++){
    innerArguments.push("arguments["+ i +"]");
  }
  // 将当前正在执行的函数复制给target;
  target.fn = this;
  // eval自动执行toString方法，以下相当于
  // 数组的toString ==> 用逗号将数组内容连接
  // target.fn(target,arguments[1],arguments[2],...arguments[n]);
  eval("target.fn("+innerArguments+")")
  // 从当前目标删除执行函数
  delete target.fn;
}
```

##### 实现apply

```js
Function.prototype.myApply = function(target,arg){
  //将当前正在执行的函数复制给target;
  if(!arg){
    target.fn();
  }else{
    let args = [];
    for(let i = 0;i<arg.length;i++){
    	args.push("arg["+ i +"]");
  	}
   	//数组的toString ==> 用逗号将数组内容连接
   	//eval自动执行toString方法，以下相当于
    //target.fn(target,arguments[1],arguments[2],...arguments[n]);
   	eval("target.fn("+args+")")
   	//从当前目标删除执行函数
   	delete target.fn;
  }
}
```

##### 手动实现bind--bind和call(apply)的区别，原型链

```js
//bind自身可以有参数，function自身也有参数，需要注意将bind的参数和function的参数合起来
//一般只有在有固定的参数的时候可以这样做，如：某个函数中有一个固定的参数，则，在bind中，只需要传递除了此参数之外的参数即可，相当于bind参数发生了位移，位移位置由原函数的固定参数决定。
Function.prototype.myBind = function(target,arg){
  if(target == null){
    target = window;
  }
  //当前this是bind函数被调用的函数所在的this;
  let _this = this;
  //被创建返回的方法
  let bindArgs = Array.prototype.slice.call(arguments,1);
  return function(){
      //先调用bind，再调用被返回的函数，所以应该以bindArgs作为开始，合并这个被返回的方法中的参数
    let args = bindArgs.concat(Array.prototype.slice.call(arguments));
    //todo 修改函数原型
    this.prototype = new _this();//(用__proto__直接赋值，则是指向同一个原型对象，无继承关系)
    return _this.call(target,args);
  }
}
```

##### 手动实现new关键字（new的实际发生事情[[proto]]和protoType的关系）

理解new: new是相当于实现一个实例，实例的原型[[proto]]都是指向构造函数的“原型对象(prototype)”

```js
var myNew = function(){
    let arg = [...arguments];
    let fn = arg[0];
    let constructorArgs = arg.slice(1);
    let o = {};
    o.__proto__ = fn.prototype; // 将需要创建的对象的原型指向构造函数的原型对象。
    fn.apply(o,constructorArgs); // 改变this指向
    return o;
}
```

```javascript
var protNew = function(){
    let o = Object.create(arguments[0].prototype); // 利用create实现原型对象的改变
    arguments[0].apply(o,null);
    return o;
}
```



##### 手动实现Object.freeze . (掌握Object.definePrototy已经四种属性特性)

#### js事件机制

简单来说，js事件机制就是由**执行栈**和**消息队列**构成，每调用一个函数，执行栈中会被推入一个函数及其参数和局部变量，执行结束便被推出执行栈。

函数在消息队列中的消息被处理到时，调用该消息对应的函数。即：消息队列中的每个消息对应一个函数，在事件循环的某个时间点，消息队列中的第一个消息会被处理，与之关联的函数（事件）会被推入执行栈以执行。当执行栈中的事件都执行结束后，会将消息队列中的第一个消息关联的事件推入执行栈。

#### 闭包

###### 闭包的概念：

​	在一个函数中可以访问另外一个函数作用域中的变量的函数被称作为闭包

```js
function outer(){
  let a = "outera";
  function inner(){
    console.log(a);
  }
}
outer();
```

在此例子中，函数```inner```可以访问外部函数```outer```中的变量a,则```inner```就是一个闭包。在outer被调用的时候，outer被推入执行栈，outer调用inner,inner被推入执行栈。inner访问outer的变量a,执行结束后回到outer，outer结束，释放outer所占用内存。

###### 作用域链

​	（顾名思义，作用域链是一个类似链表的数据结构）

​	保证对**执行环境**有权访问的**所有变量和函数**的**有序访问**（执行环境即执行上下文，每个函数都有自己的执行环境->即事件循环机制中的执行栈

​	每个执行环境都有一个与之对应的**变量对象**（类似消息队列，每个消息都有与之对应的函数）用于保存当前环境定义的变量和函数。

​	全局执行环境是最外部的执行环境，以浏览器为例，全局执行环境对应的变量对象是window对象

**作用域链的前端永远都是当前执行环境对应的变量对象。**

其中发生的作用域链变化如下所示：

初始环境：作用域链中只有window

1. 在全局作用域中调用outer.创建一个outer的执行环境；将outer推入执行栈，在outer执行的时候，创建一个作用域链。将outer对应的**活对象**（一个包含arguments的不可编码访问对象）放入作用域链首部；同时，将执行代码的控制权从window交给outer。

2. 调用inner,创建inner的执行环境，并将inner推入执行栈，在inner执行的时候，创建一个活对象，并把该对象加到作用域链的首部，将控制权从outer移交给inner。

3. inner执行结束。释放inner的作用域链中的活对象，释放控制权到outer.（同时释放了活对象占用的内存）

4. outer执行结束，释放outer的作用域链中的活对象，释放控制权到window.（同时释放了活对象占用的内存）

   ```js
   function outer(){
     let a = "outera";
     return function inner(){
       console.log(a);
     }
   }
   outer();
   ```

   以上代码，返回一个inner函数到全局作用域。由于没有指向返回函数的指针，被返回的函数会被垃圾回收机制回收。不会造成内存溢出

   若将```outer()```改为```let result = outer();```则：

   由于有引用指向返回的函数，则result指向的函数中的作用域链不可被销毁，则该作用链所对应的活对象就不可被销毁。（此时outer的作用域链由于outer的执行结束已被销毁，但outer对应的活对象由于被返回的函数的作用域链所引用，所以依旧存在内存中。）

#### 原型链 -继承

如何理解原型链：

​	原型链就是在javascript中，所有的对象都有一个属性指向创建他的函数的原型对象。

# css3

如何实现两边固定，中间自适应的布局

如何实现水平垂直居中