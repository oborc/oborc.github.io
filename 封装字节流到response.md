# 封装response



在工作中，遇到了要求在src/img 标签中直接引用图片。但是不发送request请求，不通过URL获取资源，也不通过base64编码的方式。

怎么才能解决这个问题呢？

http就是request和response组成的一种协议

返回值都是在一个response的content中。能否将图片直接存储在response的content中，通过content-type来规定content的格式。这种情况下，浏览器是否不需要发送二次图片请求，就可以直接将response中的图片解析出来呢？

思路如下：

1. 将图片转化为字节流
2. 将字节流封装在response中

实践：

```java
public void image(File file,HttpServletResponse response)
{
  //获取http的output流
  ServletOutputStream outputStream = response.getOutputStream();
  //将图片读为input 流
  InputStream in = new InputStream(file);
  response.setContentType("image/(自己的图片类型)");
  //声明一个byte 数组
  byte[] imageByres = null;
  //初始化数组为输入流 in 的大小
  imageBytes = new byte[in.available()];
  //将in 字符流写入bytes
  int n =0;
  //读字符流到imageBytes中
  while((int n =in.read(imageBytes))!=-1)
  {
    //写入到output字节流中，从0-n 的字节。
    output.write(imageBytes,0,n);
  }
  output.close;
  in.close;
}

//以上为实践思路，实际编码中还需要增加IOException
```

此时调用接口，浏览器无需发送二次请求可直接将response中的字节流解析为图片。

**优点**

1. 减少浏览器发送请求的次数
2. 减少用base64情况下，浏览器解析的负担
3. 响应快