初始化一个webpack项目

```shell
mkdir webpackDemo
cd webpackDemo
npm init -y
npm install webpack webpack-cli --save
```

打开编辑器，在当前文件夹下新建src目录

在当前文件夹下新建webpack.config.js配置文件、src目录下新建入口文件:index.js

初始化webpack.config.js

```javascript
'use strict'
const path = require('path');
module.exports = {
    entry: './src/index.js', //入口文件
    output: {
        path: path.join(__dirname,'dist'), // 出口地址
        filename: 'budle.js' // 出口文件名
    },
    mode: 'production' // 打包模式
}
```

在终端运行

```shell
npx webpack #该命令将生成 ./dist文件，并将使用webpack.config.js中配置好的output中的filename作为生成的文件名
```

可在package.json中配置script:

```javascript
"build": "webpack"
```

则可在终端运行

```shell
npm run build 代替 npx webpack
```

