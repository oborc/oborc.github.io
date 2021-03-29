### entry 用法

单入口：entry 是一个字符串

多入口：entry是一个对象，包含每个入口的名字和其对应的路径（多页面应用）

------

### Output用法

output告诉webpack如何将编译后的文件输出到磁盘

单入口：

只需要照常指定output中的filename,path即可

多入口配置：

通过占位符: [name].js生成多个文件，最终生成文件名与entry中键值对的键名相匹配

------

### Loaders

loaders本身是一个函数。由于webpack只支持js和json两种文件类型，所以需要loaders去支持其他文件类型，将其转化为有效的模块并添加到依赖图中。loader作为一个函数，输入源文件，输出转化后的结果

| 名称          | 描述                      |
| ------------- | ------------------------- |
| Babel-loader  | 转换es6-es7等js新特性语法 |
| Css-loader    | 支持.css文件的加载和解析  |
| Less-loader   | 将less转化为css           |
| Ts-loader     | 将TS转化为js              |
| File-loader   | 进行图片、字体等的大包    |
| raw-loader    | 将文件以字符串的形式导入  |
| Thread-loader | 多进程打包js和css         |

------

### plugins

用于bundle文件的优化，资源管理和环境变量注入

作用于整个构建过程

| 名称                     | 描述                                      |
| ------------------------ | ----------------------------------------- |
| CommonChunkPlugin        | 将chunks相同的代码模块提取成公共js        |
| CleanWebpackPlugin       | 清理构建目录                              |
| ExtractTextWebpackPlugin | 将css文件从budle里提取成一个独立的css文件 |
| CopyWebpackPlugin        | 将文件或者文件夹拷贝到构建的输出目录      |
| HtmlWebpackPlugin        | 创建html文件去承载输出的bundle            |
| UglifyjsWebpackPlugin    | 压缩js                                    |
| zipWebpackPlugin         | 将打包出的资源文件生成一个zip包           |

------

### mode（webpack4）

指定当前打包环境