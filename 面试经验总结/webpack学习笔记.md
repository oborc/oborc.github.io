### entry 用法

单入口：entry 是一个字符串

多入口：entry是一个对象，包含每个入口的名字和其对应的路径（多页面应用）

### Output用法

output告诉webpack如何将编译后的文件输出到磁盘

单入口：

只需要照常指定output中的filename,path即可

多入口配置：

通过占位符